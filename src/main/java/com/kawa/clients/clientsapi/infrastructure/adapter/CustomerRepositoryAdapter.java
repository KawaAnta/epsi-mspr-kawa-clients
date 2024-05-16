package com.kawa.clients.clientsapi.infrastructure.adapter;

import com.kawa.clients.clientsapi.domain.model.Customer;
import com.kawa.clients.clientsapi.domain.port.CustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepositoryAdapter extends JpaRepository<Customer, Long>, CustomerRepository {
    @Override
    List<Customer> findAll();
}
