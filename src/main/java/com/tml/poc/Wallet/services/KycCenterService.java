package com.tml.poc.Wallet.services;


import com.tml.poc.Wallet.models.kycCenter.KycCenterModel;
import com.tml.poc.Wallet.models.reponse.DataModelResponce;
import com.tml.poc.Wallet.repository.KycCenterRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class KycCenterService {

    @Autowired
    private KycCenterRepository kycCenterRepository;


    public ResponseEntity addKycCenter(KycCenterModel kycCenterModel){
        return ResponseEntity.ok(new DataReturnUtil()
                .setDataAndReturnResponseSuccess(kycCenterRepository.save(kycCenterModel),
                "Added Successfully"));
    }

    public ResponseEntity updateKycCenter(KycCenterModel kycCenterModel){
        return ResponseEntity.ok(new DataReturnUtil()
                .setDataAndReturnResponseSuccess(kycCenterRepository.save(kycCenterModel),
                        "Updated Successfully"));
    }

    public Object getAllKycCenter(){
        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(
                kycCenterRepository.findAll()));
    }




}
