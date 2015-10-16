package com.manager.admin.entity;

import java.io.Serializable;

public class AdminRoleRight implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * create table TBL_ADMIN_ROLERIGHT
		(
		  role_id     NUMBER,
		  right_id    NUMBER,
		  create_date DATE default SYSDATE not null
		)
	 */
	private String roleId;
	private String rightId;
	private String createDate;
	
	
	public AdminRoleRight() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public AdminRoleRight(String roleId, String rightId, String createDate) {
		super();
		this.roleId = roleId;
		this.rightId = rightId;
		this.createDate = createDate;
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
