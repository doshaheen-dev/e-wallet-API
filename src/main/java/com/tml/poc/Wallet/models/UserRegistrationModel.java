package com.tml.poc.Wallet.models;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserRegistrationModel {
	
	int countrycode;
	@NotEmpty
	String mobileNumber;
	
	@NotEmpty
	String emailid;
	
	private String OTP;
	
	public int getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(int countrycode) {
		this.countrycode = countrycode;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOTP() {
		return OTP;
	}
	public void setOTP(String oTP) {
		OTP = oTP;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
	
	
	
	
	

}
