package com.kawa.clients.clientsapi.db.repository;

import com.kawa.clients.clientsapi.db.models.CustomerDb;
import com.kawa.clients.clientsapi.db.port.mapper.CustomerMapper;
import com.kawa.clients.clientsapi.domain.ports.CustomerRepository;
import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerDbRepository dbRepository;
    private final CustomerMapper mapper;

    @Override
    @Transactional
    public List<Customer> getAll() {
        List<CustomerDb> customers = dbRepository.findAll();
        return customers.stream().map(mapper::mapToDomain).toList();

    }
}
