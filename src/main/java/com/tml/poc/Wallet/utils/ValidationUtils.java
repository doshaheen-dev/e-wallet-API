package com.tml.poc.Wallet.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {

	public boolean isValidEmail(String target) {
		System.out.println(target);
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(ApplicationConstants.EMAIL_PATTERN);
		matcher = pattern.matcher(target);
		return matcher.matches();
	}

	public boolean isValidPassword(String password) {
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(ApplicationConstants.PASSWORD_PATTERN);
		matcher = pattern.matcher(password);
		return matcher.matches();

	}

	public boolean isMobileNumber(String mobile) {
		System.out.println(mobile);
		try {
			long numberLong = Long.parseLong(mobile);
			return true;
		} catch (Exception e) {
			
			return false;
		}
	}

}
