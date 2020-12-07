package com.tml.poc.Wallet.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tml.poc.Wallet.models.EmployeeModel;
import com.tml.poc.Wallet.models.EmployeeRoleModel;
import com.tml.poc.Wallet.repository.EmployeeRepository;
import com.tml.poc.Wallet.repository.EmployeeRoleRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;

@RequestMapping("/employee")
@RestController
public class EmployeeController {

	
	@Autowired
	private EmployeeRepository empRepository;
	
	@Autowired
	private DataReturnUtil dataReturnUtil;
		
	@PostMapping("/add")
	private Object addEmployee(@RequestBody EmployeeModel employeeModel ) {

		return empRepository.save(employeeModel);
	}
	
}
