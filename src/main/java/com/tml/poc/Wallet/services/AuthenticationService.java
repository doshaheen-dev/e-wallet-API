package com.tml.poc.Wallet.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.tml.poc.Wallet.components.EmailComponant;
import com.tml.poc.Wallet.exception.InvalidInputException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.jwt.JwtTokenUtil;
import com.tml.poc.Wallet.jwt.resorce.AuthenticationException;
import com.tml.poc.Wallet.jwt.resorce.JwtTokenResponse;
import com.tml.poc.Wallet.models.EmployeeModel;
import com.tml.poc.Wallet.models.EmployeeRegistrationModel;
import com.tml.poc.Wallet.models.UserCredModel;
import com.tml.poc.Wallet.models.UserLoginModule;
import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.models.UserRegistrationModel;
import com.tml.poc.Wallet.models.reponse.DataModelAuthResponce;
import com.tml.poc.Wallet.models.reponse.DataModelResponce;
import com.tml.poc.Wallet.repository.EmployeeRepository;
import com.tml.poc.Wallet.repository.EmployeeRoleRepository;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.utils.CommonMethods;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import com.tml.poc.Wallet.utils.ValidationUtils;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployeeRepository emplRepository;

	@Autowired
	private DataReturnUtil dataReturnUtils;

	@Autowired
	private CommonMethods cmUtils;
	
	@Autowired
	private ValidationUtils validUtils;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	
	
    @Value("${otp.expiretime.miliseco}")
    private long otpExpireTime;
    		
	@Autowired
	private EmailComponant emailCompo;
	/**
	 * here new User Registration is going to be done only access to mobile Number
	 * and country code and we are checking it is present into database or not
	 * 
	 * @param userModel
	 * @return
	 */
	public ResponseEntity doUserAuthenticationByMobile(@Valid UserCredModel userCredModel)
			throws ResourceNotFoundException,InvalidInputException {
		DataModelResponce dataModelResponce = new DataModelResponce();
		UserModel usermodel;
		Optional<UserModel> userOptional;
		if(validUtils.isValidEmail(userCredModel.getUserCred())) {
			userOptional=userRepository.findByEmailid(userCredModel.getUserCred());
		}else if(validUtils.isMobileNumber(userCredModel.getUserCred())) {
			userOptional=userRepository.findByMobileNumber(userCredModel.getUserCred());
		}else {
			throw new InvalidInputException("Invalid Input");
		}
		if(userOptional.isPresent()) {
			UserLoginModule userLoginModule=new UserLoginModule();
			usermodel=userOptional.get();
			userLoginModule.setOtp(cmUtils.generateOTP());
			userLoginModule.setUserCred(userCredModel.getUserCred());
			usermodel.setOtp(userLoginModule.getOtp());
			usermodel.setOtpCreated(new Date(System.currentTimeMillis()));
			userRepository.save(usermodel);
			emailCompo.sendOTPEmail(usermodel.getEmailid(),usermodel.getOtp());
			return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(userLoginModule));
		}else {
			throw new ResourceNotFoundException("User Not Found");

		}
		
		
		
	}

	/**
	 * Verification of User By OTP and send Token
	 * 
	 * @param userRegistrationModel
	 * @return
	 * @throws ResourceNotFoundException 
	 */
	public ResponseEntity doUserAuthenticationVerification(@Valid UserLoginModule userLoginModule) 
			throws InvalidInputException, ResourceNotFoundException{
		DataModelResponce dataModelResponce = new DataModelResponce();
		UserModel usermodel;
		Optional<UserModel> userOptional;
		if(validUtils.isValidEmail(userLoginModule.getUserCred())) {
			userOptional=userRepository.findByEmailid(userLoginModule.getUserCred());
		}else if(validUtils.isMobileNumber(userLoginModule.getUserCred())) {
			userOptional=userRepository.findByMobileNumber(userLoginModule.getUserCred());
		}else {
			throw new InvalidInputException("Invalid Input");
		}
		if(userOptional.isPresent()) {
			usermodel=userOptional.get();
			if(verifyOTP(usermodel, userLoginModule)) {
				usermodel.setOtp("");
				usermodel.setOtpCreated(new Date(System.currentTimeMillis()));
				usermodel=userRepository.save(usermodel);
				final String token = jwtTokenUtil.generateToken1(usermodel.getUuid());
				return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForAuthRestAPI(usermodel, token));
			}
		}else {
			throw new ResourceNotFoundException("User Not Found");

		}
		
		
		return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(userLoginModule));
	}
	
	
	
	/**
	 * verify OTP
	 * @param usermodel
	 * @param userLoginModule
	 * @return
	 * @throws InvalidInputException
	 */
	private boolean verifyOTP(UserModel usermodel,UserLoginModule userLoginModule)  throws InvalidInputException{
		if(usermodel!=null&& usermodel.getOtp().equals(userLoginModule.getOtp())) {
			Date expireDate = new Date((usermodel.getOtpCreated().getTime() + otpExpireTime));
			Date currentDate = new Date(System.currentTimeMillis());	
			if(currentDate.before(expireDate)) {
				return true;
			}else {
				throw new InvalidInputException("OTP Expired");
			}
		}else {
			throw new InvalidInputException("Invalid OTP");
		}
	}
	
	
	

	public Object doEmployeeAuthentication(EmployeeRegistrationModel employeeRegistrationModel)
			throws ResourceNotFoundException {
		DataModelAuthResponce dataModelResponce = new DataModelAuthResponce();
		if (employeeRegistrationModel != null) {
			Optional<EmployeeModel> employeeModel;
			employeeModel = emplRepository.findAllByEmailid(employeeRegistrationModel.getEmailid());
			if (employeeModel.isPresent()) {
				EmployeeModel empModel = employeeModel.get();
				if (empModel.isActive()) {
					final String token = jwtTokenUtil.generateToken(empModel.getEmailid(),
							empModel.getPassword(),
							empModel.getRoleId().getRoleName());
					return dataReturnUtils.setDataAndReturnResponseForAuthRestAPI(empModel, token);
				} else {
					throw new ResourceNotFoundException("Employee is not Active");
				}
			} else {
				throw new ResourceNotFoundException("Employee not Found");
			}
		} else {
			throw new ResourceNotFoundException("Employee not Found");
		}
	}

}
