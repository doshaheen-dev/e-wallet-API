package com.tml.poc.Wallet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "app_config")
public class AppConfigModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "id of M-PIN")
    private long id;

    @Column(name = "wallet_app_icon")
    private String appIcon;
    @Column(name = "wallet_currency")
    private String currency;
    @Column(name = "transactional_limit")
    private float transactionLimit=0f;
    @Column(name = "kyc_documents")
    private String kycDocuments;

    @Column(name = "update_by")
    private String updatedBy;
    @Column(name = "created_by")
    private String createdBy;

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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(float transactionLimit) {
        this.transactionLimit = transactionLimit;
    }

    public String getKycDocuments() {
        return kycDocuments;
    }

    public void setKycDocuments(String kycDocuments) {
        this.kycDocuments = kycDocuments;
    }


}
