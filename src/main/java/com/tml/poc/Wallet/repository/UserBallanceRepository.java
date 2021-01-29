package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.transaction.UserBallanceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBallanceRepository extends JpaRepository<UserBallanceModel, Long> {


	List<UserBallanceModel> findAll();
	Optional<UserBallanceModel> findAllById(long id);

}
