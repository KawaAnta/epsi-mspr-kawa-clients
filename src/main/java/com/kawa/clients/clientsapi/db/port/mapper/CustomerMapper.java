package com.kawa.clients.clientsapi.db.port.mapper;


import com.kawa.clients.clientsapi.db.models.CustomerDb;
import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CustomerMapper {

    /**
     * Transforme l'objet DB en DTO
     *
     * @param entity l'objet DB
     * @return l'objet DTO
     */
    Customer mapToDomain(CustomerDb entity);

    /**
     * Transforme l'objet DTO en DB
     *
     * @param dto l'objet DTO
     * @return l'objet DB
     */
    CustomerDb mapFromDomain(Customer dto);

    /**
     * Tranforme une liste d'objets DB en liste d'objets DTO
     *
     * @param entities liste des objets DB
     * @return liste des objets DTO
     */
    List<Customer> mapListToDomain(List<CustomerDb> entities);
}
