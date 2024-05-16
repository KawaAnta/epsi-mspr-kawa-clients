package com.kawa.clients.clientsapi.application.service;

import com.kawa.clients.generated.api.model.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
}
