package com.tml.poc.Wallet;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.tml.poc.Wallet.config.AzureBlobProperties;

@SpringBootApplication
@EnableConfigurationProperties({AzureBlobProperties.class})
public class WalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}
		
}
