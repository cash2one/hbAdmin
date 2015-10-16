package com.manager.admin.entity;

import java.io.Serializable;

public class AdminRole implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * create table TBL_ADMIN_ROLE
		(
		  role_id      NUMBER not null,
		  role_name    NVARCHAR2(50) not null,
		  create_admin NVARCHAR2(30),
		  create_date  DATE default SYSDATE not null,
		  modify_admin NVARCHAR2(30),
		  modify_date  DATE,
		  role_state   NCHAR(1) default '0' not null
		)
	 */
	private String roleId;
	private String roleName;
	private String createAdmin;
	private String createDate;
	private String modifyAdmin;
	private String modifyDate;
	private String roleState;
	
	public AdminRole() {
		super();
	}
	
	public AdminRole(String roleId, String roleName, String createAdmin,
			String createDate, String modifyAdmin, String modifyDate,
			String roleState) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.createAdmin = createAdmin;
		this.createDate = createDate;
		this.modifyAdmin = modifyAdmin;
		this.modifyDate = modifyDate;
		this.roleState = roleState;
	}


	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the createAdmin
	 */
	public String getCreateAdmin() {
		return createAdmin;
	}
	/**
	 * @param createAdmin the createAdmin to set
	 */
	public void setCreateAdmin(String createAdmin) {
		this.createAdmin = createAdmin;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the modifyAdmin
	 */
	public String getModifyAdmin() {
		return modifyAdmin;
	}
	/**
	 * @param modifyAdmin the modifyAdmin to set
	 */
	public void setModifyAdmin(String modifyAdmin) {
		this.modifyAdmin = modifyAdmin;
	}
	/**
	 * @return the modifyDate
	 */
	public String getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * @return the roleState
	 */
	public String getRoleState() {
		return roleState;
	}
	/**
	 * @param roleState the roleState to set
	 */
	public void setRoleState(String roleState) {
		this.roleState = roleState;
	}
	
	
}
