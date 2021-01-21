package com.tml.poc.Wallet.restController;

import com.azure.core.annotation.Put;
import com.tml.poc.Wallet.models.kycCenter.KycCenterModel;
import com.tml.poc.Wallet.models.rolePrevilage.EmployeeRoleModel;
import com.tml.poc.Wallet.services.KycCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/kyc/center")
public class KycCenterController {

    @Autowired
    private KycCenterService kycCenterService;

    @PostMapping("/add")
    private Object addKycCenter(@Valid @RequestBody KycCenterModel kycCenterModel ) {
        return kycCenterService.addKycCenter(kycCenterModel);
    }

    @PutMapping("/update")
    private Object updateKycCenter(@Valid @RequestBody KycCenterModel kycCenterModel ) {
        return kycCenterService.updateKycCenter(kycCenterModel);
    }

    @GetMapping("/getAll")
    private Object getAllKycCenter() {
        return kycCenterService.getAllKycCenter();
    }

}
