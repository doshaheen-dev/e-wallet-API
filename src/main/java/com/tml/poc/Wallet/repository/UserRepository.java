package com.tml.poc.Wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.models.account.AccountModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {


	List<UserModel> findAll();
	Optional<UserModel> findAllById(long id);
	Optional<UserModel> findAllByMobileNumber(String mobile);
	Optional<UserModel> findAllByEmailid(String emailid);
		
}
