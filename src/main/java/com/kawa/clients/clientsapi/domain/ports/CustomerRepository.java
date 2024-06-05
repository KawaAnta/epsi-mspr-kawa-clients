package com.kawa.clients.clientsapi.domain.ports;

import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;

import java.util.List;

/**
 * Repository de la couche domaine.
 */
public interface CustomerRepository {

    /**
     * Lecture de tous les clients renseignés en BDD.
     * @return la liste de tous les clients.
     */
    List<Customer> getAll();

    Customer getById(Long id);

    void deleteById(Long id);
}
