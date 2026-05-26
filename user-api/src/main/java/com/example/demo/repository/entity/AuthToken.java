package com.example.demo.repository.entity;

import java.time.LocalDateTime;

public class AuthToken {

    private String token;
    private String handle;
    private LocalDateTime expiresAt;

    public AuthToken(
            String token,
            String handle,
            LocalDateTime expiresAt
    ) {
        this.token = token;
        this.handle = handle;
        this.expiresAt = expiresAt;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public String getToken() {
        return token;
    }

    public String getHandle() {
        return handle;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}