package com.tml.poc.Wallet.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tml.poc.Wallet.utils.Constants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "usersKYCTable")
public class UserKYCModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kycId")
    private long id;

    @JsonFormat(pattern = Constants.TIME_DATE)
    @Column(name = "kycApplyDateTime")
    private LocalDateTime applyDateTime;

    @Column(name = "userid")
    private long userId;

    @Column(name = "kycIsDone")
    private boolean isKYCDone;

    @Column(name = "documentType")
    private String kycDocumentType;

    @Column(name = "documentUrl")
    private String kycDocumentURL;
    @Column(name = "passportPhotoUrl")
    private String kycPassportPhoto;

    @Column(name = "kycApprovedBy")
    private long approvedBy;

    @Column(name = "dateTimeApprove")
    private Date approveDataTime;

    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "updatedBy")
    private String updatedBy;
    @Column(name = "zipCode")
    private String postalCode;

    @CreatedDate
    @CreationTimestamp
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getApplyDateTime() {
        return applyDateTime;
    }

    public void setApplyDateTime(LocalDateTime applyDateTime) {
        this.applyDateTime = applyDateTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isKYCDone() {
        return isKYCDone;
    }

    public void setKYCDone(boolean KYCDone) {
        isKYCDone = KYCDone;
    }

    public String getKycDocumentType() {
        return kycDocumentType;
    }

    public void setKycDocumentType(String kycDocumentType) {
        this.kycDocumentType = kycDocumentType;
    }

    public String getKycDocumentURL() {
        return kycDocumentURL;
    }

    public void setKycDocumentURL(String kycDocumentURL) {
        this.kycDocumentURL = kycDocumentURL;
    }

    public String getKycPassportPhoto() {
        return kycPassportPhoto;
    }

    public void setKycPassportPhoto(String kycPassportPhoto) {
        this.kycPassportPhoto = kycPassportPhoto;
    }

    public long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(long approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApproveDataTime() {
        return approveDataTime;
    }

    public void setApproveDataTime(Date approveDataTime) {
        this.approveDataTime = approveDataTime;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

}
