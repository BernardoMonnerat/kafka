package com.example.kafkaProducer.controller;

import com.example.kafkaProducer.dto.MessageRequest;
import com.example.kafkaProducer.producer.MessageProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mensagens")
public class MessageController {

    private final MessageProducer producer;

    public MessageController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> enviar(@RequestBody MessageRequest request) {
        if (request.mensagem() == null || request.mensagem().isBlank()) {
            return ResponseEntity.badRequest().body("O campo 'mensagem' e obrigatorio");
        }
        producer.enviar(request.mensagem());
        return ResponseEntity.accepted().body("Mensagem enviada para o Kafka: " + request.mensagem());
    }
}
