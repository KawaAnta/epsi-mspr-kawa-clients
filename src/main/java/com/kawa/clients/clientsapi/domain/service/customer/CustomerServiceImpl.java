package com.kawa.clients.clientsapi.domain.service.customer;

import com.kawa.clients.clientsapi.domain.ports.CustomerRepository;
import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation du service des clients.
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
