package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.transaction.RequestMoneyModel;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RequestMoneyRepository extends CrudRepository<RequestMoneyModel, Long> {

    List<RequestMoneyModel> findAll();

    Optional<RequestMoneyModel> findAllById(long id);

    Slice<RequestMoneyModel> findAllByRequesterUserIdAndCreatedAtBetween(UserModel requesterUserId,
                                                                         Date fromDate,
                                                                         Date toDate,
                                                                         Pageable pageable);

    Slice<RequestMoneyModel> findAllByRequesterUserId(UserModel requesterUserId,
                                                      Pageable pageable);


    Slice<RequestMoneyModel> findAllByRequestToUserIdAndCreatedAtBetween(UserModel requesterUserId,
                                                                         Date fromDate,
                                                                         Date toDate,
                                                                         Pageable pageable);


    Slice<RequestMoneyModel> findAllByRequestToUserId(UserModel requesterUserId,
                                                      Pageable pageable);


}
