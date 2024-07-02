package com.kawa.clients.clientsapi.client;

import com.kawa.clients.clientsapi.dto.ApiResponse;
import com.kawa.clients.clientsapi.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Client Broker lié au microservice des commandes
 */
@FeignClient(name = "order-service")
public interface OrderClient {

    @GetMapping("/internal/api/v1/orders/{orderId}")
    ApiResponse<OrderDto> getOrder(@PathVariable Long orderId);
}