package com.cg.reward.calculator.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.reward.calculator.dto.CustomerRewardsResponse;
import com.cg.reward.calculator.entity.Customer;
import com.cg.reward.calculator.entity.Transaction;
import com.cg.reward.calculator.repository.CustomerRepository;
import com.cg.reward.calculator.repository.TransactionRepository;
/*
 * Test for service.
 * @author SKH
 */
class RewardServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardServiceImpl rewardService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test the rewards
     * @return null ResourceNotFoundException if the customer id does not exist in the database
     */
    @Test
    void testGetAllRewards() {

        Customer c1 = new Customer(1L, "Sam", null);
        when(customerRepository.existsById(1L)).thenReturn(true);
        when(customerRepository.findAll()).thenReturn(List.of(c1));
        when(transactionRepository.findByCustomerId(1L))
                .thenReturn(List.of(
                        new Transaction(1L, 120.0, LocalDate.now(), c1)
                ));

        List<CustomerRewardsResponse> result = rewardService.getAllRewards();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCustomerId()).isEqualTo(1L);
        assertThat(result.get(0).getTotalPoints()).isGreaterThan(0);

        verify(customerRepository).findAll();
    }

    /**
     * Test the rewards
     * @return null ResourceNotFoundException if the customer id does not exist in the database
     */
    @Test
    void testGetRewards() {

        Customer c = new Customer(1L, "Sam", null);
        LocalDate currentTestDate = LocalDate.now();

        Transaction t1 = new Transaction(1L, 120.0, currentTestDate, c);
        Transaction t2 = new Transaction(2L, 80.0, currentTestDate, c);
        when(customerRepository.existsById(1L)).thenReturn(true);
        when(transactionRepository.findByCustomerId(1L))
                .thenReturn(List.of(t1, t2));

        CustomerRewardsResponse response = rewardService.getRewards(1L);

        assertThat(response).isNotNull();
        assertThat(response.getCustomerId()).isEqualTo(1L);
        assertThat(response.getMonthlyRewards()).isNotEmpty();
        assertThat(response.getMonthlyRewards().get(0).getMonth()).isEqualTo(currentTestDate.getMonth().toString());
        assertThat(response.getTotalPoints()).isEqualTo(120L);
    }
}
