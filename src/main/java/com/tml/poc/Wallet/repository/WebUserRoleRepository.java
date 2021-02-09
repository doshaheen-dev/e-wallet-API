package com.tml.poc.Wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tml.poc.Wallet.models.rolePrevilage.WebUserRoleModel;
import org.springframework.stereotype.Repository;

@Repository
public interface WebUserRoleRepository extends JpaRepository<WebUserRoleModel, Long> {

	List<WebUserRoleModel> findAll();
	List<WebUserRoleModel> findAllByIsActive(boolean isActive);
	List<WebUserRoleModel> findAllByIdAndIsActive(long id, boolean isActive);
	Optional<WebUserRoleModel> findByIdAndIsActive(long id, boolean isActive);

	Optional<WebUserRoleModel> findByRoleCodeAndIsActive(String rolecode, boolean b);





}
