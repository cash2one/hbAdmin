package com.manager.function.entity;

import java.io.Serializable;

public class SearchLogs  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String user_id;
	
	private String search_value;
	
	private String search_date;
	
	private String search_ip;
	
	private String startDate;
	private String endDate;
	
	

	public SearchLogs() {
		super();
	}

	public SearchLogs(String id, String user_id, String search_value,
			String search_date, String search_ip, String startDate,
			String endDate) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.search_value = search_value;
		this.search_date = search_date;
		this.search_ip = search_ip;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSearch_value() {
		return search_value;
	}

	public void setSearch_value(String search_value) {
		this.search_value = search_value;
	}

	public String getSearch_date() {
		return search_date;
	}

	public void setSearch_date(String search_date) {
		this.search_date = search_date;
	}

	public String getSearch_ip() {
		return search_ip;
	}

	public void setSearch_ip(String search_ip) {
		this.search_ip = search_ip;
	}

}
