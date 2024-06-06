package com.kawa.clients.clientsapi.api;

import com.kawa.clients.clientsapi.domain.service.customer.CustomerService;
import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;
import com.kawa.clients.generated.api.model.CustomerDto;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerApiDelegateTest {

    @Mock
    private CustomerService mockCustomerService;

    private CustomerApiDelegate customerApiDelegateUnderTest;

    @BeforeEach
    void setUp() {
        customerApiDelegateUnderTest = new CustomerApiDelegate(mockCustomerService);
    }

    @Test
    void testGetAllCustomers() {
        // GIVEN
        final CustomerDto customerDto = getCustomerDto();
        final ResponseEntity<List<CustomerDto>> expectedResult = new ResponseEntity<>(List.of(customerDto),
                HttpStatus.OK);

        final List<Customer> customerList = List.of(Customer.builder()
                .name("name")
                .username("username")
                .firstName("firstName")
                .lastName("lastName")
                .createdAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .postalCode("postalCode")
                .city("city")
                .profileFirstName("profileFirstName")
                .profileLastName("profileLastName")
                .companyName("companyName")
                .build());
        when(mockCustomerService.getAll()).thenReturn(customerList);

        // WHEN
        final ResponseEntity<List<CustomerDto>> result = customerApiDelegateUnderTest.getAllCustomers();

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllCustomers_CustomerServiceReturnsNoItems() {
        // GIVEN
        when(mockCustomerService.getAll()).thenReturn(Collections.emptyList());

        // WHEN
        final ResponseEntity<List<CustomerDto>> result = customerApiDelegateUnderTest.getAllCustomers();

        // THEN
        assertEquals(ResponseEntity.ok(Collections.emptyList()), result);
    }

    /**
     * Construction d'un DTO de customer
     */
    private static @NotNull CustomerDto getCustomerDto() {
        final CustomerDto customerDto = new CustomerDto();
        customerDto.setName("name");
        customerDto.setUsername("username");
        customerDto.setFirstName("firstName");
        customerDto.setLastName("lastName");
        customerDto.setPostalCode("postalCode");
        customerDto.setCity("city");
        customerDto.setProfileFirstName("profileFirstName");
        customerDto.setProfileLastName("profileLastName");
        customerDto.setCompanyName("companyName");
        return customerDto;
    }
}
