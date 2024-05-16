package com.kawa.clients.clientsapi.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;

    @Embedded
    private Address address;

    @Embedded
    private Profile profile;

    @Embedded
    private Company company;

    @OneToMany(mappedBy = "customerId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

    @Embeddable
    @Data
    public static class Address {
        private String postalCode;
        private String city;
    }

    @Embeddable
    @Data
    public static class Profile {
        private String firstName;
        private String lastName;
    }

    @Embeddable
    @Data
    public static class Company {
        private String companyName;
    }
}
