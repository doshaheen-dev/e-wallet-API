package com.tml.poc.Wallet.restController;

import com.tml.poc.Wallet.models.notification.FirebaseTokenModel;
import com.tml.poc.Wallet.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * FCm Token Update
 */
@RestController
@RequestMapping("notification/")
public class NotificationController {

    @Autowired
    private FirebaseService firebaseService;

    @PutMapping("firebasetoken/update")
    private ResponseEntity updateFCMTokens(@Valid @RequestBody FirebaseTokenModel firebaseTokenModel){
        return firebaseService.updateFirebaseToken(firebaseTokenModel);
    }
}
