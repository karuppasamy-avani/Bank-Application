package com.bankofindia.account_service.service;

import org.springframework.http.ResponseEntity;

import com.bankofindia.account_service.model.dto.AccountDto;
import com.bankofindia.account_service.model.entity.Account;
import com.bankofindia.account_service.model.response.Response;

public interface AccountService {
	
	Response createAccount(AccountDto Dto);
	
	Response getAccountDetails(); 
	
	Response deleteAccount(); 
	
	Response getBalance(); 
	
	Response showDetails(); 
	
	Response updateAccount();

}
