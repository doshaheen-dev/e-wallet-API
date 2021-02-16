package com.tml.poc.Wallet.services;


import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.firebase.FCMMessageBuilder;
import com.tml.poc.Wallet.models.notification.FirebaseTokenModel;
import com.tml.poc.Wallet.models.notification.PushNotificationRequest;
import com.tml.poc.Wallet.models.transaction.RequestMoneyModel;
import com.tml.poc.Wallet.repository.FirebaseRepository;
import com.tml.poc.Wallet.repository.RequestMoneyRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

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
        requesterUserPresent(requestMoneyModel.getRequesterUserId());

        /**
         * TODO: Check Requestie ID
         */
        requestToUserPresent(requestMoneyModel.getRequestToUserId());

        /**
         * TODO: send request now by Firebase
         */
        sendNotificationToRequestie(requestMoneyModel);


        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
                requestMoneyRepository.save(requestMoneyModel),
                "Request Sent"));

    }

    private boolean requesterUserPresent(long senderUserID) throws ResourceNotFoundException {
        return userService.isGetUserById(senderUserID);

    }

    private boolean requestToUserPresent(long receiverUserID) throws ResourceNotFoundException {

        return userService.isGetUserById(receiverUserID);
    }

    private Object sendNotificationToRequestie(RequestMoneyModel requestMoneyModel){
        List<FirebaseTokenModel> firebaseTokenModelOptional
                =firebaseRepository.findAllByUserId(requestMoneyModel.getRequestToUserId());
        List<String> tokenList=new ArrayList<>();
        for(FirebaseTokenModel firebaseTokenModel: firebaseTokenModelOptional) {
            tokenList.add(firebaseTokenModel.getFcmToken());


        }
        Map<String, String> data=new HashMap<String,String>();
        data.put("requestFrom", String.valueOf(requestMoneyModel.getRequesterUserId()));
        data.put("requestTo", String.valueOf(requestMoneyModel.getRequestToUserId()));
        PushNotificationRequest pushNotificationRequest=new PushNotificationRequest(
                "Request Money",
                "You Have Money Request of "+requestMoneyModel.getTransactionAmount(),
                "",
                tokenList,
                data,
                "Request Money"
                );

        pushNotificationService.sendPushNotificationToSingleUser(pushNotificationRequest);
        return "";
    }

}
