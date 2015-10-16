package com.manager.admin.entity;

import java.io.Serializable;

public class AdminUser implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * create table TBL_ADMIN_USER
		(
		  admin_user          NVARCHAR2(30) not null,
		  admin_pwd           NVARCHAR2(100) not null,
		  admin_realname      NVARCHAR2(50),
		  admin_email         NVARCHAR2(150),
		  admin_phone         NVARCHAR2(50),
		  admin_memo          NVARCHAR2(1024),
		  admin_state         NCHAR(1) default '1' not null,
		  created_date        DATE default SYSDATE not null,
		  modify_date         DATE,
		  admin_user_type     NCHAR(1) default '0',
		  last_cpassword_date DATE
		)
	 */
	private String adminAccount;
	private String adminPwd;
	private String adminRealName;
	private String adminEmail;
	private String adminPhone;
	private String adminMemo;
	private String adminState;
	private String createdDate;
	private String modifyDate;
	private String adminUserType;
	private String lastCpasswordDate;
	private String adminRole;
	
	public AdminUser() {
		super();
	}
	
	
	

	public AdminUser(String adminAccount, String adminPwd,
			String adminRealName, String adminEmail, String adminPhone,
			String adminMemo, String adminState, String createdDate,
			String modifyDate, String adminUserType, String lastCpasswordDate,
			String adminRole) {
		super();
		this.adminAccount = adminAccount;
		this.adminPwd = adminPwd;
		this.adminRealName = adminRealName;
		this.adminEmail = adminEmail;
		this.adminPhone = adminPhone;
		this.adminMemo = adminMemo;
		this.adminState = adminState;
		this.createdDate = createdDate;
		this.modifyDate = modifyDate;
		this.adminUserType = adminUserType;
		this.lastCpasswordDate = lastCpasswordDate;
		this.adminRole = adminRole;
	}




	/**
	 * @return the adminRole
	 */
	public String getAdminRole() {
		return adminRole;
	}


	/**
	 * @param adminRole the adminRole to set
	 */
	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}


	
	/**
	 * @return the adminAccount
	 */
	public String getAdminAccount() {
		return adminAccount;
	}




	/**
	 * @param adminAccount the adminAccount to set
	 */
	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}




	/**
	 * @return the adminPwd
	 */
	public String getAdminPwd() {
		return adminPwd;
	}
	/**
	 * @param adminPwd the adminPwd to set
	 */
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	/**
	 * @return the adminRealname
	 */
	public String getAdminRealName() {
		return adminRealName;
	}
	/**
	 * @param adminRealname the adminRealname to set
	 */
	public void setAdminRealName(String adminRealName) {
		this.adminRealName = adminRealName;
	}
	/**
	 * @return the adminEmail
	 */
	public String getAdminEmail() {
		return adminEmail;
	}
	/**
	 * @param adminEmail the adminEmail to set
	 */
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	/**
	 * @return the adminPhone
	 */
	public String getAdminPhone() {
		return adminPhone;
	}
	/**
	 * @param adminPhone the adminPhone to set
	 */
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	/**
	 * @return the adminMemo
	 */
	public String getAdminMemo() {
		return adminMemo;
	}
	/**
	 * @param adminMemo the adminMemo to set
	 */
	public void setAdminMemo(String adminMemo) {
		this.adminMemo = adminMemo;
	}
	/**
	 * @return the adminState
	 */
	public String getAdminState() {
		return adminState;
	}
	/**
	 * @param adminState the adminState to set
	 */
	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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
	 * @return the adminUserType
	 */
	public String getAdminUserType() {
		return adminUserType;
	}
	/**
	 * @param adminUserType the adminUserType to set
	 */
	public void setAdminUserType(String adminUserType) {
		this.adminUserType = adminUserType;
	}
	/**
	 * @return the lastCpasswordDate
	 */
	public String getLastCpasswordDate() {
		return lastCpasswordDate;
	}
	/**
	 * @param lastCpasswordDate the lastCpasswordDate to set
	 */
	public void setLastCpasswordDate(String lastCpasswordDate) {
		this.lastCpasswordDate = lastCpasswordDate;
	}
	
	
}
