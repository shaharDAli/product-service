package com.ms.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest
class DemoApplicationTests {
	
	MongoDBContainer mongoDBContainer = new MongoDBContainer("7.0.12");

	@Test
	void contextLoads() {
	}

}
