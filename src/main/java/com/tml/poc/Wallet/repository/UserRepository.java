package com.tml.poc.Wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tml.poc.Wallet.models.usermodels.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {


	List<UserModel> findAll();
	Optional<UserModel> findAllById(long id);
	List<UserModel> findAllByMobileNumberOrEmailidAndIsActive(String mobile,String email,boolean isActive);
	Optional<UserModel> findByEmailid(String emailid);
	Optional<UserModel> findByMobileNumber(String mobile);
	Optional<UserModel> findByQrCode(String qrCode);

	Optional<UserModel> findByEmailidAndIsActive(String emailid,boolean isactive);
	Optional<UserModel> findByMobileNumberAndIsActive(String mobile,boolean isactive);


}
