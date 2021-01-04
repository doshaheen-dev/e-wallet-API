package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.models.UserKYCModel;
import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.repository.UserKYCRepository;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserKYCService {

    @Autowired
    private UserKYCRepository userKYCRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity applyUserKYC(UserKYCModel userKYCModel) {
        if (userRepository.findAllById(userKYCModel.getUserId()).isPresent()) {
            return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(userKYCRepository.save(userKYCModel)));
        } else{
            throw new UsernameNotFoundException("User Not Found");
        }
    }


    public ResponseEntity approveKYC(UserKYCModel userKYCModel) {
        Optional<UserModel> userModelOption=userRepository.findAllById(userKYCModel.getUserId());
        if (userModelOption.isPresent()) {
            UserModel userModel = userModelOption.get();
            if (!userModel.isKYC()) {
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
