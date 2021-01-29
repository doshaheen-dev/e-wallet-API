package com.tml.poc.Wallet.restController;


import com.azure.core.annotation.Post;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.models.transaction.SendMoneyModel;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.repository.TransactionRepository;
import com.tml.poc.Wallet.services.MPinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MPinServices mPinServices;


    @Post("/sendMoney")
    private Object sendMoneyToReceipient(SendMoneyModel sendMoneyModel) throws BadPaddingException, ResourceNotFoundException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException {
//        MPINModel mpinModel=new MPINModel();
//        mpinModel.setmPin(sendMoneyModel.getMpin());
//        mpinModel.setUserID(sendMoneyModel.getSenderuserID());
//        if(mPinServices.isMPINVerified(mpinModel)){
//
//            TransactionModel transactionModel=new TransactionModel(0,
//                    sendMoneyModel.getSenderuserID(),
//                    sendMoneyModel.getTransactionType(),
//                    0,
//                    sendMoneyModel.getTransactionAmount()
//                    );
//        }

        return "";
    }
}
