package com.tml.poc.Wallet.restController;


import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.recharge.AddCashToWalletModel;
import com.tml.poc.Wallet.services.WalletRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobileuser/recharge")
public class RechargeController {

    @Autowired
    private WalletRechargeService walletRechargeService;

    @GetMapping("/{mobileUserId}/getBallance")
    public Object getUserBallance(@PathVariable(name = "mobileUserId") long mobileUserId)
            throws ResourceNotFoundException {

        return walletRechargeService.getUserBallance(mobileUserId);
    }


    @PostMapping("/cash/add")
    public Object addCashToWallet(@RequestBody AddCashToWalletModel addCashToWalletModel){

        return addCashToWalletModel;
    }
}
