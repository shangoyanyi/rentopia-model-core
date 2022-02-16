package com.rentopia.model.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
 * 使用 @RestController 等於使用了 @Controller 和 @ResponseBody
 */
@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String sayHello() {
		
		return "hello!";
	}

}
