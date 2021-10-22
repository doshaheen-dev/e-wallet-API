package com.tml.poc.Wallet.models.webuser;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import javax.validation.constraints.Size;

import com.tml.poc.Wallet.models.rolePrevilage.WebUserRoleModel;
import com.tml.poc.Wallet.models.utilsmodels.LoginHistoryModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tml.poc.Wallet.utils.Constants;

import static com.tml.poc.Wallet.utils.Constants.ENTER_VALUE;
import static com.tml.poc.Wallet.utils.Constants.VALID_EMAILID;

@Entity
@Table(name = "web_user")
public class WebUserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String countrycode;
    private String mobileNumber;

    @NotEmpty(message =ENTER_VALUE)
    @Email
    @Size(max = 100)
    @Pattern(regexp = Constants.EMAIL_REGEX, flags = Flag.UNICODE_CASE, message =VALID_EMAILID)
    @Column(name="email_id", unique = true)
    private String emailid;

    @Column(name = "first_name", length = 50)
    private String firstname;
    @Column(name = "last_name", length = 50)
    private String lastname;

//    @JsonIgnore
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthdate", columnDefinition = "TIMESTAMP")
    private Date dob;
    private String gender;
    private String profile_image;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "webuser")
    private WebUserRoleModel roleId;

    @Column(name="role_code")
    private String roleCode;

    @Column(name = "created_by", length = 50, nullable = false, updatable = false)
    private String createdBy;
    @Column(name = "updated_by", length = 50, nullable = false, updatable = false)
    private String updatedBy;


    @CreatedDate
    @CreationTimestamp
    @JsonIgnore
    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @JsonIgnore
    private boolean isActive;

    @Transient
    private String jwToken;



    public WebUserModel() {
        super();
    }

    public WebUserModel(long id){
        super();
        this.id=id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public WebUserRoleModel getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = new WebUserRoleModel(roleId);
    }

    public void setRoleIdModel(WebUserRoleModel roleId) {
        this.roleId = roleId;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getJwToken() {
        return jwToken;
    }

    public void setJwToken(String jwToken) {
        this.jwToken = jwToken;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

}
