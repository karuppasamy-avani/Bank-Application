package com.bankofindia.transaction_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankofindia.transaction_service.model.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
