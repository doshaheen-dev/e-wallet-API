package com.tml.poc.Wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tml.poc.Wallet.models.rolePrevilage.EmployeeRoleModel;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRoleModel, Long> {


	List<EmployeeRoleModel> findAll();
	
	
	
}
