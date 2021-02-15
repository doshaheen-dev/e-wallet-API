package com.tml.poc.Wallet.services;

import java.util.Optional;

import javax.validation.Valid;

import com.tml.poc.Wallet.models.OTPModel;
import com.tml.poc.Wallet.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tml.poc.Wallet.components.EmailComponant;
import com.tml.poc.Wallet.exception.InvalidInputException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.jwt.JwtTokenUtil;
import com.tml.poc.Wallet.models.UserLoginModule;
import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.models.UserRegistrationModel;
import com.tml.poc.Wallet.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataReturnUtil dataReturnUtils;

    @Autowired
    private CommonMethods cmUtils;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ValidationUtils validUtils;

    @Value("${otp.expiretime.miliseco}")
    private long otpExpireTime;


    @Autowired
    private EmailComponant emailCompo;

    @Autowired
    private OTPService otpService;

    /**
     * here new User Registration is going to be done only access to mobile Number
     * and country code and we are checking it is present into database or not
     *
     * @param
     * @return
     * @throws InvalidInputException
     * @throws ResourceNotFoundException
     */
    public ResponseEntity doUserRegstration(@Valid UserRegistrationModel userRegistrationModel)
            throws InvalidInputException, ResourceNotFoundException {
        String message = "";
        if (!validUtils.isValidEmail(userRegistrationModel.getEmailid())) {
            message = message + "Emailid Is not valid | ";
        }
        if (!validUtils.isMobileNumber(userRegistrationModel.getMobileNumber())) {
            message = message + " Mobile Number Is not valid ";
        }
        if (!message.equals("")) {
            throw new InvalidInputException(message);
        }
        Optional<UserModel> userOptional = null;
        userOptional = userRepository.findByEmailid(userRegistrationModel.getEmailid());
        if (userOptional.isPresent()) {
            message = message + " EmailId is Already Present |";
        }
        if (userRepository.findByMobileNumber(userRegistrationModel.getMobileNumber()).isPresent()) {
            message = message + " Mobile number is Already Present ";
        }
        if (message.equals("")) {
            UserModel usermodel = new UserModel();

            /**
             * create OTP
             */

            userRegistrationModel.setOTP(cmUtils.generateOTP());

            usermodel.setSaltKey(new PasswordUtils().getSalt(50));
            usermodel.setEmailid(userRegistrationModel.getEmailid());
            usermodel.setMobileNumber(userRegistrationModel.getMobileNumber());
            usermodel.setActive(false);

            setOTP(usermodel,userRegistrationModel);
            /**
             * to send Email
             */
            emailCompo.sendOTPEmail(usermodel.getEmailid(), userRegistrationModel.getOTP());

            return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(userRegistrationModel));
        } else {
            throw new InvalidInputException(message);
        }

    }

    private OTPModel createOTPModel(long userID){
        OTPModel otpModel=new OTPModel();
        otpModel=otpService.getUserOTPCreate(userID);
        return  otpModel;
    }

    private UserLoginModule setOTP(UserModel usermodel, UserLoginModule userLoginModule)
    {
        /**
         * save and Get UserID for OTP
         */
        UserModel userModelSave=userRepository.save(usermodel);

        /**
         * create OTP and set UserID
         */
        OTPModel otpModel=createOTPModel(userModelSave.getId());
        userLoginModule.setOtp(otpModel.getOtp());
        userModelSave=userRepository.save(usermodel);


        return  userLoginModule;
    }


    private UserRegistrationModel setOTP(UserModel usermodel, UserRegistrationModel userRegistrationModel)
    {
        /**
         * save and Get UserID for OTP
         */
        UserModel userModelSave=userRepository.save(usermodel);

        /**
         * create OTP and set UserID
         */
        OTPModel otpModel=createOTPModel(userModelSave.getId());
        userRegistrationModel.setOTP(otpModel.getOtp());
        userModelSave.setUserOtpId(otpModel.getId());
        userModelSave=userRepository.save(userModelSave);


        return  userRegistrationModel;
    }


    /**
     * to ReSend Otp set here
     *
     * @param userCred
     * @return
     * @throws InvalidInputException
     * @throws ResourceNotFoundException
     */
    public ResponseEntity doResendOTP(String userCred)
            throws InvalidInputException, ResourceNotFoundException {
        String message = "";
        Optional<UserModel> userOpt = null;
        if (validUtils.isValidEmail(userCred)) {
            userOpt = userRepository.findByEmailid(userCred);
        }
        if (validUtils.isMobileNumber(userCred)) {
            userOpt = userRepository.findByMobileNumber(userCred);
        }


        if (userOpt.isPresent()) {
            UserModel usermodel = userOpt.get();
            UserLoginModule userLoginModule = new UserLoginModule();

            userLoginModule.setUserCred(userCred);


            UserLoginModule userLoginModule1=setOTP(usermodel,userLoginModule);
            userLoginModule.setOtp(userLoginModule1.getOtp());

            /**
             * to send Email
             */
            emailCompo.sendOTPEmail(usermodel.getEmailid(), userLoginModule.getOtp());



            return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(userLoginModule));
        } else {

            throw new InvalidInputException(message);

        }

    }

    /**
     * after registration check mobile number is already present and then checking
     * OTP
     *
     * @param
     * @return
     * @throws InvalidInputException
     * @throws ResourceNotFoundException
     */
    public ResponseEntity doUserRegistrationVerification(UserRegistrationModel userLoginModule)
            throws InvalidInputException, ResourceNotFoundException {
        UserModel usermodel;
        Optional<UserModel> userOptional = userRepository.findByEmailid(userLoginModule.getEmailid());
        if (userOptional.isPresent()) {
            usermodel = userOptional.get();
            if (otpService.verifyOTP(usermodel.getUserOtpId(), userLoginModule.getOTP())) {
                usermodel.setActive(true);
                usermodel.setMobileVerified(true);
                usermodel.setEmailVerified(false);
                usermodel = userRepository.save(usermodel);
                final String token = jwtTokenUtil.generateToken1(usermodel.getQrCode());
                return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForAuthRestAPI(usermodel, token));
            } else {
                throw new InvalidInputException("Invalid OTP");
            }
        } else {
            throw new ResourceNotFoundException("User Not Found");

        }
    }

//
//    /**
//     * verify OTP
//     *
//     * @param usermodel
//     * @param userLoginModule
//     * @return
//     * @throws InvalidInputException
//     */
//    private boolean verifyOTP(UserModel usermodel, UserRegistrationModel userLoginModule)
//            throws InvalidInputException {
//        if (usermodel != null && usermodel.getOtp().equals(userLoginModule.getOTP())) {
//            Date expireDate = new Date((usermodel.getOtpCreated().getTime() + otpExpireTime));
//            Date currentDate = new Date(System.currentTimeMillis());
//            if (currentDate.before(expireDate)) {
//                return true;
//            } else {
//                throw new InvalidInputException("OTP Expired");
//            }
//        } else {
//            throw new InvalidInputException("Invalid OTP");
//        }
//    }


    /**
     * update user as per given usermodel from controller
     *
     * @param id
     * @param userModel
     * @return
     */
    public Object doUserUpdate(long id, UserModel userModel,boolean isMobileUpdat) {
        if (userModel != null) {
            Optional<UserModel> userModelEntity = userRepository.findById(id);
            if (userModelEntity.isPresent()) {
                UserModel userModelDB = userModelEntity.get();
                userModel.setId(userModelDB.getId());
                if(!isMobileUpdat) {
                    userModel.setMobileNumber(userModelDB.getMobileNumber());
                    userModel.setEmailid(userModelDB.getEmailid());
                }
                if (userModel.getFirstname().isEmpty() ||
                        userModel.getLastname().isEmpty()) {
                    userModel.setProfileComplete(false);
                } else {
                    userModel.setProfileComplete(true);
                }

                return dataReturnUtils.setDataAndReturnResponseForRestAPI(userRepository.save(userModel));

            } else {
                return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "User Not Found");
            }
        } else {
            return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "No Object Found");
        }
    }

    /**
     * get User By Id
     * which one called from another service
     *
     * @param id
     * @return
     */
    public Object doGetUserById(long id) {
        if (id > 0) {
            Optional<UserModel> userModelEntity = userRepository.findById(id);
            if (userModelEntity.isPresent()) {
                UserModel userModelDB = userModelEntity.get();

                return dataReturnUtils.setDataAndReturnResponseForRestAPI(userModelDB);
            } else {
                return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "User Not Found");
            }
        } else {
            return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "Id not Found");
        }
    }

    /**
     * get User By Id
     * which one called from another service
     *
     * @param id
     * @return
     */
    public boolean isGetUserById(long id) throws ResourceNotFoundException {
        if (id > 0) {
            Optional<UserModel> userModelEntity = userRepository.findById(id);
            if (userModelEntity.isPresent()) {
                UserModel userModelDB = userModelEntity.get();
                if(userModelDB.isActive()) {
                    return true;
                }else{
                    throw  new ResourceNotFoundException("User is InActive");
                }
            } else {
                throw  new ResourceNotFoundException("User Not Found");
            }
        } else {
            throw new ResourceNotFoundException("Id not Found");
        }
    }

    /**
     * delete user here
     * user will not delete directly but it will soft delete from DB
     *
     * @param id
     * @return
     */
    public Object doDeleteUserById(long id) {
        if (id > 0) {
            Optional<UserModel> userModelEntity = userRepository.findById(id);
            if (userModelEntity.isPresent()) {
                UserModel userModelDB = userModelEntity.get();

                userModelDB.setActive(false);
                return dataReturnUtils.setDataAndReturnResponseForRestAPI(userRepository.save(userModelDB));
            } else {
                return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "User Not Found");
            }
        } else {
            return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "Id not Found");
        }
    }

    /**
     * images will be remove from DB table user
     *
     * @param id
     * @return
     */
    public Object doRemoveImageUserById(long id) {
        if (id > 0) {
            Optional<UserModel> userModelEntity = userRepository.findById(id);
            if (userModelEntity.isPresent()) {
                UserModel userModelDB = userModelEntity.get();

                userModelDB.setProfile_image("");
                return dataReturnUtils.setDataAndReturnResponseForRestAPI(userRepository.save(userModelDB));
            } else {
                return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "User Not Found");
            }
        } else {
            return dataReturnUtils.setDataAndReturnResponseForRestAPI(null, "Id not Found");
        }
    }



    public UserModel findUserByUserID(long userid) throws ResourceNotFoundException {
        if (userid > 0) {
            Optional<UserModel> userModelEntity = userRepository.findById(userid);
            if (userModelEntity.isPresent()) {
                return userModelEntity.get();
            } else {
               throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
            }
        } else {
            throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);

        }
    }
}
