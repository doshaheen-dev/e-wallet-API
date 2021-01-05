package com.tml.poc.Wallet.models.transaction;

import javax.persistence.*;

@Entity
@Table(name = "Transactions")
public class TransactionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("trans_id")
    private long id;

    private String senderID;
}
