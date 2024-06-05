package com.kawa.clients.clientsapi.domain.service.customer;

import com.kawa.clients.clientsapi.domain.service.customer.dto.Customer;

import java.util.List;

/**
 * Service des clients, contient toutes les méthodes de traitement des données clients en BDD.
 */
public interface CustomerService {

    /**
     * Lecture de tous les clients présents en BDD.
     * @return la liste de tous les clients.
     */
    List<Customer> getAll();

    /**
     * Lecture d'un client à partir de son identifiant.
     * @param id identifiant du client.
     * @return un client.
     */
    Customer getById(Long id);

    /**
     * Supprime un client à travers son identifiant.
     * @param id identifiant du client à supprimer.
     */
    void deleteById(Long id);
}
