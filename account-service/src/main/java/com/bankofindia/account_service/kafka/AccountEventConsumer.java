package com.bankofindia.account_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bankofindia.account_service.model.externaldto.UserDto;
import com.bankofindia.account_service.service.AccountService;

@Service
public class AccountEventConsumer {
	
	@Autowired
	private AccountService accountService;
	
	 @KafkaListener(topics = "user-created", groupId = "banking-group",
             containerFactory = "userKafkaListenerContainerFactory")
    public void handleUserCreated(UserDto event) {
        System.out.println("Received new user event: " + event.getUserId());

        // Logic to create a bank account
//        System.out.println("Creating bank account for user: " + event.getUserId());
        accountService.createAccount(event);
        
        
    }
}
