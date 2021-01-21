package com.tml.poc.Wallet.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tml.poc.Wallet.utils.Constants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
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
    @Column(name = "kycApplyDateTime",columnDefinition = "DATETIME")
    private Date applyDateTime;


    @Column(name = "userid")
    private long userId;

    @Column(name = "kycIsDone")
    private boolean isKYCDone;

    @NotNull
    @NotEmpty
    @Column(name = "documentType")
    private String kycDocumentType;

    @NotNull
    @NotEmpty
    @Column(name = "documentUrl")
    private String kycDocument;

    @Transient
    private String kycDocumentExt;

    @Column(name = "passportPhotoUrl")
    private String kycPassportPhoto;

    @Transient
    private String kycPassportPhotoExt;

    @Column(name = "kycApprovedBy")
    private long approvedBy;

    @JsonFormat(pattern = Constants.TIME_DATE)
    @Column(name = "dateTimeApprove",columnDefinition = "DATETIME")
    private Timestamp approveDataTime;

    @NotNull
    @NotEmpty
    @Column(name = "createdBy", nullable = false, updatable = false)
    private String createdBy;

    @NotNull
    @NotEmpty
    @Column(name = "updatedBy")
    private String updatedBy;

    @Column(name = "zipCode")
    private String postalCode;

    @CreatedDate
    @CreationTimestamp
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "latitude")
    private double lat;
    @Column(name = "longitude")
    private double lon;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getApplyDateTime() {
        return applyDateTime;
    }

    public void setApplyDateTime(Date applyDateTime) {
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

    public String getKycDocument() {
        return kycDocument;
    }

    public void setKycDocument(String kycDocument) {
        this.kycDocument = kycDocument;
    }

    public String getKycDocumentExt() {
        return kycDocumentExt;
    }

    public void setKycDocumentExt(String kycDocumentExt) {
        this.kycDocumentExt = kycDocumentExt;
    }

    public String getKycPassportPhoto() {
        return kycPassportPhoto;
    }

    public void setKycPassportPhoto(String kycPassportPhoto) {
        this.kycPassportPhoto = kycPassportPhoto;
    }

    public String getKycPassportPhotoExt() {
        return kycPassportPhotoExt;
    }

    public void setKycPassportPhotoExt(String kycPassportPhotoExt) {
        this.kycPassportPhotoExt = kycPassportPhotoExt;
    }

    public long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(long approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Timestamp getApproveDataTime() {
        return approveDataTime;
    }

    public void setApproveDataTime(Timestamp approveDataTime) {
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
