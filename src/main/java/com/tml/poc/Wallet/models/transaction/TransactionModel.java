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

    @Column(name = "user_id")
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

}
