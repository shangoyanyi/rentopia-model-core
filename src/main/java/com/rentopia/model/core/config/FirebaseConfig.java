package com.rentopia.model.core.config;

import java.io.InputStream;

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
        	// when reading a file under resources folder, we need to push a slash in front of the file path
	        InputStream firebaseCredential = this.getClass().getResourceAsStream("/credentials/firebase-credential.json");
	
	        log.info("firebaseCredential:" + firebaseCredential);
	        
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

}
