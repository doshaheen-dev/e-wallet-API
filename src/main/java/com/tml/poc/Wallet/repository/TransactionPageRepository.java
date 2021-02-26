package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.transaction.TransactionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TransactionPageRepository extends PagingAndSortingRepository<TransactionModel, Long> {


    Page<TransactionModel> findAllByUserID(long userId, Pageable pageable);


}
