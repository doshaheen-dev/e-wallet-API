package com.tml.poc.Wallet.restController;


import com.azure.core.annotation.Post;
import com.azure.core.annotation.QueryParam;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tml.poc.Wallet.converter.JsonToMapConverter;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.exception.TransactionFailedException;
import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.models.reponse.EncryptDataModel;
import com.tml.poc.Wallet.models.transaction.RequestMoneyModel;
import com.tml.poc.Wallet.models.transaction.RequestMoneyReqModel;
import com.tml.poc.Wallet.models.transaction.SendMoneyModel;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.repository.TransactionRepository;
import com.tml.poc.Wallet.serviceImpl.TransactionPageImplService;
import com.tml.poc.Wallet.services.MPinServices;
import com.tml.poc.Wallet.services.RequestMoneyService;
import com.tml.poc.Wallet.services.TransactionService;
import com.tml.poc.Wallet.utils.CommonMethods;
import com.tml.poc.Wallet.utils.Constants;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import java.util.Date;
import java.util.HashMap;

/**
 * Mobile Wallet Transactions for Mobile User
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    /**
     * Send Money requesr from One User To Another
     * @param encryptedPayload
     * @return
     * @throws BadPaddingException
     * @throws ResourceNotFoundException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeySpecException
     * @throws TransactionFailedException
     */
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

    /**
     * Request Money from One User  To Another User
     * @param encryptedPayload
     * @return
     * @throws BadPaddingException
     * @throws ResourceNotFoundException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeySpecException
     * @throws TransactionFailedException
     */
    @PostMapping("/requestMoney")
    public Object requestMoneyToReceipient(@RequestBody EncryptDataModel encryptedPayload) throws BadPaddingException,
            ResourceNotFoundException, InvalidKeyException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            InvalidKeySpecException, TransactionFailedException {

        RequestMoneyReqModel requestMoneyModel
                = new Gson().fromJson(
                commonMethods.encryptionStringToJson(
                        encryptedPayload.getEncryptedData())
                , RequestMoneyReqModel.class);

        RequestMoneyModel requestMoneyModel1=new RequestMoneyModel();
        requestMoneyModel1.setRequestToUserId(requestMoneyModel.getRequestToUserId());
        requestMoneyModel1.setRequesterUserId(requestMoneyModel.getRequesterUserId());
        requestMoneyModel1.setTransactionAmount(requestMoneyModel.getTransactionAmount());
        LOGGER.error(new Gson().toJson(requestMoneyModel));

        return requestMoneyService.requestMoney(requestMoneyModel1);
    }

    /**
     * Search Mobile user Transactions by user ID
     * @param userid
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/search/mobileUser")
    public Object getTransactionSearch(
            @RequestParam(defaultValue = "0", name = "userId") long userid,
            @RequestParam(defaultValue = "0", name = "pageNo") int pageNo,
            @RequestParam(defaultValue = "20", name = "pageSize") int pageSize) {

        return ResponseEntity.ok(new DataReturnUtil()
                .setDataAndReturnResponseForRestAPI(transactionPageImplService
                        .getAllTransactions(userid,
                                pageNo,
                                pageSize,
                                "id")));
    }


    /**
     * List of Requested Money by User ID which are Send
     * @param fromDate
     * @param toDate
     * @param userid
     * @param pageNo
     * @param pageSize
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/requestMoney/sent/getAll")
    public Object getRequestMoneyAll(
            @RequestParam( name = "fromDate",required = false)@DateTimeFormat(pattern= Constants.TIME_DATE) Date fromDate,
            @RequestParam( name = "toDate",required = false) @DateTimeFormat(pattern=Constants.TIME_DATE)Date toDate,
            @RequestParam(defaultValue = "0", name = "userId") long userid,
            @RequestParam(defaultValue = "0", name = "pageNo") int pageNo,
            @RequestParam(defaultValue = "20", name = "pageSize") int pageSize)
            throws ResourceNotFoundException {

        System.out.println("userId "+userid);

        return ResponseEntity.ok(new DataReturnUtil()
                .setDataAndReturnResponseForRestAPI(requestMoneyService
                        .getSentRequest(userid,
                                fromDate,
                                toDate,
                                pageSize,
                                pageNo,
                                "id")));
    }

    /**
     * List of Requested Money by User ID which are Requested to Given user
     * @param fromDate
     * @param toDate
     * @param userid
     * @param pageNo
     * @param pageSize
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/requestMoney/get/getAll")
    public Object getAllRequestMoneyFromAnotherUser(
            @RequestParam( name = "fromDate",required = false)
                @DateTimeFormat(pattern=Constants.TIME_DATE) Date fromDate,
            @RequestParam( name = "toDate",required = false)
                @DateTimeFormat(pattern=Constants.TIME_DATE) Date toDate,
            @RequestParam(defaultValue = "0", name = "userId") long userid,
            @RequestParam(defaultValue = "0", name = "pageNo") int pageNo,
            @RequestParam(defaultValue = "20", name = "pageSize") int pageSize)
            throws ResourceNotFoundException {

        System.out.println("userId "+userid);

        return ResponseEntity.ok(new DataReturnUtil()
                .setDataAndReturnResponseForRestAPI(requestMoneyService
                        .getSentRequestFromAnotherUser(userid,
                                fromDate,
                                toDate,
                                pageSize,
                                pageNo,
                                "id")));
    }




}
