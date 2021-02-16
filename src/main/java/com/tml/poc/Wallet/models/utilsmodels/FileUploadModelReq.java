package com.tml.poc.Wallet.models.utilsmodels;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * model to use for upload file
 * @author vaibhavbhavsar
 *
 */
public class FileUploadModelReq {
	
	
	@ApiModelProperty(notes="Base 64 String which one is collected from any Blob")
	@NotEmpty
	@NotNull
	private String base64String;
	
	@ApiModelProperty(notes="Uploder's UserId")
	private long userid;
	
	@ApiModelProperty(notes="Upload File Extension")
	@NotEmpty
	@NotNull
	private String extension;
	
	@ApiModelProperty(notes="file discription Ex. Profile Image , KYC Document, Document name (Optional)")
	private String desciption;

	public String getBase64String() {
		return base64String;
	}

	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	

	
	
	

}
