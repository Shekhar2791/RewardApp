package com.cg.reward.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.cg.reward.calculator.util.RewardCalculator;
/**
 * Unit tests for {@link RewardCalculator}.
 *
 * <p>This class verifies the core reward calculation algorithm
 * independent of the service layer.
 * 
 * @author SKH
 */
 class RewardCalculatorTest {

	    @Test
	    @DisplayName("Should correctly calculate traditional edge case point boundaries")
	    void testCalculatePointsBoundaries() {
	        assertEquals(0, RewardCalculator.calculatePoints(BigDecimal.valueOf(49.99)));
	        assertEquals(0, RewardCalculator.calculatePoints(BigDecimal.valueOf(50.0)));
	        assertEquals(1, RewardCalculator.calculatePoints(BigDecimal.valueOf(51.0)));
	        assertEquals(50, RewardCalculator.calculatePoints(BigDecimal.valueOf(100.0)));
	        assertEquals(90, RewardCalculator.calculatePoints(BigDecimal.valueOf(120.0)));
	        assertEquals(250, RewardCalculator.calculatePoints(BigDecimal.valueOf(200.0)));
	    }
    
	    @ParameterizedTest(name = "Spent ${0} -> Expected Points: {1}")
	    @CsvSource({
	        "0.0, 0",
	        "50.50, 0",
	        "100.99, 50"
	    })
	    @DisplayName("Should correctly calculate points using BigDecimal input boundaries")
	    void testCalculatePointsWithBigDecimalBoundaries(double inputAmount, int expectedPoints) {
	        assertEquals(expectedPoints, RewardCalculator.calculatePoints(BigDecimal.valueOf(inputAmount)));
	    }

	    @Test
	    @DisplayName("Should handle null inputs gracefully by returning 0 points")
	    void testCalculatePointsWithNullInputs() {
	        assertEquals(0, RewardCalculator.calculatePoints((BigDecimal) null));
	        assertEquals(0, RewardCalculator.calculatePoints((Double) null));
	    }
	    
	    @Test
	    @DisplayName("Should properly delegate calculations for the Double data type variant method")
	    void testCalculatePointsWithDoubleOverload() {
	        assertEquals(90, RewardCalculator.calculatePoints(120.0));
	    }
}
