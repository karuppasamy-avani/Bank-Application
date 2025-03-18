package com.bankofindia.account_service.serviceimpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import javax.security.auth.login.AccountNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankofindia.account_service.exception.ResourceConflict;
import com.bankofindia.account_service.exception.ResourceNotFound;
import com.bankofindia.account_service.external.UserService;
import com.bankofindia.account_service.kafka.TransactionEventProducer;
import com.bankofindia.account_service.model.AccountStatus;
import com.bankofindia.account_service.model.AccountType;
import com.bankofindia.account_service.model.dto.AccountDto;
import com.bankofindia.account_service.model.entity.Account;
import com.bankofindia.account_service.model.externaldto.TransactionDto;
import com.bankofindia.account_service.model.externaldto.UserDto;
import com.bankofindia.account_service.model.response.Response;
import com.bankofindia.account_service.repository.AccountRepository;
import com.bankofindia.account_service.service.AccountService;
import com.bankofindia.account_service.util.GetTheTimeAndDate;

import jakarta.transaction.Transactional;

import static com.bankofindia.account_service.model.Constants.ACC_PREFIX;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service  
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionEventProducer transactionEventProducer;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private GetTheTimeAndDate getcurrentTime;
	
	@Value("${spring.application.ok:SUCCESS}")
	private String success;

	
	@Override
	@Transactional
	public Response createAccount(UserDto userDto) {
        accountRepo.findAccountByUserIdAndAccountType(userDto.getUserId(), AccountType.SAVINGS)
                .ifPresent(account -> {
                    log.error("Account already exists on the server");
                    throw new ResourceConflict("Account already exists on the server");
                });
        
        Account account = new Account();
        modelMapper.map(userDto, account);
        account.setAccountId(null);
        long number = 1_000_000_000L + Math.abs(new Random().nextLong() % 9_000_000_000L); 
        account.setAccountNumber(ACC_PREFIX + number);
        account.setAccountStatus(AccountStatus.PENDING);
        account.setAvailableBalance(BigDecimal.valueOf(0));
        account.setAccountType(AccountType.SAVINGS);
        accountRepo.save(account);
        return Response.builder()
                .responseCode(success)
                .data(account)
                .message("Account created successfully").build();
    }
	

	@Override
	@Transactional
	public Response getAccountDetails(AccountDto accountDto) {
		Optional<Account> accountOptional = accountRepo.findByAccountNumber(accountDto.getAccountNumber());

	    if (accountOptional.isEmpty()) {
	        throw new ResourceNotFound("Account not found for account number: " + accountDto.getAccountNumber());
	    }
		 Account account = accountOptional.get();
		    
		    return Response.builder()
		            .responseCode(success)
		            .message("Account details retrieved successfully")
		            .data(account) 
		            .build();
	}
	
	@Override
	public Response updateAccountStatus(String accountNumber, AccountStatus status) {
	    Account account = accountRepo.findByAccountNumber(accountNumber)
	            .orElseThrow(() -> new ResourceNotFound("Account not found with account number: " + accountNumber));

	    account.setAccountStatus(status);
	    accountRepo.save(account);
	    log.info("Account has been updated");
	    
	    return Response.builder()
	            .responseCode(success)
	            .message("Account details retrieved successfully")
	            .data(modelMapper.map(account, AccountDto.class))
	            .build();
	}
	
	@Override
	public Response getBalance(String accountNumber) {
		Account account = accountRepo.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new ResourceNotFound("Account not found with account number: " + accountNumber));
		log.info("Retrieving balance in given account");
		BigDecimal balance =  account.getAvailableBalance();
		return Response.builder()
				.responseCode(success)
				.message("Available Balance")
				.data(balance)
				.build();
	}


	@Override
	public Response depositMoney(TransactionDto transaction, LocalDateTime time) {
		
		transaction.setTime(getcurrentTime.getFormattedTime(time));
		
		transactionEventProducer.sendTransactionEvent(transaction);
		
		return Response.builder()
				.responseCode(success)
				.message("Available Balance")
				.data(transaction)
				.build();
	}
	
	



//	@Override
//	public Response deleteAccount() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//

//
//	@Override
//	public Response showDetails() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Response updateAccount() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
