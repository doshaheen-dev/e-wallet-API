package com.tml.poc.Wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tml.poc.Wallet.models.EmployeeModel;
import com.tml.poc.Wallet.models.EmployeeRoleModel;
import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.models.account.AccountModel;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRoleModel, Long> {


	List<EmployeeRoleModel> findAll();
	
	
	
}
