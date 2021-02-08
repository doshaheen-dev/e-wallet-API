package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.exception.InvalidInputException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.OTPModel;
import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.repository.UserOTPRepository;
import com.tml.poc.Wallet.utils.CommonMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.tml.poc.Wallet.utils.Constants.*;

@Service
public class OTPService {

    @Autowired
    private UserOTPRepository userOTPRepository;

    @Value("${otp.expiretime.miliseco}")
    private long otpExpireTime;

    /**
     * Create OTP to User
     * @param userid
     * @return
     */
    public OTPModel getUserOTPCreate(long userid){
        String OTP=new CommonMethods().generateOTP();
        OTPModel otpModel=new OTPModel();
        otpModel.setOtp(OTP);
        otpModel.setUserID(userid);
        otpModel = userOTPRepository.save(otpModel);
        return otpModel;
    }


    /**
     * Create OTP to MPIN
     * @param mpinID
     * @return
     */
    public OTPModel getMPINOTPCreate(long mpinID){
        OTPModel otpModel=new OTPModel();
        otpModel.setOtp(new CommonMethods().generateOTP());
        otpModel.setMPinId(mpinID);

        otpModel = userOTPRepository.save(otpModel);

        return otpModel;
    }

    /**
     * MPIN OTP Verification
     * @return
     * @throws ResourceNotFoundException
     */
    public boolean verifyOTP(long otpId,String otp) throws ResourceNotFoundException {

        Optional<OTPModel> otpModelOptional=
                userOTPRepository.findAllById(otpId);
        if(!otpModelOptional.isPresent()){
            throw new ResourceNotFoundException(OTP_NOT_FOUND);
        }
        if(otp.equals(otpModelOptional.get().getOtp())){
            return true;
        }else {
            throw new ResourceNotFoundException(OTP_NOT_MATCHED);

        }
    }


//    /**
//     * get OTP By userid and check otp
//     * @param otpId
//     * @param otp
//     * @return
//     * @throws ResourceNotFoundException
//     * @throws InvalidInputException
//     */
//    public boolean verifyOTP(long otpId,String otp) throws ResourceNotFoundException, InvalidInputException {
//
//        Optional<OTPModel> otpModelOptional=
//                userOTPRepository.findById(otpId);
//        if(!otpModelOptional.isPresent()){
//            throw new ResourceNotFoundException("OTP Not Found");
//        }
//        if(verifyOTPByDate(otpModelOptional.get(),otp)){
//            return true;
//        }else {
//            throw new ResourceNotFoundException("OTP Not matched");
//        }
//    }


    /**
     * verifyDate And OTP
     * @param otpModel
     * @param otp
     * @return
     * @throws InvalidInputException
     */
    private boolean verifyOTPByDate(OTPModel otpModel, String  otp)
            throws InvalidInputException {
        if (otpModel != null && otpModel.getOtp().equals(otp)) {
            Date expireDate = new Date((otpModel.getCreatedAt().getTime() + otpExpireTime));
            Date currentDate = new Date(System.currentTimeMillis());
            if (currentDate.before(expireDate)) {
                return true;
            } else {
                throw new InvalidInputException(OTP_EXPIRED);
            }
        } else {
            throw new InvalidInputException(OTP_NOT_MATCHED);
        }
    }


}
