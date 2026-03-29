-- SQLite
CREATE TABLE IF NOT EXISTS users (
    id         INTEGER      PRIMARY KEY AUTOINCREMENT,
    handle     VARCHAR(255) UNIQUE NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS roles (
    id   INTEGER      PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS profiles (
    id      INTEGER       PRIMARY KEY AUTOINCREMENT,
    name    VARCHAR(255),
    company VARCHAR(255),
    type    VARCHAR(255),
    FOREIGN KEY (id) REFERENCES users(id)
);

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

INSERT OR IGNORE INTO roles (name) VALUES ('ROLE_USER');
INSERT OR IGNORE INTO roles (name) VALUES ('ROLE_GUEST');
INSERT OR IGNORE INTO roles (name) VALUES ('ROLE_VIEWER');