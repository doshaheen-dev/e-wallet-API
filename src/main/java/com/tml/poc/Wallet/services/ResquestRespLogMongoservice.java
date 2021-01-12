package com.tml.poc.Wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tml.poc.Wallet.mongomodels.RequestResponceLogModel;
import com.tml.poc.Wallet.mongorepository.RequestRespLogMongoRepository;

@Component
public class ResquestRespLogMongoservice {

	
	@Autowired
	private RequestRespLogMongoRepository resquestRespLogMongoservice;
	
	
	@Async
	public void addRequestIntoLogMongo(String requestId,String url,String requestHeader,String requestBody) {
		RequestResponceLogModel requestResponceLogModel=new RequestResponceLogModel();
		requestResponceLogModel.setRequestID(requestId);
		requestResponceLogModel.setRequestHeader(requestHeader);
		requestResponceLogModel.setUrl(url);
		requestResponceLogModel.setRequestBody(requestBody);
		resquestRespLogMongoservice.save(requestResponceLogModel);
	}
	
	
	@Async
	public void addResponseIntoLogMongo(String requestId,String responseHeader,String responseBody) {
		RequestResponceLogModel requestResponceLogModel=new RequestResponceLogModel();
		requestResponceLogModel=resquestRespLogMongoservice.findByRequestID(requestId).get();
		requestResponceLogModel.setResponseHeader(responseHeader);
		requestResponceLogModel.setRequestBody(responseBody);
		resquestRespLogMongoservice.save(requestResponceLogModel);
	}
	
	
	

	
	
}
