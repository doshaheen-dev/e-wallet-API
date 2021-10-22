package com.tml.poc.Wallet.restController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tml.poc.Wallet.exception.InvalidInputException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.usermodels.UserRegistrationModel;
import com.tml.poc.Wallet.services.UserService;
import com.tml.poc.Wallet.utils.Constants;

/**
 * Register and Verify Mobile User
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserRegistrationController {
	
	@Autowired
	private UserService userService;


	/**
	 * Mobile User Registration
	 * @param userRegistrationModel
	 * @return
	 * @throws InvalidInputException
	 * @throws ResourceNotFoundException
	 */
	@PostMapping("/register")
	public Object getUserRegister(@Valid @RequestBody UserRegistrationModel userRegistrationModel) 
			throws InvalidInputException, ResourceNotFoundException {
		
		return userService.doUserRegstration(userRegistrationModel);
	}

	/**
	 * mobile USer Verification by OTP
	 * @param userLoginModule
	 * @return
	 * @throws InvalidInputException
	 * @throws ResourceNotFoundException
	 */
	@PostMapping("/verify")
	public ResponseEntity getUserRegisterVerify(@Valid @RequestBody UserRegistrationModel userLoginModule) 
		throws InvalidInputException, ResourceNotFoundException {

		return userService.doUserRegistrationVerification(userLoginModule);
	}

	/**
	 * OTP Resend to user Credit
	 * @param userCred
	 * @return
	 * @throws InvalidInputException
	 * @throws ResourceNotFoundException
	 */
	@PostMapping("/otp/resend/{userCred}")
	public ResponseEntity getUserRegisterVerify( @PathVariable("userCred") String userCred) 
		throws InvalidInputException, ResourceNotFoundException {

		return userService.doResendOTP(userCred);
	}
			
}
