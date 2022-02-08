package com.example.kry.health.healthdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HealthdashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthdashboardApplication.class, args);
	}

}
