package com.tml.poc.Wallet.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class UserLoginModule {

	@NotEmpty
	@NotBlank
	@NotNull
	private String userCred;
	
	private String otp;

	public String getUserCred() {
		return userCred;
	}

	public void setUserCred(String userCred) {
		this.userCred = userCred;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
	
}


