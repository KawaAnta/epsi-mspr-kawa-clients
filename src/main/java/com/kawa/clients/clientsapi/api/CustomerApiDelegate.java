package com.kawa.clients.clientsapi.api;

import com.kawa.clients.clientsapi.domain.service.customer.CustomerService;
import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;
import com.kawa.clients.clientsapi.utils.NullAwareBeanUtilsBean;
import com.kawa.clients.generated.api.model.CustomerDto;
import com.kawa.clients.generated.api.server.CustomersApiDelegate;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Délégué API des clients.
 * Assure la connexion entre l'API Gateway générale et les micro-services des clients.
 */
@Component
public class CustomerApiDelegate implements CustomersApiDelegate {

    private final CustomerService customerService;

    public CustomerApiDelegate(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        try {
            final List<Customer> customerList = customerService.getAll();
            final List<CustomerDto> customerDtoList = new ArrayList<>(customerList.size());

            for (final Customer customer : customerList) {
                customerDtoList.add(mapToDto(customer));
            }

            return ResponseEntity.ok(customerDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomerById(Long id) {
        try {
            Customer customer = customerService.getById(id);

            if (customer.getId() == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(mapToDto(customer));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Void> deleteCustomerById(Long id) {
        try {
            Customer customer = customerService.getById(id);

            if (customer.getId() == null) {
                return ResponseEntity.notFound().build();
            }

            customerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Void> createCustomer(@Valid CustomerDto customerDto) {
        try {
            final Customer customer = Customer.builder()
                    .name(customerDto.getName())
                    .username(customerDto.getUsername())
                    .firstName(customerDto.getFirstName())
                    .lastName(customerDto.getLastName())
                    .createdAt(LocalDateTime.now())
                    .postalCode(customerDto.getPostalCode())
                    .city(customerDto.getCity())
                    .profileFirstName(customerDto.getProfileFirstName())
                    .profileLastName(customerDto.getProfileLastName())
                    .companyName(customerDto.getCompanyName())
                    .build();

            customerService.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Void> updateCustomer(Long id, CustomerDto customerDto) {
        try {
            Customer customer = customerService.getById(id);

            if (customer == null) {
                return ResponseEntity.notFound().build();
            }

            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(customer, customerDto);

            customerService.save(customer);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @NotNull
    private static CustomerDto mapToDto(@NotNull final Customer customer) {
        return new CustomerDto(
                customer.getName(),
                customer.getUsername(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPostalCode(),
                customer.getCity(),
                customer.getProfileFirstName(),
                customer.getProfileLastName(),
                customer.getCompanyName()
        );
    }
}
