package com.bankofindia.account_service.serviceimpl;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankofindia.account_service.exception.ResourceConflict;
import com.bankofindia.account_service.exception.ResourceNotFound;
import com.bankofindia.account_service.external.UserService;
import com.bankofindia.account_service.model.AccountStatus;
import com.bankofindia.account_service.model.AccountType;
import com.bankofindia.account_service.model.dto.AccountDto;
import com.bankofindia.account_service.model.entity.Account;
import com.bankofindia.account_service.model.externaldto.UserDto;
import com.bankofindia.account_service.model.response.Response;
import com.bankofindia.account_service.repository.AccountRepository;
import com.bankofindia.account_service.service.AccountService;
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
	private ModelMapper modelMapper;
	
	@Value("${spring.application.ok:Default success message}")
	private String success;

	
	@Override
	public Response createAccount(AccountDto accountDto) {
		ResponseEntity<Boolean> user = userService.fetchUserById(accountDto.getUserId());
        if (Boolean.FALSE.equals(user.getBody()))  {
            throw new ResourceNotFound("user not found on the server");
        }

        accountRepo.findAccountByUserIdAndAccountType(accountDto.getUserId(), accountDto.getAccountType())
                .ifPresent(account -> {
                    log.error("Account already exists on the server");
                    throw new ResourceConflict("Account already exists on the server");
                });

        Account account = new Account();
        modelMapper.map(accountDto, account);
        
        long number = 1_000_000_000L + Math.abs(new Random().nextLong() % 9_000_000_000L); 
        
        account.setAccountNumber(ACC_PREFIX + number);
        account.setAccountStatus(AccountStatus.PENDING);
        account.setAvailableBalance(BigDecimal.valueOf(0));
        account.setAccountType(accountDto.getAccountType());
        accountRepo.save(account);
        return Response.builder()
                .responseCode(success)
                .message("Account created successfully").build();
    }
	

	@Override
	public Response getAccountDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getBalance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response showDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateAccount() {
		// TODO Auto-generated method stub
		return null;
	}

}
