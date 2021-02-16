package com.tml.poc.Wallet.restController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tml.poc.Wallet.exception.InvalidInputException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.jwt.JwtTokenUtil;
import com.tml.poc.Wallet.jwt.JwtUserDetails;
import com.tml.poc.Wallet.models.webuser.WebUserRegistrationModel;
import com.tml.poc.Wallet.models.usermodels.UserCredModel;
import com.tml.poc.Wallet.models.usermodels.UserLoginModule;
import com.tml.poc.Wallet.services.AuthenticationService;
import com.tml.poc.Wallet.utils.DataReturnUtil;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user/auth")
@Api(value = "Authentication for User and Admin", description = "Authentication for User and Admin")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	private DataReturnUtil dataReturnUtils;
	
	


	/**
	 * customer login req for OTP
	 * 
	 * @param userRegistrationModel
	 * @return
	 * @throws InvalidInputException
	 */
	@PostMapping("/login")
	private ResponseEntity doLoginCall(@RequestBody UserCredModel userLoginModule)
			throws ResourceNotFoundException, InvalidInputException {
		return authenticationService.doUserAuthenticationByMobile(userLoginModule);
	}

	/**
	 * Customer Verificcation by OTP Note:SMS Gate way is remain
	 * 
	 * @param userRegistrationModel
	 * @return
	 */
	@PostMapping("/verify")
	private ResponseEntity doLoginCallVerify(@RequestBody UserLoginModule userRegistrationModel)
			throws InvalidInputException, ResourceNotFoundException {

		return authenticationService.doUserAuthenticationVerification(userRegistrationModel);

	}

	/**
	 * Employee Verificcation by Emailid password
	 * 
	 * @param webUserRegistrationModel
	 * @return
	 */
	@PostMapping("/webuser/login")
	private Object doMobileLoginCall(@RequestBody WebUserRegistrationModel webUserRegistrationModel)
			throws ResourceNotFoundException {
		return authenticationService.doEmployeeAuthentication(webUserRegistrationModel);
	}

	@RequestMapping(value = "/user/refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request)
			throws ResourceNotFoundException {
		try {
			String authToken = request.getHeader(tokenHeader);
			final String token = authToken.substring(7);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			JwtUserDetails user = (JwtUserDetails) jwtInMemoryUserDetailsService.loadUserByUsername(username);

			if (jwtTokenUtil.canTokenBeRefreshed(token)) {
				String refreshedToken = jwtTokenUtil.refreshToken(token);
//			return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
				return ResponseEntity
						.ok(dataReturnUtils.setDataAndReturnResponseForAuthRestAPI(null, refreshedToken));
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("Data Not Found");
		}
	}

}
