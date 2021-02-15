package com.tml.poc.Wallet.restController;

import com.google.gson.Gson;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.UserKYCModel;
import com.tml.poc.Wallet.repository.UserKYCRepository;
import com.tml.poc.Wallet.services.UserKYCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("kyc/user")
public class UserKYCController {

    @Autowired
    private UserKYCService userKYCService;

    @PostMapping("/apply")
    private ResponseEntity applyUserKYC( @RequestBody UserKYCModel userKYCModel) throws IOException, ResourceNotFoundException {

        return userKYCService.applyUserKYC(userKYCModel);
    }

    @PutMapping("/approval")
    private ResponseEntity getApprovalUserKYC(@Valid @RequestBody UserKYCModel userKYCModel){
        return userKYCService.approveKYC(userKYCModel);
    }
}
