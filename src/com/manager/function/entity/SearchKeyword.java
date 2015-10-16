package com.manager.function.entity;

import java.io.Serializable;

public class SearchKeyword implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String keyword_name;
	
	private String sort;
	
	private String status;
	

	public SearchKeyword() {
		super();
	}

	public SearchKeyword(String id, String keyword_name, String sort,
			String status) {
		super();
		this.id = id;
		this.keyword_name = keyword_name;
		this.sort = sort;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeyword_name() {
		return keyword_name;
	}

	public void setKeyword_name(String keyword_name) {
		this.keyword_name = keyword_name;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
