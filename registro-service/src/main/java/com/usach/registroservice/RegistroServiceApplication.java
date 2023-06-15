package com.usach.registroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RegistroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistroServiceApplication.class, args);
	}

}
