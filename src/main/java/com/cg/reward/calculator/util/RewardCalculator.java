package com.cg.reward.calculator.util;

import java.math.BigDecimal;

public class RewardCalculator {

	private RewardCalculator() {}

    /**
     * Calculate points given amount in dollars (BigDecimal).
     * Rules:
     *  - 1 point for each dollar between 50 and 100 (inclusive of >50 up to 100)
     *  - 2 points for each dollar over 100
     * Only whole dollar amounts count.
     * @author SKH
     */
    public static int calculatePoints(BigDecimal amount) {
        if (amount == null) return 0;
        int dollars = amount.intValue();
        if (dollars <= 50) return 0;
        int points = 0;
        if (dollars > 100) {
            points += (dollars - 100) * 2;
            points += 50; 
        } else {
            points += (dollars - 50);
        }
        return points;
    }

    public static int calculatePoints(Double amount) {
        if (amount == null) return 0;
        return calculatePoints(BigDecimal.valueOf(amount));
    }
}
