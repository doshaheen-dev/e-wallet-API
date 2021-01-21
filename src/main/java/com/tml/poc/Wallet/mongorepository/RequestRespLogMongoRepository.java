package com.tml.poc.Wallet.mongorepository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tml.poc.Wallet.mongomodels.RequestResponceLogModel;

@Repository
public interface RequestRespLogMongoRepository extends CrudRepository<RequestResponceLogModel, Long>{
	
	public Optional<RequestResponceLogModel> findByRequestID(String appShortName);
	
}

