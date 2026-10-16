package com.cg.reward.calculator.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cg.reward.calculator.dto.CustomerRewardsResponse;
import com.cg.reward.calculator.dto.MonthlyReward;
import com.cg.reward.calculator.dto.TransactionRequest;
import com.cg.reward.calculator.entity.Customer;
import com.cg.reward.calculator.entity.Transaction;
import com.cg.reward.calculator.exception.InvalidTransactionException;
import com.cg.reward.calculator.exception.ResourceNotFoundException;
import com.cg.reward.calculator.repository.CustomerRepository;
import com.cg.reward.calculator.repository.TransactionRepository;
import com.cg.reward.calculator.util.RewardCalculator;

import lombok.RequiredArgsConstructor;
/**
 * Implementation of reward calculation business logic.
 * Applies rules:
 * - 1 point per $1 spent between $50–$100
 * - 2 points per $1 spent above $100
 */
@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService{

	private final CustomerRepository customerRepository;
	private final TransactionRepository transactionRepository;

    /**
     * creates a transaction.
     * @param TransactionRequest 
     */

	@Override
	public void addTransaction(TransactionRequest req) {
		Customer customer = customerRepository.findById(req.getCustomerId())
		.orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
		
		if (req.getAmount() <= 0) { throw new InvalidTransactionException("Amount must be positive");}
		
		Transaction t = Transaction.builder()
						.amount(req.getAmount())
						.date(req.getDate())
						.customer(customer)
						.build();
						transactionRepository.save(t);
	}

    /**
     * calculates rewards for a specific customer based on id for last 3 months.
     * @param customerId 
     */
	@Override
	public CustomerRewardsResponse getRewards(Long customerId) {
        
		if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer not found with id: " + customerId);
        }

	    List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);

	    // only last 3 months
	    LocalDate now = LocalDate.now();
	    LocalDate threeMonthsAgo = now.minusMonths(3).withDayOfMonth(1);

        List<Transaction> recentTransactions = transactions.stream()
                .filter(t -> !t.getDate().isBefore(threeMonthsAgo))
                .toList();

        if (recentTransactions.isEmpty()) {
            return CustomerRewardsResponse.builder()
                    .customerId(customerId)
                    .monthlyRewards(Collections.emptyList())
                    .totalPoints(0L)
                    .build();
        }

	    // Group monthly
	    Map<Month, Long> monthlyPoints = transactions.stream()
	            .collect(Collectors.groupingBy(
	                    t -> t.getDate().getMonth(),
	                    Collectors.summingLong(t -> RewardCalculator.calculatePoints(t.getAmount()))
	            ));

	    List<MonthlyReward> monthlyRewardList = monthlyPoints.entrySet()
	            .stream().map(e -> new MonthlyReward(e.getKey().toString(), e.getValue())).toList();

	    long total = monthlyPoints.values().stream().mapToLong(Long::longValue).sum();

	    return CustomerRewardsResponse.builder()
	            .customerId(customerId)
	            .monthlyRewards(monthlyRewardList)
	            .totalPoints(total)
	            .build();
	}
	
	public List<CustomerRewardsResponse> getAllRewards() {
	return customerRepository.findAll().stream().map(c -> getRewards(c.getId())).toList();
	}

}
