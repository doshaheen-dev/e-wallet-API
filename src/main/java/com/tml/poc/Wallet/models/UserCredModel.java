package com.tml.poc.Wallet.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class UserCredModel {
		
	@NotEmpty
	@NotBlank
	@NotNull
	private String userCred;

	public String getUserCred() {
		return userCred;
	}

	public void setUserCred(String userCred) {
		this.userCred = userCred;
	}

}
