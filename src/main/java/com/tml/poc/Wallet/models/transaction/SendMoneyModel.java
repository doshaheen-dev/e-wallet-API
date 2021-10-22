package com.tml.poc.Wallet.models.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "sendMoney")
public class SendMoneyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "send_money_id",  nullable = false, updatable = false)
    private long id;

    @Column(name = "sender_user_id")
    private long senderuserID;

    @Column(name = "receiver_user_id")
    private long receiveruserID;

    @Column(name = "send_money_type",length = 50)
    @Size( max = 50)
    private String transactionType;

    @Column(name = "send_money_amount")
    private float transactionAmount;

    @Column(name = "send_money_status")
    private int status;

    @Column(name = "send_money_MPIN",length = 250)
    @Size( max = 250)
    private String mpin;

    @Column(name="transaction_status_message",length = 100)
    @Size( max = 100)
    private String statusRespMessage;

    @Column(name="send_money_discription",length = 100)
    @Size( max = 100)
    private String senderDisc;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "trans_date_time", nullable = false, updatable = false)
    private Timestamp transactionDateTime;




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

    public long getSenderuserID() {
        return senderuserID;
    }

    public void setSenderuserID(long senderuserID) {
        this.senderuserID = senderuserID;
    }

    public long getReceiveruserID() {
        return receiveruserID;
    }

    public void setReceiveruserID(long receiveruserID) {
        this.receiveruserID = receiveruserID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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

    public String getMpin() {
        return mpin;
    }

    public void setMpin(String mpin) {
        this.mpin = mpin;
    }

    public String getStatusRespMessage() {
        return statusRespMessage;
    }

    public void setStatusRespMessage(String statusRespMessage) {
        this.statusRespMessage = statusRespMessage;
    }

    public String getSenderDisc() {
        return senderDisc;
    }

    public void setSenderDisc(String senderDisc) {
        this.senderDisc = senderDisc;
    }

    public Timestamp getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(Timestamp transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }



    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
