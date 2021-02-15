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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name ="mobile_users" )
@Table(name = "mobile_users")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private long id;
	/**
	 * uuid is use to CreateUI
	 */
	@Column(name = "uuid",length = 50, updatable = false, nullable = false)
	@Size( max = 50)
	private  String qrCode=UUID.randomUUID().toString();
	
    @Column(name="mobile_number",length = 15, unique=true)
	@Size( max = 15)
	private String mobileNumber;
    
    @Column(name="email_id",length = 50, unique=true)
	@Size( max = 50)
	@JsonProperty("emailId")
	private String emailid;
	@Column(name="is_kyc_approved")
	private boolean iskycDone;
	@Size( max = 20)
	private String firstname;
	@Size( max = 20)
	private String lastname;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="birthdate")
	private Date dob;
	@Size( max = 10)
	@Column(name = "gender",length = 10)
	private String gender;
	@Size( max = 100)
	@Column(name = "profile_image",length = 100)
	private String profile_image;
	@JsonProperty("isProfileComplete")
	@Column(name="is_profile_completed")
	private boolean isProfileComplete;
	@Size( max = 20)
	private String updatedBy;

	@JsonIgnore
	@CreatedDate
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;

	@JsonIgnore
	@LastModifiedDate
	@UpdateTimestamp
	@Column(name = "updated_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime updatedAt;


	@Column(name = "is_mobile_verified")
	@JsonProperty("isMobileVerified")
	private boolean isMobileVerified;
	@Column(name = "is_email_verified")
	@JsonProperty("isEmailVerified")
	private boolean isEmailVerified;

	@Column(name = "saltKey_pass", nullable = false, updatable = false)
	@Size(max = 100)
	@JsonIgnore
	private String saltKey;


	@Column(name = "isUserActivated", nullable = false)
	@JsonProperty("isActive")
	private boolean isActive;

	@JsonIgnore
	@Column(name = "isUserBlocked", nullable = false)
	private boolean isBlockedByAdmin;

	@JsonIgnore
	@Column(name = "user_otp_id", nullable = false)
	private long userOtpId;


	@Transient
	@JsonProperty("isMPINCreated")
	private boolean isMPINCreated;

	@JsonProperty("isKYCApplied")
	private boolean isKYCApplied;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@JsonProperty("emailId")
	public String getEmailid() {
		return emailid;
	}

	@JsonProperty("emailId")
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public boolean isIskycDone() {
		return iskycDone;
	}

	public void setIskycDone(boolean iskycDone) {
		this.iskycDone = iskycDone;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
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

	@JsonProperty("isProfileComplete")
	public boolean isProfileComplete() {
		return isProfileComplete;
	}

	@JsonProperty("isProfileComplete")
	public void setProfileComplete(boolean profileComplete) {
		isProfileComplete = profileComplete;
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

	@JsonProperty("isMobileVerified")
	public boolean isMobileVerified() {
		return isMobileVerified;
	}

	@JsonProperty("isMobileVerified")
	public void setMobileVerified(boolean mobileVerified) {
		isMobileVerified = mobileVerified;
	}

	@JsonProperty("isEmailVerified")
	public boolean isEmailVerified() {
		return isEmailVerified;
	}

	@JsonProperty("isEmailVerified")
	public void setEmailVerified(boolean emailVerified) {
		isEmailVerified = emailVerified;
	}

	public String getSaltKey() {
		return saltKey;
	}

	public void setSaltKey(String saltKey) {
		this.saltKey = saltKey;
	}

	@JsonProperty("isActive")
	public boolean isActive() {
		return isActive;
	}

	@JsonProperty("isActive")
	public void setActive(boolean active) {
		isActive = active;
	}

	@JsonIgnore
	public boolean isBlockedByAdmin() {
		return isBlockedByAdmin;
	}

	@JsonIgnore
	public void setBlockedByAdmin(boolean blockedByAdmin) {
		isBlockedByAdmin = blockedByAdmin;
	}

	public long getUserOtpId() {
		return userOtpId;
	}

	public void setUserOtpId(long userOtpId) {
		this.userOtpId = userOtpId;
	}

	@JsonProperty("isMPINCreated")
	public boolean isMPINCreated() {
		return isMPINCreated;
	}

	@JsonProperty("isMPINCreated")
	public void setMPINCreated(boolean MPINCreated) {
		isMPINCreated = MPINCreated;
	}

	public boolean isKYCApplied() {
		return isKYCApplied;
	}

	public void setKYCApplied(boolean KYCApplied) {
		isKYCApplied = KYCApplied;
	}
}
