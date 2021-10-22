package com.tml.poc.Wallet.models.recharge;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "add_cash_to_wallet")
public class AddCashToWalletModel {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private long userId;

        private long cashAddBy;

        private float cashAmount;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "added_at", nullable = false, updatable = false)
    @JsonIgnore
    private Timestamp addedAt;

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCashAddBy() {
        return cashAddBy;
    }

    public void setCashAddBy(long cashAddBy) {
        this.cashAddBy = cashAddBy;
    }

    public float getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(float cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
