package com.tml.poc.Wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tml.poc.Wallet.models.EmployeeModel;
import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.models.account.AccountModel;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {


	List<EmployeeModel> findAll();
	Optional<EmployeeModel> findAllById(long id);
	Optional<EmployeeModel> findAllByMobileNumber(String mobile);
	Optional<EmployeeModel> findAllByEmailid(String emailid);
	
	
}
