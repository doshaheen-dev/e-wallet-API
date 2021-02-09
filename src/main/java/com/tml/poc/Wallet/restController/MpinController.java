package com.tml.poc.Wallet.restController;


import com.tml.poc.Wallet.exception.InvalidInputException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.services.MPinServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("api/mpin")
public class MpinController {

    @Autowired
    private MPinServices mPinServices;



    @ApiOperation(value = "Create M-PIN For New User")
    @PostMapping("/create")
    public Object createMpin(@RequestBody MPINModel mpinModel) throws ResourceNotFoundException,
            BadPaddingException, InvalidKeyException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            InvalidKeySpecException {
        return mPinServices.createMPin(mpinModel);
    }

    @ApiOperation(value = "Verify M-PIN From New User")
    @PostMapping("/create/otp/verify")
    public Object verifyMpinUser(@RequestBody MPINModel mpinModel) throws ResourceNotFoundException,
            BadPaddingException, InvalidKeyException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            InvalidKeySpecException, InvalidInputException {
        return mPinServices.verifycreateMPinAccount(mpinModel);
    }

    @ApiOperation(value = "For before transaction verify MPIN")
    @PostMapping("/verify")
    public Object verifyMpin(@RequestBody MPINModel mpinModel) throws ResourceNotFoundException,
            BadPaddingException, InvalidKeyException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            InvalidKeySpecException, InvalidInputException {
        return mPinServices.checkPrevMPIN(mpinModel);
    }

    @ApiOperation(value = "to check MPIN is Created or not")
    @GetMapping("/{userId}/verify")
    public Object isMpinCreated(@PathVariable(name = "userId")long userId) throws ResourceNotFoundException,
            BadPaddingException, InvalidKeyException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            InvalidKeySpecException, InvalidInputException {
        return mPinServices.checkMPiCreatedOrNot(userId);
    }



}
