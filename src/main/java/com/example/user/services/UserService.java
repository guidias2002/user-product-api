package com.example.user.services;

import com.example.user.domains.user.AuthenticationDto;
import com.example.user.domains.user.RegisterDto;
import com.example.user.domains.user.User;
import com.example.user.dtos.productDto.LoginResponseDto;
import com.example.user.exceptions.InvalidCredentialsException;
import com.example.user.exceptions.UserExist;
import com.example.user.infra.security.TokenService;
import com.example.user.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public String login(@RequestBody AuthenticationDto data){
        var user = this.userRepository.findByLogin(data.login());

        if(user == null || !passwordEncoder.matches(data.password(), user.getPassword())){
            throw new InvalidCredentialsException();
        };

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generateToken((User) auth.getPrincipal());

        return token;
    }

    public void registerUser(RegisterDto data){
        //verifica se ja existe um usuario com o mesmo login
        if(this.userRepository.findByLogin(data.login()) != null) throw new UserExist();

        //criptografa a senha
        var encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);
    }
}
