package com.rentopia.model.core.controller;

import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.rentopia.model.core.entity.User;

import lombok.extern.slf4j.Slf4j;


/*
 * 使用 @RestController 等於使用了 @Controller 和 @ResponseBody
 */
@Slf4j
@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String sayHello() {
		
		return "hello";
	}
	
	
	/*
	 * test string
	 * http://localhost:8080/firebase
	 * 
	 */
	@RequestMapping("/firebase")
	public String sayFirebase() {
		String token = "PUT-IDTOKEN-HERE";
		
		log.info(token);
        if ((token == null) || "".equals(token)) {
        	return "error: no token";
        }
        
        FirebaseToken decodedToken = null;
        try {
        	log.info("decode token...");
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            
            log.info("decoded token:" + decodedToken.toString());
    
        } catch (FirebaseAuthException e) {
            log.error("Firebase Exception: ", e.toString());
            return "error: invalid token";
        }
        
        return "hello firebase user:" + decodedToken.getUid();
        
	}
	
	
	
	

}
