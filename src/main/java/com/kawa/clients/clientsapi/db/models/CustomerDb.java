package com.kawa.clients.clientsapi.db.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Classe java / DB représentante de la table client / customer
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMERS")
public class CustomerDb {
    @Id
    @Column(name = "CUSTOMER_ID")
    @SequenceGenerator(name = "customerIdGenerator",
            sequenceName = "CUSTOMER_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerIdGenerator")
    private Long id;

    @Column(name = "CUSTOMER_NAME")
    private String name;

    @Column(name = "CUSTOMER_USERNAME")
    private String username;

    @Column(name = "CUSTOMER_FIRSTNAME")
    private String firstName;

    @Column(name = "CUSTOMER_LASTNAME")
    private String lastName;

    @Column(name = "CUSTOMER_CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "ADDRESS_POSTAL_CODE")
    private String postalCode;

    @Column(name = "ADDRESS_CITY")
    private String city;

    @Column(name = "PROFILE_FIRST_NAME")
    private String profileFirstName;

    @Column(name = "PROFILE_LAST_NAME")
    private String profileLastName;

    @Column(name = "COMPANY_NAME")
    private String companyName;
}
