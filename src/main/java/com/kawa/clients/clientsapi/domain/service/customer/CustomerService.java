package com.kawa.clients.clientsapi.domain.service.customer;

import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAll();
}
