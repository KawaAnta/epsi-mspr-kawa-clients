package com.kawa.clients.clientsapi.client;

import com.kawa.clients.clientsapi.dto.ApiResponse;
import com.kawa.clients.clientsapi.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Client Broker lié au microservice des produits
 */
@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/internal/api/v1/products/{productId}")
    ApiResponse<ProductDto> getProduct(@PathVariable Long productId);
}