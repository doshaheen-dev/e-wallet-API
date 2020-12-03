package com.tml.poc.Wallet.services;

import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.jwt.JwtTokenUtil;
import com.tml.poc.Wallet.jwt.resorce.AuthenticationException;
import com.tml.poc.Wallet.jwt.resorce.JwtTokenResponse;
import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.models.UserRegistrationModel;
import com.tml.poc.Wallet.models.reponse.DataModelResponce;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.utils.CommonMethods;
import com.tml.poc.Wallet.utils.DataReturnUtil;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DataReturnUtil dataReturnUtils;

	@Autowired
	private CommonMethods cmUtils;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
																	
	/**
	 * here new User Registration is going to be done only access to mobile Number
	 * and country code and we are checking it is present into database or not
	 * 
	 * @param userModel
	 * @return
	 */
	public Object doUserAuthenticationByMobile(@Valid UserRegistrationModel userRegistrationModel) throws ResourceNotFoundException {
		DataModelResponce dataModelResponce = new DataModelResponce();
		if (userRegistrationModel != null) {
			Optional<UserModel> userModel = userRepository
					.findAllByMobileNumber(userRegistrationModel.getMobileNumber());
					
			userModel.orElseThrow(()
					-> new ResourceNotFoundException("User Not Found " + userRegistrationModel.getMobileNumber()));
					
				UserModel userEntity = userModel.get();
				userEntity.setOtp(cmUtils.generateOTP());
				userEntity = userRepository.save(userEntity);
//				userEntity.setOtp(null);	
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(userEntity);
			
			
		}
		return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "No Object Found");
	}

	/**
	 * Verification of User By OTP and send Token
	 * @param userRegistrationModel
	 * @return
	 */
	public Object doUserAuthenticationVerification(@Valid UserRegistrationModel userRegistrationModel) {
		DataModelResponce dataModelResponce = new DataModelResponce();
		if (userRegistrationModel != null) {
			Optional<UserModel> userModel;
			userModel = userRepository.findAllByMobileNumber(userRegistrationModel.getMobileNumber());
			if (userModel.isPresent()) {
				UserModel userEntity = userModel.get();
				if (userEntity.getOtp().equals(userRegistrationModel.getOTP())) {

					
					final UserDetails userDetails = jwtInMemoryUserDetailsService
							.loadUserByUsername(userRegistrationModel.getMobileNumber());
					
					final String token = jwtTokenUtil.generateToken1(userRegistrationModel.getMobileNumber());
					

					userEntity.setJwToken(token);
					return dataReturnUtils.setDataAndReturnResponseForRestAPI(userEntity);
				} else {
					return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "Wrong OTP");
				}
			} else {
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "User is not register");
			}
		}
		return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "No Object Found");
	}

	
	

}
