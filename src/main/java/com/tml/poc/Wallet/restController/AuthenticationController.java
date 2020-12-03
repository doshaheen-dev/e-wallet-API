package com.tml.poc.Wallet.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.jwt.resorce.AuthenticationException;
import com.tml.poc.Wallet.models.UserRegistrationModel;
import com.tml.poc.Wallet.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	/**
	 * customer login req for OTP
	 * @param userRegistrationModel
	 * @return
	 */
	@PostMapping("/mobile")
	private Object doMobileLoginCall(@RequestBody UserRegistrationModel userRegistrationModel) throws 
	ResourceNotFoundException
	 {
		return authenticationService.doUserAuthenticationByMobile(userRegistrationModel);
	}

	
	/**
	 * Customer Verificcation by OTP
	 * Note:SMS Gate way is remain
	 * @param userRegistrationModel
	 * @return
	 */
	@PostMapping("/verify")
	private Object doEmailLoginCallVerify(@RequestBody UserRegistrationModel userRegistrationModel)  {
		
		
		return ResponseEntity.ok(authenticationService.doUserAuthenticationVerification(userRegistrationModel));
		
	}

}
