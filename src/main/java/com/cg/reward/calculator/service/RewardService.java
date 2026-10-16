package com.cg.reward.calculator.service;

import java.util.List;

import com.cg.reward.calculator.dto.CustomerRewardsResponse;
import com.cg.reward.calculator.dto.TransactionRequest;
/**
 * Service interface defining methods for calculating reward points.
 */	
public interface RewardService {

	void addTransaction(TransactionRequest req);
	CustomerRewardsResponse getRewards(Long customerId);
	List<CustomerRewardsResponse> getAllRewards();
}
