package com.Prosper.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
@Component
public class FBInitialize {

    @PostConstruct
    public void initialize() {
        try {
        	FileInputStream serviceAccount =
        			  new FileInputStream("./service.json");

        			FirebaseOptions options = new FirebaseOptions.Builder()
        			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        			  .setDatabaseUrl("https://fir-db-for-spring-boot-28985-default-rtdb.firebaseio.com/")
        			  .build();

        			FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
