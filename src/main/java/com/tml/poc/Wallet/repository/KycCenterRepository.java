package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.kycCenter.KycCenterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface KycCenterRepository extends JpaRepository<KycCenterModel, Long> {


	List<KycCenterModel> findAll();

	
	
}
