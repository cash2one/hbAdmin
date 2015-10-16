package com.manager.function.entity;

import java.io.Serializable;

public class Bigeye implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;        //int(11)     
	private String img_url;   //varchar(100)
	private String link_url;  //varchar(200)
	private String content;   //varchar(100)
	private String module_id; //int(11)     
	private String sort;      //int(11)  
	private String status;
	private String module_name;
	private int num;
	
	private String link_type;//int(1) 默认0 url
	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	
	public Bigeye(String id, String img_url, String link_url, String content,
			String module_id, String sort, String status, String module_name,
			int num, String link_type) {
		super();
		this.id = id;
		this.img_url = img_url;
		this.link_url = link_url;
		this.content = content;
		this.module_id = module_id;
		this.sort = sort;
		this.status = status;
		this.module_name = module_name;
		this.num = num;
		this.link_type = link_type;
	}

	/**
	 * @return the link_type
	 */
	public String getLink_type() {
		return link_type;
	}

	/**
	 * @param link_type the link_type to set
	 */
	public void setLink_type(String link_type) {
		this.link_type = link_type;
	}

	public Bigeye() {
		super();
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the module_name
	 */
	public String getModule_name() {
		return module_name;
	}

	/**
	 * @param module_name the module_name to set
	 */
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the img_url
	 */
	public String getImg_url() {
		return img_url;
	}
	/**
	 * @param img_url the img_url to set
	 */
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	/**
	 * @return the link_url
	 */
	public String getLink_url() {
		return link_url;
	}
	/**
	 * @param link_url the link_url to set
	 */
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the module_id
	 */
	public String getModule_id() {
		return module_id;
	}
	/**
	 * @param module_id the module_id to set
	 */
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	
}
