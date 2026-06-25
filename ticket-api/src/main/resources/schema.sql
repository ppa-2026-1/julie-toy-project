-- SQLite - banco exclusivo do microsserviço de tickets
CREATE TABLE IF NOT EXISTS ticket (
    id           INTEGER      PRIMARY KEY AUTOINCREMENT,
    acao         VARCHAR(255) NOT NULL,
    objeto       VARCHAR(255) NOT NULL,
    detalhes     TEXT,
    criador      VARCHAR(255) NOT NULL,
    destinatario VARCHAR(255) NOT NULL,
    responsavel  VARCHAR(255),
    status       VARCHAR(20)  NOT NULL CHECK (status IN ('PENDENTE', 'ANDAMENTO', 'RESOLVIDO', 'CANCELADO')),
    motivo       TEXT,
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP    NOT NULL
);

CREATE TABLE IF NOT EXISTS ticket_observadores (
    ticket_id INTEGER      NOT NULL,
    email     VARCHAR(255) NOT NULL,
    PRIMARY KEY (ticket_id, email),
    FOREIGN KEY (ticket_id) REFERENCES ticket(id)
);
