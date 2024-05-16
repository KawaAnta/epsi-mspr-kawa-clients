package com.kawa.clients.clientsapi.domain.ports;

import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> getAll();

}
