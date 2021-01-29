package com.tml.poc.Wallet.repository;

import java.util.List;
import java.util.Optional;

import com.tml.poc.Wallet.models.rolePrevilage.PrivilageMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrivilageRepository extends JpaRepository<PrivilageMasterModel, Long> {


	List<PrivilageMasterModel> findAll();
	Optional<PrivilageMasterModel> findAllById(long id);

}
