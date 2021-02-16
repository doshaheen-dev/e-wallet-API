package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.AppConfigModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfigRepository extends JpaRepository<AppConfigModel, Long> {

	List<AppConfigModel> findAll();
	Optional<AppConfigModel> findAllById(long id);

}
