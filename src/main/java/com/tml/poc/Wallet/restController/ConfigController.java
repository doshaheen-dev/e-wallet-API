package com.tml.poc.Wallet.restController;

import com.tml.poc.Wallet.models.AppConfigModel;
import com.tml.poc.Wallet.repository.ConfigRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Autowired
    private ConfigRepository configRepository;

    @PostMapping("/add")
    public Object addNewConfigValue(@RequestBody AppConfigModel appConfigModel){

        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
                configRepository.save(appConfigModel)
        ,"Success"));
    }

//    @PostMapping("/update")
//    public Object updateConfigValue(@RequestBody AppConfigModel appConfigModel){
//        appConfigModel.setId(1);
//        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
//                configRepository.save(appConfigModel)
//                ,"Success"));
//    }

    @GetMapping("/get")
    public Object getConfigValues(){
        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
                configRepository.findAll()
                ,"Success"));
    }


}