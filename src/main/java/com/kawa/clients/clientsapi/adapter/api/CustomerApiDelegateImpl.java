package com.kawa.clients.clientsapi.adapter.api;

import com.kawa.clients.clientsapi.application.service.CustomerService;
import com.kawa.clients.generated.api.model.CustomerDto;
import com.kawa.clients.generated.api.server.CustomersApiDelegate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerApiDelegateImpl implements CustomersApiDelegate {

    private CustomerService customerService;

    @Override
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

}
