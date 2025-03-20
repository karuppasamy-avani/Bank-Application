package com.bankofindia.account_service.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankofindia.account_service.model.AccountStatus;
import com.bankofindia.account_service.model.dto.AccountDto;
import com.bankofindia.account_service.model.externaldto.TransactionDto;
import com.bankofindia.account_service.model.externaldto.UserDto;
import com.bankofindia.account_service.model.response.Response;
import com.bankofindia.account_service.service.AccountService;

@RestController
@RequestMapping("/boi/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/create")
	// officer
	public ResponseEntity<Response> createAccount(@RequestBody UserDto userDto){
		return new ResponseEntity<>(accountService.createAccount(userDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/details")
	// officer
	public ResponseEntity<Response> getAccountDetails(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.getAccountDetails(accountDto), HttpStatus.OK);
		
	}
	
	@PutMapping("/update/{accountNumber}")
	public ResponseEntity<Response> updateAccountStatus(@PathVariable String accountNumber, @RequestParam AccountStatus status) {
	    return new ResponseEntity<>(accountService.updateAccountStatus(accountNumber, status), HttpStatus.OK);
	}


	@GetMapping("/balance/{accountNumber}")
	// officer
	public ResponseEntity<Response> getBalance(@PathVariable String accountNumber){
		return new ResponseEntity<>(accountService.getBalance(accountNumber), HttpStatus.OK);
	}
	
	
	@PostMapping("/deposit")
	public ResponseEntity<Response> depositMoney(@RequestBody TransactionDto transaction){
		LocalDateTime currentTime = LocalDateTime.now(); 
		return new ResponseEntity<>(accountService.depositMoney(transaction, currentTime), HttpStatus.OK);
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Response> withdrawMoney(@RequestBody TransactionDto transaction){
		LocalDateTime currentTime = LocalDateTime.now(); 
		return new ResponseEntity<>(accountService.withdrawMoney(transaction, currentTime), HttpStatus.OK);
	}
	
	
	
	
}
