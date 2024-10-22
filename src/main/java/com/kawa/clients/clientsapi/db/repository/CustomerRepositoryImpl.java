package com.kawa.clients.clientsapi.db.repository;

import com.kawa.clients.clientsapi.db.models.CustomerDb;
import com.kawa.clients.clientsapi.db.port.mapper.CustomerMapper;
import com.kawa.clients.clientsapi.domain.ports.CustomerRepository;
import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implémentation du repository de la couche domaine.
 * Elle permet d'implémenter les méthodes assurant les opérations métier - DB.
 * ELle implémente les mapper assurant la transformation des objets DB - DTO.
 */
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

    @Override
    public Customer getById(Long id) {
        CustomerDb customerDb = dbRepository.findById(id).orElse(null);
        return mapper.mapToDomain(customerDb);
    }

    @Override
    public void deleteById(Long id) {
        dbRepository.deleteById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return mapper.mapToDomain(dbRepository.save(mapper.mapFromDomain(customer)));
    }
}
