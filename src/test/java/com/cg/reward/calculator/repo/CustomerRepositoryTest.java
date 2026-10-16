package com.cg.reward.calculator.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.cg.reward.calculator.entity.Customer;
import com.cg.reward.calculator.repository.CustomerRepository;

/*
 * Test repository functionality for Customer persistence.
 * @author SKH
 */
@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    void testSaveAndFind() {
        Customer c = Customer.builder().name("Test").build();
        Customer saved = repository.save(c);

        assertNotNull(saved.getId());
        assertEquals("Test", saved.getName());
    }
}
