package com.bankofindia.transaction_service.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import com.bankofindia.transaction_service.model.TransactionStatus;
import com.bankofindia.transaction_service.model.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
	
    private String accountNumber;

    private TransactionType transactionType;  

    private BigDecimal amount;

    private TransactionStatus status;

    private LocalDateTime transactionTime;
}
