package com.tml.poc.Wallet.restController;

import com.tml.poc.Wallet.enums.WebDashBoardFeatureModel;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/features")
public class GetAllFeatureListController {


    @GetMapping("/getAll")
    public Object getAllFeature(){
        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(WebDashBoardFeatureModel.FEATURES_MODULE_LIST,"Found All Feature List"));
    }

}
