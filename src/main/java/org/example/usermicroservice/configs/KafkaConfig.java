package org.example.usermicroservice.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfig {
    private KafkaTemplate<String, String> kafkaTemplate;

}
