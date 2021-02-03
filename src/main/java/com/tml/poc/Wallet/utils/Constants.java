package com.tml.poc.Wallet.utils;

public class Constants {

	
	public static final String EMAIL_REGEX="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
	public static final String TIME_DATE="yyyy-MM-dd HH:mm:ss";

	public static final String FCM_ADMIN_SDK_JSON_PATH = "google/tml-wallet-firebase-adminsdk.json";
	public static final String FCM_UNICAST_SUCCESS_MESSAGE = "Sent Unicast message to token. Received response : ";
	public static final String FCM_UNICAST_ERROR_MESSAGE = "Sending Unicast message failed due to error: ";
	public static final String FCM_MULTICAST_SUCCESS_MESSAGE = " messages were sent successfully";
	public static final String FCM_MULTICAST_ERROR_MESSAGE = "Sending Multicast message failed due to error: ";
	public static final String FCM_TOPIC_SUCCESS_MESSAGE = "Sent Topic message to subscribers. Received response : ";
	public static final String FCM_TOPIC_ERROR_MESSAGE = "Sending Topic message failed due to error: ";
	public static final String FCM_SUBSCRIPTION_SUCCESS_MESSAGE = " tokens were subscribed successfully";
	public static final String FCM_SUBSCRIPTION_ERROR_MESSAGE = "Subscribing tokens to topic failed due to error: ";
	public static final String FCM_UNSUBSCRIPTION_SUCCESS_MESSAGE = " tokens were unsubscribed successfully";
	public static final String FCM_UNSUBSCRIPTION_ERROR_MESSAGE = "Unsubscribing tokens from topic failed due to error: ";

}
