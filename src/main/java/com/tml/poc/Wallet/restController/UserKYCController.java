package com.tml.poc.Wallet.restController;

import com.tml.poc.Wallet.models.UserKYCModel;
import com.tml.poc.Wallet.repository.UserKYCRepository;
import com.tml.poc.Wallet.services.UserKYCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("kyc/user")
public class UserKYCController {

    @Autowired
    private UserKYCService userKYCService;

    @PostMapping("/apply")
    private ResponseEntity applyUserKYC(@Valid @RequestBody UserKYCModel userKYCModel){
        return userKYCService.applyUserKYC(userKYCModel);
    }

    @PostMapping("/approval")
    private ResponseEntity getApprovalUserKYC(@Valid @RequestBody UserKYCModel userKYCModel){
        return userKYCService.applyUserKYC(userKYCModel);
    }
}
