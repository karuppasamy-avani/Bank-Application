package com.bankofindia.notification_service.model.extrenaldto;

import java.math.BigDecimal;

import com.bankofindia.notification_service.model.AccountStatus;
import com.bankofindia.notification_service.model.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
	
	private String accountNumber;
	  
	private AccountType accountType;
	
	private AccountStatus accountStatus;
	
	private BigDecimal availableBalance;

	private Long userId;

}
