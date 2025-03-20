package com.bankofindia.transaction_service.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankofindia.transaction_service.model.dto.TransactionDto;
import com.bankofindia.transaction_service.model.response.Response;
import com.bankofindia.transaction_service.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/boi/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/deposit")
	public ResponseEntity<Response> depositMoney(@RequestBody TransactionDto transactionDto){
		return new ResponseEntity<>(transactionService.depositMoney(transactionDto), HttpStatus.OK);
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Response> withdrawMoney(@RequestBody TransactionDto transactionDto){
		return new ResponseEntity<>(transactionService.withdrawMoney(transactionDto), HttpStatus.OK);
	}
	
//	@GetMapping("/account/{accountNumber}")
//	public ResponseEntity<Response> fetchTransactionsOfAccount(@PathVariable String accountNumber) {
//		return new ResponseEntity<>(transactionService.fetchTransactionsOfAccount(accountNumber), HttpStatus.OK);
//	}
	
	@GetMapping("/history/{accountNumber}")
	public ResponseEntity<Response> getTransactions(
	        @PathVariable String accountNumber,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) 
	{

	    Response response;
	    
	    if (startDate == null || endDate == null) {
	        response = transactionService.fetchTransactionsOfAccount(accountNumber);
	    } else {
	        response = transactionService.fetchTransactionsOfAccount(accountNumber, startDate, endDate);
	    }

	    return ResponseEntity.ok(response);
	}


}
