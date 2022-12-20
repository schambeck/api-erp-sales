package com.schambeck.erp.sales.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.schambeck.erp.sales")
public class SalesApplication {
	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
}
