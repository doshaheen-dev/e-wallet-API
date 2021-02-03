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
}
