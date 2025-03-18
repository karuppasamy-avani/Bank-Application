package com.bankofindia.transaction_service.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankofindia.transaction_service.model.dto.TransactionDto;
import com.bankofindia.transaction_service.model.response.Response;
import com.bankofindia.transaction_service.service.TransactionService;


@RestController
@RequestMapping("/boi/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/deposit")
	public ResponseEntity<Response> depositMoney(@RequestBody TransactionDto transaction){
		return new ResponseEntity<>(transactionService.depositMoney(transaction), HttpStatus.OK);
	}
	
}
