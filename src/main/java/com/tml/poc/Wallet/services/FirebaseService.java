package com.tml.poc.Wallet.services;

import com.google.gson.Gson;
import com.tml.poc.Wallet.models.notification.FirebaseTokenModel;
import com.tml.poc.Wallet.models.request.FirebaseNotificationModel;
import com.tml.poc.Wallet.repository.FirebaseRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;


@Service
public class FirebaseService {

    private String FCM_SERVER_KEY="/** fcm key**/";

    @Autowired
    private FirebaseRepository firebaseRepository;


    private void callFirebaseNotification()
    {
        FirebaseNotificationModel firebaseNotificationModel=new FirebaseNotificationModel();
        /**
         *  to send notification fill *FirebaseNotificationModel*  and send into body
         */
        final String uri = "https://fcm.googleapis.com/fcm/send";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authentication","key="+FCM_SERVER_KEY);

        HttpEntity<String> entity = new HttpEntity<String>(new Gson().toJson(firebaseNotificationModel), headers);

        restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        String result = restTemplate.getForObject(uri, String.class);


        System.out.println(result);
    }


    public ResponseEntity updateFirebaseToken(FirebaseTokenModel firebaseTokenModel){

        Optional<FirebaseTokenModel> firebaseTokenModelOptional=firebaseRepository.
                findByDeviceIDAndUserId(firebaseTokenModel.getDeviceID(),firebaseTokenModel.getUserId());
        if(firebaseTokenModelOptional.isPresent()){
            firebaseTokenModel.setFirebaseTokenModelId(firebaseTokenModelOptional.get().getFirebaseTokenModelId());
            firebaseTokenModel= firebaseRepository.save(firebaseTokenModel);
        }else {
            firebaseTokenModel= firebaseRepository.save(firebaseTokenModel);
        }


        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(firebaseTokenModel));
    }
}
