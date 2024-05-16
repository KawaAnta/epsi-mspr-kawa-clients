package com.kawa.clients.clientsapi.db.repository;

import com.kawa.clients.clientsapi.db.models.CustomerDb;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDbRepository extends CrudRepository<CustomerDb, Long> {

    @NonNull
    List<CustomerDb> findAll();
}
