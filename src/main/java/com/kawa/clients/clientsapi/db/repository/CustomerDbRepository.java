package com.kawa.clients.clientsapi.db.repository;

import com.kawa.clients.clientsapi.db.models.CustomerDb;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository de la couche DB, il assure les connexions avec la base de données directement.
 */
@Repository
public interface CustomerDbRepository extends CrudRepository<CustomerDb, Long> {

    /**
     * Lecture de tous les clients présents en BDD.
     * @return la liste de tous les clients.
     */
    @NonNull
    List<CustomerDb> findAll();
}
