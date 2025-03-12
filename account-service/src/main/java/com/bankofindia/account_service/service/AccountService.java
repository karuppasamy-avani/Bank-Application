package com.bankofindia.account_service.service;

import org.springframework.http.ResponseEntity;

import com.bankofindia.account_service.model.AccountStatus;
import com.bankofindia.account_service.model.dto.AccountDto;
import com.bankofindia.account_service.model.entity.Account;
import com.bankofindia.account_service.model.externaldto.TransactionDto;
import com.bankofindia.account_service.model.response.Response;

public interface AccountService {
	
	Response createAccount(AccountDto Dto);
	
	Response getAccountDetails(AccountDto accountDto); 
	
	Response updateAccountStatus(String accountNumber, AccountStatus status);
	
	Response getBalance(String accountNumber);
	
	Response depositMoney(TransactionDto transaction);
	
	

}
