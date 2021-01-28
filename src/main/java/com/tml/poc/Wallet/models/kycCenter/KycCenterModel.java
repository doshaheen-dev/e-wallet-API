package com.tml.poc.Wallet.models.kycCenter;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "kyc_center")
public class KycCenterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "kyc_center_name")
    @Size( max = 50)
    private String centerName;
    @Column(name = "kyc_center_contact_number")
    @Size( max = 20)
    private String centerContactNumber;
    @Column(name = "kyc_center_emailid")
    @Size( max = 50)
    private String centerEmailID;
    @Column(name = "kyc_center_area")
    @Size( max = 20)
    private String centerArea;
    @Column(name = "kyc_center_postal_code")
    @Size( max = 10)
    private String centerZip;
    @Column(name = "kyc_center_full_address")
    @Size( max = 250)
    private String centerAddress;
    @Column(name = "kyc_center_city")
    @Size( max = 20)
    private String centerCity;
    @Column(name = "kyc_center_country")
    @Size( max = 20)
    private String centerCountry;

    @Column(name = "kyc_center_type")
    private String centerType;

    private double latitude;
    private double longitude;

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
    private String createdBy;
    private String updatedBy;

    @JsonIgnore
    boolean isActive;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterContactNumber() {
        return centerContactNumber;
    }

    public void setCenterContactNumber(String centerContactNumber) {
        this.centerContactNumber = centerContactNumber;
    }

    public String getCenterEmailID() {
        return centerEmailID;
    }

    public void setCenterEmailID(String centerEmailID) {
        this.centerEmailID = centerEmailID;
    }

    public String getCenterArea() {
        return centerArea;
    }

    public void setCenterArea(String centerArea) {
        this.centerArea = centerArea;
    }

    public String getCenterZip() {
        return centerZip;
    }

    public void setCenterZip(String centerZip) {
        this.centerZip = centerZip;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public String getCenterCity() {
        return centerCity;
    }

    public void setCenterCity(String centerCity) {
        this.centerCity = centerCity;
    }

    public String getCenterCountry() {
        return centerCountry;
    }

    public void setCenterCountry(String centerCountry) {
        this.centerCountry = centerCountry;
    }

    public String getCenterType() {
        return centerType;
    }

    public void setCenterType(String centerType) {
        this.centerType = centerType;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
