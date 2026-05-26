package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.repository.AuthTokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.AuthToken;

@Service
public class AuthService {

    private final AuthTokenRepository authTokenRepository;
    private final UserRepository userRepository;

    public AuthService(
            AuthTokenRepository authTokenRepository,
            UserRepository userRepository
    ) {
        this.authTokenRepository = authTokenRepository;
        this.userRepository = userRepository;
    }

    public String login(String handle) {

        userRepository.findByHandle(handle)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuário não encontrado")
                );

        String token = UUID.randomUUID().toString();

        AuthToken authToken = new AuthToken(
                token,
                handle,
                LocalDateTime.now().plusHours(2)
        );

        authTokenRepository.save(authToken);

        return token;
    }

    public String validateToken(String token) {

        var optionalToken = authTokenRepository.findByToken(token);

        if (optionalToken.isEmpty()) {
            return null;
        }

        AuthToken authToken = optionalToken.get();

        if (authToken.isExpired()) {
            authTokenRepository.delete(authToken);
            return null;
        }

        return authToken.getHandle();
    }

    public void logout(String token) {

        authTokenRepository.findByToken(token)
                .ifPresent(authTokenRepository::delete);
    }
}