package com.example.demo.model;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.NewNotificationDTO;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void escreverAosInteressados(NewNotificationDTO dto) {
        if (dto.destinatarios() == null || dto.destinatarios().isEmpty()) {
            throw new IllegalArgumentException("Informe pelo menos um destinatário");
        }
        if (dto.assunto() == null || dto.assunto().isBlank()) {
            throw new IllegalArgumentException("Assunto é obrigatório");
        }
        if (dto.mensagem() == null || dto.mensagem().isBlank()) {
            throw new IllegalArgumentException("Mensagem é obrigatória");
        }

        logger.info("[{}] Notificação enviada para {} | {} | {}",
                LocalDateTime.now(), dto.destinatarios(), dto.assunto(), dto.mensagem());
    }
}
