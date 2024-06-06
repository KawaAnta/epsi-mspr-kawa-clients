package com.kawa.clients.clientsapi.api;

import com.kawa.clients.clientsapi.domain.service.customer.CustomerService;
import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;
import com.kawa.clients.generated.api.model.CustomerDto;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

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

        final Customer customer = getCustomerFromDto(customerDto);

        final List<Customer> customerList = List.of(customer);
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

    @Test
    void testGetCustomerById_shouldGetCustomer() {
        // GIVEN
        final CustomerDto customerDto = getCustomerDto();
        final ResponseEntity<CustomerDto> expectedResult = new ResponseEntity<>(customerDto, HttpStatus.OK);
        final Long id = 1L;

        final Customer customer = getCustomerFromDto(customerDto);
        customer.setId(id);
        when(mockCustomerService.getById(id)).thenReturn(customer);

        // WHEN
        final ResponseEntity<CustomerDto> result = customerApiDelegateUnderTest.getCustomerById(id);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetCustomerById_shouldThrowError() {
        // GIVEN
        final Long id = 1L;
        when(mockCustomerService.getById(id)).thenReturn(null);

        // WHEN
        final ResponseEntity<CustomerDto> result = customerApiDelegateUnderTest.getCustomerById(id);

        // THEN
        assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null), result);
    }

    @Test
    void testDeleteCustomerById_shouldDeleteCustomer() {
        // GIVEN
        final Long id = 1L;
        final Customer customer = getCustomerFromDto(getCustomerDto());
        customer.setId(id);
        when(mockCustomerService.getById(id)).thenReturn(customer);

        // WHEN
        final ResponseEntity<Void> result = customerApiDelegateUnderTest.deleteCustomerById(id);

        // THEN
        assertEquals(ResponseEntity.noContent().build(), result);
    }

    @Test
    void testDeleteCustomerById_shouldThrowError() {
        // GIVEN
        final Long id = 1L;
        when(mockCustomerService.getById(id)).thenReturn(null);

        // WHEN
        final ResponseEntity<Void> result = customerApiDelegateUnderTest.deleteCustomerById(id);

        // THEN
        assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).build(), result);
    }

    @Test
    void testCreateCustomer_shouldCreateCustomer() {
        // GIVEN
        final CustomerDto customerDto = getCustomerDto();

        final Customer customer = getCustomerFromDto(customerDto);
        Long id = 1L;
        customer.setId(id);
        // Use ArgumentCaptor to capture the argument passed to save method
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);

        when(mockCustomerService.save(customerCaptor.capture())).thenReturn(customer);

        // WHEN
        final ResponseEntity<String> result = customerApiDelegateUnderTest.createCustomer(customerDto);

        // THEN
        assertEquals(id.toString(), result.getBody());
    }

    @Test
    void testCreateCustomer_shouldThrowError() {
        // GIVEN
        final CustomerDto customerDto = getCustomerDto();

        final Customer customer = getCustomerFromDto(customerDto);
        when(mockCustomerService.save(customer)).thenReturn(null);

        // WHEN
        final ResponseEntity<String> result = customerApiDelegateUnderTest.createCustomer(customerDto);

        // THEN
        assertEquals(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build().getStatusCode(),
                result.getStatusCode());
    }

    @Test
    void testCreateCustomer_shouldCreateWhenOnlyNameField() {
        // GIVEN
        final CustomerDto customerDto = new CustomerDto();
        customerDto.setName("name");

        final Customer customer = getCustomerFromDto(customerDto);
        Long id = 1L;
        customer.setId(id);
        // Use ArgumentCaptor to capture the argument passed to save method
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);

        when(mockCustomerService.save(customerCaptor.capture())).thenReturn(customer);

        // WHEN
        final ResponseEntity<String> result = customerApiDelegateUnderTest.createCustomer(customerDto);

        // THEN
        assertEquals(id.toString(), result.getBody());
    }

    @Test
    void testCreateCustomer_shouldThrowErrorWhenEmptyDto() {
        // GIVEN
        final CustomerDto customerDto = new CustomerDto();

        // WHEN
        final ResponseEntity<String> result = customerApiDelegateUnderTest.createCustomer(customerDto);

        // THEN
        assertEquals(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build().getStatusCode(),
                result.getStatusCode());
    }

    @Test
    void testUpdateCustomer_shouldUpdateCustomer() {
        // GIVEN
        final CustomerDto customerDto = getCustomerDto();
        final Long id = 1L;

        final Customer customer = getCustomerFromDto(customerDto);
        customer.setId(id);
        when(mockCustomerService.getById(id)).thenReturn(customer);
        when(mockCustomerService.getAll()).thenReturn(List.of(customer));

        // WHEN
        final ResponseEntity<Void> result = customerApiDelegateUnderTest.updateCustomer(id, customerDto);

        // THEN
        assertEquals(ResponseEntity.ok().build(), result);
    }

    @Test
    void testUpdateCustomer_shouldThrowError() {
        // GIVEN
        final CustomerDto customerDto = getCustomerDto();
        final Long id = 1L;

        when(mockCustomerService.getById(id)).thenReturn(null);

        // WHEN
        final ResponseEntity<Void> result = customerApiDelegateUnderTest.updateCustomer(id, customerDto);

        // THEN
        assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).build(), result);
    }

    @Test
    void testUpdateCustomer_shouldNeverCallSaveWhenNoId() {
        // updateCustomer should not invoke save method
        // using mockito verify
        // GIVEN
        final CustomerDto customerDto = getCustomerDto();

        // WHEN
        customerApiDelegateUnderTest.updateCustomer(null, customerDto);

        // THEN
        verify(mockCustomerService, never()).save(any(Customer.class));
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

    private static Customer getCustomerFromDto(CustomerDto customerDto) {
        return Customer.builder()
                .name(customerDto.getName())
                .username(customerDto.getUsername())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .createdAt(LocalDateTime.ofInstant(OffsetDateTime.now().toInstant(), ZoneOffset.UTC))
                .postalCode(customerDto.getPostalCode())
                .city(customerDto.getCity())
                .profileFirstName(customerDto.getProfileFirstName())
                .profileLastName(customerDto.getProfileLastName())
                .companyName(customerDto.getCompanyName())
                .build();
    }
}
