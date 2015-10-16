package com.manager.function.entity;


public class EmailBean {

	private String email_recipients;
	private String email_title;
	private String email_remark;
	private String email_date;
	private String email_time;
	
	public EmailBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail_recipients() {
		return email_recipients;
	}

	public void setEmail_recipients(String email_recipients) {
		this.email_recipients = email_recipients;
	}

	public String getEmail_title() {
		return email_title;
	}

	public void setEmail_title(String email_title) {
		this.email_title = email_title;
	}

	public String getEmail_remark() {
		return email_remark;
	}

	public void setEmail_remark(String email_remark) {
		this.email_remark = email_remark;
	}

	public String getEmail_date() {
		return email_date;
	}

	public void setEmail_date(String email_date) {
		this.email_date = email_date;
	}

	public String getEmail_time() {
		return email_time;
	}

	public void setEmail_time(String email_time) {
		this.email_time = email_time;
	}

}
