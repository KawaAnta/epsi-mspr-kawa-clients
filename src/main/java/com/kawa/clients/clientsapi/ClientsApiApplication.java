package com.kawa.clients.clientsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kawa.clients.*"})
public class ClientsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientsApiApplication.class, args);
	}

}
