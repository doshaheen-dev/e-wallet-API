package com.tml.poc.Wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tml.poc.Wallet.models.WebUserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface WebUserRepository extends JpaRepository<WebUserModel, Long> {


	List<WebUserModel> findAll();
	Optional<WebUserModel> findAllById(long id);
	Optional<WebUserModel> findAllByIdAndIsActive(long id, boolean isActive);
	List<WebUserModel> findAllByIsActive( boolean isActive);
	Optional<WebUserModel> findAllByMobileNumber(String mobile);
	Optional<WebUserModel> findAllByEmailid(String emailid);
	Optional<WebUserModel> findAllByEmailidAndIsActive(String emailid, boolean isActive);
	
	
}
