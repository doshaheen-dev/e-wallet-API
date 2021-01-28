package com.tml.poc.Wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tml.poc.Wallet.models.EmployeeModel;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {


	List<EmployeeModel> findAll();
	Optional<EmployeeModel> findAllById(long id);
	Optional<EmployeeModel> findAllByIdAndIsActive(long id,boolean isActive);
	Optional<EmployeeModel> findAllByMobileNumber(String mobile);
	Optional<EmployeeModel> findAllByEmailid(String emailid);
	Optional<EmployeeModel> findAllByEmailidAndIsActive(String emailid,boolean isActive);
	
	
}
