package com.bankofindia.transaction_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankofindia.transaction_service.model.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	boolean existsByAccountNumber(String accountNumber);
	
	List<Transaction> findByAccountNumber(String accountNumber);
	
	List<Transaction> findByAccountNumberAndTransactionTimeBetween(
            String accountNumber, LocalDateTime startDate, LocalDateTime endDate);

}
