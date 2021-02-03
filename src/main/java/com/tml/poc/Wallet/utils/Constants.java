package com.tml.poc.Wallet.utils;

public class Constants {

	/**
	 * generic Constants
	 */
	public static final String EMAIL_REGEX="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
	public static final String TIME_DATE="yyyy-MM-dd'T'HH:mm:ss Z";
/**
	 * security
	 */
	public static final int SALT_COUNT=128;


	/**
	 * transactional Constants
	 */
	public static final String INSUFFICIENT_BALANCE="Insufficient Balance";
	public static final String TRANSACTION_FAILED="Transaction Getting Failed";


	public static final String TRANSACTION_COMPLETED_SUCCESSFULLY = "Transaction Completed Successfully";


	/**
	 * M-PIN
	 */
	public static final String MPIN_ALREADY_CREATED="M-PIN Created already";
	
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
