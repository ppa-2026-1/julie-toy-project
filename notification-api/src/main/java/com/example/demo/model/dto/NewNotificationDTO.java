package com.example.demo.model.dto;

import java.util.List;

public record NewNotificationDTO(
        List<String> destinatarios,
        String assunto,
        String mensagem
) {}
