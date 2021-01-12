package com.tml.poc.Wallet.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tml.poc.Wallet.models.EmployeeRoleModel;
import com.tml.poc.Wallet.repository.AccountRepository;
import com.tml.poc.Wallet.repository.EmployeeRepository;
import com.tml.poc.Wallet.repository.EmployeeRoleRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;

@RequestMapping("/employee/role")
@RestController
public class EmployeeRoleController {

	
	@Autowired
	private EmployeeRoleRepository employeeRoleRepository;
	
	@Autowired
	private DataReturnUtil dataReturnUtil;
	
	@GetMapping("/add")
	private Object addEmployeeRole(@RequestBody EmployeeRoleModel emlRoleModel ) {

		return employeeRoleRepository.save(emlRoleModel);
	}
	
	
}
