package com.tml.poc.Wallet.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long accountid;

	/**
	 * uuid is use to CreateUI
	 */
	@Column(name = "uuid", updatable = false, nullable = false)
	private  String qrCode=UUID.randomUUID().toString();
	
    @Column(name="mobileNumber", unique=true)
	private String mobileNumber;
    
    @Column(name="emailid", unique=true)
	private String emailid;
	private boolean isKYC;

	private String firstname;
	private String lastname;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dob;
	private String gender;
	private String profile_image;
	private boolean isProfileComplete;
	private long role;
	private String createdBy;
	private String updatedBy;

	@CreatedDate
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	
	private boolean isMobileVerified;
	private boolean isEmailVerified;
	
	private String otp;
	@JsonIgnore
	private Date otpCreated;
	@Column(name = "isUserActivated", nullable = false, updatable = true)
	private boolean isActive;
	
	@Column(name = "isUserBlocked", nullable = false, updatable = false)
	private boolean isBlockedByAdmin;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public long getAccountid() {
		return accountid;
	}

	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public boolean isKYC() {
		return isKYC;
	}

	public void setKYC(boolean isKYC) {
		this.isKYC = isKYC;
	}

	public LocalDateTime getDob() {
		return dob;
	}

	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isMobileVerified() {
		return isMobileVerified;
	}

	public void setMobileVerified(boolean isMobileVerified) {
		this.isMobileVerified = isMobileVerified;
	}

	public boolean isEmailVerified() {
		return isEmailVerified;
	}

	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public Date getOtpCreated() {
		return otpCreated;
	}

	public void setOtpCreated(Date otpCreated) {
		this.otpCreated = otpCreated;
	}

	public boolean isProfileComplete() {
		return isProfileComplete;
	}

	public void setProfileComplete(boolean isProfileComplete) {
		this.isProfileComplete = isProfileComplete;
	}

	public boolean isBlockedByAdmin() {
		return isBlockedByAdmin;
	}

	public void setBlockedByAdmin(boolean isBlockedByAdmin) {
		this.isBlockedByAdmin = isBlockedByAdmin;
	}
	
	
	

}
