package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.exception.InvalidInputException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.utilsmodels.OTPModel;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.models.reponse.DataFoundModel;
import com.tml.poc.Wallet.models.request.ChangeMPINModel;
import com.tml.poc.Wallet.repository.MPinRepository;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import static com.tml.poc.Wallet.utils.Constants.MPIN_CHANGED_SUCCESFULLY;

@Service
public class MPinServices {

    @Autowired
    private MPinRepository mMPinRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPService otpService;

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
        Optional<MPINModel> mpinModelOptionalByuserInactive=mMPinRepository
                .findByUserID(mpinModel.getUserID());
        if(mpinModelOptionalByuserInactive.isPresent()){
            mpinModel.setId(mpinModelOptionalByuserInactive.get().getId());
        }
//        mpinModel.setSecretkey(new PasswordUtils().getSalt(Constants.SALT_COUNT));
        mpinModel=setOTP(mpinModel);
        mpinModel.setmPin(new BCryptPasswordEncoder().encode(mpinModel.getmPin()));
        mpinModel=mMPinRepository.save(mpinModel);
        mpinModel.setmPin("");
       return ResponseEntity.ok(new DataReturnUtil()
               .setDataAndReturnResponseForRestAPI(mpinModel));
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
    public Object verifycreateMPinAccount(MPINModel mpinModel) throws ResourceNotFoundException,
            BadPaddingException, InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            NoSuchPaddingException, InvalidKeyException,
            InvalidKeySpecException, InvalidInputException {
        Optional<MPINModel> mpinModelOptional=mMPinRepository.findByUserID(mpinModel.getUserID());
        if(!mpinModelOptional.isPresent()){
            throw new ResourceNotFoundException("M-PIN Not Created");
        }
        mpinModel.setId(mpinModelOptional.get().getId());
        Optional<UserModel> userModelOptional=userRepository.findById(mpinModel.getUserID());
        if(!userModelOptional.isPresent()){
            throw new ResourceNotFoundException("Given User not found");
        }
        MPINModel usermodelFromDb=mpinModelOptional.get();

        otpService.verifyOTP(usermodelFromDb.getOtpId(),mpinModel.getOtp());
        usermodelFromDb.setVerified(true);
        usermodelFromDb.setActive(true);
        usermodelFromDb.setmPin(new BCryptPasswordEncoder().encode(mpinModel.getmPin()));

        mpinModel=mMPinRepository.save(usermodelFromDb);
        mpinModel.setmPin("");
        return ResponseEntity.ok(new DataReturnUtil()
                .setDataAndReturnResponseForRestAPI(mpinModel));
    }

    /**
     * verify MPIN while doing Transaction
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
     * @throws InvalidInputException
     */
    public Object checkPrevMPIN(MPINModel mpinModel) throws ResourceNotFoundException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, InvalidInputException {
        Optional<MPINModel> mpinModelOptional=mMPinRepository.findByUserIDAndIsActive(mpinModel.getUserID(),
                true);
        if(!mpinModelOptional.isPresent()){
            throw new ResourceNotFoundException("M-PIN Not Created");
        }
        mpinModel.setId(mpinModelOptional.get().getId());
        Optional<UserModel> userModelOptional=userRepository.findById(mpinModel.getUserID());
        if(!userModelOptional.isPresent()){
            throw new ResourceNotFoundException("Given User not found");
        }
        if(!new BCryptPasswordEncoder().matches(mpinModel.getmPin(),mpinModelOptional.get().getmPin())){
            throw new ResourceNotFoundException("M-PIN Not Matched");
        }
        return ResponseEntity.ok(new DataReturnUtil()
                .setDataAndReturnResponseForRestAPI(mMPinRepository.save(mpinModel)));
    }
    public ResponseEntity checkMPiCreatedOrNot(long mobileUserId) throws ResourceNotFoundException,
            BadPaddingException, InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            NoSuchPaddingException, InvalidKeyException,
            InvalidKeySpecException, InvalidInputException {
        DataFoundModel dataFoundModel=new DataFoundModel(mobileUserId,isMPINCreated(mobileUserId),"MPIN");
        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(dataFoundModel));
    }

    public boolean isMPINCreated(long mobileUserId) {
        Optional<MPINModel> mpinModelOptional=mMPinRepository.findByUserIDAndIsActive(mobileUserId,
                true);


        Optional<UserModel> userModelOptional=userRepository.findById(mobileUserId);
        if(!userModelOptional.isPresent()){
           return false;
        }
        if(!mpinModelOptional.isPresent()){
            return false;
        }
        return true;
    }


    public boolean isMPINVerified(String inputMPIN,MPINModel mpinModel) throws ResourceNotFoundException,
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
        if(!new BCryptPasswordEncoder().matches(inputMPIN,mpinModelOptional.get().getmPin())){
            throw new ResourceNotFoundException("M-PIN Not Matched");
        }
        return true;
    }

//    public String getEncryptedString(MPINModel mpinModel) throws NoSuchAlgorithmException,
//            InvalidKeySpecException, IllegalBlockSizeException,
//            InvalidKeyException, BadPaddingException,
//            InvalidAlgorithmParameterException,
//            NoSuchPaddingException {
//       String privateSecretKey= globalSecretKey+ mpinModel.getSecretkey();
//        String cipherText = AES.encrypt(mpinModel.getmPin(), privateSecretKey);
//        return cipherText;
//    }

//    public String getDecryptedString(MPINModel mpinModel) throws NoSuchAlgorithmException,
//            InvalidKeySpecException, IllegalBlockSizeException,
//            InvalidKeyException, BadPaddingException,
//            InvalidAlgorithmParameterException,
//            NoSuchPaddingException {
//        String privateSecretKey= globalSecretKey+mpinModel.getSecretkey();
//        String decryptedCipherText = AES.decrypt(mpinModel.getmPin(), privateSecretKey);
//        System.out.println("decryptedCipherText = "+decryptedCipherText);
//        return decryptedCipherText;
//    }

    private MPINModel setOTP(MPINModel mpinModel)
    {
        MPINModel mpinModelReturn;
        /**
         * save and Get UserID for OTP
         */
        MPINModel mpinModel1=mMPinRepository.save(mpinModel);
        mpinModelReturn=mpinModel1;
        /**
         * create OTP and set UserID
         */
        OTPModel otpModel=createOTPModel(mpinModel.getId());
        mpinModelReturn.setOtp(otpModel.getOtp());
        mpinModelReturn.setOtpId(otpModel.getId());
        return  mpinModelReturn;
    }

    private OTPModel createOTPModel(long mpinId){
        OTPModel otpModel=new OTPModel();
        otpModel=otpService.getMPINOTPCreate(mpinId);
        return  otpModel;
    }


    /**
     * to change mpin
     * @param changeMPINModel
     * @return
     * @throws InvalidInputException
     * @throws ResourceNotFoundException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     */
    public Object changeMPIN(ChangeMPINModel changeMPINModel) throws InvalidInputException, ResourceNotFoundException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        if(!changeMPINModel.getOldMPIN().equals(changeMPINModel.getNewMpin())){
            Optional<UserModel> userModelOptional=userRepository.findById(changeMPINModel.getUserID());
            Optional<MPINModel> mpinModelOptional=mMPinRepository.findByUserID(changeMPINModel.getUserID());
            if(!userModelOptional.isPresent()){
                throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
            }
            if(!mpinModelOptional.isPresent()){
                throw new ResourceNotFoundException(Constants.MPIN_NOT_FOUND);
            }

            MPINModel mpinModel=mpinModelOptional.get();
            isMPINVerified(changeMPINModel.getOldMPIN(),mpinModel);
            mpinModel.setmPin(changeMPINModel.getNewMpin());
            String mpinString=new BCryptPasswordEncoder().encode(mpinModel.getmPin());
            mpinModel.setmPin(mpinString);
            mpinModel=mMPinRepository.save(mpinModel);
           return ResponseEntity.ok(new DataReturnUtil()
                   .setDataAndReturnResponseSuccess(changeMPINModel,
                   MPIN_CHANGED_SUCCESFULLY));
        }else
        {
            throw new InvalidInputException("Old MPIN and New MPIN are same");
        }
    }

    /**
     * Forgot MPIN for User and send OTP
     */
    public Object forgotMPin(MPINModel mpinModel) throws ResourceNotFoundException,
            BadPaddingException,
            InvalidAlgorithmParameterException,
            NoSuchAlgorithmException,
            IllegalBlockSizeException, NoSuchPaddingException,
            InvalidKeyException, InvalidKeySpecException {

        Optional<MPINModel> mpinModelOptional=mMPinRepository
                .findByUserID(mpinModel.getUserID());
        if(!mpinModelOptional.isPresent()){
            throw new ResourceNotFoundException("MPIN Not Created");
        }
        Optional<UserModel> userModelOptional=userRepository.findById(mpinModel.getUserID());
        if(!userModelOptional.isPresent()){
            throw new ResourceNotFoundException("Given User not found");
        }
        Optional<MPINModel> mpinModelOptionalByuserInactive=mMPinRepository
                .findByUserID(mpinModel.getUserID());
        if(mpinModelOptionalByuserInactive.isPresent()){
            mpinModel.setId(mpinModelOptionalByuserInactive.get().getId());
        }
        mpinModel=setOTP(mpinModel);
        mpinModel.setmPin(mpinModelOptionalByuserInactive.get().getmPin());
        mpinModel=mMPinRepository.save(mpinModel);
        mpinModel.setmPin("");
        return ResponseEntity.ok(new DataReturnUtil()
                .setDataAndReturnResponseForRestAPI(mpinModel));
    }

}
