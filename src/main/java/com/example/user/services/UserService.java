package com.example.user.services;

import com.example.user.domains.User;
import com.example.user.dtos.CreateUserDto;
import com.example.user.dtos.UpdateUserDto;
import com.example.user.exceptions.UserNotFoundException;
import com.example.user.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public User getUserById(UUID id){
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        return user;
    }

    public void updateUser(UUID id, UpdateUserDto updateUserDto){
        Optional<User> user = this.userRepository.findById(id);

        if(user.isPresent()){
            var userBody = user.get();

            userBody.setName(updateUserDto.name());
            this.userRepository.save(userBody);
        }
    }
}
