package com.medical.appointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.medical.appointment.repository"})
@EnableDiscoveryClient
public class Main8081 {
    public static void main(String[] args) {

        SpringApplication.run(Main8081.class,args);
    }
}