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

    /**
     * Lecture d'un client par ID.
     * @param id identifiant du client.
     * @return un client.
     */
    Customer getById(Long id);

    /**
     * Supprime un client à travers son ID.
     * @param id identifiant du client.
     */
    void deleteById(Long id);

    /**
     * Enregistre un client en BDD.
     * @param customer client.
     */
    void save(Customer customer);
}
