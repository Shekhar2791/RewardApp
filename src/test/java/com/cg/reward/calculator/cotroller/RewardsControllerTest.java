package com.cg.reward.calculator.cotroller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.reward.calculator.controller.RewardController;
import com.cg.reward.calculator.dto.CustomerRewardsResponse;
import com.cg.reward.calculator.service.RewardService;
/*
 * Test controller
 * @author SKH
 */
@WebMvcTest(controllers = RewardController.class)
class RewardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RewardService rewardService;

    @Test
    void getCustomerRewards_returnsOk() throws Exception {
        CustomerRewardsResponse resp = CustomerRewardsResponse.builder()
                .customerId(1L)
                .monthlyRewards(Collections.emptyList())
                .totalPoints(90L) 
                .build();

        Mockito.when(rewardService.getRewards(1L)).thenReturn(resp);

        mockMvc.perform(get("/api/rewards/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(1)))
                .andExpect(jsonPath("$.totalPoints", is(90)));
    }
}

