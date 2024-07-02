package com.kawa.clients.clientsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Classe java / DTO représentante de l'objet produit / product
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String price;
    private String description;
    private String color;
    private LocalDateTime createdAt;
    private Long stock;
}
