package com.tml.poc.Wallet.models;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WebUserRegistrationModel {
	
	
	
	@NotEmpty
	String emailid;
	
	@NotEmpty
	private String password;

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
	
	

}
