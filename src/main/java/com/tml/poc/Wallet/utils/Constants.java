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
	public static final String ENCRYPTION_SECRETKEY="xr726wHFRL+Q0qozgtzNqA==";
	public static final byte[] IV_PARAM_SPEC={12,34,54,78,95,90,34,32,36,24,10,40,38,42,06,03};
	public static final String ENCRYPTION_ALGO = "AES/CBC/PKCS5Padding";



	/**
	 * transactional Constants
	 */
	public static final String INSUFFICIENT_BALANCE="Insufficient Balance";
	public static final String TRANSACTION_FAILED="Transaction Getting Failed";


	public static final String TRANSACTION_COMPLETED_SUCCESSFULLY = "Transaction Completed Successfully";


	/**
	 * fcm Constants
	 */
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


	/**
	 * OTP Constants
	 */
	public static final String OTP_NOT_MATCHED="OTP you entered is Invalid. Please enter correct OTP.";
	public static final String OTP_NOT_FOUND="OTP Not Found";
	public static final String OTP_EXPIRED="OTP is Expired";

	/**
	 * M-PIN
	 */
	public static final String MPIN_ALREADY_CREATED="M-PIN Created already";
	public static final String MPIN_NOT_FOUND="M-PIN Not Found";
	public static final String MPIN_CHANGED_SUCCESFULLY="M-PIN Changed Successfully";

	/**
	 * Role
	 */
	public static final String ROLE_ALREADY_ADDED="Role Code Already Added";
	public static final String ROLE_NOT_FOUND="Role Not Found";
	public static final String ROLE_ADDED_SUCCESSFULLY="Role Added Successfully";
	public static final String ROLE_DELETED_SUCCESSFULLY="Role DELETED Successfully";

	/**
	 * kyc
	 */
	public static final String KYC_DOCUMENT_NOT_FOUND="KYC Document not Found";

	/**
	 * Wallet balance
	 */
	public static final String WALLET_BALLANCE_SUCCESS="Wallet balance found Successfully";


	/**
	 * Validation
	 */
	public static final String VALID_EMAILID="Enter Valid EmailId";
	public static final String ENTER_VALUE="Enter Value";
	public static final String USER_NOT_FOUND = "User not found";
}
