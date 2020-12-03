package com.tml.poc.Wallet.models.reponse;

public class DataModelResponce {
	
	

	private Object data;
	
	private String message;
	
	private Object error;

	private boolean success;
	
	private int resCode;

	
	
	public DataModelResponce() {
		super();
	}

	public DataModelResponce(Object data, String message, Object error, boolean success, int resCode) {
		super();
		this.data = data;
		this.message = message;
		this.error = error;
		this.success = success;
		this.resCode = resCode;
	}

	public int getResCode() {
		return resCode;
	}

	public void setResCode(int resCode) {
		this.resCode = resCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "DataModelResponce [data=" + data + ", message=" + message + ", success=" + success + "]";
	}
	
	
	
}