# Kafka com Spring Boot

Demonstração do fluxo de mensageria com Kafka:
`POST /mensagens` → **kafkaProducer** publica no tópico `meu-topico` → **kafkaConsumer** consome com `@KafkaListener`.

| Projeto | Porta | Responsabilidade |
|---|---|---|
| `kafkaProducer` | 8081 | Endpoint REST + envio com `KafkaTemplate` |
| `kafkaConsumer` | 8082 | Leitura do tópico com `@KafkaListener` |

## Como rodar

1. Suba um broker Kafka em `localhost:9092`.
2. `cd kafkaProducer && ./mvnw spring-boot:run`
3. `cd kafkaConsumer && ./mvnw spring-boot:run`
4. Envie uma mensagem:

```bash
curl -X POST localhost:8081/mensagens -H "Content-Type: application/json" -d '{"mensagem":"oi"}'
```

A mensagem aparece no log do consumer.
