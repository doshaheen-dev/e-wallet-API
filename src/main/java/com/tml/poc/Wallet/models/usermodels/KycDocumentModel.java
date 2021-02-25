package com.tml.poc.Wallet.models.usermodels;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tml.poc.Wallet.models.rolePrevilage.WebUserRoleModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "kyc_documents")
public class KycDocumentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kycId")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.ALL
    })
    @JoinColumn(name = "documentModelList")
    private UserKYCModel userKYCModel;

    @Column(name = "document")
    private String document;

    @Transient
    @Column(name = "docuemntExt")
    private String documentExt;

    @Column(name = "document_name")
    private String documentName;

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

    public UserKYCModel getUserKYCModel() {
        return userKYCModel;
    }

    public void setUserKYCModel(UserKYCModel userKYCModel) {
        this.userKYCModel = userKYCModel;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDocumentExt() {
        return documentExt;
    }

    public void setDocumentExt(String documentExt) {
        this.documentExt = documentExt;
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

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
}
