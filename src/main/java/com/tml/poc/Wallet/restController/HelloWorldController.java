package com.tml.poc.Wallet.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.utils.DataReturnUtil;

/**
 * for POC Purpose
 * @author vaibhavbhavsar
 *
 */
@RestController
@RequestMapping("/hello")
public class HelloWorldController {
	
	
	
	@GetMapping("/hi")
	private Object getHelloWorld() {
		DataReturnUtil dataReturnUtil=new DataReturnUtil();
		
		return dataReturnUtil.setDataAndReturnResponseForRestAPI("Hi");
		
	}
	
	@GetMapping("/hi/list")
	private Object getHelloWorldList() {
		DataReturnUtil dataReturnUtil=new DataReturnUtil();
		List<String> strings=new ArrayList();
		
		strings.add("Hi");
		strings.add("Hello");
		strings.add("What up");
		return dataReturnUtil.setDataAndReturnResponseForRestAPI(strings);
		
	}
	
	@GetMapping("/hi/obj")
	private Object getHelloWorldObj() {
		DataReturnUtil dataReturnUtil=new DataReturnUtil();
		UserModel userModel=new UserModel();
		return dataReturnUtil.setDataAndReturnResponseForRestAPI(userModel);
		
	}

}