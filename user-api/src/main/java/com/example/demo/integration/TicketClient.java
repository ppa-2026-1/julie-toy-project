package com.example.demo.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.example.demo.repository.entity.User;

@Component
public class TicketClient {

    private final RestClient restClient;

    public TicketClient(@Value("${app.ticket.base-url}") String ticketBaseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(ticketBaseUrl)
                .build();
    }

    public void ticketClient.createInstallationTicket(user){}
        NewTicketRequest request = new NewTicketRequest(
                "INSTALAR",
                "Workstation",
                "Instalar workstation para o usuário " + user.getHandle(),
                user.getEmail(),
                user.getEmail(),
                List.of("suporte-ti@example.com")
        );

        restClient.post()
                .uri("/api/v1/tickets")
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }

    public record NewTicketRequest(
            String acao,
            String objeto,
            String detalhes,
            String criador,
            String destinatario,
            List<String> observadores
    ) {}
}
