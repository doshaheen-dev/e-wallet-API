package com.tml.poc.Wallet.services;

import com.google.gson.Gson;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.models.transaction.SendMoneyModel;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.repository.SendMoneyRepository;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.utils.CommonMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class SendMoneyService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MPinServices mPinServices;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private SendMoneyRepository sendMoneyRepository;

    @Autowired
    private CommonMethods commonMethods;


    public String sendMoneyToTransaction(SendMoneyModel sendMoneyModel) throws BadPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException {
       return commonMethods.plainTestToCipherText(new Gson().toJson(sendMoneyRepository.save(sendMoneyModel)));
   }

}
