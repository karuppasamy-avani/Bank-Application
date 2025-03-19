package com.bankofindia.account_service.model.externaldto;

import java.math.BigDecimal;
import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceDto {
	
	private String accountNumber;
	private BigDecimal amount;
	private BigDecimal previousBalance;
	private LocalDateTime time;
}