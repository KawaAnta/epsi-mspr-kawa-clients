package com.kawa.clients.clientsapi.domain.port;

import com.kawa.clients.generated.api.model.CustomerDto;

import java.util.List;

public interface CustomerRepository {
    List<CustomerDto> findAll();
}
