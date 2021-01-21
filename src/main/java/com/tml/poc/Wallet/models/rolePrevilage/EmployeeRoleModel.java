package com.tml.poc.Wallet.models.rolePrevilage;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tml.poc.Wallet.models.EmployeeModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "EmployeeRole")
public class EmployeeRoleModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String roleName;
									
	String createdBy;
	String updatedBy;

	@CreatedDate
	@CreationTimestamp
	@JsonIgnore
	@Column(name = "created_at", nullable = false, updatable = false)
	LocalDateTime createdAt;

	@LastModifiedDate
	@UpdateTimestamp
	@JsonIgnore
	@Column(name = "updated_at")
	LocalDateTime updatedAt;
		
	@OneToMany
	private List<EmployeeModel> employee;

	@JsonIgnore
	boolean isActive;

	public EmployeeRoleModel() {
		super();
	}

	public EmployeeRoleModel(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

//	public List<EmployeeModel> getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(List<EmployeeModel> employee) {
//		this.employee = employee;
//	}
	
	
	
}
