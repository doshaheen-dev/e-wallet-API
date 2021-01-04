package com.tml.poc.Wallet.restController;

import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.services.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/serach")
public class UserSearchController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSearchService userSearchService;
    @GetMapping("/mobile/{mobileNumber}")
    private ResponseEntity getUserByMobileNumber(@PathVariable(name = "mobileNumber")String mobileNumber){
        return userSearchService.searchUserByMobile(mobileNumber);
    }

}
