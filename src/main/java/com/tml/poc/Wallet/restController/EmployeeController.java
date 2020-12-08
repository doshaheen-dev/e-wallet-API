package com.tml.poc.Wallet.restController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.EmployeeModel;
import com.tml.poc.Wallet.models.EmployeeRoleModel;
import com.tml.poc.Wallet.models.reponse.DataModelResponce;
import com.tml.poc.Wallet.repository.EmployeeRepository;
import com.tml.poc.Wallet.repository.EmployeeRoleRepository;
import com.tml.poc.Wallet.services.EmployeeService;
import com.tml.poc.Wallet.utils.DataReturnUtil;

@RequestMapping("/employee")
@RestController
public class EmployeeController {

	
	@Autowired
	private EmployeeService emplService;
	
	@Autowired
	private DataReturnUtil dataReturnUtil;
		
	@PostMapping("/add")
	private ResponseEntity addEmployee(@Valid @RequestBody EmployeeModel employeeModel ) 
			throws ResourceNotFoundException {
		
		return emplService.addEmployee(employeeModel);
	}
	
	@PostMapping("/update")
	private ResponseEntity updateEmployee(@Valid @RequestBody EmployeeModel employeeModel ) 
			throws ResourceNotFoundException {
		
		return emplService.UpdateEmployee(employeeModel);
	}
	
	
	
	
}
