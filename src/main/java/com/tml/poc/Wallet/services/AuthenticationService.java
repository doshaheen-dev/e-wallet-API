package com.tml.poc.Wallet.services;

import java.util.Optional;

import javax.validation.Valid;

import com.tml.poc.Wallet.models.usermodels.UserCredModel;
import com.tml.poc.Wallet.models.usermodels.UserLoginModule;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import com.tml.poc.Wallet.models.utilsmodels.OTPModel;
import com.tml.poc.Wallet.models.webuser.WebUserModel;
import com.tml.poc.Wallet.models.webuser.WebUserRegistrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.tml.poc.Wallet.components.EmailComponant;
import com.tml.poc.Wallet.exception.InvalidInputException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.jwt.JwtTokenUtil;
import com.tml.poc.Wallet.models.reponse.DataModelAuthResponce;
import com.tml.poc.Wallet.repository.WebUserRepository;
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
	private WebUserRepository emplRepository;

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

	@Autowired
	private OTPService otpService;

	@Autowired
	private MPinServices mPinServices;

	/**
	 * here new User Registration is going to be done only access to mobile Number
	 * and country code and we are checking it is present into database or not
	 *
	 * @param
	 * @return
	 */
	public ResponseEntity doUserAuthenticationByMobile(@Valid UserCredModel userCredModel)
			throws ResourceNotFoundException,InvalidInputException {
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
			userLoginModule.setUserCred(userCredModel.getUserCred());

			userLoginModule=setOTP(usermodel,userLoginModule);

			return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(userLoginModule));
		}else {
			throw new ResourceNotFoundException("User Not Found");

		}



	}

	/**
	 * Verification of User By OTP and send Token
	 *
	 * @param
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public ResponseEntity doUserAuthenticationVerification(@Valid UserLoginModule userLoginModule)
			throws InvalidInputException, ResourceNotFoundException{
		UserModel usermodel;
		Optional<UserModel> userOptional;
		if(validUtils.isValidEmail(userLoginModule.getUserCred())) {
			userOptional=userRepository.findByEmailid(userLoginModule.getUserCred());
		}else if(validUtils.isMobileNumber(userLoginModule.getUserCred())) {
			userOptional=userRepository.findByMobileNumber(userLoginModule.getUserCred());
		}else {
			throw new InvalidInputException("Credential not found");
		}
		if(userOptional.isPresent()) {
			usermodel=userOptional.get();
			if(otpService.verifyOTP(usermodel.getUserOtpId(), userLoginModule.getOtp())) {
				usermodel.setMPINCreated(mPinServices.isMPINCreated(usermodel.getId()));
				final String token = jwtTokenUtil.generateToken1(usermodel.getQrCode());
				return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForAuthRestAPI(usermodel, token));
			}
		}else {
			throw new ResourceNotFoundException("User Not Found");

		}


		return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(userLoginModule));
	}








	public Object doEmployeeAuthentication(WebUserRegistrationModel webUserRegistrationModel)
			throws ResourceNotFoundException {
		DataModelAuthResponce dataModelResponce = new DataModelAuthResponce();
		if (webUserRegistrationModel != null) {
			Optional<WebUserModel> employeeModel;
			employeeModel = emplRepository.findAllByEmailid(webUserRegistrationModel.getEmailid());
			if (employeeModel.isPresent()) {
				WebUserModel empModel = employeeModel.get();
				if (empModel.isActive()) {
					final String token = jwtTokenUtil.generateToken(empModel.getEmailid(),
							empModel.getPassword(),
							empModel.getRoleId().getRoleName());
					return dataReturnUtils.setDataAndReturnResponseForAuthRestAPI(empModel, token);
				} else {
					throw new ResourceNotFoundException("WebUser is not Active");
				}
			} else {
				throw new ResourceNotFoundException("WebUser not Found");
			}
		} else {
			throw new ResourceNotFoundException("WebUser not Found");
		}
	}



	private UserLoginModule setOTP(UserModel usermodel, UserLoginModule userLoginModule)
	{
		/**
		 * save and Get UserID for OTP
		 */
		UserModel userModelSave=userRepository.save(usermodel);

		/**
		 * create OTP and set UserID
		 */
		OTPModel otpModel=createOTPModel(userModelSave.getId());
		userLoginModule.setOtp(otpModel.getOtp());
        userModelSave.setUserOtpId(otpModel.getId());
		userModelSave=userRepository.save(userModelSave);


		return  userLoginModule;
	}

	private OTPModel createOTPModel(long userID){
		OTPModel otpModel=new OTPModel();
		otpModel=otpService.getUserOTPCreate(userID);
		return  otpModel;
	}

}
