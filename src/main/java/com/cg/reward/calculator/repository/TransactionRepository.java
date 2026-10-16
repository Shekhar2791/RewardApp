package com.cg.reward.calculator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.reward.calculator.entity.Transaction;
/**
 * Repository for Transaction entity CRUD operations.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCustomerId(Long customerId);

}
