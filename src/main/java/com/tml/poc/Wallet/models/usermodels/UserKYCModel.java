package com.tml.poc.Wallet.models.usermodels;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tml.poc.Wallet.utils.Constants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;



@Entity
@Table(name = "mobile_users_kyc")
public class UserKYCModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kycId")
    private long id;

    @JsonFormat(pattern = Constants.TIME_DATE)
    @Column(name = "kycApplyDateTime",columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private Date applyDateTime;


//    @Column(name = "user_id")
    @JsonProperty("userId")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "KycUserId")
    private UserModel KycUserId;

    @Column(name = "is_kyc_approved")
    private boolean isKYCDone;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<KycDocumentModel> documentModelList;

    @Column(name = "kycApprovedBy")
    private long approvedBy;

    @JsonFormat(pattern = Constants.TIME_DATE)
    @Column(name = "dateTimeApprove",columnDefinition = "TIMESTAMP")
    private Date approveDataTime;

    @NotNull
    @NotEmpty
    @Column(name = "createdBy", nullable = false, updatable = false)
    private String createdBy;

    @NotNull
    @NotEmpty
    @Column(name = "updatedBy")
    private String updatedBy;

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



    @JsonProperty("userId")
    public UserModel getKycUserId() {
        return KycUserId;
    }

    @JsonProperty("userId")
    public void setKycUserId(long kycUserId) {
        KycUserId =new UserModel(kycUserId);
    }

    public boolean isKYCDone() {
        return isKYCDone;
    }

    public void setKYCDone(boolean KYCDone) {
        isKYCDone = KYCDone;
    }

    public List<KycDocumentModel> getDocumentModelList() {
        return documentModelList;
    }

    public void setDocumentModelList(List<KycDocumentModel> documentModelList) {
        this.documentModelList = documentModelList;
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

}
