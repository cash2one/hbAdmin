package com.manager.function.entity;

import java.io.Serializable;

public class Baby implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String user_id;
	
	private String baby_nickname;

	private String baby_avatar;
	
	private String baby_birthday;
	
	private String create_date;
	
	private String level_id;
	
	private String property_id;
	
	private String update_date;
	
	private String baby_status;
	
	private String baby_language;

	public String getBaby_status() {
		return baby_status;
	}

	public void setBaby_status(String baby_status) {
		this.baby_status = baby_status;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getLevel_id() {
		return level_id;
	}

	public void setLevel_id(String level_id) {
		this.level_id = level_id;
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

	public String getBaby_nickname() {
		return baby_nickname;
	}

	public String getProperty_id() {
		return property_id;
	}

	public void setProperty_id(String property_id) {
		this.property_id = property_id;
	}

	public void setBaby_nickname(String baby_nickname) {
		this.baby_nickname = baby_nickname;
	}

	public String getBaby_avatar() {
		return baby_avatar;
	}

	public void setBaby_avatar(String baby_avatar) {
		this.baby_avatar = baby_avatar;
	}

	public String getBaby_birthday() {
		return baby_birthday;
	}

	public void setBaby_birthday(String baby_birthday) {
		this.baby_birthday = baby_birthday;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String getBaby_language() {
		return baby_language;
	}

	public void setBaby_language(String baby_language) {
		this.baby_language = baby_language;
	}
}
