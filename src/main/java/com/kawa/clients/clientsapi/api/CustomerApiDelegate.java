package com.kawa.clients.clientsapi.api;

import com.kawa.clients.clientsapi.domain.service.customer.CustomerService;
import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;
import com.kawa.clients.generated.api.model.CustomerDto;
import com.kawa.clients.generated.api.server.CustomersApiDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
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
        } catch (Exception exception) {
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
        } catch (Exception exception) {
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
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Override
    public ResponseEntity<String> createCustomer(@Valid CustomerDto customerDto) {
        try {
            Customer customer = new Customer();
            customer.setName(customerDto.getName());
            customer.setUsername(customerDto.getUsername());
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setCreatedAt(LocalDateTime.now());
            customer.setPostalCode(customerDto.getPostalCode());
            customer.setCity(customerDto.getCity());
            customer.setProfileFirstName(customerDto.getProfileFirstName());
            customer.setProfileLastName(customerDto.getProfileLastName());
            customer.setCompanyName(customerDto.getCompanyName());

            Customer savedCustomer = customerService.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer.getId().toString());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erreur inattendue.");
        }
    }

    @Override
    public ResponseEntity<Void> updateCustomer(Long id, CustomerDto customerDto) {
        try {
            // Récupérer le client existant avec l'ID spécifié
            Customer customer = customerService.getById(id);

            // Récupérer la liste de tous les IDs des clients existants
            List<Customer> customerList = customerService.getAll();
            List<Long> ids = new ArrayList<>();
            for (Customer existingCustomer : customerList) {
                ids.add(existingCustomer.getId());
            }

            // Vérifier si l'ID spécifié ne correspond à aucun des IDs existants
            if (!ids.contains(id)) {
                return ResponseEntity.notFound().build();
            }

            // Copier les propriétés non null de customerDto vers customer
            copyProperties(customerDto, customer);

            // Enregistrer les modifications
            customerService.save(customer);

            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    /**
     * Vérifie les champs mentionnés dans la requête API pour les modifier dans l'objet client.
     *
     * @param dto la source
     * @param target l'objet client en BDD
     */
    private void copyProperties(CustomerDto dto, Customer target) throws IllegalAccessException {
        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(dto);
            if (value != null) {
                Field targetField;
                try {
                    targetField = target.getClass().getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, value);
                } catch (NoSuchFieldException ignored) {
                }
            }
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
