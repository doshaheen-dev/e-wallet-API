package com.tml.poc.Wallet.models.utilsmodels;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tml.poc.Wallet.utils.Constants;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "login_history")
public class LoginHistoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name = "mobile_user_id")
    private long mobileUserId;

    @Column(name = "web_user_id")
    private long webUserId;

    @CreatedDate
    @CreationTimestamp
    @JsonFormat(pattern = Constants.TIME_DATE)
    @Column(name = "login_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private Date loginDate;

    public LoginHistoryModel(long id, long mobileUserId, long webUserId) {
        this.id = id;
        this.mobileUserId = mobileUserId;
        this.webUserId = webUserId;
    }

    public LoginHistoryModel() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMobileUserId() {
        return mobileUserId;
    }

    public void setMobileUserId(long mobileUserId) {
        this.mobileUserId = mobileUserId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public long getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(long webUserId) {
        this.webUserId = webUserId;
    }
}
