package com.tml.poc.Wallet.models.rolePrevilage;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "privilage_master")
public class PrivilageMasterModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long privilageId;
	private long roleId;
	private boolean isRead;		
	private boolean isWrite;		
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

	@OneToMany
	private List<PrivilageMasterModel> privilageMaster;
	
	@JsonIgnore
	boolean isActive;

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public boolean isWrite() {
		return isWrite;
	}

	public void setWrite(boolean isWrite) {
		this.isWrite = isWrite;
	}

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

	public long getPrivilageId() {
		return privilageId;
	}

	public void setPrivilageId(long privilageId) {
		this.privilageId = privilageId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

//	public List<PrivilageMasterModel> getPrivilageMaster() {
//		return privilageMaster;
//	}
//
//	public void setPrivilageMaster(List<PrivilageMasterModel> privilageMaster) {
//		this.privilageMaster = privilageMaster;
//	}
//
//	
//	
	
	
}
