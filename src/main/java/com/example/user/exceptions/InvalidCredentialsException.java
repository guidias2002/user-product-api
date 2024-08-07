package com.example.user.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException() {
        super("Login ou senha incorretos");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
