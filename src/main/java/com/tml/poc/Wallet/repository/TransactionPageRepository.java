package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.transaction.TransactionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionPageRepository extends PagingAndSortingRepository<TransactionModel, Long> {


    Page<TransactionModel> findAllByUserID(long userId, Pageable pageable);


}
