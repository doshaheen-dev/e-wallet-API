package com.tml.poc.Wallet.services;


import com.google.gson.Gson;
import com.tml.poc.Wallet.models.kycCenter.KycCenterModel;
import com.tml.poc.Wallet.models.reponse.DataModelResponce;
import com.tml.poc.Wallet.repository.KycCenterRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

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

    public Object findKYCCenterByLatLon(double lat, double lon, double distanceInKM){
        List<KycCenterModel> kycCenterModelListReturn=new ArrayList<>();
        List<KycCenterModel> kycCenterModelList=kycCenterRepository.findAll();



        for(KycCenterModel kycCenterModel:kycCenterModelList){
           if(distance(kycCenterModel.getLatitude(),kycCenterModel.getLongitude(),
                    lat,lon,"K")<distanceInKM){
               kycCenterModelListReturn.add(kycCenterModel);
           }
        }
        System.out.println("orig"+new Gson().toJson(kycCenterModelList));
        System.out.println("new list"+new Gson().toJson(kycCenterModelListReturn));

        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(
                kycCenterModelListReturn));
    }


    private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            System.out.println("distt:"+dist);
            return (dist);
        }
    }




}
