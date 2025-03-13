package com.bankofindia.user_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bankofindia.user_service.model.dto.UserDto;

@Service
public class UserEventProducer {
    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    public UserEventProducer(KafkaTemplate<String, UserDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserCreatedEvent(UserDto event) {
        kafkaTemplate.send("user-created", event);
    }
}