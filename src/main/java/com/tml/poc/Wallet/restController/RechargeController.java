package com.tml.poc.Wallet.restController;


import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.recharge.AddCashToWalletModel;
import com.tml.poc.Wallet.services.WalletRechargeService;
import com.tml.poc.Wallet.utils.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Mobile user Recharge
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/mobileuser/recharge")
public class RechargeController {

    @Autowired
    private WalletRechargeService walletRechargeService;

    /**
     * get Know mobile user Ballance
     * @param mobileUserId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/{mobileUserId}/getBallance")
    public Object getUserBallance(@PathVariable(name = "mobileUserId") long mobileUserId)
            throws ResourceNotFoundException {

        return walletRechargeService.getUserBallance(mobileUserId);
    }


    /**
     * add cash
     * TODO: Have to add Further Service
     * @param addCashToWalletModel
     * @return
     */
    @PostMapping("/cash/add")
    public Object addCashToWallet(@RequestBody AddCashToWalletModel addCashToWalletModel){

        return addCashToWalletModel;
    }
}
