package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.AuthToken;

@Repository
public class AuthTokenRepository {

    private final Map<String, AuthToken> tokens = new HashMap<>();

    public Optional<AuthToken> findByToken(String token) {
        return Optional.ofNullable(tokens.get(token));
    }

    public void save(AuthToken authToken) {
        tokens.put(authToken.getToken(), authToken);
    }

    public void delete(AuthToken authToken) {
        tokens.remove(authToken.getToken());
    }
}