package com.tml.poc.Wallet.firebase;

import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.CriticalSound;
import org.springframework.stereotype.Service;

/**
 * Class responsible for creating APNS configurations to be used by @{@link FCMMessageBuilder}
 */
@Service
public class FCMAppleConfigCreator {

    /**
     * Creates an Apple Notification Service Configuration
     * Can set parameters such as sound, notification priority, category etc
     * @return @{@link ApnsConfig} object
     */
    public ApnsConfig getApnsConfig() {
        return ApnsConfig.builder()
                .setAps(Aps.builder()
                        .setSound(CriticalSound.builder()
                                .setCritical(true)
                                .setName(NotificationParameter.SOUND.getValue())
                                .build())
                        .build())
                .build();
    }
}
