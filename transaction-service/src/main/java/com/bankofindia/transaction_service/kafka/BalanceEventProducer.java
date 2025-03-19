package com.bankofindia.transaction_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import com.bankofindia.transaction_service.model.externaldto.BalanceDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BalanceEventProducer {
	
	private final KafkaTemplate<String, BalanceDto> kafkaTemplate;

	public BalanceEventProducer(KafkaTemplate<String, BalanceDto> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendBalanceCheckEvent(BalanceDto balanceDto) {
		kafkaTemplate.send("balance-transaction-happened", balanceDto);
	}
}
