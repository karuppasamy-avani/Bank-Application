package com.bankofindia.account_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bankofindia.account_service.model.externaldto.TransactionDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionEventProducer {

	private final KafkaTemplate<String, TransactionDto> kafkaTemplate;

	public TransactionEventProducer(KafkaTemplate<String, TransactionDto> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendTransactionEvent(TransactionDto transactionDto) {
		kafkaTemplate.send("transaction-happened", transactionDto);
	}
}
