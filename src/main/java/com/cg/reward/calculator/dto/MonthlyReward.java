package com.cg.reward.calculator.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Rewards DTO for monthly rewards.
 * @author SKH
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReward {

	@NotNull(message = "Month indicator label cannot be null")
	private String month;
	@PositiveOrZero(message = "Accumulated points metrics must be zero or a positive value")
	private long points;

}
