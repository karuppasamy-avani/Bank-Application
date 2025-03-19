package com.bankofindia.transaction_service.serviceimpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bankofindia.transaction_service.kafka.BalanceEventProducer;
import com.bankofindia.transaction_service.model.TransactionStatus;
import com.bankofindia.transaction_service.model.TransactionType;
import com.bankofindia.transaction_service.model.dto.TransactionDto;
import com.bankofindia.transaction_service.model.entity.Transaction;
import com.bankofindia.transaction_service.model.externaldto.BalanceDto;
import com.bankofindia.transaction_service.model.response.Response;
import com.bankofindia.transaction_service.repository.TransactionRepository;
import com.bankofindia.transaction_service.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BalanceEventProducer balanceEventProducer;
	
	@Value("${spring.application.ok:SUCCESS}")
	private String success;

	@Override
	public Response depositMoney(TransactionDto transactionDto) {
//        System.out.println("current time:"+transactionDto.getTransactionTime());
      
		
		BalanceDto balanceDto = new BalanceDto();
		balanceDto.setAccountNumber(transactionDto.getAccountNumber());
		balanceDto.setAmount(transactionDto.getAmount());
		balanceDto.setTime(transactionDto.getTransactionTime());
		balanceDto.setPreviousBalance(transactionDto.getBalance());
		
		balanceEventProducer.sendBalanceCheckEvent(balanceDto);
		
		
		Transaction transaction = new Transaction();
		transactionDto.setTransactionType(TransactionType.DEPOSIT);
		modelMapper.map(transactionDto, transaction);
		transactionRepository.save(transaction);

		
		return Response.builder()
				.responseCode(success)
				.data(transaction)
				.message("Transaction happened successfully").build();
	}

}
