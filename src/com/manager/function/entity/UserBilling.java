package com.manager.function.entity;

import java.io.Serializable;

public class UserBilling implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String user_id;
	
	private String user_money;
	
	private String user_score;
	
	private String lastpay_date;
	
	private String lastpay_money;
	
	private String user_use_money;
	
	private String user_use_score;

	public String getUser_use_money() {
		return user_use_money;
	}

	public void setUser_use_money(String user_use_money) {
		this.user_use_money = user_use_money;
	}

	public String getUser_use_score() {
		return user_use_score;
	}

	public void setUser_use_score(String user_use_score) {
		this.user_use_score = user_use_score;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_money() {
		return user_money;
	}

	public void setUser_money(String user_money) {
		this.user_money = user_money;
	}

	public String getUser_score() {
		return user_score;
	}

	public void setUser_score(String user_score) {
		this.user_score = user_score;
	}

	public String getLastpay_date() {
		return lastpay_date;
	}

	public void setLastpay_date(String lastpay_date) {
		this.lastpay_date = lastpay_date;
	}

	public String getLastpay_money() {
		return lastpay_money;
	}

	public void setLastpay_money(String lastpay_money) {
		this.lastpay_money = lastpay_money;
	}

}
