package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.transaction.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {


	List<TransactionModel> findAll();
	Optional<TransactionModel> findAllById(long id);
	Optional<TransactionModel> findAllByUserID(long userid);

}
