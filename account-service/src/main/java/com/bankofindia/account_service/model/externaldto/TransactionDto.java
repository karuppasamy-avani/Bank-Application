package com.bankofindia.account_service.model.externaldto;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import com.bankofindia.account_service.model.AccountStatus;
import com.bankofindia.account_service.model.TransactionStatus;
import com.bankofindia.account_service.model.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
	
	private String accountNumber;
	
	private TransactionType transactionType; 
	
	private BigDecimal amount;
	
//	private BigDecimal balance;
	
	private TransactionStatus status; 
	
	private LocalDateTime transactionTime;
}
