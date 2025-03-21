package com.bankofindia.account_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bankofindia.account_service.model.dto.AccountDto;
import com.bankofindia.account_service.model.externaldto.NotificationDto;

@Service
public class AccountCreatedEventProducer {
	
	private final KafkaTemplate<String, NotificationDto> kafkaTemplate;

	public AccountCreatedEventProducer(KafkaTemplate<String, NotificationDto> kafkaTemplate) {
	        this.kafkaTemplate = kafkaTemplate;
	    }

	public void sendAccountCreatedEvent(NotificationDto notificationDto) {
		kafkaTemplate.send("account-created", notificationDto);
	}
}
