package com.tml.poc.Wallet.models.utilsmodels;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "UserOTP")
public class OTPModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="otp_id")
    private long id;

    @Size(max = 10)
    @Column(name="otp",length = 200)
    private String otp;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private Timestamp createdAt;

    @Column(name="user_id")
    private long userID;

    @Column(name="MPIN_id")
    private long MPinId;

    private boolean isVerified;
    private Timestamp verifiedAt;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getMPinId() {
        return MPinId;
    }

    public void setMPinId(long MPinId) {
        this.MPinId = MPinId;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Timestamp verifiedAt) {
        this.verifiedAt = verifiedAt;
    }
}
