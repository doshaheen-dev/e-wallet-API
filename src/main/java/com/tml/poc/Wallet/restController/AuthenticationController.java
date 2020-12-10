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
import com.tml.poc.Wallet.models.EmployeeRegistrationModel;
import com.tml.poc.Wallet.models.UserRegistrationModel;
import com.tml.poc.Wallet.services.AuthenticationService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/auth")
@Api(value="Authentication for User and Admin", description="Operations pertaining to products in Online Store")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	
	
	/**
	 * customer login req for OTP
	 * @param userRegistrationModel
	 * @return
	 */
	@PostMapping("/login")
	private Object doLoginCall(@RequestBody UserRegistrationModel userRegistrationModel) throws 
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
	private Object doLoginCallVerify(@RequestBody UserRegistrationModel userRegistrationModel)  {
		
		
		return ResponseEntity.ok(authenticationService.doUserAuthenticationVerification(userRegistrationModel));
		
	}
	
	
	/**
	 * Employee Verificcation by Emailid password
	 * @param employeeRegistrationModel
	 * @return
	 */
	@PostMapping("/employee/login")
	private Object doMobileLoginCall(@RequestBody EmployeeRegistrationModel employeeRegistrationModel) throws 
	ResourceNotFoundException
	 {
		return authenticationService.doEmployeeAuthentication(employeeRegistrationModel);
	}

	
	

}
