package com.usach.quincenaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class QuincenaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuincenaServiceApplication.class, args);
    }

}
