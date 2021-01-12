package com.tml.poc.Wallet.components;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailComponant {
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	

	 public void sendOTPEmail(String sendTo,String OTP) {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setFrom("vaibhavbhavsar@doshaheen.com");
	        msg.setTo(sendTo);

	        msg.setSubject("TML OTP Verification");
	        msg.setText("This is your OTP for Verification: "+OTP);

	        javaMailSender.send(msg);

	    }
}
