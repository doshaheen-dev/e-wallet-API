package com.tml.poc.Wallet.services;

import com.google.firebase.messaging.*;
import com.tml.poc.Wallet.firebase.FCMMessageBuilder;
import com.tml.poc.Wallet.models.notification.PushNotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.tml.poc.Wallet.utils.Constants.*;

/**
 * The Service class responsible for sending the push notifications using Firebase Messaging
 * This class is internally used by @{@link PushNotificationService}
 */
@Service
public class FCMService {

    Logger logger = LoggerFactory.getLogger(FCMService.class);

    @Autowired
    FCMMessageBuilder fcmMessageBuilder;

    /**
     * Send a unicast message using Firebase Messaging
     *
     * @param request - Identifies the details of the notification request like title,message,tokens,data
     *                request MUST CONTAIN ONE TOKEN IN THE LIST
     */
    public void sendUnicastMessage(PushNotificationRequest request) {
        try {
            Message message = fcmMessageBuilder.getPreconfiguredUnicastMessage(request);
            String response = sendUnicastAndGetResponse(message);
            logger.info(FCM_UNICAST_SUCCESS_MESSAGE + response);
        } catch (InterruptedException | ExecutionException e) {
            logger.info(FCM_UNICAST_ERROR_MESSAGE + e.getMessage());
        }
    }

    /**
     * Send a multicast message using Firebase Messaging
     *
     * @param request - Identifies the details of the notification request like title,message,tokens,data
     *                request MUST CONTAIN MULTIPLE NON-NULL TOKENS IN THE LIST
     */
    public void sendMulticastMessage(PushNotificationRequest request) {
        try {
            MulticastMessage multicastMessage = fcmMessageBuilder.getPreconfiguredMulticastMessage(request);
            BatchResponse batchResponse = sendMulticastAndGetResponse(multicastMessage);
            logger.info(batchResponse.getSuccessCount() + FCM_MULTICAST_SUCCESS_MESSAGE);
        } catch (FirebaseMessagingException e) {
            logger.info(FCM_MULTICAST_ERROR_MESSAGE + e.getMessage());
        }
    }

    /**
     * Sends a message to subscribers of a Topic
     *
     * @param request - Identifies the details of the notification request like topic,title,message,data
     *                request MUST CONTAIN A TOPIC. TOKENS ARE NOT NECESSARY HERE
     */
    public void sendMessageToSubscribersOfTopic(PushNotificationRequest request) {
        try {
            Message message = fcmMessageBuilder.getPreconfiguredTopicMessage(request);
            String response = sendTopicMessageAndGetResponse(message);
            logger.info(FCM_TOPIC_SUCCESS_MESSAGE + response);
        } catch (FirebaseMessagingException e) {
            logger.info(FCM_TOPIC_ERROR_MESSAGE + e.getMessage());
        }
    }

    /**
     * Subscribes registration tokens to a topic
     * @param tokens - List of registration tokens
     * @param topic - Topic to subscribe to
     */
    public void subscribeTokensToTopic(List<String> tokens,String topic){
        try {
            TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(tokens, topic);
            logger.info(response.getSuccessCount()  + FCM_SUBSCRIPTION_SUCCESS_MESSAGE);
        }catch (FirebaseMessagingException e) {
            logger.info(FCM_SUBSCRIPTION_ERROR_MESSAGE + e.getMessage());
        }
    }

    /**
     * Unsubscribes registration tokens from a topic
     * @param tokens - List of registration tokens
     * @param topic - Topic to unsubscribe from
     */
    public void unsubscribeTokensFromTopic(List<String> tokens,String topic){
        try {
            TopicManagementResponse response = FirebaseMessaging.getInstance().unsubscribeFromTopic(tokens, topic);
            logger.info(response.getSuccessCount()  + FCM_UNSUBSCRIPTION_SUCCESS_MESSAGE);
        }catch (FirebaseMessagingException e) {
            logger.info(FCM_UNSUBSCRIPTION_ERROR_MESSAGE + e.getMessage());
        }
    }

    /**
     * Use Firebase Messaging and send a Unicast @{@link Message} and return the response
     * @param message - The @{@link Message} object to be sent.
     * @return Response in String format after sending the message
     * @throws ExecutionException  thrown when attempting to retrieve the result of a task that aborted by throwing an exception
     * @throws InterruptedException if the thread is waiting,sleeping, occupied and the thread is interrupted
     */
    private String sendUnicastAndGetResponse(Message message) throws ExecutionException, InterruptedException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    /**
     * Use Firebase Messaging and send a @{@link MulticastMessage} to multiple users and return the response
     * @param message - The @{@link MulticastMessage} object to be sent
     * @return BatchResponse which indicates success count after sending message
     * @throws FirebaseMessagingException if internal error in firebase messaging library occurs
     */
    private BatchResponse sendMulticastAndGetResponse(MulticastMessage message) throws FirebaseMessagingException {
        return FirebaseMessaging.getInstance().sendMulticast(message);
    }

    /**
     * Use Firebase Messaging and send a @{@link Message} with a topic so that only subscribers are notified
     * @param message - The @{@link Message} object to be sent.
     * @return Response in String format after sending the message
     * @throws FirebaseMessagingException if internal error in firebase messaging library occurs
     */
    private String sendTopicMessageAndGetResponse(Message message) throws FirebaseMessagingException {
        return FirebaseMessaging.getInstance().send(message);
    }

}
