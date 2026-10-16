package com.cg.reward.calculator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.cg.reward.calculator.dto.CustomerRewardsResponse;
import com.cg.reward.calculator.dto.TransactionRequest;
import com.cg.reward.calculator.service.RewardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
/**
 * REST Controller exposing end points for reward calculation.
 * @author SKH
 */
@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {

	private final RewardService rewardService;

    /**
     * Accepts and persists a new client transaction.      *
     * @param req verified details container holding transaction data elements
     * @return operation status payload confirmation string wrapped in ResponseEntity
     */
	@Operation(summary = "endpoint for creating a transaction")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Ok"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	
	@PostMapping("/transactions")
	public ResponseEntity<String> addTransaction(@Valid @RequestBody TransactionRequest req) {
	rewardService.addTransaction(req);
	return ResponseEntity.ok("Transaction added successfully");
	}

	@Operation(summary = "endpoint for geting the rewards for a customer")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Ok"), 
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerRewardsResponse> getRewards(@PathVariable Long customerId) {
        CustomerRewardsResponse response = rewardService.getRewards(customerId);
        return ResponseEntity.ok(response);
	}

	@Operation(summary = "endpoint for getting all rewards")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Ok"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping
	public ResponseEntity<List<CustomerRewardsResponse>> getAllRewards() {
        List<CustomerRewardsResponse> responseList = rewardService.getAllRewards();
        return ResponseEntity.ok(responseList);
	}
}
