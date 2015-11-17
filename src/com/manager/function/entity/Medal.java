package com.manager.function.entity;

import java.io.Serializable;

public class Medal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String baby_id;
	
	private String resourse_id;

	public String getBaby_id() {
		return baby_id;
	}

	public void setBaby_id(String baby_id) {
		this.baby_id = baby_id;
	}

	public String getResourse_id() {
		return resourse_id;
	}

	public void setResourse_id(String resourse_id) {
		this.resourse_id = resourse_id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
