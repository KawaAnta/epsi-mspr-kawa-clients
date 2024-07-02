package com.kawa.clients.clientsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Wrapper de réponse API générique contenant un success flag, message, et data.
 *
 * @param <T> le type de données contenues dans la réponse.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
}