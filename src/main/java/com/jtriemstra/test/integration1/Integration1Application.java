package com.jtriemstra.test.integration1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class Integration1Application {

	public static void main(String[] args) {
		SpringApplication.run(Integration1Application.class, args);
	}

}
