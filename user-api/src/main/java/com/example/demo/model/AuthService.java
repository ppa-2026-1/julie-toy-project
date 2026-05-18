package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.LoginDTO;
import com.example.demo.model.dto.LoginResponseDTO;
import com.example.demo.repository.AuthTokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.AuthToken;
import com.example.demo.repository.entity.User;

@Service
public class AuthService {

    private static final long TOKEN_TTL_HOURS = 2;

    private final UserRepository userRepository;
    private final AuthTokenRepository authTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, AuthTokenRepository authTokenRepository) {
        this.userRepository = userRepository;
        this.authTokenRepository = authTokenRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        String login = firstNotBlank(loginDTO.handle(), loginDTO.username(), loginDTO.email());

        if (login == null || isBlank(loginDTO.password())) {
            throw new IllegalArgumentException("Login e senha são obrigatórios");
        }

        User user = userRepository.findByHandle(login)
                .or(() -> userRepository.findByEmail(login))
                .orElseThrow(() -> new IllegalArgumentException("Usuário ou senha inválidos"));

        if (!passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
            throw new IllegalArgumentException("Usuário ou senha inválidos");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.plusHours(TOKEN_TTL_HOURS);

        AuthToken authToken = new AuthToken();
        authToken.setToken(UUID.randomUUID().toString());
        authToken.setUser(user);
        authToken.setCreatedAt(now);
        authToken.setExpiresAt(expiresAt);

        authTokenRepository.save(authToken);

        return new LoginResponseDTO(authToken.getToken(), "Bearer", user.getHandle(), expiresAt);
    }

    public String validateTokenAndGetHandle(String token) {
        if (isBlank(token)) {
            throw new IllegalArgumentException("Token não informado");
        }

        return authTokenRepository.findValidToken(token, LocalDateTime.now())
                .map(authToken -> authToken.getUser().getHandle())
                .orElseThrow(() -> new IllegalArgumentException("Token inválido ou expirado"));
    }

    public void logout(String token) {
        if (isBlank(token)) {
            throw new IllegalArgumentException("Token não informado");
        }

        authTokenRepository.revoke(token, LocalDateTime.now());
    }

    private String firstNotBlank(String... values) {
        for (String value : values) {
            if (!isBlank(value)) {
                return value.trim();
            }
        }
        return null;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
