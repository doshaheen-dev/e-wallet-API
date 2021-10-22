package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.utilsmodels.OTPModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserOTPRepository extends JpaRepository<OTPModel, Long> {


	List<OTPModel> findAll();
	Optional<OTPModel> findAllById(long id);
//	Optional<OTPModel> findFirstByOrderByUserID(long userid);
	Optional<OTPModel> findFirstByMPinId(long mpinid);
//	Optional<OTPModel> findFirstByUserIDAsc(long userid);
	Optional<OTPModel> findAllByUserID(long userid);

	Optional<OTPModel> findFirstByUserIDOrderByUserIDDesc(long userid);
	Optional<OTPModel> findFirstByUserIDOrderByUserIDAsc(long userid);
	Optional<OTPModel> findTopByUserIDOrderByUserIDAsc(long userid);
//	Optional<OTPModel> findByOrderBySeatNumberAsc(long userid);


}
