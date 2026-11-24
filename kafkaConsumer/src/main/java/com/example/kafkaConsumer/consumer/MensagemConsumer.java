package com.example.kafkaConsumer.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MensagemConsumer {

    private static final Logger log = LoggerFactory.getLogger(MensagemConsumer.class);

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumir(ConsumerRecord<String, String> record) {
        log.info("Mensagem recebida | tópico={} partição={} offset={} chave={} valor={}",
                record.topic(), record.partition(), record.offset(), record.key(), record.value());
    }
}
