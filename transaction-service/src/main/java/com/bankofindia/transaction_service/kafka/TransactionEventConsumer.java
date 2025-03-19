package com.bankofindia.transaction_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.bankofindia.transaction_service.model.dto.TransactionDto;
import com.bankofindia.transaction_service.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionEventConsumer {

	@Autowired
	private TransactionService transactionService;
	
	@KafkaListener(topics = "transaction-happened", groupId = "transaction-service-group")
	public void handleUserCreated(@Payload TransactionDto transactionDto) {
	    log.info("Received transaction-happened event: {}", transactionDto);
	    transactionService.depositMoney(transactionDto);
	}

}
