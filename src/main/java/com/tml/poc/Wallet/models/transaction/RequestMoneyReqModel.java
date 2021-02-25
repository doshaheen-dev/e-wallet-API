package com.tml.poc.Wallet.models.transaction;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;


public class RequestMoneyReqModel {


    private long requesterUserId;

    private long requestToUserId;

    private float transactionAmount;

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
}
