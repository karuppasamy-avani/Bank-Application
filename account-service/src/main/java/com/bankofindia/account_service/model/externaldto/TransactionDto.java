package com.bankofindia.account_service.model.externaldto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bankofindia.account_service.model.AccountStatus;

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
	
	private BigDecimal amount;
	
	private BigDecimal balance;
	
	private AccountStatus status; 
	
	private LocalDateTime transactionTime;
}
