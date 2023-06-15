package com.usach.grasasolidoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GrasasolidoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrasasolidoServiceApplication.class, args);
	}

}
