package com.tml.poc.Wallet.repository;

import com.tml.poc.Wallet.models.recharge.AddCashToWalletModel;
import com.tml.poc.Wallet.models.transaction.RequestMoneyModel;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AddCashToWalletModelRepository extends CrudRepository<AddCashToWalletModel, Long> {

    public List<AddCashToWalletModel> findAllByUserId(long userid);


}
