package com.tml.poc.Wallet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.azure.core.http.rest.Response;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.jwt.JwtTokenUtil;
import com.tml.poc.Wallet.models.EmployeeModel;
import com.tml.poc.Wallet.repository.EmployeeRepository;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.utils.CommonMethods;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import com.tml.poc.Wallet.utils.PasswordUtils;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Autowired
	private DataReturnUtil dataReturnUtils;
	
	@Autowired
	private PasswordUtils passwordUtils;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	
	
																
	
	
	public ResponseEntity addEmployee(EmployeeModel employeeModel) throws ResourceNotFoundException{
		
		if(employeeModel==null)
		{
			throw new  ResourceNotFoundException("No Employee Found");
		}
		Optional<EmployeeModel> employeeModelOpt=employeeRepository.findAllByEmailid(employeeModel.getEmailid());
		if(employeeModelOpt.isPresent()) {
			throw new  ResourceNotFoundException("User Already present");
		}else {
			
			BCryptPasswordEncoder beBCryptPasswordEncoder=new BCryptPasswordEncoder(12);
			/**
			 * send it by Email
			 */
			employeeModel.setPassword(beBCryptPasswordEncoder.encode(passwordUtils.generatePassword(8)));
			return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(
					employeeRepository.save(employeeModel)));
		}
		
		
	}
	
	

	public ResponseEntity UpdateEmployee(EmployeeModel employeeModel) throws ResourceNotFoundException{
		
		if(employeeModel==null)
		{
			throw new  ResourceNotFoundException("No Employee Found");
		}
		Optional<EmployeeModel> employeeModelOpt=employeeRepository.findAllByEmailid(employeeModel.getEmailid());
		if(employeeModelOpt.isPresent()) {
			
			employeeModel.setId(employeeModelOpt.get().getId());
			employeeModel.setPassword(employeeModelOpt.get().getPassword());
			
			return  ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(
					employeeRepository.save(employeeModel)));
		}else {
			throw new  ResourceNotFoundException("User Not Found");
			
			/**
			 * send it by Email
			 */
			
		}
		
		
	}
	
	public ResponseEntity deleteEmployee(long employeeIdDelete) throws ResourceNotFoundException{
		
		if(employeeIdDelete==0)
		{
			throw new  ResourceNotFoundException("No Employee Found");
		}
		Optional<EmployeeModel> employeeModelOpt=employeeRepository.findAllByIdAndIsActive(employeeIdDelete,true);
		if(employeeModelOpt.isPresent()) {
			
			EmployeeModel employeModel=employeeModelOpt.get();
			employeModel.setActive(false);
			return  ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(
					employeeRepository.save(employeModel)));
		}else {
			throw new  ResourceNotFoundException("User Not Found");
			
			/**
			 * send it by Email
			 */
			
		}
		
		
	}
	
}
