package com.manager.admin.entity;

import java.io.Serializable;

public class AdminRightUrl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * create table TBL_ADMIN_RIGHT_URL
		(
		  righturl_name NVARCHAR2(100) not null,
		  right_url     NVARCHAR2(1024) not null,
		  right_id      NUMBER not null
		)
	 */
	private String righturlName;
	private String rightUrl;
	private String rightId;
	
	public AdminRightUrl() {
		super();
	}
	
	public AdminRightUrl(String righturlName, String rightUrl, String rightId) {
		super();
		this.righturlName = righturlName;
		this.rightUrl = rightUrl;
		this.rightId = rightId;
	}
	/**
	 * @return the righturlName
	 */
	public String getRighturlName() {
		return righturlName;
	}
	/**
	 * @param righturlName the righturlName to set
	 */
	public void setRighturlName(String righturlName) {
		this.righturlName = righturlName;
	}
	/**
	 * @return the rightUrl
	 */
	public String getRightUrl() {
		return rightUrl;
	}
	/**
	 * @param rightUrl the rightUrl to set
	 */
	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
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
	
	
}
