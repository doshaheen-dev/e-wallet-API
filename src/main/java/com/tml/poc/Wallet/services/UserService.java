package com.tml.poc.Wallet.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tml.poc.Wallet.components.EmailComponant;
import com.tml.poc.Wallet.exception.InvalidInputException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.jwt.JwtTokenUtil;
import com.tml.poc.Wallet.models.UserLoginModule;
import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.models.UserRegistrationModel;
import com.tml.poc.Wallet.models.reponse.DataModelResponce;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.utils.CommonMethods;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import com.tml.poc.Wallet.utils.ValidationUtils;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DataReturnUtil dataReturnUtils;

	@Autowired
	private CommonMethods cmUtils;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private ValidationUtils validUtils;

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
	 * @throws InvalidInputException 
	 * @throws ResourceNotFoundException 
	 */
	public ResponseEntity doUserRegstration(@Valid UserRegistrationModel userRegistrationModel) 
			throws InvalidInputException, ResourceNotFoundException {
		String message="";
		if(!validUtils.isValidEmail(userRegistrationModel.getEmailid())) {
			message=message+"Emailid Is not valid | ";
		} if(!validUtils.isMobileNumber(userRegistrationModel.getMobileNumber())) {
			message=message+" Mobile Number Is not valid ";
		}
		if(!message.equals("")) {
			throw new InvalidInputException(message);
		}
		Optional<UserModel> userOptional=null;
		userOptional=userRepository.findByEmailid(userRegistrationModel.getEmailid());
		if(userOptional.isPresent()) {
			message=message+" EmailId is Already Present |";
		}
		if(userRepository.findByMobileNumber(userRegistrationModel.getMobileNumber()).isPresent()) {
			message=message+" Mobile number is Already Present ";
		}
		if(message.equals("")) {
			UserModel usermodel=new UserModel();
			userRegistrationModel.setOTP(cmUtils.generateOTP());
			usermodel.setEmailid(userRegistrationModel.getEmailid());
			usermodel.setMobileNumber(userRegistrationModel.getMobileNumber());
			usermodel.setOtp(userRegistrationModel.getOTP());
			usermodel.setOtpCreated(new Date(System.currentTimeMillis()));
			usermodel.setActive(false);
			userRepository.save(usermodel);
			
			emailCompo.sendOTPEmail(usermodel.getEmailid(),usermodel.getOtp());

			return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(userRegistrationModel));
		}else {
			throw new InvalidInputException(message);
		}
		
	}
	
	
	/**
	 * to ReSend Otp set here
	 * @param userCred
	 * @return
	 * @throws InvalidInputException
	 * @throws ResourceNotFoundException
	 */
	public ResponseEntity doResendOTP(String userCred) 
			throws InvalidInputException, ResourceNotFoundException {
		String message="";
		Optional<UserModel> userOpt=null;
		if(validUtils.isValidEmail(userCred)) {
			userOpt=userRepository.findByEmailid(userCred);
		}
		if(validUtils.isMobileNumber(userCred)) {
			userOpt=userRepository.findByMobileNumber(userCred);
		}
		
		
		if(userOpt.isPresent()) {
			UserModel usermodel=userOpt.get();
			UserLoginModule userLoginModule=new UserLoginModule();
			userLoginModule.setOtp(cmUtils.generateOTP());
			userLoginModule.setUserCred(userCred);
			usermodel.setOtp(userLoginModule.getOtp());
			usermodel.setOtpCreated(new Date(System.currentTimeMillis()));
			userRepository.save(usermodel);
			emailCompo.sendOTPEmail(usermodel.getEmailid(),usermodel.getOtp());

			return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(userLoginModule));
		}else {
			
			throw new InvalidInputException(message);

		}
		
	}
					
	/**
	 * after registration check mobile number is already present and then checking
	 * OTP
	 * 
	 * @param userRegistrationModel
	 * @return
	 * @throws InvalidInputException 
	 * @throws ResourceNotFoundException 
	 */
	public ResponseEntity doUserRegistrationVerification( UserRegistrationModel userLoginModule) 
			throws InvalidInputException, ResourceNotFoundException {
		UserModel usermodel;
		Optional<UserModel> userOptional=userRepository.findByEmailid(userLoginModule.getEmailid());
		
		if(userOptional.isPresent()) {
			usermodel=userOptional.get();
			if(verifyOTP(usermodel, userLoginModule)) {
				usermodel.setOtp("");
				usermodel.setOtpCreated(new Date(System.currentTimeMillis()));
				usermodel.setActive(true);
				usermodel.setMobileVerified(true);
				usermodel.setEmailVerified(false);
				usermodel=userRepository.save(usermodel);
				final String token = jwtTokenUtil.generateToken1(usermodel.getUuid());
				return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForAuthRestAPI(usermodel, token));
			}
			else {
				throw new InvalidInputException("Invalid OTP");
			}
		}else {
			throw new ResourceNotFoundException("User Not Found");

		}
	}
	
	
	/**
	 * verify OTP
	 * @param usermodel
	 * @param userLoginModule
	 * @return
	 * @throws InvalidInputException
	 */
	private boolean verifyOTP(UserModel usermodel,UserRegistrationModel userLoginModule)  throws InvalidInputException{
		if(usermodel!=null&& usermodel.getOtp().equals(userLoginModule.getOTP())) {
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
	
			
	/**
	 * update user as per given usermodel from controller
	 * @param id
	 * @param userModel
	 * @return
	 */
	public Object doUserUpdate(long id, UserModel userModel) {
		if (userModel != null) {
			Optional<UserModel> userModelEntity = userRepository.findById(id);
			if (userModelEntity.isPresent()) {
				UserModel userModelDB=userModelEntity.get();
				userModel.setId(userModelDB.getId());
				userModel.setMobileNumber(userModelDB.getMobileNumber());
				userModel.setEmailid(userModelDB.getEmailid());
				
				if(userModel.getFirstname().isEmpty()||
						userModel.getLastname().isEmpty()) {
					userModel.setProfileComplete(false);
				}else {
					userModel.setProfileComplete(true);
				}
				
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(userRepository.save(userModel));

			} else {			
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "User Not Found");
			}
		} else {
			return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "No Object Found");
		}
	}
				
	/**
	 * get User By Id
	 * which one called from another service
	 * @param id
	 * @return
	 */
	public Object doGetUserById(long id) {
		if (id >0) {
			Optional<UserModel> userModelEntity = userRepository.findById(id);
			if (userModelEntity.isPresent()) {
				UserModel userModelDB=userModelEntity.get();
				
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(userModelDB);
			} else {			
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "User Not Found");
			}
		} else {
			return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "Id not Found");
		}
	}
		
	/**
	 * delete user here
	 * user will not delete directly but it will soft delete from DB
	 * @param id
	 * @return
	 */
	public Object doDeleteUserById(long id) {
		if (id >0) {
			Optional<UserModel> userModelEntity = userRepository.findById(id);
			if (userModelEntity.isPresent()) {
				UserModel userModelDB=userModelEntity.get();
				
				userModelDB.setActive(false);
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(userRepository.save(userModelDB));
			} else {			
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "User Not Found");
			}
		} else {
			return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "Id not Found");
		}
	}
	
	/**
	 * images will be remove from DB table user
	 * @param id
	 * @return
	 */
	public Object doRemoveImageUserById(long id) {
		if (id >0) {
			Optional<UserModel> userModelEntity = userRepository.findById(id);
			if (userModelEntity.isPresent()) {
				UserModel userModelDB=userModelEntity.get();
				
				userModelDB.setProfile_image("");
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(userRepository.save(userModelDB));
			} else {			
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "User Not Found");
			}
		} else {
			return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "Id not Found");
		}
	}
}
