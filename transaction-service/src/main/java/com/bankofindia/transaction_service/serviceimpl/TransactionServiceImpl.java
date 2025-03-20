package com.bankofindia.transaction_service.serviceimpl;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bankofindia.transaction_service.kafka.BalanceEventProducer;
import com.bankofindia.transaction_service.model.TransactionStatus;
import com.bankofindia.transaction_service.model.TransactionType;
import com.bankofindia.transaction_service.model.dto.TransactionDto;
import com.bankofindia.transaction_service.model.entity.Transaction;
import com.bankofindia.transaction_service.model.externaldto.BalanceDto;
import com.bankofindia.transaction_service.model.response.Response;
import com.bankofindia.transaction_service.repository.TransactionRepository;
import com.bankofindia.transaction_service.service.TransactionService;
import com.bankofindia.transaction_service.exception.*;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
//	@Autowired
//	private BalanceEventProducer balanceEventProducer;
	
	@Value("${spring.application.ok:SUCCESS}")
	private String success;

	@Override
	public Response depositMoney(TransactionDto transactionDto) {
		
		Transaction transaction = new Transaction();
//		transactionDto.setTransactionType(TransactionType.DEPOSIT);
		modelMapper.map(transactionDto, transaction);
		transactionRepository.save(transaction);

		
		return Response.builder()
				.responseCode(success)
				.data(transaction)
				.message("Transaction happened successfully").build();
	}

	@Override
	public Response withdrawMoney(TransactionDto transactionDto) {
		
		Transaction transaction = new Transaction();

		modelMapper.map(transactionDto, transaction);
		transactionRepository.save(transaction);

		
		return Response.builder()
				.responseCode(success)
				.data(transaction)
				.message("Transaction happened successfully").build();
	
	}

	@Override
	public Response fetchTransactionsOfAccount(String accountNumber) {

		if (!transactionRepository.existsByAccountNumber(accountNumber)) {
			throw new ResourceNotFound("Account not found for account number: " + accountNumber);
		}
		
		List<Transaction> listOfTransactions = transactionRepository.findByAccountNumber(accountNumber);
		
	    List<TransactionDto> listOfTransactionDtos = listOfTransactions.stream()
	            .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
	            .collect(Collectors.toList());
		
		return Response.builder()
				.responseCode(success)
				.data(listOfTransactionDtos)
				.message("Transaction history retrieved successfully")
				.build();

	}

	@Override
	public Response fetchTransactionsOfAccount(String accountNumber, LocalDateTime startDate, LocalDateTime endDate) {
		    if (!transactionRepository.existsByAccountNumber(accountNumber)) {
		        throw new ResourceNotFound("Account not found for account number: " + accountNumber);
		    }

		    List<Transaction> listOfTransactions = transactionRepository
		            .findByAccountNumberAndTransactionTimeBetween(accountNumber, startDate, endDate);

		    if (listOfTransactions.isEmpty()) {
		        return Response.builder()
		                .responseCode(success)
		                .data(Collections.emptyList())
		                .message("No transactions found for the given date range")
		                .build();
		    }

		    List<TransactionDto> listOfTransactionDtos = listOfTransactions.stream()
		            .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
		            .collect(Collectors.toList());

		    return Response.builder()
		            .responseCode(success)
		            .data(listOfTransactionDtos)
		            .message("Transactions history retrieved successfully")
		            .build();
		

	}

}
