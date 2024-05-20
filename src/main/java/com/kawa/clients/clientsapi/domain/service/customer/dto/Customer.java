package com.kawa.clients.clientsapi.domain.service.customer.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Classe java / DTO représentante de l'objet client / customer
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private String postalCode;
    private String city;
    private String profileFirstName;
    private String profileLastName;
    private String companyName;
}
