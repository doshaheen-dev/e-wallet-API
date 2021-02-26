package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.transaction.RequestMoneyModel;
import com.tml.poc.Wallet.models.utilsmodels.AppConfigModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestMoneyRepository extends JpaRepository<RequestMoneyModel, Long> {

	List<RequestMoneyModel> findAll();
	Optional<RequestMoneyModel> findAllById(long id);

}
