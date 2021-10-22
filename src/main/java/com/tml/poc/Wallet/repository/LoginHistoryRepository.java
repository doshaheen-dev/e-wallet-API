package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import com.tml.poc.Wallet.models.utilsmodels.LoginHistoryModel;
import com.tml.poc.Wallet.models.webuser.WebUserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginHistoryRepository extends PagingAndSortingRepository<LoginHistoryModel, Long> {



	List<LoginHistoryModel> findAll();
	Optional<LoginHistoryModel> findAllById(long id);
	List<LoginHistoryModel> findAllByMobileUserId(long id);
	List<LoginHistoryModel> findAllByWebUserId(long id);

	Page<LoginHistoryModel> findAll(Pageable pageable);
	Page<LoginHistoryModel> findAllByMobileUserId(long userId, Pageable pageable);
	Page<LoginHistoryModel> findAllByMobileUserId(UserModel userId, Pageable pageable);
	Page<LoginHistoryModel> findAllByWebUserId(long userId, Pageable pageable);
	Page<LoginHistoryModel> findAllByWebUserId(WebUserModel userId, Pageable pageable);


}
