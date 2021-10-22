package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.models.transaction.UserBallanceModel;
import com.tml.poc.Wallet.repository.UserBallanceRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MobileUserBallanceService {

    @Autowired
    private UserBallanceRepository userBallanceRepository;



    public UserBallanceModel getUserBallanceByUserID(long userId){
        Optional<UserBallanceModel> userBallanceModelOptional=userBallanceRepository.findAllByUserID(userId);
        if(userBallanceModelOptional.isPresent()){
            return  userBallanceModelOptional.get();
        }else{
            UserBallanceModel userBallanceModel=new UserBallanceModel();
            userBallanceModel.setUserID(userId);
            userBallanceModel.setAvailableBalance(0);
            return userBallanceRepository.save(userBallanceModel);
        }
    }


    @SneakyThrows
    public UserBallanceModel addMoneyToBallance(float addMoney, long userId) {
        Optional<UserBallanceModel> userBallanceModelOptional=userBallanceRepository.findAllByUserID(userId);
        if(userBallanceModelOptional.isPresent()){
            UserBallanceModel userBallanceModel=userBallanceModelOptional.get();
            userBallanceModel.setAvailableBalance(userBallanceModel.getAvailableBalance()+addMoney);
            return userBallanceRepository.save(userBallanceModel);
        }
        throw new ResourceNotFoundException("User not found");
    }

    public UserBallanceModel saveNewBallance(UserBallanceModel userBallanceModel){
       return userBallanceRepository.save(userBallanceModel);
    }

}
