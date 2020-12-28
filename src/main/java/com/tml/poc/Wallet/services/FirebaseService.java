package com.tml.poc.Wallet.services;

import com.google.gson.Gson;
import com.tml.poc.Wallet.models.request.FirebaseNotificationModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class FirebaseService {

    private String FCM_SERVER_KEY="/** fcm key**/";


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
}
