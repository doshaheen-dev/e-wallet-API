package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.models.transaction.SendMoneyModel;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SendMoneyService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MPinServices mPinServices;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public Object sendMoneyTransaction(SendMoneyModel sendMoneyModel)
            throws BadPaddingException,
            ResourceNotFoundException,
            InvalidKeyException, NoSuchAlgorithmException,
            IllegalBlockSizeException, NoSuchPaddingException,
            InvalidAlgorithmParameterException, InvalidKeySpecException {
        MPINModel mpinModel=new MPINModel();
        mpinModel.setmPin(sendMoneyModel.getMpin());
        mpinModel.setUserID(sendMoneyModel.getSenderuserID());
        if(mPinServices.isMPINVerified(mpinModel)){
                
        }


        return "";
    }


    private boolean isBallanceMoreThanTransAmount(float transAmount){

        return true;
    }

}
