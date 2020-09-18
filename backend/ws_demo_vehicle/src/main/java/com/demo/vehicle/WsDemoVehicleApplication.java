package com.demo.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class WsDemoVehicleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsDemoVehicleApplication.class, args);
	}

}
