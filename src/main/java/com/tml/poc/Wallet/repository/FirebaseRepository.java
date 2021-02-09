package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.notification.FirebaseTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FirebaseRepository extends JpaRepository<FirebaseTokenModel, Long> {


	List<FirebaseTokenModel> findAll();
	Optional<FirebaseTokenModel> findById(long id);
	Optional<FirebaseTokenModel> findByDeviceIDAndFcmToken(String deviceId,String fcmToken);

}
