package com.tml.poc.Wallet.services;


import com.google.gson.Gson;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.firebase.FCMMessageBuilder;
import com.tml.poc.Wallet.models.notification.FirebaseTokenModel;
import com.tml.poc.Wallet.models.notification.PushNotificationRequest;
import com.tml.poc.Wallet.models.transaction.RequestMoneyModel;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import com.tml.poc.Wallet.repository.FirebaseRepository;
import com.tml.poc.Wallet.repository.RequestMoneyRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

/**
 * This requestMoney Service is use to identify request money from another user
 */
@Service
public class RequestMoneyService {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestMoneyRepository requestMoneyRepository;

    @Autowired
    private FirebaseRepository firebaseRepository;

    @Autowired
    private PushNotificationService pushNotificationService;

    /**
     * check RequestMoney call
     * and send Notification
     * @param requestMoneyModel
     * @return
     * @throws ResourceNotFoundException
     */
    public Object requestMoney(RequestMoneyModel requestMoneyModel) throws ResourceNotFoundException {
        /**
         * TODO: requester ID check
         */
        UserModel reqFrom=requesterUserPresent(requestMoneyModel.getRequesterUserId().getId());

        /**
         * TODO: Check Requestie ID
         */
        UserModel reqTo=requestToUserPresent(requestMoneyModel.getRequestToUserId().getId());

        /**
         * TODO:Save Request Money
         */
        RequestMoneyModel requestMoneyModelSave=requestMoneyRepository.save(requestMoneyModel);

        /**
         * TODO: send request now by Firebase
         */
        requestMoneyModelSave.requesterUserId=reqFrom;
        requestMoneyModelSave.requestToUserId=reqTo;
        sendNotificationToRequestie(requestMoneyModelSave);


        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
                requestMoneyModelSave,
                "Request Sent"));

    }

    /**
     * get Request sender User Info
     * @param senderUserID
     * @return
     * @throws ResourceNotFoundException
     */
    private UserModel requesterUserPresent(long senderUserID) throws ResourceNotFoundException {
        return userService.isGetUserById(senderUserID);

    }

    /**
     * get Request_To user info
     * @param receiverUserID
     * @return
     * @throws ResourceNotFoundException
     */
    private UserModel requestToUserPresent(long receiverUserID) throws ResourceNotFoundException {

        return userService.isGetUserById(receiverUserID);
    }

    /**
     * Send Firebase Notification to mobile user
     * @param requestMoneyModel
     * @return
     */
    public Object sendNotificationToRequestie(RequestMoneyModel requestMoneyModel){
        try {
            List<FirebaseTokenModel> firebaseTokenModelOptional
                    =firebaseRepository.findAllByUserId(
                            String.valueOf(requestMoneyModel.getRequestToUserId().getId()),Sort.by("firebaseTokenModelId").descending());
            List<String> tokenList=new ArrayList<>();
            System.out.println("requestMoneyModel.getRequestToUserId().getId()="+requestMoneyModel.getRequestToUserId().getId());
            for(FirebaseTokenModel firebaseTokenModel: firebaseTokenModelOptional) {
                tokenList.add(firebaseTokenModel.getFcmToken());
            }
            Map<String, String> data=new HashMap<String,String>();
            data.put("requestFrom", String.valueOf(requestMoneyModel.getRequesterUserId().getId()));
            data.put("requestTo", String.valueOf(requestMoneyModel.getRequestToUserId().getId()));
            data.put("requestPayload", new Gson().toJson(requestMoneyModel));
            PushNotificationRequest pushNotificationRequest=new PushNotificationRequest(
                    "Request Money",
                    "You Have Money Request of "+requestMoneyModel.getTransactionAmount(),
                    "",
                    tokenList,
                    data,
                    "Request Money"
                    );

            pushNotificationService.sendPushNotificationToMultipleUsers(pushNotificationRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }



    public Slice<RequestMoneyModel> getSentRequest(long sendReqUserID,Date fromdate,
                                     Date toDate,
                                     Integer pageSize,
                                     Integer pageNo,
                                     String sort) throws ResourceNotFoundException {
        UserModel userModel=userService.isGetUserById(sendReqUserID);

        Pageable paging = PageRequest.of(pageNo, pageSize,  Sort.by(sort).descending());

        Slice<RequestMoneyModel> pagedResult
                = requestMoneyRepository.findAllByRequesterUserIdAndCreatedAtBetween(
                userModel,fromdate,toDate,paging);

        return pagedResult;


    }

    public Slice<RequestMoneyModel> getSentRequestFromAnotherUser(long getRequserId,Date fromdate,
                                     Date toDate,
                                     Integer pageSize,
                                     Integer pageNo,
                                     String sort) throws ResourceNotFoundException {

        UserModel userModel=userService.isGetUserById(getRequserId);

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sort).descending());

        Slice<RequestMoneyModel> pagedResult
                = requestMoneyRepository.findAllByRequestToUserIdAndCreatedAtBetween(
                userModel,fromdate,toDate,paging);

        return pagedResult;
    }

}
