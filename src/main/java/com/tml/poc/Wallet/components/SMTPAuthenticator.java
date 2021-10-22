package com.tml.poc.Wallet.components;

import javax.mail.PasswordAuthentication;

import org.springframework.stereotype.Component;


/**
 * Configuration of
 * SMTP Authentication For Email
 */
@Component
public class SMTPAuthenticator extends javax.mail.Authenticator {
	private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";
    private static final String SMTP_AUTH_USER = "your_sendgrid_username";
    private static final String SMTP_AUTH_PWD = "your_sendgrid_password";

	public PasswordAuthentication getPasswordAuthentication() {
		String username = SMTP_AUTH_USER;
		String password = SMTP_AUTH_PWD;
		return new PasswordAuthentication(username, password);
	}
}