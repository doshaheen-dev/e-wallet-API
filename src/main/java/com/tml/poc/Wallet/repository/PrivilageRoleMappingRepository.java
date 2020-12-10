package com.tml.poc.Wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tml.poc.Wallet.models.PrivilageRoleMappingModel;
import com.tml.poc.Wallet.models.account.AccountModel;

public interface PrivilageRoleMappingRepository extends JpaRepository<PrivilageRoleMappingModel, Long> {


	List<PrivilageRoleMappingModel> findAll();
	Optional<PrivilageRoleMappingModel> findAllById(long id);

}
