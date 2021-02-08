package com.tml.poc.Wallet.services;

import java.util.Optional;

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
	private DataReturnUtil dataReturnUtils;
	
	@Autowired
	private PasswordUtils passwordUtils;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	
	
																
	
	
	public ResponseEntity addWebUser(WebUserModel webUserModel) throws ResourceNotFoundException{
		
		if(webUserModel ==null)
		{
			throw new  ResourceNotFoundException("No Employee Found");
		}
		Optional<WebUserModel> employeeModelOpt= webUserRepository.findAllByEmailid(webUserModel.getEmailid());
		if(employeeModelOpt.isPresent()) {
			throw new  ResourceNotFoundException("User Already present");
		}else {
			
			BCryptPasswordEncoder beBCryptPasswordEncoder=new BCryptPasswordEncoder(12);
			/**
			 * send it by Email
			 */
//			webUserModel.setPassword(beBCryptPasswordEncoder.encode(passwordUtils.generatePassword(8)));
			webUserModel.setPassword(passwordUtils.generatePassword(8));
			return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(
					webUserRepository.save(webUserModel)));
		}
		
		
	}
	
	

	public ResponseEntity updateWebUser(WebUserModel webUserModel) throws ResourceNotFoundException{
		
		if(webUserModel ==null)
		{
			throw new  ResourceNotFoundException("No Employee Found");
		}
		Optional<WebUserModel> employeeModelOpt= webUserRepository.findAllByEmailid(webUserModel.getEmailid());
		if(employeeModelOpt.isPresent()) {
			
			webUserModel.setId(employeeModelOpt.get().getId());
			webUserModel.setPassword(employeeModelOpt.get().getPassword());
			
			return  ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(
					webUserRepository.save(webUserModel)));
		}else {
			throw new  ResourceNotFoundException("User Not Found");
			
			/**
			 * send it by Email
			 */
			
		}
		
		
	}
	
	public ResponseEntity deleteWebUser(long webUserIdDelete) throws ResourceNotFoundException{
		
		if(webUserIdDelete==0)
		{
			throw new  ResourceNotFoundException("No Employee Found");
		}
		Optional<WebUserModel> employeeModelOpt= webUserRepository.findAllByIdAndIsActive(webUserIdDelete,true);
		if(employeeModelOpt.isPresent()) {
			
			WebUserModel employeModel=employeeModelOpt.get();
			employeModel.setActive(false);
			return  ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(
					webUserRepository.save(employeModel)));
		}else {
			throw new  ResourceNotFoundException("User Not Found");
			
			/**
			 * send it by Email
			 */
			
		}
		
		
	}
	
}
