package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.exception.InvalidInputException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.repository.MPinRepository;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
public class MPinServices {

    @Autowired
    private MPinRepository mMPinRepository;
    @Autowired
    private UserRepository userRepository;

    @Value("${aes.secretkey}")
    private String globalSecretKey;

    /**
     * create MPIN for User and send OTP
     */
    public Object createMPin(MPINModel mpinModel) throws ResourceNotFoundException,
            BadPaddingException,
            InvalidAlgorithmParameterException,
            NoSuchAlgorithmException,
            IllegalBlockSizeException, NoSuchPaddingException,
            InvalidKeyException, InvalidKeySpecException {

        Optional<MPINModel> mpinModelOptional=mMPinRepository
                .findByUserIDAndIsActive(mpinModel.getUserID(),
                true);
        if(mpinModelOptional.isPresent()){
          throw new ResourceNotFoundException("MPIN Already Created");
        }
        Optional<UserModel> userModelOptional=userRepository.findById(mpinModel.getUserID());
        if(!userModelOptional.isPresent()){
            throw new ResourceNotFoundException("Given User not found");
        }
        mpinModel.setSecretkey(new PasswordUtils().getSalt(Constants.SALT_COUNT));
        mpinModel.setOtp(new CommonMethods().generateOTP());
        mpinModel.setmPin(getEncryptedString(mpinModel));
        mpinModel=mMPinRepository.save(mpinModel);
        mpinModel.setmPin("");
       return ResponseEntity.ok(new DataReturnUtil()
               .setDataAndReturnResponseForRestAPI(mMPinRepository.save(mpinModel)));
    }

    /**
     * verify user account with OTP for MPIN
     * @param mpinModel
     * @return
     * @throws ResourceNotFoundException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     */
    public Object verifycreateMPinAccount(MPINModel mpinModel) throws ResourceNotFoundException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, InvalidInputException {
        Optional<MPINModel> mpinModelOptional=mMPinRepository.findByUserIDAndIsActive(mpinModel.getUserID(),
                true);
        mpinModel.setId(mpinModelOptional.get().getId());
        if(!mpinModelOptional.isPresent()){
            throw new ResourceNotFoundException("M-PIN Not Created");
        }
        Optional<UserModel> userModelOptional=userRepository.findById(mpinModel.getUserID());
        if(!userModelOptional.isPresent()){
            throw new ResourceNotFoundException("Given User not found");
        }
        if(!mpinModel.getOtp().equals(mpinModelOptional.get().getOtp())){
            throw new InvalidInputException("Otp Not Matched");
        }
        mpinModel.setVerified(true);
        mpinModel.setActive(true);
        return ResponseEntity.ok(new DataReturnUtil()
                .setDataAndReturnResponseForRestAPI(mMPinRepository.save(mpinModel)));
    }

    public Object checkPrevMPIN(MPINModel mpinModel) throws ResourceNotFoundException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, InvalidInputException {
        Optional<MPINModel> mpinModelOptional=mMPinRepository.findByUserIDAndIsActive(mpinModel.getUserID(),
                true);
        if(!mpinModelOptional.isPresent()){
            throw new ResourceNotFoundException("M-PIN Not Created");
        }
//        if(mpinModel.isVerified()){
//            throw new InvalidInputException("Already Verified");
//        }
        mpinModel.setId(mpinModelOptional.get().getId());
        Optional<UserModel> userModelOptional=userRepository.findById(mpinModel.getUserID());
        if(!userModelOptional.isPresent()){
            throw new ResourceNotFoundException("Given User not found");
        }
        if(!mpinModel.getOtp().equals(getDecryptedString(mpinModelOptional.get()))){
            throw new ResourceNotFoundException("M-PIN Not Matched");
        }
        return ResponseEntity.ok(new DataReturnUtil()
                .setDataAndReturnResponseForRestAPI(mMPinRepository.save(mpinModel)));
    }
    
    public boolean isMPINVerified(MPINModel mpinModel) throws ResourceNotFoundException,
            BadPaddingException, InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            NoSuchPaddingException, InvalidKeyException,
            InvalidKeySpecException {
        Optional<MPINModel> mpinModelOptional=mMPinRepository
                .findByUserIDAndIsActive(mpinModel.getUserID(),
                true);
        if(!mpinModelOptional.isPresent()){
            throw new ResourceNotFoundException("M-PIN Not Created");
        }
        Optional<UserModel> userModelOptional=userRepository.findById(mpinModel.getUserID());
        if(!userModelOptional.isPresent()){
            throw new ResourceNotFoundException("Given User not found");
        }
        if(!mpinModel.getOtp().equals(getDecryptedString(mpinModelOptional.get()))){
            throw new ResourceNotFoundException("M-PIN Not Matched");
        }
        
        return true;
    }

    public String getEncryptedString(MPINModel mpinModel) throws NoSuchAlgorithmException,
            InvalidKeySpecException, IllegalBlockSizeException,
            InvalidKeyException, BadPaddingException,
            InvalidAlgorithmParameterException,
            NoSuchPaddingException {
       String privateSecretKey=
               globalSecretKey+
                       mpinModel.getSecretkey();

        String cipherText = AES.encrypt(mpinModel.getmPin(), privateSecretKey);

        return cipherText;
    }
    public String getDecryptedString(MPINModel mpinModel) throws NoSuchAlgorithmException,
            InvalidKeySpecException, IllegalBlockSizeException,
            InvalidKeyException, BadPaddingException,
            InvalidAlgorithmParameterException,
            NoSuchPaddingException {

        String privateSecretKey= globalSecretKey+mpinModel.getSecretkey();
        String decryptedCipherText = AES.encrypt(mpinModel.getmPin(), privateSecretKey);

        return decryptedCipherText;
    }


}
