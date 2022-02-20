package com.rentopia.model.core.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloControllerTests {
	@Autowired
    private HelloController helloController;
	
	// 直接對方法進行測試 (未模擬web環境)
	@Test
	void sayHelloTester() throws Exception {
		String actual = helloController.sayHello();
        String expected = "hello";
        Assertions.assertEquals(expected, actual);
	}

}
