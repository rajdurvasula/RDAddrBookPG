package com.microsoft.azure.sb.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RdAddrBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdAddrBookApplication.class, args);
	}
}
