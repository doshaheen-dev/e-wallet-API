package com.tml.poc.Wallet.restController;

import com.tml.poc.Wallet.models.kycCenter.KycCenterModel;
import com.tml.poc.Wallet.services.KycCenterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * For Physical KYC Center
 */
@RestController
@RequestMapping("/kyc/center")
public class KycCenterController {

    @Autowired
    private KycCenterService kycCenterService;

    /**
     * add KYC Center
     * @param kycCenterModel
     * @return
     */
    @PostMapping("/add")
    private Object addKycCenter(@Valid @RequestBody KycCenterModel kycCenterModel ) {
        return kycCenterService.addKycCenter(kycCenterModel);
    }

    /**
     * update KYC Center
     * @param kycCenterModel
     * @return
     */
    @PutMapping("/update")
    private Object updateKycCenter(@Valid @RequestBody KycCenterModel kycCenterModel ) {
        return kycCenterService.updateKycCenter(kycCenterModel);
    }

    /**
     * get all KYC Center
     * @return
     */
    @GetMapping("/getAll")
    private Object getAllKycCenter() {
        return kycCenterService.getAllKycCenter();
    }

    /**
     * Search KYC Center by Lat Long and Distance(KM) radius
     * @param latitude
     * @param longitude
     * @param distanceInKM
     * @return
     */
    @GetMapping("/search")
    @ApiOperation(value = "Search KYC Center by Lat Long and Distance(KM) radius")
    private Object searchAllKycCenter(
            @RequestParam(name = "lat")double latitude,
            @RequestParam(name = "lon") double longitude,
            @RequestParam(name = "distanceInKM", defaultValue = "5") double distanceInKM) {
        return kycCenterService.findKYCCenterByLatLon(latitude,longitude,distanceInKM);
    }

}
