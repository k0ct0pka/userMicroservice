package org.example.usermicroservice;

import org.example.usermicroservice.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
public class UserMicroserviceApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserMicroserviceApplication.class, args);
    }
    @Bean
    public org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }
}
