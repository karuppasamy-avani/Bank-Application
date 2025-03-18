package com.bankofindia.transaction_service.model.externaldto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
	
	private String accountNumber;
	private BigDecimal amount;
	private String time;
}
