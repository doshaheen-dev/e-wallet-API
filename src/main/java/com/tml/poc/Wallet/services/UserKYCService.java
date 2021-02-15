package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.UserKYCModel;
import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.repository.UserKYCRepository;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.s3Config.S3Wrapper;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.UnexpectedTypeException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.tml.poc.Wallet.utils.Constants.KYC_DOCUMENT_NOT_FOUND;

@Service
public class UserKYCService {

    @Autowired
    private UserKYCRepository userKYCRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private S3Wrapper s3Wrapper;

    public ResponseEntity applyUserKYC( UserKYCModel userKYCModel) throws IOException,UsernameNotFoundException,ResourceNotFoundException {
        try {
            Optional<UserModel> userModelOptional=userRepository.findAllById(userKYCModel.getUserId());
            if (userModelOptional.isPresent()) {
                UserModel userModel=userModelOptional.get();
                userKYCModel.setId(0);
                if(userKYCModel.getKycDocument()!=null&& !userKYCModel.getKycDocument().isEmpty()) {
                    userKYCModel.setKycDocument(s3Wrapper.uploadPrev("kyc-" + userKYCModel.getKycDocumentType() + userKYCModel.getUserId(),
                            userKYCModel.getKycDocument(),
                            userKYCModel.getKycDocumentExt()));
                }else
                {
                    throw new ResourceNotFoundException(KYC_DOCUMENT_NOT_FOUND);
                }
                if(userKYCModel.getKycPassportPhoto()!=null&& !userKYCModel.getKycPassportPhoto().isEmpty()) {
                    userKYCModel.setKycPassportPhoto(s3Wrapper.uploadPrev("kycPassport" + userKYCModel.getUserId(),
                            userKYCModel.getKycPassportPhoto(),
                            userKYCModel.getKycPassportPhotoExt()));
                }
                userKYCModel.setKYCDone(false);
                userModel.setKYCApplied(true);
                userRepository.save(userModel);
                return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(userKYCRepository.save(userKYCModel),"Document uploaded successfully. Sent to support for approval"));
            } else{
                throw new ResourceNotFoundException("User Not Found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());

        } catch (UnexpectedTypeException e){
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }
    }



    public ResponseEntity approveKYC(UserKYCModel userKYCModel) {
        Optional<UserModel> userModelOption=userRepository.findAllById(userKYCModel.getUserId());
        if (userModelOption.isPresent()) {
            UserModel userModel = userModelOption.get();

            if (!userModel.isIskycDone()) {
                return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(userKYCRepository.save(userKYCModel)));
            }else{
                return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(null,"Already KYC Done"));
            }
        }else{
            throw new UsernameNotFoundException("User Not Found");
        }
    }

    public ResponseEntity getAllKYCNotDone() {
        List<UserKYCModel> userModelOption=userKYCRepository.findAllByIsKYCDone(false);
        if (!userModelOption.isEmpty()) {

                return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(userModelOption));

        }else{
            throw new UsernameNotFoundException("User Not Found");
        }
    }

}
