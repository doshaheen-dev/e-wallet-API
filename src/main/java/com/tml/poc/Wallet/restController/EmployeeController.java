package com.tml.poc.Wallet.restController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.EmployeeModel;
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
	
	
	@PutMapping("/update")
	private ResponseEntity updateEmployee(@Valid @RequestBody EmployeeModel employeeModel ) 
			throws ResourceNotFoundException {
		
		return emplService.UpdateEmployee(employeeModel);
	}
	
	@DeleteMapping("/{id}/delete")
	private ResponseEntity deleteEmployee(@PathVariable long id ) 
			throws ResourceNotFoundException {
		
		return emplService.deleteEmployee(id);
	}
	
	
	
	
}
