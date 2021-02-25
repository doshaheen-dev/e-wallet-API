package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.models.usermodels.KycDocumentModel;
import com.tml.poc.Wallet.models.usermodels.UserKYCModel;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import com.tml.poc.Wallet.repository.UserKYCRepository;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.s3Config.S3Wrapper;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.UnexpectedTypeException;
import java.io.IOException;
import java.util.ArrayList;
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

    public ResponseEntity applyUserKYC(UserKYCModel userKYCModel) throws IOException,
            UsernameNotFoundException,
            ResourceNotFoundException {
        try {
            Optional<UserModel> userModelOptional = userRepository.findAllById(userKYCModel.getKycUserId().getId());
            if (userModelOptional.isPresent()) {
                UserModel userModel = userModelOptional.get();
                userKYCModel.setId(0);
                if (userKYCModel.getDocumentModelList().size() == 0) {
                    throw new ResourceNotFoundException(KYC_DOCUMENT_NOT_FOUND);
                }
                for (KycDocumentModel kycDocumentModel : userKYCModel.getDocumentModelList()) {
                    if (kycDocumentModel.getDocumentName() != null && !kycDocumentModel.getDocumentName().isEmpty()
                            && kycDocumentModel.getDocument() != null && !kycDocumentModel.getDocument().isEmpty()) {
                        kycDocumentModel.setDocument(s3Wrapper.uploadPrev(kycDocumentModel.getDocumentName() + "-" + userKYCModel.getKycUserId().getId(),
                                kycDocumentModel.getDocument(),
                                kycDocumentModel.getDocumentExt()));
                    } else {
                        throw new ResourceNotFoundException(KYC_DOCUMENT_NOT_FOUND);
                    }
                }
                userKYCModel.setKYCDone(false);
                userModel.setKYCApplied(true);
                userRepository.save(userModel);
                return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(userKYCRepository.save(userKYCModel), "Document uploaded successfully. Sent to support for approval"));
            } else {
                throw new ResourceNotFoundException("User Not Found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());

        } catch (UnexpectedTypeException e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }
    }

    public ResponseEntity approveKYC(UserKYCModel userKYCModel) {
        Optional<UserModel> userModelOption = userRepository.findAllById(userKYCModel.getKycUserId().getId());
        if (userModelOption.isPresent()) {
            UserModel userModel = userModelOption.get();

            if (!userModel.isIskycDone()) {
                return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(userKYCRepository.save(userKYCModel)));
            } else {
                return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(null, "Already KYC Done"));
            }
        } else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }

//
//    /**
//     * get userKyc by kycNot Done
//     * @param id
//     * @return
//     */
//    public ResponseEntity getUser(long id) {
//        List<UserKYCModel> userKYCModelList;
//        if (id == 0) {
//            userKYCModelList = userKYCRepository.findAllByIsKYCDone(false);
//        } else {
//            userKYCModelList = userKYCRepository.findAllByKycUserIdAndIsKYCDone(new UserModel(id), false);
//        }
//        if (!userKYCModelList.isEmpty()) {
//
//
//            return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(userKYCModelList));
//
//        } else {
//            throw new UsernameNotFoundException("User Not Found");
//        }
//    }

    public ResponseEntity getAllKYCNotDone() {
        List<UserKYCModel> userModelOption = userKYCRepository.findAllByIsKYCDone(false);
        if (!userModelOption.isEmpty()) {

            return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(userModelOption));

        } else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }


    public Object getAllNotCompleted(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<UserKYCModel> pagedResult = userKYCRepository.findAllByIsKYCDone(false,paging);

        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(pagedResult));
    }

}
