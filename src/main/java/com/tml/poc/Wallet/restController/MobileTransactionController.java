package com.tml.poc.Wallet.restController;


import com.azure.core.annotation.Post;
import com.google.gson.Gson;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.exception.TransactionFailedException;
import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.models.reponse.EncryptDataModel;
import com.tml.poc.Wallet.models.transaction.RequestMoneyModel;
import com.tml.poc.Wallet.models.transaction.SendMoneyModel;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.repository.TransactionRepository;
import com.tml.poc.Wallet.services.MPinServices;
import com.tml.poc.Wallet.services.RequestMoneyService;
import com.tml.poc.Wallet.services.TransactionService;
import com.tml.poc.Wallet.utils.CommonMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/transactions")
public class MobileTransactionController {

   @Autowired
   private TransactionService transactionService;

   @Autowired
   private RequestMoneyService requestMoneyService;

   @Autowired
   private CommonMethods commonMethods;

    @PostMapping("/sendMoney")
    public Object sendMoneyToReceipient(@RequestBody EncryptDataModel encryptedPayload) throws BadPaddingException,
            ResourceNotFoundException, InvalidKeyException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            InvalidKeySpecException, TransactionFailedException {

        SendMoneyModel sendMoneyModel=new Gson().fromJson(commonMethods.encryptionStringToJson(encryptedPayload.getEncryptedData())
                ,SendMoneyModel.class);

        return transactionService.sendMoneyTransaction(sendMoneyModel);
    }

    @PostMapping("/requestMoney")
    public Object requestMoneyToReceipient(@RequestBody EncryptDataModel encryptedPayload) throws BadPaddingException,
            ResourceNotFoundException, InvalidKeyException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            InvalidKeySpecException, TransactionFailedException {

        RequestMoneyModel requestMoneyModel
                =new Gson().fromJson(
                        commonMethods.encryptionStringToJson(
                                encryptedPayload.getEncryptedData())
                ,RequestMoneyModel.class);


        return requestMoneyService.requestMoney(requestMoneyModel);
    }




}
