package com.tml.poc.Wallet.mongomodels;

import javax.annotation.Generated; 

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RequestResponceLog")
public class RequestResponceLogModel {

	@Id
	private String requestID;
	
	private Object url;

	private Object requestHeader;
	private Object responseHeader;
	private Object requestBody;
	private Object responseBody;

	

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
			
	public Object getUrl() {
		return url;
	}

	public void setUrl(Object url) {
		this.url = url;
	}

	public Object getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(Object requestHeader) {
		this.requestHeader = requestHeader;
	}

	public Object getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(Object responseHeader) {
		this.responseHeader = responseHeader;
	}

	public Object getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

	
	
	
	
}
