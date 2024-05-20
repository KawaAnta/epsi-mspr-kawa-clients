package com.kawa.clients.clientsapi.api;

import com.kawa.clients.clientsapi.domain.service.customer.CustomerService;
import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;
import com.kawa.clients.generated.api.model.CustomerDto;
import com.kawa.clients.generated.api.server.CustomersApiDelegate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * Délégué API des clients.
 * Assure la connexion entre l'API Gateway générale et les micro-service des clients.
 */
@Component
@RequiredArgsConstructor
public class CustomerApiDelegate implements CustomersApiDelegate {

    private final CustomerService customerService;

    @Override
    @NonNull
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        final List<Customer> customerList = customerService.getAll();
        final List<CustomerDto> customerDtoList = new ArrayList<>(customerList.size());

        for (final Customer customer : customerList) {
            customerDtoList.add(mapToDto(customer));
        }

        return ResponseEntity.ok(customerDtoList);
    }


    @NotNull
    private static CustomerDto mapToDto(@NotNull final Customer customer) {
        return new CustomerDto(
                customer.getCreatedAt().atOffset(ZoneOffset.UTC),
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
