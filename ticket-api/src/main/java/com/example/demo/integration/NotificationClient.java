package com.example.demo.integration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.example.demo.repository.entity.Ticket;

@Component
public class NotificationClient {

    private final RestClient restClient;

    public NotificationClient(@Value("${app.notification.base-url}") String notificationBaseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(notificationBaseUrl)
                .build();
    }

    public void notificarNovoTicket(Ticket ticket) {
        List<String> interessados = new ArrayList<>();

        if (ticket.getCriador() != null && !ticket.getCriador().isBlank()) {
            interessados.add(ticket.getCriador());
        }

        if (ticket.getDestinatario() != null && !ticket.getDestinatario().isBlank()) {
            interessados.add(ticket.getDestinatario());
        }

        if (ticket.getObservadores() != null) {
            interessados.addAll(ticket.getObservadores());
        }

        NotificationRequest request = new NotificationRequest(
                interessados.stream().distinct().toList(),
                "Novo ticket criado: #" + ticket.getId(),
                "Ticket criado para " + ticket.getAcao() + " " + ticket.getObjeto()
                        + ". Detalhes: " + (ticket.getDetalhes() != null ? ticket.getDetalhes() : "sem detalhes")
        );

        restClient.post()
                .uri("/api/v1/notifications")
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }

    public record NotificationRequest(
            List<String> destinatarios,
            String assunto,
            String mensagem
    ) {}
}
