package com.tml.poc.Wallet.firebase;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * Class responsible for creating Android configurations to be used by @{@link FCMMessageBuilder}
 */
@Service
public class FCMAndroidConfigCreator {

    /**
     * Creates an Android Notification configuration
     * Can set parameters such as sound, notification color etc
     * @return @{@link AndroidConfig} object
     */
    public AndroidConfig getAndroidConfig() {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis())
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                        .setSound(NotificationParameter.SOUND.getValue())
                        .setColor(NotificationParameter.COLOR.getValue())
                        .setPriority(AndroidNotification.Priority.HIGH)
                        .build())
                .build();
    }

}
