package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.models.transaction.SendMoneyModel;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.repository.SendMoneyRepository;
import com.tml.poc.Wallet.repository.UserRepository;
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

   public SendMoneyModel sendMoneyToTransaction(SendMoneyModel sendMoneyModel)
   {
       return sendMoneyRepository.save(sendMoneyModel);
   }

}
