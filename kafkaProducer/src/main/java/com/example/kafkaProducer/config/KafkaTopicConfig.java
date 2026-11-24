package com.example.kafkaProducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    // Cria o topico no broker ao subir a aplicacao, caso ainda nao exista
    @Bean
    public NewTopic topico(@Value("${app.kafka.topic}") String nomeTopico) {
        return TopicBuilder.name(nomeTopico)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
