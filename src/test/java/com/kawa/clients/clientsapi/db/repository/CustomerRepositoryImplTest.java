package com.kawa.clients.clientsapi.db.repository;

import com.kawa.clients.clientsapi.db.models.CustomerDb;
import com.kawa.clients.clientsapi.db.port.mapper.CustomerMapper;
import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryImplTest {

    @Mock
    private CustomerDbRepository mockDbRepository;
    @Mock
    private CustomerMapper mockMapper;

    private CustomerRepositoryImpl customerRepositoryImplUnderTest;

    @BeforeEach
    void setUp() {
        customerRepositoryImplUnderTest = new CustomerRepositoryImpl(mockDbRepository, mockMapper);
    }

    @Test
    void testGetAll() {
        // GIVEN
        final CustomerDb customerDb = new CustomerDb();
        customerDb.setId(0L);
        customerDb.setName("name");
        customerDb.setUsername("username");
        customerDb.setFirstName("firstName");
        customerDb.setLastName("lastName");
        final List<CustomerDb> customerDbs = List.of(customerDb);
        when(mockDbRepository.findAll()).thenReturn(customerDbs);

        when(mockMapper.mapToDomain(any(CustomerDb.class))).thenReturn(Customer.builder().build());

        // WHEN
        final List<Customer> result = customerRepositoryImplUnderTest.getAll();

        // THEN
        assertEquals(customerDbs.size(), result.size());
    }

    @Test
    void testGetAll_CustomerDbRepositoryReturnsNoItems() {
        // GIVEN
        when(mockDbRepository.findAll()).thenReturn(Collections.emptyList());

        // WHEN
        final List<Customer> result = customerRepositoryImplUnderTest.getAll();

        // THEN
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetById() {
        // GIVEN
        final CustomerDb customerDb1 = new CustomerDb();
        customerDb1.setId(0L);
        customerDb1.setName("name");
        customerDb1.setUsername("username");
        customerDb1.setFirstName("firstName");
        customerDb1.setLastName("lastName");
        final Optional<CustomerDb> customerDb = Optional.of(customerDb1);
        when(mockDbRepository.findById(0L)).thenReturn(customerDb);


        Customer customer = Customer.builder().build();
        customer.setId(0L);
        when(mockMapper.mapToDomain(any(CustomerDb.class))).thenReturn(customer);

        // WHEN
        final Customer result = customerRepositoryImplUnderTest.getById(0L);

        // THEN
        assertEquals(customerDb.get().getId(), result.getId());
    }

    @Test
    void testDeleteById() {
        // WHEN
        customerRepositoryImplUnderTest.deleteById(0L);

        // THEN
        verify(mockDbRepository).deleteById(0L);
    }

    @Test
    void testSave() {
        // GIVEN
        final Customer customer = Customer.builder().build();

        final CustomerDb customerDb = new CustomerDb();
        customerDb.setId(0L);
        customerDb.setName("name");
        customerDb.setUsername("username");
        customerDb.setFirstName("firstName");
        customerDb.setLastName("lastName");
        when(mockMapper.mapFromDomain(any(Customer.class))).thenReturn(customerDb);

        final CustomerDb customerDb1 = new CustomerDb();
        customerDb1.setId(0L);
        customerDb1.setName("name");
        customerDb1.setUsername("username");
        customerDb1.setFirstName("firstName");
        customerDb1.setLastName("lastName");
        when(mockDbRepository.save(any(CustomerDb.class))).thenReturn(customerDb1);

        Customer customer1 = Customer.builder().build();
        customer1.setId(0L);
        when(mockMapper.mapToDomain(any(CustomerDb.class))).thenReturn(customer1);

        // WHEN
        final Customer result = customerRepositoryImplUnderTest.save(customer);

        // THEN
        assertEquals(customerDb.getId(), result.getId());
    }
}
