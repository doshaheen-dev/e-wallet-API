package com.tml.poc.Wallet.models.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "account")
public class AccountModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Please provide a Account Name")
	private String accountname;
	@NotEmpty(message = "Please provide a Account URL")
	private String accountURL;
	@NotEmpty(message = "Please provide a Account Mobile")
	private String accountMobile;
	@NotEmpty(message = "Please provide a Account Email id")
	private String accountEmail;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAccountURL() {
		return accountURL;
	}

	public void setAccountURL(String accountURL) {
		this.accountURL = accountURL;
	}

	public String getAccountMobile() {
		return accountMobile;
	}

	public void setAccountMobile(String accountMobile) {
		this.accountMobile = accountMobile;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

}
