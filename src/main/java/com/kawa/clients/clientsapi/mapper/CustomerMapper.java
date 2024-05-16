package com.kawa.clients.clientsapi.mapper;

import com.kawa.clients.clientsapi.domain.model.Customer;
import com.kawa.clients.generated.api.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);

}
