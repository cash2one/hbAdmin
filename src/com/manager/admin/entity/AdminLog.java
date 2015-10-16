package com.manager.admin.entity;

import java.io.Serializable;

public class AdminLog implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * create table TBL_ADMIN_LOG
		(
		  oper_date    DATE,
		  oper_service NVARCHAR2(20) not null,
		  oper_admin   NVARCHAR2(30) not null,
		  oper_ip      NVARCHAR2(30),
		  oper_lang    NVARCHAR2(20),
		  oper_memo    NVARCHAR2(1024),
		  open_state   NCHAR(1) default '0'
		)
	 */
	
	private String operDate;
	private String operService;
	private String operAdmin;
	private String operIp;
	private String operLang;
	private String operMemo;
	private Integer openState;
	private String startDate;
	private String endDate;
	private String openStateInfo;
	
	
	public AdminLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public AdminLog(String operDate, String operService, String operAdmin,
			String operIp, String operLang, String operMemo, Integer openState,
			String startDate, String endDate, String openStateInfo) {
		super();
		this.operDate = operDate;
		this.operService = operService;
		this.operAdmin = operAdmin;
		this.operIp = operIp;
		this.operLang = operLang;
		this.operMemo = operMemo;
		this.openState = openState;
		this.startDate = startDate;
		this.endDate = endDate;
		this.openStateInfo = openStateInfo;
	}




	public String getOpenStateInfo() {
		return openStateInfo;
	}




	public void setOpenStateInfo(String openStateInfo) {
		this.openStateInfo = openStateInfo;
	}




	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}


	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}


	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	/**
	 * @return the operDate
	 */
	public String getOperDate() {
		return operDate;
	}
	/**
	 * @param operDate the operDate to set
	 */
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}
	/**
	 * @return the operService
	 */
	public String getOperService() {
		return operService;
	}
	/**
	 * @param operService the operService to set
	 */
	public void setOperService(String operService) {
		this.operService = operService;
	}
	/**
	 * @return the operAdmin
	 */
	public String getOperAdmin() {
		return operAdmin;
	}
	/**
	 * @param operAdmin the operAdmin to set
	 */
	public void setOperAdmin(String operAdmin) {
		this.operAdmin = operAdmin;
	}
	/**
	 * @return the operIp
	 */
	public String getOperIp() {
		return operIp;
	}
	/**
	 * @param operIp the operIp to set
	 */
	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}
	/**
	 * @return the operLang
	 */
	public String getOperLang() {
		return operLang;
	}
	/**
	 * @param operLang the operLang to set
	 */
	public void setOperLang(String operLang) {
		this.operLang = operLang;
	}
	/**
	 * @return the operMemo
	 */
	public String getOperMemo() {
		return operMemo;
	}
	/**
	 * @param operMemo the operMemo to set
	 */
	public void setOperMemo(String operMemo) {
		this.operMemo = operMemo;
	}


	/**
	 * @return the openState
	 */
	public Integer getOpenState() {
		return openState;
	}


	/**
	 * @param openState the openState to set
	 */
	public void setOpenState(Integer openState) {
		this.openState = openState;
	}
	
}
