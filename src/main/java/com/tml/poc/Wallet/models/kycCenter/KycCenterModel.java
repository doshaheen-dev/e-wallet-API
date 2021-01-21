package com.tml.poc.Wallet.models.kycCenter;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "kyc_center")
public class KycCenterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "kyc_center_name")
    private String centerName;
    @Column(name = "kyc_center_contact_number")
    private String centerContactNumber;
    @Column(name = "kyc_center_emailid")
    private String centerEmailID;
    @Column(name = "kyc_center_area")
    private String centerArea;
    @Column(name = "kyc_center_postal_code")
    private String centerZip;
    @Column(name = "kyc_center_full_address")
    private String centerAddress;
    @Column(name = "kyc_center_city")
    private String centerCity;
    @Column(name = "kyc_center_country")
    private String centerCountry;
    @Column(name = "kyc_center_country_code")
    private String centerCountryCode;

    @Column(name = "kyc_center_type")
    private String centerType;

    private double latitude;
    private double longitude;

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

    public String getCenterCountryCode() {
        return centerCountryCode;
    }

    public void setCenterCountryCode(String centerCountryCode) {
        this.centerCountryCode = centerCountryCode;
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

    public String getCenterType() {
        return centerType;
    }

    public void setCenterType(String centerType) {
        this.centerType = centerType;
    }
}
