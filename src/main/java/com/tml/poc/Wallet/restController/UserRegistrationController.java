package com.tml.poc.Wallet.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tml.poc.Wallet.jwt.resorce.JwtTokenResponse;
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
	public Object getUserRegister(@RequestBody UserRegistrationModel userRegistrationModel) {
		
		return ResponseEntity.ok((userService.doUserRegstration(userRegistrationModel)));
	}
	
	@PostMapping("/verify")
	public Object getUserRegisterVerify(@RequestBody UserRegistrationModel userRegistrationModel) {
		
		return ResponseEntity.ok((userService.doUserRegistrationVerification(userRegistrationModel)));
	}
			
}
