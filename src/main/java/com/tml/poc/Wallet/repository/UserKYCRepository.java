package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.UserKYCModel;
import com.tml.poc.Wallet.models.notification.FirebaseTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserKYCRepository extends JpaRepository<UserKYCModel, Long> {


	List<UserKYCModel> findAll();
	Optional<UserKYCModel> findById(long id);

}
