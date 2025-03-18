package com.bankofindia.account_service.service;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import com.bankofindia.account_service.model.AccountStatus;
import com.bankofindia.account_service.model.dto.AccountDto;
import com.bankofindia.account_service.model.entity.Account;
import com.bankofindia.account_service.model.externaldto.TransactionDto;
import com.bankofindia.account_service.model.externaldto.UserDto;
import com.bankofindia.account_service.model.response.Response;

public interface AccountService {
	
	Response createAccount(UserDto Dto);
	
	Response getAccountDetails(AccountDto accountDto); 
	
	Response updateAccountStatus(String accountNumber, AccountStatus status);
	
	Response getBalance(String accountNumber);
	
	Response depositMoney(TransactionDto transaction, LocalDateTime time);
	
	

}
