package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.rolePrevilage.PrivilageMasterModel;
import com.tml.poc.Wallet.models.transaction.SendMoneyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SendMoneyRepository extends JpaRepository<SendMoneyModel, Long> {

	List<SendMoneyModel> findAll();
	Optional<SendMoneyModel> findAllById(long id);

}
