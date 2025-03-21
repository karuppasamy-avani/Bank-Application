package com.bankofindia.notification_service.model.dto;

import java.math.BigDecimal;

import com.bankofindia.notification_service.model.AccountStatus;
import com.bankofindia.notification_service.model.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
	
	private String accountNumber;
	  
	private AccountType accountType;
	
	private AccountStatus accountStatus;
	
	private BigDecimal availableBalance;
	
	private String emailId;
	
	private String contactNumber;
}
