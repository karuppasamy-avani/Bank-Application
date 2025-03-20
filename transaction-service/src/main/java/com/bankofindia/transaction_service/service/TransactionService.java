package com.bankofindia.transaction_service.service;

import java.time.LocalDateTime;

import com.bankofindia.transaction_service.model.dto.TransactionDto;
import com.bankofindia.transaction_service.model.response.Response;

public interface TransactionService {
	Response depositMoney(TransactionDto transactionDto);

	Response withdrawMoney(TransactionDto transactionDto);

	Response fetchTransactionsOfAccount(String accountNumber);

	Response fetchTransactionsOfAccount(String accountNumber, LocalDateTime startDate, LocalDateTime endDate); 
}
