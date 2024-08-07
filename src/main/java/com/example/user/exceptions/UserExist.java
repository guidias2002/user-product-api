package com.example.user.exceptions;

public class UserExist extends RuntimeException {
    public UserExist(String message) {
        super(message);
    }

    public UserExist() {
        super("Usuário já existe");
    }
}
