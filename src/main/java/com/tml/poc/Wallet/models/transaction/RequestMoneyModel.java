package com.tml.poc.Wallet.models.transaction;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "requestMoney")
public class RequestMoneyModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false, updatable = false)
    private long id;

//    @Column(name = "requester_user_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "requesterUserId")
    private UserModel requesterUserId;

//    @Column(name = "request_to_user_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "requestToUserId")
    private UserModel requestToUserId;

    @Column(name = "req_money_amount")
    private float transactionAmount;

    @Column(name = "req_money_status")
    private int status;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserModel getRequesterUserId() {
        return requesterUserId;
    }

    public void setRequesterUserId(long requesterUserId) {
        this.requesterUserId = new UserModel(requesterUserId);
    }

    public UserModel getRequestToUserId() {
        return requestToUserId;
    }

    public void setRequestToUserId(long requestToUserId) {
        this.requestToUserId = new UserModel(requestToUserId);
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
