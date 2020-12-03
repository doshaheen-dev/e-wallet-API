package com.tml.poc.Wallet.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tml.poc.Wallet.jwt.JwtTokenUtil;
import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.models.UserRegistrationModel;
import com.tml.poc.Wallet.models.reponse.DataModelResponce;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.utils.CommonMethods;
import com.tml.poc.Wallet.utils.DataReturnUtil;

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


	/**
	 * here new User Registration is going to be done only access to mobile Number
	 * and country code and we are checking it is present into database or not
	 * 
	 * @param userModel
	 * @return
	 */
	public Object doUserRegstration(@Valid UserRegistrationModel userRegistrationModel) {
		DataModelResponce dataModelResponce = new DataModelResponce();
		if (userRegistrationModel != null) {
			if (!userRepository.findAllByMobileNumber(userRegistrationModel.getMobileNumber()).isPresent()) {
				userRegistrationModel.setOTP(cmUtils.generateOTP());
				UserModel userEntity = new UserModel();
				userEntity.setCountrycode(userRegistrationModel.getCountrycode());
				userEntity.setMobileNumber(userRegistrationModel.getMobileNumber());
				userEntity.setOtp(userRegistrationModel.getOTP());
				userEntity = userRepository.save(userEntity);
//				userEntity.setOtp(null);
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(userEntity);
			} else {
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(null,
						"User Already Registered with given Mobile Number");
			}
		}

		return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "No Object Found");
	}

	/**
	 * after registration check mobile number is already present and then checking
	 * OTP
	 * 
	 * @param userRegistrationModel
	 * @return
	 */
	public Object doUserRegistrationVerification(UserRegistrationModel userRegistrationModel) {
		if (userRegistrationModel != null) {
			Optional<UserModel> userModelEntity = userRepository
					.findAllByMobileNumber(userRegistrationModel.getMobileNumber());
			if (userModelEntity.isPresent()) {
				UserModel userEntity = userModelEntity.get();
				System.out.println(userEntity);
				if (userRegistrationModel.getOTP().equals(userEntity.getOtp())) {
					userEntity.setMobileVerified(true);
					userEntity = userRepository.save(userEntity);
					final String token = jwtTokenUtil.generateToken1(userRegistrationModel.getMobileNumber());
					userEntity.setJwToken(token);
					return dataReturnUtils.setDataAndReturnResponseForRestAPI(userEntity, "User Verified Successfully");
				} else {
					return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "Wrong OTP");
				}
			} else {
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(userRegistrationModel, "User not found");
			}
		}
		return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "No Object Found");
	}
		
	public Object doUserUpdate(long id, UserModel userModel) {
		DataModelResponce dataModelResponce = new DataModelResponce();
		if (userModel != null) {
			Optional<UserModel> userModelEntity = userRepository.findById(id);

			if (userModelEntity.isPresent()) {
				UserModel userModelDB=userModelEntity.get();
				userModel.setId(userModelDB.getId());
				userModel.setMobileNumber(userModelDB.getMobileNumber());
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(userModel);

			} else {			
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "User Not Found");
			}
		} else {
			return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "No Object Found");
		}
	}

	public Object doGetUserById(long id) {
		DataModelResponce dataModelResponce = new DataModelResponce();
		if (id >0) {
			Optional<UserModel> userModelEntity = userRepository.findById(id);
			if (userModelEntity.isPresent()) {
				UserModel userModelDB=userModelEntity.get();
				userModelDB.setOtp("");
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(userModelDB);
			} else {			
				return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "User Not Found");
			}
		} else {
			return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "Id not Found");
		}
	}
}
