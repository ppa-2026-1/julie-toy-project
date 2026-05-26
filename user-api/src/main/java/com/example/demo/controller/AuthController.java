package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.AuthService;
import com.example.demo.model.dto.AuthResponseDTO;
import com.example.demo.model.dto.LoginDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO.handle());

        return ResponseEntity.ok(
                new AuthResponseDTO(token, "Bearer", loginDTO.handle())
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        authService.logout(token);
        return ResponseEntity.noContent().build();
    }
}