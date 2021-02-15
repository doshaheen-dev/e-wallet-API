package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.utils.Constants;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WalletRechargeService {

    @Autowired
    private UserService userService;

    @Autowired
    private MobileUserBallanceService mobileUserBallanceService;

    public Object getUserBallance(long mobileUserID) throws ResourceNotFoundException {

        userService.findUserByUserID(mobileUserID);

        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
                mobileUserBallanceService.getUserBallanceByUserID(mobileUserID),
                Constants.WALLET_BALLANCE_SUCCESS));
    }

}
