package com.tml.poc.Wallet.restController;

import com.tml.poc.Wallet.models.kycCenter.KycCenterModel;
import com.tml.poc.Wallet.services.KycCenterService;
import io.swagger.annotations.ApiParam;
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

    @GetMapping("/search")
    private Object searchAllKycCenter(
            @RequestParam(name = "lat")double latitude,
            @RequestParam(name = "lon") double longitude,
            @RequestParam(name = "distanceInKM", defaultValue = "5") double distanceInKM) {
        return kycCenterService.findKYCCenterByLatLon(latitude,longitude,distanceInKM);
    }

}
