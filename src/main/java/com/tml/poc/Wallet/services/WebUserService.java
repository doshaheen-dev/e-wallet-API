package com.tml.poc.Wallet.services;

import java.util.Optional;

import com.tml.poc.Wallet.models.rolePrevilage.WebUserRoleModel;
import com.tml.poc.Wallet.repository.WebUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.WebUserModel;
import com.tml.poc.Wallet.repository.WebUserRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import com.tml.poc.Wallet.utils.PasswordUtils;

@Service
public class WebUserService {

	@Autowired
	private WebUserRepository webUserRepository;

	@Autowired
	private WebUserRoleRepository webUserRoleRepository;
	
	@Autowired
	private DataReturnUtil dataReturnUtils;
	
	@Autowired
	private PasswordUtils passwordUtils;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;


	/**
	 * to add Web User
	 * @param webUserModel
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public ResponseEntity addWebUser(WebUserModel webUserModel) throws ResourceNotFoundException{
		
		if(webUserModel ==null)
		{
			throw new  ResourceNotFoundException("No Web User Found");
		}
		Optional<WebUserModel> employeeModelOpt= webUserRepository.findAllByEmailid(webUserModel.getEmailid());
		if(employeeModelOpt.isPresent()) {
			throw new  ResourceNotFoundException("Web User Already present");
		}else {
			
			BCryptPasswordEncoder beBCryptPasswordEncoder=new BCryptPasswordEncoder(12);
			/**
			 * send it by Email
			 */
//			webUserModel.setPassword(beBCryptPasswordEncoder.encode(passwordUtils.generatePassword(8)));
//			webUserModel.setPassword(passwordUtils.generatePassword(8));
			return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseSuccess(
					webUserRepository.save(webUserModel),"Web User Added"));
		}
		
		
	}


	/**
	 * to update WebUser
	 * @param webUserModel
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public ResponseEntity updateWebUser(WebUserModel webUserModel) throws ResourceNotFoundException{
		
		if(webUserModel ==null)
		{
			throw new  ResourceNotFoundException("No Web User Found");
		}
		Optional<WebUserModel> employeeModelOpt= webUserRepository.findAllByEmailid(webUserModel.getEmailid());
		Optional<WebUserRoleModel> roleModelOptional= webUserRoleRepository
				.findByRoleCodeAndIsActive(webUserModel.getRoleCode(),true);
		if(!roleModelOptional.isPresent()){
			throw new ResourceNotFoundException("Role Code not Found");
		}
		if(employeeModelOpt.isPresent()) {

			webUserModel.setId(employeeModelOpt.get().getId());
			webUserModel.setPassword(employeeModelOpt.get().getPassword());
			webUserModel.setRoleIdModel(roleModelOptional.get());

			return  ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(
					webUserRepository.save(webUserModel)));
		}else {
			throw new  ResourceNotFoundException("Web User Not Found");
			
			/**
			 * send it by Email
			 */
			
		}
		
		
	}

	/** to inactive webuser
	 */
	public ResponseEntity deleteWebUser(long webUserIdDelete) throws ResourceNotFoundException{
		
		if(webUserIdDelete==0)
		{
			throw new  ResourceNotFoundException("No Web User Found");
		}
		Optional<WebUserModel> employeeModelOpt= webUserRepository.findAllByIdAndIsActive(webUserIdDelete,true);
		Optional<WebUserRoleModel> roleModelOptional= webUserRoleRepository
				.findByRoleCodeAndIsActive(employeeModelOpt.get().getRoleCode(),true);
		if(!roleModelOptional.isPresent()){
			throw new ResourceNotFoundException("Role Code not Found");
		}
		if(employeeModelOpt.isPresent()) {
			
			WebUserModel employeModel=employeeModelOpt.get();
			employeModel.setActive(false);
			employeModel.setRoleIdModel(roleModelOptional.get());
			return  ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(
					webUserRepository.save(employeModel)));
		}else {
			throw new  ResourceNotFoundException("User Not Found");
			
			/**
			 * send it by Email
			 */
			
		}
		
		
	}


	/**
	 * get Single user by webuserId
	 * @param webUserId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public ResponseEntity getWebUser(long webUserId) throws ResourceNotFoundException{

		if(webUserId==0)
		{
			throw new  ResourceNotFoundException("No WebUser Found");
		}
		Optional<WebUserModel> employeeModelOpt= webUserRepository.findAllByIdAndIsActive(webUserId,true);
		if(employeeModelOpt.isPresent()) {

			WebUserModel employeModel=employeeModelOpt.get();
//			employeModel.setActive(false);
			return  ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(
					employeeModelOpt.get()));
		}else {
			throw new  ResourceNotFoundException("User Not Found");

			/**
			 * send it by Email
			 */

		}


	}

	/**
	 * get all WebUser
	 * @return
	 */
	public ResponseEntity getAllWebUser(){
		return  ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(
				webUserRepository.findAllByIsActive(true)));
	}
	
}
