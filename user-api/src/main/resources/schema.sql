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
id INTEGER  PRIMARY KEY AUTOINCREMENT,
acao VARCHAR(50) NOT NULL,
objeto VARCHAR(50) NOT NULL,
criador TEXT NOT NULL,
destinatario TEXT,
responsavel TEXT,
create_at TIMESTAMP NOT NULL,
update_at TIMESTAMP NOT NULL,
status VARCHAR(20) CHECK (status IN ('pendente', 'andamento', 'resolvido', 'cancelado')) NOT NULL,
FOREIGN KEY(criador_id) REFERENCES users_id,
FOREIGN KEY(destinatario_id) REFERENCES users_id,
FOREIGN KEY(responsavel_id) REFERENCES users_id
);

CREATE TABLE IF NOT EXISTS ticket_observadores(
ticket_id INTEGER PRIMARY KEY AUTOINCREMENT,
email VARCHAR(100) NOT NULL,
FOREIGN KEY(ticket_id) REFERENCES ticket(id)
);