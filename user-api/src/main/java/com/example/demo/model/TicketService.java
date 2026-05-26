package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.NewTicketDTO;
import com.example.demo.model.dto.TicketStatusDTO;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.entity.Ticket;
import com.example.demo.repository.entity.Ticket.Status;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket criar(NewTicketDTO dto) {
        if (dto.acao() == null || dto.acao().isBlank())
            throw new IllegalArgumentException("Ação é obrigatória");
        if (dto.objeto() == null || dto.objeto().isBlank())
            throw new IllegalArgumentException("Objeto é obrigatório");
        if (dto.criador() == null || dto.criador().isBlank())
            throw new IllegalArgumentException("Criador é obrigatório");

        Ticket ticket = new Ticket();
        ticket.setAcao(dto.acao());
        ticket.setObjeto(dto.objeto());
        ticket.setDetalhes(dto.detalhes());
        ticket.setCriador(dto.criador());
        ticket.setDestinatario(dto.destinatario() != null ? dto.destinatario() : dto.criador());
        ticket.setObservadores(dto.observadores() != null ? dto.observadores() : List.of());

        LocalDateTime now = LocalDateTime.now();
        ticket.setCreatedAt(now);
        ticket.setUpdatedAt(now);

        return ticketRepository.save(ticket);
    }

    public Ticket atualizarStatus(Integer id, TicketStatusDTO dto) {
        Ticket ticket = ticketRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ticket não encontrado"));

        Status novoStatus = dto.status();

        if (novoStatus == Status.ANDAMENTO) {
            if (dto.responsavel() == null || dto.responsavel().isBlank())
                throw new IllegalArgumentException("Responsável é obrigatório ao colocar em andamento");
            ticket.setResponsavel(dto.responsavel());
        }

        if (novoStatus == Status.CANCELADO) {
            if (dto.motivo() == null || dto.motivo().isBlank())
                throw new IllegalArgumentException("Motivo é obrigatório ao cancelar");
            ticket.setMotivo(dto.motivo());
        }

        ticket.setStatus(novoStatus);
        ticket.setUpdatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public List<Ticket> listar() {
        return ticketRepository.findAll();
    }

    public Ticket buscar(Integer id) {
        return ticketRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ticket não encontrado"));
    }
}
