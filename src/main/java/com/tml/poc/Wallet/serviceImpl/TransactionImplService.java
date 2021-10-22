package com.tml.poc.Wallet.serviceImpl;


import com.tml.poc.Wallet.exception.TransactionFailedException;
import com.tml.poc.Wallet.models.transaction.SendMoneyModel;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.models.transaction.UserBallanceModel;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import com.tml.poc.Wallet.repository.TransactionRepository;
import com.tml.poc.Wallet.services.MobileUserBallanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.tml.poc.Wallet.utils.Constants.TRANSACTION_FAILED;

/**
 *
 */
@Service
public class TransactionImplService {


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MobileUserBallanceService mobileUserBallanceService;


    /**
     * debit amount and update into Transaction
     * @param sendMoneyModel
     * @param userModelSender
     * @param userModelReceiver 
     * @return
     * @throws TransactionFailedException
     */
    public TransactionModel debitTransaction(SendMoneyModel sendMoneyModel, UserModel userModelSender, UserModel userModelReceiver) throws TransactionFailedException {
        UserBallanceModel userBallanceModel=mobileUserBallanceService.getUserBallanceByUserID(sendMoneyModel.getSenderuserID());
        float debitorBalance=debit(sendMoneyModel.getTransactionAmount(),userBallanceModel.getAvailableBalance());
        TransactionModel transactionModel=new TransactionModel(0,
                sendMoneyModel.getSenderuserID(),
                sendMoneyModel.getTransactionType(),
                0,sendMoneyModel.getTransactionAmount(),
                0,
                userModelReceiver.getFirstname()+" "+userModelReceiver.getLastname(),
                userModelSender.getId(),
                userModelSender.getFirstname()+" "+userModelSender.getLastname()
                );
        transactionModel.setAvailableBalance(debitorBalance);
        TransactionModel transactionModelSaved=transactionRepository.save(transactionModel);

        if(transactionModelSaved!=null&&transactionModelSaved.getId()!=0)
        {
            return transactionModel;
        }

        throw new TransactionFailedException(TRANSACTION_FAILED);
    }

    /**
     * credit amount into transaction
     * @param sendMoneyModel
     * @param userModelReceiver
     * @param userModelSender2 
     * @return
     * @throws TransactionFailedException
     */
    public TransactionModel creditTransaction(SendMoneyModel sendMoneyModel, UserModel userModelReceiver, UserModel userModelSender) throws TransactionFailedException {
        UserBallanceModel userBallanceModel=mobileUserBallanceService.getUserBallanceByUserID(sendMoneyModel.getReceiveruserID());
        float creditorBalance=credit(sendMoneyModel.getTransactionAmount(),userBallanceModel.getAvailableBalance());
        TransactionModel transactionModel=new TransactionModel(0,
                sendMoneyModel.getReceiveruserID(),
                sendMoneyModel.getTransactionType(),
                sendMoneyModel.getTransactionAmount(),
                0,
                userModelReceiver.getId(),
                userModelReceiver.getFirstname()+" "+userModelReceiver.getLastname(),
                0,
                userModelSender.getFirstname()+" "+userModelSender.getLastname());
        TransactionModel transactionModelSaved=transactionRepository.save(transactionModel);
        transactionModel.setAvailableBalance(creditorBalance);

        if(transactionModelSaved!=null&&transactionModelSaved.getId()!=0)
        {
            return transactionModel;
        }

        throw new TransactionFailedException(TRANSACTION_FAILED);
    }

    private float debit(float amount,float balance){
        return balance-amount;
    }

    private float credit(float amount,float balance){
        return balance+amount;
    }
}
