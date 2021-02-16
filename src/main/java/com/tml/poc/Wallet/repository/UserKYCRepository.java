package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.usermodels.UserKYCModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserKYCRepository extends JpaRepository<UserKYCModel, Long> {


	List<UserKYCModel> findAll();
	List<UserKYCModel> findAllByIsKYCDone(boolean isKyecDone);
	Optional<UserKYCModel> findById(long id);

}
