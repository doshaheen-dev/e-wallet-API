package com.tml.poc.Wallet.restController;

import com.tml.poc.Wallet.models.utilsmodels.AppConfigModel;
import com.tml.poc.Wallet.models.utilsmodels.AppUpdateModel;
import com.tml.poc.Wallet.repository.ConfigRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Application Configuration Crud
 */
@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Autowired
    private ConfigRepository configRepository;

    /**
     * Add and Update App Config
     * @param appConfigModel
     * @return
     */
    @PostMapping("/add")
    public Object addNewConfigValue(@RequestBody AppConfigModel appConfigModel){
        List<AppConfigModel> appConfigModelList=configRepository.findAll(Sort.by("id").descending());
        if(appConfigModelList.size()!=0) {
            appConfigModel.setId(appConfigModelList.get(0).getId());
        }
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

    /**
     * get Config API
     * @return
     */
    @GetMapping("/get")
    @ApiOperation(value = "get Config API")
    public Object getConfigValues(){
        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
                configRepository.findAll().get(0)
                ,"Success"));
    }


    /**
     * get version
     * temp for FMCG
     */
    @GetMapping("/get/app/update")
    @ApiOperation("for new App update")
    public Object  getAppUpdate(){
        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
                new AppUpdateModel()
                ,"You have new Update, Please compare with your current one"));
    }


}
