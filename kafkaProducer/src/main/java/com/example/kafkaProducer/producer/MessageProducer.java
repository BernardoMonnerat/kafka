package com.example.kafkaProducer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private static final Logger log = LoggerFactory.getLogger(MessageProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topico;

    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate,
                           @Value("${app.kafka.topic}") String topico) {
        this.kafkaTemplate = kafkaTemplate;
        this.topico = topico;
    }

    public void enviar(String mensagem) {
        log.info("Enviando mensagem para o topico '{}': {}", topico, mensagem);

        kafkaTemplate.send(topico, mensagem)
                .whenComplete((resultado, erro) -> {
                    if (erro != null) {
                        log.error("Falha ao enviar mensagem: {}", erro.getMessage());
                    } else {
                        var metadata = resultado.getRecordMetadata();
                        log.info("Mensagem enviada! topico={} particao={} offset={}",
                                metadata.topic(), metadata.partition(), metadata.offset());
                    }
                });
    }
}
