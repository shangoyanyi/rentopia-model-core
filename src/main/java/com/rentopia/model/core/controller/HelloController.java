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
	 * test stiring
	 * http://localhost:8080/firebase?token=eyJhbGciOiJSUzI1NiIsImtpZCI6IjUxMDM2YWYyZDgzOWE4NDJhZjQzY2VjZmJiZDU4YWYxYTc1OGVlYTIiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vbXVzaWNwbGF5ZXItOTc3MjYiLCJhdWQiOiJtdXNpY3BsYXllci05NzcyNiIsImF1dGhfdGltZSI6MTU5OTE0Nzk5NSwidXNlcl9pZCI6IjBKcDEyRWlGTnVSQ1RRMHE4V3NoZ0s3RGkydzIiLCJzdWIiOiIwSnAxMkVpRk51UkNUUTBxOFdzaGdLN0RpMncyIiwiaWF0IjoxNTk5MTQ3OTk1LCJleHAiOjE1OTkxNTE1OTUsImVtYWlsIjoidGVzdEB0ZXN0LmNvbSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJlbWFpbCI6WyJ0ZXN0QHRlc3QuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.lLxSEur3aC3Xlw4naaFi8z_hcEgv2V_wqH6vNArDlcslksCvhZJytjNUN75fVJM8H6kto06r9VIz75rxVKorCpadWtqI1muPseBZf9qAptclH4aSXLqAwgO3OMY2p36GakFJ8irdF2Fy_T4gZAGXtnUMMJf8Cf7PsWJQO-Sc4gdEvtT2NvRdPdcCvI-kZdFsJCrHVN5QZ2ifXPW88PzHkq99KWVlcIWxD-LzAmmq28m4QOYB5DNVGGE4mcYpjkneysPZ0u2XxVMjnO8HvwBSBEwEO3ssdz12o2K_Ws7rT_WOTpVwkGTXJ_5rr1yHcx7J8lO4h6kcbx70-zxLaNOGeQ
	 */
	@RequestMapping("/firebase")
	public String sayFirebase(@RequestParam("token") String token) {
        log.info(token);
        if ((token == null) || "".equals(token)) {
        	return "error: no token";
        }
        
        try {
        	// when reading a file under resources folder, we need to push a slash in front of the file path
	        InputStream firebaseCredential = this.getClass().getResourceAsStream("/credentials/firebase-credential.json");
	
	        log.info("firebaseCredential:" + firebaseCredential);
	        
			FirebaseOptions options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(firebaseCredential))
			  .build();
	
			FirebaseApp.initializeApp(options);
			
			log.info("firebase init success");
	        
        }catch(Exception e) {
        	log.error(e.toString());
        	return "error:" + e.toString();
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
