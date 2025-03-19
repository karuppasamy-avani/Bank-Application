package com.bankofindia.account_service.kafka;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.bankofindia.account_service.model.externaldto.BalanceDto;
import com.bankofindia.account_service.model.externaldto.UserDto;
import com.bankofindia.account_service.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountEventConsumer {

	@Autowired
	private AccountService accountService;

	@KafkaListener(topics = "user-created", groupId = "account-service-group")
	public void handleUserCreated(@Payload UserDto userDto) {
	    log.info("Received user-created event: ", userDto);
	    accountService.createAccount(userDto);
	}
	
	@KafkaListener(topics = "balance-update", groupId = "account-service-group")
	public void handleBalanceUpdated(@Payload BalanceDto balanceDto) {
	    log.info("Received balance-update event: ", balanceDto);
	    accountService.balanceUpdate(balanceDto);
	}

}
