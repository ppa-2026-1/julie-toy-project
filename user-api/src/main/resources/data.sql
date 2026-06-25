-- Seed users
INSERT OR IGNORE INTO users (handle, email, password) VALUES ('alice', 'alice@example.com', '$2a$10$placeholder');
INSERT OR IGNORE INTO users (handle, email, password) VALUES ('bob', 'bob@example.com', '$2a$10$placeholder');

-- Seed vulnerability_reports (25 records for pagination testing)
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (1, 'Sistema A', '2024-01-01 10:00:00', '2024-01-01 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (1, 'Sistema B', '2024-01-02 10:00:00', '2024-01-02 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (2, 'Sistema C', '2024-01-03 10:00:00', '2024-01-03 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (2, 'Sistema D', '2024-01-04 10:00:00', '2024-01-04 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (1, 'Sistema E', '2024-01-05 10:00:00', '2024-01-05 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (1, 'Sistema F', '2024-01-06 10:00:00', '2024-01-06 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (2, 'Sistema G', '2024-01-07 10:00:00', '2024-01-07 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (2, 'Sistema H', '2024-01-08 10:00:00', '2024-01-08 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (1, 'Sistema I', '2024-01-09 10:00:00', '2024-01-09 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (1, 'Sistema J', '2024-01-10 10:00:00', '2024-01-10 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (2, 'Sistema K', '2024-01-11 10:00:00', '2024-01-11 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (2, 'Sistema L', '2024-01-12 10:00:00', '2024-01-12 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (1, 'Sistema M', '2024-01-13 10:00:00', '2024-01-13 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (1, 'Sistema N', '2024-01-14 10:00:00', '2024-01-14 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (2, 'Sistema O', '2024-01-15 10:00:00', '2024-01-15 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (2, 'Sistema P', '2024-01-16 10:00:00', '2024-01-16 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (1, 'Sistema Q', '2024-01-17 10:00:00', '2024-01-17 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (1, 'Sistema R', '2024-01-18 10:00:00', '2024-01-18 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (2, 'Sistema S', '2024-01-19 10:00:00', '2024-01-19 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (2, 'Sistema T', '2024-01-20 10:00:00', '2024-01-20 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (1, 'Sistema U', '2024-01-21 10:00:00', '2024-01-21 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (1, 'Sistema V', '2024-01-22 10:00:00', '2024-01-22 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (2, 'Sistema W', '2024-01-23 10:00:00', '2024-01-23 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (2, 'Sistema X', '2024-01-24 10:00:00', '2024-01-24 10:00:00');
INSERT OR IGNORE INTO vulnerability_reports (user_id, system_under_test, created_at, updated_at) VALUES (1, 'Sistema Y', '2024-01-25 10:00:00', '2024-01-25 10:00:00');

-- Seed vulnerabilities (2 per report)
INSERT OR IGNORE INTO vulnerabilities (description, severity, report_id, created_at, updated_at) VALUES ('SQL Injection no login', 'CRITICAL', 1, '2024-01-01 10:00:00', '2024-01-01 10:00:00');
INSERT OR IGNORE INTO vulnerabilities (description, severity, report_id, created_at, updated_at) VALUES ('XSS no campo de busca', 'HIGH', 1, '2024-01-01 10:00:00', '2024-01-01 10:00:00');
INSERT OR IGNORE INTO vulnerabilities (description, severity, report_id, created_at, updated_at) VALUES ('CSRF no formulário', 'MEDIUM', 2, '2024-01-02 10:00:00', '2024-01-02 10:00:00');
INSERT OR IGNORE INTO vulnerabilities (description, severity, report_id, created_at, updated_at) VALUES ('Exposição de dados sensíveis', 'HIGH', 2, '2024-01-02 10:00:00', '2024-01-02 10:00:00');
INSERT OR IGNORE INTO vulnerabilities (description, severity, report_id, created_at, updated_at) VALUES ('Autenticação fraca', 'HIGH', 3, '2024-01-03 10:00:00', '2024-01-03 10:00:00');
INSERT OR IGNORE INTO vulnerabilities (description, severity, report_id, created_at, updated_at) VALUES ('Falta de rate limiting', 'LOW', 3, '2024-01-03 10:00:00', '2024-01-03 10:00:00');
INSERT OR IGNORE INTO vulnerabilities (description, severity, report_id, created_at, updated_at) VALUES ('Path traversal', 'CRITICAL', 4, '2024-01-04 10:00:00', '2024-01-04 10:00:00');
INSERT OR IGNORE INTO vulnerabilities (description, severity, report_id, created_at, updated_at) VALUES ('Injeção de comandos', 'CRITICAL', 4, '2024-01-04 10:00:00', '2024-01-04 10:00:00');
INSERT OR IGNORE INTO vulnerabilities (description, severity, report_id, created_at, updated_at) VALUES ('Sessão sem expiração', 'MEDIUM', 5, '2024-01-05 10:00:00', '2024-01-05 10:00:00');
INSERT OR IGNORE INTO vulnerabilities (description, severity, report_id, created_at, updated_at) VALUES ('Log de senhas em texto claro', 'HIGH', 5, '2024-01-05 10:00:00', '2024-01-05 10:00:00');
