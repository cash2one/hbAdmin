package com.manager.function.entity;

import java.io.Serializable;

public class ResourceType implements Serializable {

	private static final long serialVersionUID = 1L;
	/*
	 * CREATE TABLE `tbl_resource_type` (
		  `id` int(11) NOT NULL COMMENT '资源类型ID',
		  `type_name` varchar(20) DEFAULT NULL COMMENT '资源类型名称',
		  `type_status` char(1) DEFAULT NULL COMMENT '类型状态（0-对内 1-对外 9-作废）',
		  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
		  `create_adminuser` varchar(30) DEFAULT NULL COMMENT '创建管理员',
		  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
		  `update_adminuser` varchar(30) DEFAULT NULL COMMENT '更新管理员',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8
	 */
	private String id;
	private String type_name;
	private String type_status;
	private String create_date;
	private String create_adminuser;
	private String update_date;
	private String update_adminuser;
	
	public ResourceType() {
		super();
	}
	
	public ResourceType(String id, String type_name, String type_status,
			String create_date, String create_adminuser, String update_date,
			String update_adminuser) {
		super();
		this.id = id;
		this.type_name = type_name;
		this.type_status = type_status;
		this.create_date = create_date;
		this.create_adminuser = create_adminuser;
		this.update_date = update_date;
		this.update_adminuser = update_adminuser;
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
	 * @return the type_name
	 */
	public String getType_name() {
		return type_name;
	}
	/**
	 * @param type_name the type_name to set
	 */
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	/**
	 * @return the type_status
	 */
	public String getType_status() {
		return type_status;
	}
	/**
	 * @param type_status the type_status to set
	 */
	public void setType_status(String type_status) {
		this.type_status = type_status;
	}
	/**
	 * @return the create_date
	 */
	public String getCreate_date() {
		return create_date;
	}
	/**
	 * @param create_date the create_date to set
	 */
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	/**
	 * @return the create_adminuser
	 */
	public String getCreate_adminuser() {
		return create_adminuser;
	}
	/**
	 * @param create_adminuser the create_adminuser to set
	 */
	public void setCreate_adminuser(String create_adminuser) {
		this.create_adminuser = create_adminuser;
	}
	/**
	 * @return the update_date
	 */
	public String getUpdate_date() {
		return update_date;
	}
	/**
	 * @param update_date the update_date to set
	 */
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	/**
	 * @return the update_adminuser
	 */
	public String getUpdate_adminuser() {
		return update_adminuser;
	}
	/**
	 * @param update_adminuser the update_adminuser to set
	 */
	public void setUpdate_adminuser(String update_adminuser) {
		this.update_adminuser = update_adminuser;
	}
	
	
	
}
