package com.gorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(GOrderApplication.class, args);
	}

}
