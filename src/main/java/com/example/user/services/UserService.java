package com.example.user.services;

import com.example.user.domains.user.RegisterDto;
import com.example.user.domains.user.User;
import com.example.user.exceptions.UserExist;
import com.example.user.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(RegisterDto data){
        //verifica se ja existe um usuario com o mesmo login
        if(this.userRepository.findByLogin(data.login()) != null){
            new UserExist();
        }

        //criptografa a senha
        var encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);
    }
}
