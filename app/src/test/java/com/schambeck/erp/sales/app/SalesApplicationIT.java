package com.schambeck.erp.sales.app;

import com.schambeck.erp.sales.app.config.RabbitTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(RabbitTestConfiguration.class)
class SalesApplicationIT {
	@Autowired
	private SalesApplication context;

	@Test
	void contextLoads() {
		assertNotNull(context);
	}
}
