package com.tml.poc.Wallet.models.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "mobile_user_wallet_ballance")
public class UserBallanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_wallet_bal_id",  nullable = false, updatable = false)
    private long id;

    @Column(name = "mobile_user_id")
    private long userID;

    @Column(name = "wallet_avail_balance")
    private float availableBalance;

    @CreatedDate
    @CreationTimestamp
    @JsonIgnore
    @Column(name = "created_at",columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private Timestamp createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "updated_at",columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;

    @Column(name = "wallet_last_transaction_id")
    private long transactionID;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public float getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(float availableBalance) {
        this.availableBalance = availableBalance;
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

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }
}
