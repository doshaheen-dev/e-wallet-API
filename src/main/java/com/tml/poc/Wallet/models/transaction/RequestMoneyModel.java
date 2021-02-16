package com.tml.poc.Wallet.models.transaction;


import javax.persistence.*;

@Entity
@Table(name = "requestMoney")
public class RequestMoneyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false, updatable = false)
    private long id;

    @Column(name = "requester_user_id")
    private long requesterUserId;

    @Column(name = "request_to_user_id")
    private long requestToUserId;

    @Column(name = "req_money_amount")
    private float transactionAmount;

    @Column(name = "req_money_status")
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRequesterUserId() {
        return requesterUserId;
    }

    public void setRequesterUserId(long requesterUserId) {
        this.requesterUserId = requesterUserId;
    }

    public long getRequestToUserId() {
        return requestToUserId;
    }

    public void setRequestToUserId(long requestToUserId) {
        this.requestToUserId = requestToUserId;
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
}
