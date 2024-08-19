package org.example.usermicroservice.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfig {
    private KafkaTemplate<String, String> kafkaTemplate;

    @Bean
    public NewTopic userTopic() {
        return new NewTopic("users", 3, (short) 1);
    }

    @Bean
    public NewTopic logsTopic() {
        return new NewTopic("logs", 3, (short) 1);
    }
}
