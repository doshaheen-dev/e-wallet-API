package com.tml.poc.Wallet.utils;

import org.springframework.stereotype.Service;

import com.tml.poc.Wallet.models.reponse.DataModelResponce;
@Service
public class DataReturnUtil {
	
	
	public  Object setDataAndReturnResponseForRestAPI(Object obj) {
		DataModelResponce dataModelRespo=new DataModelResponce();
		if(obj!=null) {
			dataModelRespo.setData(obj);
			dataModelRespo.setSuccess(true);
			dataModelRespo.setMessage("Success");
		}else {
			dataModelRespo.setData(null);
			dataModelRespo.setSuccess(false);
			dataModelRespo.setMessage("Fail");
		}
		
		return dataModelRespo;
		
	}
	
	
	public  Object setDataAndReturnResponseForRestAPI(Object obj,String failMessage) {
		DataModelResponce dataModelRespo=new DataModelResponce();
		if(obj!=null) {
			dataModelRespo.setData(obj);
			dataModelRespo.setSuccess(true);
			dataModelRespo.setMessage("Success");
		}else {
			dataModelRespo.setData(null);
			dataModelRespo.setSuccess(false);
			dataModelRespo.setMessage(failMessage);
		}
		
		return dataModelRespo;
		
	}

}
