package com.tml.poc.Wallet.restController;


import com.azure.core.annotation.Post;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.exception.TransactionFailedException;
import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.models.transaction.SendMoneyModel;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.repository.TransactionRepository;
import com.tml.poc.Wallet.services.MPinServices;
import com.tml.poc.Wallet.services.TransactionService;
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

    @PostMapping("/sendMoney")
    public Object sendMoneyToReceipient(@RequestBody SendMoneyModel sendMoneyModel) throws BadPaddingException,
            ResourceNotFoundException, InvalidKeyException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            InvalidKeySpecException, TransactionFailedException {

        return transactionService.sendMoneyTransaction(sendMoneyModel);
    }


}
