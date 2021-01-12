package com.tml.poc.Wallet.restController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tml.poc.Wallet.components.EmailComponant;
import com.tml.poc.Wallet.exception.InvalidInputException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.jwt.resorce.JwtTokenResponse;
import com.tml.poc.Wallet.models.UserLoginModule;
import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.models.UserRegistrationModel;
import com.tml.poc.Wallet.models.reponse.DataModelResponce;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.services.UserService;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {
	
	@Autowired
	private UserService userService;
	
	

		
	@PostMapping("/register")
	public Object getUserRegister(@Valid @RequestBody UserRegistrationModel userRegistrationModel) 
			throws InvalidInputException, ResourceNotFoundException {
		
		return userService.doUserRegstration(userRegistrationModel);
	}
								
	@PostMapping("/verify")
	public ResponseEntity getUserRegisterVerify(@Valid @RequestBody UserRegistrationModel userLoginModule) 
		throws InvalidInputException, ResourceNotFoundException {

		return userService.doUserRegistrationVerification(userLoginModule);
	}
	
	@PostMapping("/otp/resend/{userCred}")
	public ResponseEntity getUserRegisterVerify( @PathVariable("userCred") String userCred) 
		throws InvalidInputException, ResourceNotFoundException {

		return userService.doResendOTP(userCred);
	}
			
}
