package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.kycCenter.KycCenterModel;
import com.tml.poc.Wallet.models.rolePrevilage.EmployeeRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KycCenterRepository extends JpaRepository<KycCenterModel, Long> {


	List<KycCenterModel> findAll();

	
	
}
