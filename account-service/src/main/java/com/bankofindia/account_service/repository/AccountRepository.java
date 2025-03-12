package com.bankofindia.account_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankofindia.account_service.model.AccountType;
import com.bankofindia.account_service.model.entity.Account;
import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findAccountByUserIdAndAccountType(Long userId, AccountType accountType);
	
	Optional<Account> findByAccountNumber(String accountNumber);
}
