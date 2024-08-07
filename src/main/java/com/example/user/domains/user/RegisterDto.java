package com.example.user.domains.user;

public record RegisterDto(String login, String password, UserRole role) {
}
