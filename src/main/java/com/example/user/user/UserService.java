package com.example.user.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto){
        User newUser = new User(createUserDto);
        this.userRepository.save(newUser);

        return newUser.getId();
    }

    public List<User> listUsers(){
        return this.userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id){
        return this.userRepository.findById(id);
    }

    public void updateUser(UUID id, UpdateUserDto updateUserDto){
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com " + id + " não encontrado"));

        user.setName(updateUserDto.name());
        this.userRepository.save(user);
    }
}
