package com.tml.poc.Wallet.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static com.tml.poc.Wallet.utils.Constants.FCM_ADMIN_SDK_JSON_PATH;

/**
 * Class responsible for initializing the firebase library when app is started
 */
@Service
public class FCMInitializer {

    Logger logger = LoggerFactory.getLogger(FCMInitializer.class);

    /**
     * Method to initialize the FirebaseApp using credentials in JSON file for Admin SDK
     */
    @PostConstruct
    public void initialize() {
        try {
            FirebaseOptions.Builder builder = FirebaseOptions.builder();
            FirebaseOptions options = builder.setCredentials(
                    GoogleCredentials.fromStream(
                            new ClassPathResource(FCM_ADMIN_SDK_JSON_PATH).getInputStream()
                    )).build();
            if(FirebaseApp.getApps().isEmpty()){
                FirebaseApp.initializeApp(options);
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
