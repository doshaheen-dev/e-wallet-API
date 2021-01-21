package com.tml.poc.Wallet.models.rolePrevilage;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "privilage_role_mapper")
public class PrivilageRoleMappingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch=FetchType.EAGER, cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
        })
	@JoinColumn(name = "employee")
	private EmployeeRoleModel role;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
        })
	@JoinColumn(name = "privilageMaster")
	private PrivilageMasterModel privilage;

	private boolean isRead;
	private boolean isWrite;

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

	@Column(nullable = false, updatable = false)
	private String createdBy;
	private String updatedBy;

	@JsonIgnore
	boolean isActive;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EmployeeRoleModel getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = new EmployeeRoleModel (role);
		
	}

	public PrivilageMasterModel getPrivilage() {
		return privilage;
	}

	public void setPrivilage(PrivilageMasterModel privilage) {
		this.privilage = privilage;
	}

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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	

}
