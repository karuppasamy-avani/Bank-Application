package com.bankofindia.account_service.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bankofindia.account_service.model.externaldto.TransactionDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositResponseDto {
	
    private String accountNumber;
    private BigDecimal amount;
    private LocalDateTime transactionTime;
    
}