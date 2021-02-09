package com.tml.poc.Wallet.firebase;

import com.google.firebase.messaging.Notification;
import com.tml.poc.Wallet.models.notification.PushNotificationRequest;

/**
 * Utility class to build a FCM Notification
 */
public class FCMNotificationBuilderUtil {

    /**
     * Builds a notification using information from @{@link PushNotificationRequest} object sent as @param
     * @param request - The @{@link PushNotificationRequest} containing title,message and image url for
     *                the notification
     * @return A @{@link Notification} object
     */
    public Notification buildNotification(PushNotificationRequest request){
        Notification.Builder notificationBuilder = Notification.builder()
                .setTitle(request.getTitle())
                .setBody(request.getMessage());
        if(!request.getImageUrl().isEmpty())
            notificationBuilder.setImage(request.getImageUrl());
        return notificationBuilder.build();
    }
}
