package com.usach.planillapagosservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PlanillapagosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanillapagosServiceApplication.class, args);
	}

}
