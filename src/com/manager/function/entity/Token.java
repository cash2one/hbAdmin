package com.manager.function.entity;

import java.io.Serializable;

public class Token implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String token_str;
	
	private String token_id;

	public String getToken_str() {
		return token_str;
	}

	public void setToken_str(String token_str) {
		this.token_str = token_str;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getToken_id() {
		return token_id;
	}

	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}
}
