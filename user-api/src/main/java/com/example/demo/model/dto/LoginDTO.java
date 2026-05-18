package com.example.demo.model.dto;

public record LoginDTO(
    String handle,
    String username,
    String email,
    String password
) {}
