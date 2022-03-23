package com.rentopia.model.core.config;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FirebaseConfig {
	
	@Bean
	public void initFirebaseAppBean() throws Exception {
		try {
			InputStream firebaseCredential = this.getFirebaseCredential();
 
			FirebaseOptions options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(firebaseCredential))
			  .build();
	
			if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
			
			log.info("firebase init success");
	        
        }catch(Exception e) {
        	log.error(e.toString());
        	throw e;
        }
	}
	
	
	/**
	 * get firebase-credentil from project folder or from env variable value
	 * @return InputStream
	 * @throws Exception
	 */
	private InputStream getFirebaseCredential() throws Exception {
		InputStream firebaseCredential = null;
		try {
			/*
			 * 1st: read credential from folder (for develop)
			 */
			// when reading a file under resources folder, we need to put a slash in front of the file path
	        firebaseCredential = this.getClass().getResourceAsStream("/credentials/firebase-credential.json");
	        log.info("firebaseCredential:" + firebaseCredential);
	        
	        if(firebaseCredential!=null) {
	        	return firebaseCredential;
	        }
	        
			
	        /*
	         * 2nd: read credential from environment (for build and runtime)
	         */
	        Map<String, String> env = System.getenv();
	        for (String envName : env.keySet()) {
	            System.out.format("%s=%s%n",
	                              envName,
	                              env.get(envName));
	        }
	        
	        String firebaseCredentialStringInBase64 = System.getenv().getOrDefault("firebase-credential", "");
			log.info("firebaseCredentialStringInBase64 = " + firebaseCredentialStringInBase64);
			
			if(!"".equals(firebaseCredentialStringInBase64)) {
				String firebaseCredentialString = new String(Base64.getDecoder().decode(firebaseCredentialStringInBase64), "utf-8");
				log.info("firebaseCredentialString = " + firebaseCredentialString);
			
				firebaseCredential = new ByteArrayInputStream(firebaseCredentialString.getBytes());
				log.info("firebaseCredential:" + firebaseCredential);
				
				return firebaseCredential;
			}
			
			
			/*
			 * 3rd: no any credential to use
			 */
			throw new Exception("error: no firebase-credential.json file or env variable");
			
		}catch(Exception e) {
			log.error(e.toString());
			throw e;
		}
	}

}
