package com.tml.poc.Wallet.restController;

import com.tml.poc.Wallet.enums.WebDashBoardFeatureModel;
import com.tml.poc.Wallet.utils.Constants;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * For Web Panel Feature
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/features")
public class GetAllFeatureListController {

    /**
     * Get FEATURE for Web ADMIN while adding role
     * @return
     */
    @GetMapping("/getAll")
    @ApiOperation(value = "Get FEATURE for Web ADMIN while adding role")
    public Object getAllFeature(){
        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(WebDashBoardFeatureModel.FEATURES_MODULE_LIST,"Found All Feature List"));
    }

}
