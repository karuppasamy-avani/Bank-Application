package com.bankofindia.notification_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


import com.bankofindia.notification_service.model.dto.NotificationDto;
import com.bankofindia.notification_service.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationConsumer {
	
	@Autowired
	private NotificationService notificationService;
	
	 // Account Creation Events
    @KafkaListener(topics = "account-created", groupId = "notification-group")
    public void consumeAccountCreation(@Payload NotificationDto notificationDto) {
    	notificationService.sendEmail(notificationDto, "ACCOUNT_CREATED");
    }

//	  //Transaction Events
//    @KafkaListener(topics = "transaction-topic", groupId = "notification-group")
//    public void consumeTransaction(@Payload NotificationDto notificationDto) {
//       System.out.println("Received Transaction message: " + message);
//        notificationService.sendEmail(notificationDto, "TRANSACTION");
//    }

   
}
