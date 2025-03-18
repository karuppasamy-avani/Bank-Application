package com.bankofindia.user_service.kafka;

import org.apache.kafka.clients.admin.NewTopic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.bankofindia.user_service.model.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserEventProducer {
	
    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    public UserEventProducer(KafkaTemplate<String, UserDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void sendUserCreatedEvent(UserDto userDto) {
        kafkaTemplate.send("user-created", userDto);
    }
}