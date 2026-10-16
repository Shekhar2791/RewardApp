package com.cg.reward.calculator.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Transaction Request DTO
 * @author SKH
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    @NotNull(message = "Customer account ID reference is required")
	private Long customerId;
    @NotNull(message = "Transaction purchase amount is required")
    @Positive(message = "Transaction purchase amount must be strictly greater than zero")
	private double amount;
    @NotNull(message = "Transaction occurrence date context is required")
    @PastOrPresent(message = "Transaction date timestamp cannot point to a future timeline")
	private LocalDate date;
	
}
