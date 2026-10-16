package com.cg.reward.calculator.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Customer Rewards Response DTO 
 * @author SKH
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRewardsResponse {

	private Long customerId;
	private List<MonthlyReward> monthlyRewards;
	private long totalPoints;
}
