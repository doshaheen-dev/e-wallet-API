package com.tml.poc.Wallet;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.tml.poc.Wallet.components.EmailComponant;
import com.tml.poc.Wallet.config.AzureBlobProperties;

@SpringBootApplication
@EnableConfigurationProperties({AzureBlobProperties.class})
public class WalletApplication  {
	
	
	@Autowired
    private JavaMailSender javaMailSender;
	@Autowired
	private static  EmailComponant emailCompo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}
	
	
	 

	    
		
}
