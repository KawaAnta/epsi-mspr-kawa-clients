package com.kawa.clients.clientsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {
        "com.kawa.clients.clientsapi",
        "com.kawa.clients.generated.api"
})
public class ClientsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientsApiApplication.class, args);
    }

}
