package com.tml.poc.Wallet.models.transaction;

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
@Table(name = "Transactions")
public class TransactionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id",  nullable = false, updatable = false)
    private long id;

    @Column(name = "mobile_user_id")
    private long userID;

    @Column(name = "trans_type")
    @Size( max = 10)
    private String transactionType;

    @Column(name = "transaction_credit_amount")
    private float transactionCrAmount;

    @Column(name = "transaction_debit_amount")
    private float transactionDebAmount;

    @Column(name = "trans_avail_balance")
    private float availableBalance;

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

    @Column(name = "trans_status")
    private int status;

    public TransactionModel() {
    }

    public TransactionModel(long id, long userID,
                            String transactionType,
                            float transactionCrAmount,
                            float transactionDebAmount) {
        this.id = id;
        this.userID = userID;
        this.transactionType = transactionType;
        this.transactionCrAmount = transactionCrAmount;
        this.transactionDebAmount = transactionDebAmount;
    }

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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public float getTransactionCrAmount() {
        return transactionCrAmount;
    }

    public void setTransactionCrAmount(float transactionCrAmount) {
        this.transactionCrAmount = transactionCrAmount;
    }

    public float getTransactionDebAmount() {
        return transactionDebAmount;
    }

    public void setTransactionDebAmount(float transactionDebAmount) {
        this.transactionDebAmount = transactionDebAmount;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
