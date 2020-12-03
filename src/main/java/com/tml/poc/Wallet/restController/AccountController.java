package com.tml.poc.Wallet.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tml.poc.Wallet.models.reponse.DataModelResponce;
import com.tml.poc.Wallet.repository.AccountRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;

@RequestMapping("/account")
@RestController
public class AccountController {
	
	@Autowired
	private AccountRepository accountrepo;
	
	@Autowired
	private DataReturnUtil dataReturnUtil;
	
	@GetMapping("/getallaccount")
	private Object getallaccount() {

		return dataReturnUtil.setDataAndReturnResponseForRestAPI(accountrepo.findAll());
	}
	
	

}
