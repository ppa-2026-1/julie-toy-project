package com.example.demo.model.dto;

import java.util.List;

public record NewTicketDTO(
    String acao,
    String objeto,
    String detalhes,
    String criador,
    String destinatario,
    List<String> observadores
) {}
