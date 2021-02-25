package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.usermodels.UserKYCModel;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserKYCRepository extends PagingAndSortingRepository<UserKYCModel, Long> {


	List<UserKYCModel> findAll();
	List<UserKYCModel> findAllByIsKYCDone(boolean isKyecDone);
	Page<UserKYCModel> findAllByIsKYCDone(boolean isKyecDone, Pageable pageable);
//	List<UserKYCModel> findAllByKycUserIdAndIsKYCDone(UserModel userID, boolean isKyecDone);
//	List<UserKYCModel> findAllByKycUserIdAndIsKYCDone(UserModel userID, boolean isKyecDone);
	Optional<UserKYCModel> findById(long id);

}
