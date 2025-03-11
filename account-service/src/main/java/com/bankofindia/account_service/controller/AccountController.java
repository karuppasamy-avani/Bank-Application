package com.bankofindia.account_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankofindia.account_service.model.dto.AccountDto;
import com.bankofindia.account_service.model.response.Response;
import com.bankofindia.account_service.service.AccountService;

@RestController
@RequestMapping("/boi/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/create")
	// officer
	public ResponseEntity<Response> createAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
	@GetMapping("/details/{id}")
	// officer
	public ResponseEntity<String> getAccountDetails(){
		return null;
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAccount(){
		return null;
		
	}
	@GetMapping("/balance")
	// officer
	public ResponseEntity<String> getBalance(){
		return null;
		
	}
	@GetMapping("/me")
	// user
	public ResponseEntity<String> showDetails(){
		return null;
		
	}
	// officer
	@PutMapping("/update") 
	public ResponseEntity<String> updateAccount(){
		return null;
		
	}
}
