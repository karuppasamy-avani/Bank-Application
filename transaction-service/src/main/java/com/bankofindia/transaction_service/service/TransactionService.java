package com.bankofindia.transaction_service.service;

import com.bankofindia.transaction_service.model.dto.TransactionDto;
import com.bankofindia.transaction_service.model.response.Response;

public interface TransactionService {
	Response depositMoney(TransactionDto transaction); 
}
