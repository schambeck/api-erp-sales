package com.schambeck.erp.sales.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SalesApplicationTests {
	@Autowired
	private SalesApplication context;

	@Test
	void contextLoads() {
		assertNotNull(context);
	}
}
