package com.tml.poc.Wallet.models.rolePrevilage;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.tml.poc.Wallet.models.WebUserModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "webuser_role")
public class WebUserRoleModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@NotEmpty
	@Column(name = "web_role_name",length = 20)
	private String roleName;

	@NotNull(message = "Role Code can not be null")
	@NotEmpty(message = "Role Code can not be Empty")
	@Column(name = "web_role_code",length = 20)
	private String roleCode;

	@Column(name = "created_by",length = 50)
	private String createdBy;
	@Column(name = "updated_by",length = 50)
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
	private List<WebUserModel> webuser;

	@Column(name = "access_module")
	private String accessLayerModule;

	@JsonIgnore
	@Column(name = "is_active")
	boolean isActive;

	public WebUserRoleModel() {
		super();
	}

	public WebUserRoleModel(long id) {
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

	public String getAccessLayerModule() {
		return accessLayerModule;
	}

	public void setAccessLayerModule(String accessModule) {
		this.accessLayerModule = accessModule;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


}
