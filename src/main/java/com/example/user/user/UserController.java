package com.example.user.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto createUserDto){
        UUID newUserId = this.userService.createUser(createUserDto);

        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers(){
        var users = this.userService.listUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id){
        return this.userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable UUID id, @RequestBody UpdateUserDto updateUserDto){
        this.userService.updateUser(id, updateUserDto);

        return ResponseEntity.noContent().build();
    }
}