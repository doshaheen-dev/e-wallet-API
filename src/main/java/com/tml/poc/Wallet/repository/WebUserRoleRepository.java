package com.tml.poc.Wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tml.poc.Wallet.models.rolePrevilage.WebUserRoleModel;
import org.springframework.stereotype.Repository;

@Repository
public interface WebUserRoleRepository extends JpaRepository<WebUserRoleModel, Long> {


	List<WebUserRoleModel> findAll();
	
	
	
}
