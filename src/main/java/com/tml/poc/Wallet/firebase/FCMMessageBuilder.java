package com.tml.poc.Wallet.firebase;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.tml.poc.Wallet.models.notification.PushNotificationRequest;
import com.tml.poc.Wallet.services.FCMService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class responsible for building the message and multicast message objects for @{@link FCMService}
 */
public class FCMMessageBuilder {

    @Autowired
    FCMAndroidConfigCreator fcmAndroidConfigCreator;

    @Autowired
    FCMAppleConfigCreator fcmAppleConfigCreator;

    @Autowired
    FCMNotificationBuilderUtil fcmNotificationBuilderUtil;

    /**
     * Configures and returns a Unicast Message using request param
     * Adds the Android,APNS config and the Token in request(since it is unicast)
     * Adds payload data if present in the request
     * Adds a notification object with title,message and image if required
     * @param request - The @{@link PushNotificationRequest} object with title,message token
     * @return A configured @{@link Message} object that can be sent to a single user with given token
     */
    public Message getPreconfiguredUnicastMessage(PushNotificationRequest request) {
        AndroidConfig androidConfig = fcmAndroidConfigCreator.getAndroidConfig();
        ApnsConfig apnsConfig = fcmAppleConfigCreator.getApnsConfig();
        Message.Builder messageBuilder = Message.builder();
        if (payloadDataPresentInRequest(request))
            messageBuilder.putAllData(request.getData());
        return messageBuilder
                .setAndroidConfig(androidConfig)
                .setApnsConfig(apnsConfig)
                .setToken(request.getTokenList().get(0))
                .setNotification(fcmNotificationBuilderUtil.buildNotification(request))
                .build();
    }

    /**
     * Configures and returns a Multicast Message using request param
     * Adds the Android,APNS config and the list of tokens in request(since it is multicast)
     * Adds payload data if present in the request
     * Adds a notification object with title,message and image if required
     * @param request - The @{@link PushNotificationRequest} object with title,message token
     * @return A configured @{@link MulticastMessage} object that can be sent to a multiple users
     * whos tokens are in the list
     */
    public MulticastMessage getPreconfiguredMulticastMessage(PushNotificationRequest request) {
        AndroidConfig androidConfig = fcmAndroidConfigCreator.getAndroidConfig();
        ApnsConfig apnsConfig = fcmAppleConfigCreator.getApnsConfig();
        MulticastMessage.Builder multicastMessageBuilder = MulticastMessage.builder();
        if (payloadDataPresentInRequest(request))
            multicastMessageBuilder.putAllData(request.getData());
        return multicastMessageBuilder
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .addAllTokens(request.getTokenList())
                .setNotification(fcmNotificationBuilderUtil.buildNotification(request))
                .build();
    }

    /**
     * Configures and returns a Message using request param
     * Adds the Android,APNS config and the Topic in request
     * Adds payload data if present in the request
     * Adds a notification object with title,message and image if required
     * @param request - The @{@link PushNotificationRequest} object with title,message token
     * @return A configured @{@link Message} object that can be sent to a user subscribed to specified topic
     */
    public Message getPreconfiguredTopicMessage(PushNotificationRequest request){
        AndroidConfig androidConfig = fcmAndroidConfigCreator.getAndroidConfig();
        ApnsConfig apnsConfig = fcmAppleConfigCreator.getApnsConfig();
        Message.Builder messageBuilder = Message.builder();
        if (payloadDataPresentInRequest(request))
            messageBuilder.putAllData(request.getData());
        return messageBuilder
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .setTopic(request.getTopic())
                .setNotification(fcmNotificationBuilderUtil.buildNotification(request))
                .build();
    }

    /**
     * Check if the map data in @{@link PushNotificationRequest} is empty or not
     * @param request - The @{@link PushNotificationRequest} object containing payload data
     * @return True if data in request is not empty; false otherwise
     */
    private boolean payloadDataPresentInRequest(PushNotificationRequest request) {
        return !MapUtils.isEmpty(request.getData());
    }
}
