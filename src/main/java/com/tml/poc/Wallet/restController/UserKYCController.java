package com.tml.poc.Wallet.restController;

import com.azure.core.annotation.QueryParam;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.usermodels.UserKYCModel;
import com.tml.poc.Wallet.services.UserKYCService;
import com.tml.poc.Wallet.utils.Constants;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Mobile USer KYC
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("kyc/user")
public class UserKYCController {

    @Autowired
    private UserKYCService userKYCService;

    /**
     * Mobile User Apply
     * @param userKYCModel
     * @return
     * @throws IOException
     * @throws ResourceNotFoundException
     */
    @PostMapping("/apply")
    private ResponseEntity applyUserKYC(
            @RequestBody UserKYCModel userKYCModel) throws IOException, ResourceNotFoundException {
        return userKYCService.applyUserKYC(userKYCModel);
    }


    /**
     * Mobile User Approval
     * @param userKYCModel
     * @return
     */
    @PutMapping("/approval")
    private ResponseEntity getApprovalUserKYC(
            @Valid @RequestBody UserKYCModel userKYCModel){
        return userKYCService.approveKYC(userKYCModel);
    }

//    @GetMapping("/search/user")
//    private ResponseEntity getApprovalUserKYC(
//            @RequestParam(value = "userId",required = false) long id){
//        return userKYCService.getUser(id);
//    }

    /**
     * Search and get all not approved
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @return
     */
    @GetMapping("/search/getAll/notApproved")
    private Object getAllApprovalNotApprovedUserKYC(
            @RequestParam(defaultValue = "0", name = "pageNo") int pageNo,
            @RequestParam(defaultValue = "20", name = "pageSize") int pageSize,
            @RequestParam(defaultValue = "id", name = "sortBy",required = false) String sortBy){
        return userKYCService.getAllNotCompleted(pageNo,pageSize,sortBy);
    }

}
