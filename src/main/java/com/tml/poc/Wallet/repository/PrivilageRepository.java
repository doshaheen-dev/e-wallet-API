package com.tml.poc.Wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tml.poc.Wallet.models.account.AccountModel;

public interface PrivilageRepository extends JpaRepository<AccountModel, Long> {


	List<AccountModel> findAll();
	Optional<AccountModel> findAllById(long id);

}
