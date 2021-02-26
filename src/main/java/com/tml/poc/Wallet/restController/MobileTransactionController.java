package com.tml.poc.Wallet.restController;


import com.azure.core.annotation.Post;
import com.azure.core.annotation.QueryParam;
import com.google.gson.Gson;
import com.tml.poc.Wallet.converter.JsonToMapConverter;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.exception.TransactionFailedException;
import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.models.reponse.EncryptDataModel;
import com.tml.poc.Wallet.models.transaction.RequestMoneyModel;
import com.tml.poc.Wallet.models.transaction.SendMoneyModel;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.repository.TransactionRepository;
import com.tml.poc.Wallet.serviceImpl.TransactionPageImplService;
import com.tml.poc.Wallet.services.MPinServices;
import com.tml.poc.Wallet.services.RequestMoneyService;
import com.tml.poc.Wallet.services.TransactionService;
import com.tml.poc.Wallet.utils.CommonMethods;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonToMapConverter.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private RequestMoneyService requestMoneyService;

    @Autowired
    private CommonMethods commonMethods;

    @Autowired
    private TransactionPageImplService transactionPageImplService;

    @PostMapping("/sendMoney")
    public Object sendMoneyToReceipient(@RequestBody EncryptDataModel encryptedPayload) throws BadPaddingException,
            ResourceNotFoundException, InvalidKeyException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            InvalidKeySpecException, TransactionFailedException {

        SendMoneyModel sendMoneyModel = new Gson().fromJson(commonMethods.encryptionStringToJson(encryptedPayload.getEncryptedData())
                , SendMoneyModel.class);

        return transactionService.sendMoneyTransaction(sendMoneyModel);
    }

    @PostMapping("/requestMoney")
    public Object requestMoneyToReceipient(@RequestBody EncryptDataModel encryptedPayload) throws BadPaddingException,
            ResourceNotFoundException, InvalidKeyException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            InvalidKeySpecException, TransactionFailedException {

        RequestMoneyModel requestMoneyModel
                = new Gson().fromJson(
                commonMethods.encryptionStringToJson(
                        encryptedPayload.getEncryptedData())
                , RequestMoneyModel.class);

        LOGGER.error(new Gson().toJson(requestMoneyModel));

        return requestMoneyService.requestMoney(requestMoneyModel);
    }

    @GetMapping("/search/mobileUser")
    public Object getTransactionSearch(@RequestParam(defaultValue = "0", name = "userId") long userid,
                                       @RequestParam(defaultValue = "0", name = "pageNo") int pageNo,
                                       @RequestParam(defaultValue = "20", name = "pageSize") int pageSize) {


        return ResponseEntity.ok(new DataReturnUtil()
                .setDataAndReturnResponseForRestAPI(transactionPageImplService
                        .getAllTransactions(userid,
                                pageNo,
                                pageSize,
                                "userID")));
    }


}
