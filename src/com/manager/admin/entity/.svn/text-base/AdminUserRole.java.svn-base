package com.manager.admin.entity;

import java.io.Serializable;

public class AdminUserRole implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * create table TBL_ADMIN_USERROLE
		(
		  admin_user  NVARCHAR2(30),
		  right_id    NUMBER,
		  role_id     NUMBER,
		  create_date DATE default SYSDATE not null
		)
	 */
	private String adminUser;
	private String rightId;
	private String roleId;
	private String createDate;
	
	
	public AdminUserRole() {
		super();
	}
	
	
	public AdminUserRole(String adminUser, String rightId, String roleId,
			String createDate) {
		super();
		this.adminUser = adminUser;
		this.rightId = rightId;
		this.roleId = roleId;
		this.createDate = createDate;
	}


	/**
	 * @return the adminUser
	 */
	public String getAdminUser() {
		return adminUser;
	}
	/**
	 * @param adminUser the adminUser to set
	 */
	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}
	/**
	 * @return the rightId
	 */
	public String getRightId() {
		return rightId;
	}
	/**
	 * @param rightId the rightId to set
	 */
	public void setRightId(String rightId) {
		this.rightId = rightId;
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
	
	
}
