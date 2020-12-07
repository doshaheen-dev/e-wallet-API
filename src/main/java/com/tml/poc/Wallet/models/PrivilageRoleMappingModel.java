package com.tml.poc.Wallet.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PrivilageRoleMappingModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String privilageName;
	private String privilageDiscription;
									
	private String createdBy;
	private String updatedBy;

	@CreatedDate
	@CreationTimestamp
	@JsonIgnore
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@UpdateTimestamp
	@JsonIgnore
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@JsonIgnore
	boolean isActive;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getPrivilageName() {
		return privilageName;
	}

	public void setPrivilageName(String privilageName) {
		this.privilageName = privilageName;
	}

	public String getPrivilageDiscription() {
		return privilageDiscription;
	}

	public void setPrivilageDiscription(String privilageDiscription) {
		this.privilageDiscription = privilageDiscription;
	}
	
	
	
	
}
