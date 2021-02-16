package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSearchService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity searchUserByMobile(String mobilenumber){
        Optional<UserModel> userModelList=userRepository.findByMobileNumber(mobilenumber);
        if(userModelList.isPresent()){
            return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(userModelList.get()));
        }else{
            throw new UsernameNotFoundException("Mobile number is not linked");
        }
    }

    public ResponseEntity searchUserByUUID(String uuid){
        Optional<UserModel> userModelList=userRepository.findByQrCode(uuid);
        if(userModelList.isPresent()){
            return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(userModelList.get()));
        }else{
            throw new UsernameNotFoundException("QR Code is not Found");
        }
    }


    public ResponseEntity searchUserMobile(String mobilenumber){
        Optional<UserModel> userModelList=userRepository.findByMobileNumber(mobilenumber);
        if(userModelList.isPresent()){
            return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(userModelList.get()));
        }else{
            throw new UsernameNotFoundException("Mobile number is not linked");
        }
    }



    public ResponseEntity getAllUser(){
        List<UserModel> userModelList=userRepository.findAll();
            return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(userModelList));

    }

}
