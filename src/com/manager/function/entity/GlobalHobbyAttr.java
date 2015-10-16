package com.manager.function.entity;

import java.io.Serializable;

public class GlobalHobbyAttr implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String level_id;
	
	private String hobby_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLevel_id() {
		return level_id;
	}

	public void setLevel_id(String level_id) {
		this.level_id = level_id;
	}

	public String getHobby_id() {
		return hobby_id;
	}

	public void setHobby_id(String hobby_id) {
		this.hobby_id = hobby_id;
	}

}
