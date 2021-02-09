package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.models.notification.PushNotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service provides convenience methods to send a FCM Push Notification to Single, Multiple
 * or Users that have subscribed to a topic.
 * It also provides methods for Topic management ie. subscribe/unsubscribe
 * USE THESE METHODS IN OTHER SERVICES FROM WHERE A PUSH NOTIFICATION NEEDS TO BE SENT
 */
@Service
public class PushNotificationService {

    @Autowired
    private FCMService fcmService;

    /**
     * Sends a push notification to a single user identified by user's token
     * @param request - The request consisting of token, title, message, data and title
     */
    public void sendPushNotificationToSingleUser(PushNotificationRequest request){
        fcmService.sendUnicastMessage(request);
    }

    /**
     * Sends a push notification to multiple users identified by user's token
     * @param request - The request consisting of list of tokens, message, data and title
     */
    public void sendPushNotificationToMultipleUsers(PushNotificationRequest request){
        fcmService.sendMulticastMessage(request);
    }

    /**
     * Sends a push notification to users who have subscribed to a topic (Use for Promotional messages)
     * @param request - The request consisting of title,message,topic
     */
    public void sendPushNotificationToTopicSubscribers(PushNotificationRequest request){
        fcmService.sendMessageToSubscribersOfTopic(request);
    }

    /**
     * Subscribe the specified registration tokens to the specified topic
     * @param tokens - List of tokens that need to be subscribed
     * @param topic - The topic to which the tokens need to be subscribed to
     */
    public void subscribeTokensToTopic(List<String> tokens,String topic){
        fcmService.subscribeTokensToTopic(tokens,topic);
    }

    /**
     * Unsubscribe the specified registration tokens from the specified topic
     * @param tokens - List of tokens that need to be unsubscribed
     * @param topic - The topic from which the tokens need to be unsubscribed from
     */
    public void unsubscribeTokensFromTopic(List<String> tokens,String topic){
        fcmService.unsubscribeTokensFromTopic(tokens,topic);
    }
}
