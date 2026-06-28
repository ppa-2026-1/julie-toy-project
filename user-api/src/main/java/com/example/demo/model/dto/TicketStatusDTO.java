package com.example.demo.model.dto;

import com.example.demo.repository.entity.Ticket.Status;

public record TicketStatusDTO(
    Status status,
    String responsavel,
    String motivo
) {}
