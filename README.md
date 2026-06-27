# Toy Project - Microsserviços

O projeto foi separado em três microsserviços Spring Boot:

| Microsserviço | Porta | Responsabilidade |
|---|---:|---|
| `user-api` | `8080` | Cadastro e listagem de usuários |
| `ticket-api` | `8081` | Criação, listagem, busca e atualização de tickets |
| `notification-api` | `8082` | Receber notificações e escrever/logar aos interessados |

## Fluxo implementado

1. Um usuário é cadastrado no `user-api`.
2. O `user-api` salva o usuário e chama o `ticket-api` via HTTP.
3. O `ticket-api` cria automaticamente um ticket de instalação de workstation.
4. Ao criar qualquer ticket novo, o `ticket-api` chama o `notification-api`.
5. O `notification-api` escreve a notificação no log da aplicação.

## Como executar

Abra três terminais, um para cada microsserviço.

### Terminal 1 - Notification API

```bash
cd notification-api
./gradlew bootRun
```

### Terminal 2 - Ticket API

```bash
cd ticket-api
./gradlew bootRun
```

### Terminal 3 - User API

```bash
cd user-api
./gradlew bootRun
```

A ordem recomendada é essa porque o `ticket-api` depende do `notification-api`, e o `user-api` depende do `ticket-api`.

## Testando o fluxo completo

Com os três serviços rodando:

```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Maria Silva",
    "handle": "maria",
    "email": "maria@example.com",
    "password": "Senha123",
    "company": "IFRS",
    "type": "FREE",
    "roles": []
  }'
```

Depois, confira os tickets criados:

```bash
curl http://localhost:8081/api/v1/tickets
```

No terminal do `notification-api`, deve aparecer uma mensagem de log informando os interessados do novo ticket.

### Ordem do teste

1. Envie `POST /api/v1/users` no `user-api`.
2. Verifique `GET /api/v1/tickets` no `ticket-api`.
3. Confirme no log do `notification-api` que a notificação foi escrita.

### Corpo mínimo do usuário

```json
{
  "name": "Julie",
  "handle": "julie",
  "email": "julie@example.com",
  "password": "Senha123",
  "company": "IFRS",
  "type": "FREE",
  "roles": []
}
```

### O que validar no ticket

- `acao = INSTALACAO`
- `objeto = Workstation`
- `criador = email do usuário cadastrado`
- `destinatario = email do usuário cadastrado`

## Criar ticket diretamente

```bash
curl -X POST http://localhost:8081/api/v1/tickets \
  -H "Content-Type: application/json" \
  -d '{
    "acao": "INSTALAR",
    "objeto": "Workstation",
    "detalhes": "Instalar workstation para novo colaborador",
    "criador": "admin@example.com",
    "destinatario": "usuario@example.com",
    "observadores": ["suporte-ti@example.com"]
  }'
```

## Observação

A comunicação foi feita de forma simples usando HTTP síncrono com `RestClient`, para facilitar o entendimento. Em um cenário real, esse fluxo poderia ser evoluído para mensageria, por exemplo RabbitMQ ou Kafka.
