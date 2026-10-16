package com.cg.reward.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.reward.calculator.entity.Customer;
/**
 * Repository for Customer entity CRUD operations.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
