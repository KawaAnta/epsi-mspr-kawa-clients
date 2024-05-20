package com.kawa.clients.clientsapi.domain.service.customer;

import com.kawa.clients.clientsapi.domain.ports.CustomerRepository;
import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository mockCustomerRepository;

    private CustomerServiceImpl customerServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        customerServiceImplUnderTest = new CustomerServiceImpl(mockCustomerRepository);
    }

    @Test
    void testGetAll() {
        List<Customer> customers = List.of(Mockito.mock(Customer.class));
        // GIVEN
        when(mockCustomerRepository.getAll()).thenReturn(customers);

        // WHEN
        final List<Customer> result = customerServiceImplUnderTest.getAll();

        // THEN
        assertEquals(result, customers);
    }

    @Test
    void testGetAll_CustomerRepositoryReturnsNoItems() {
        // GIVEN
        when(mockCustomerRepository.getAll()).thenReturn(Collections.emptyList());

        // WHEN
        final List<Customer> result = customerServiceImplUnderTest.getAll();

        // THEN
        assertEquals(Collections.emptyList(), result);
    }
}
