package com.tml.poc.Wallet.serviceImpl;

import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.repository.TransactionPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionPageImplService {

    @Autowired
    private TransactionPageRepository transactionPageRepository;


    public Page<TransactionModel> getAllTransactions(long userID,Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<TransactionModel> pagedResult = transactionPageRepository.findAllByUserID(userID,paging);

       return pagedResult;
    }
}
