package com.example.demo.controller;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private String username;

    private LocalDateTime expiration;

    public AuthToken() {
    }

    public AuthToken(String token, String username, LocalDateTime expiration) {
        this.token = token;
        this.username = username;
        this.expiration = expiration;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public boolean isExpired() {
        return expiration.isBefore(LocalDateTime.now());
    }
}