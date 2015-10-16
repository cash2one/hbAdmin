package com.manager.function.entity;

import java.io.Serializable;

public class ResourceInfo implements Serializable{

	/*
	 * CREATE TABLE `tbl_resource_info` (
	  `id` int(11) NOT NULL COMMENT '主键ID',
	  `resource_id` int(11) DEFAULT NULL COMMENT '资源ID',
	  `set_number` varchar(10) DEFAULT NULL COMMENT '动画片集数',
	  `level_id` int(11) DEFAULT NULL COMMENT '阶段ID',
	  `property_id` int(11) DEFAULT NULL COMMENT '属性ID',
	  `language_level` int(11) DEFAULT NULL COMMENT '语言难度ID',
	  `resource_url` varchar(10) DEFAULT NULL COMMENT '资源地址',
	  `resource_keyword` varchar(30) DEFAULT NULL COMMENT '关键词',
	  `resource_lyrics` varchar(50) DEFAULT NULL COMMENT '歌词地址',
	  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
	  `create_adminuser` varchar(30) DEFAULT NULL COMMENT '创建管理员',
	  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
	  `update_adminuser` varchar(30) DEFAULT NULL COMMENT '修改管理员',
	  PRIMARY KEY (`id`),
	  KEY `tbl_fr_level` (`level_id`),
	  KEY `tbl_fr_hobby` (`language_level`),
	  KEY `tbl_fr_property` (`property_id`),
	  KEY `tbl_fr_resource` (`resource_id`),
	  CONSTRAINT `tbl_fr_hobby` FOREIGN KEY (`language_level`) REFERENCES `tbl_global_hobby` (`id`),
	  CONSTRAINT `tbl_fr_level` FOREIGN KEY (`level_id`) REFERENCES `tbl_global_level` (`id`),
	  CONSTRAINT `tbl_fr_property` FOREIGN KEY (`property_id`) REFERENCES `tbl_global_property` (`id`),
	  CONSTRAINT `tbl_fr_resource` FOREIGN KEY (`resource_id`) REFERENCES `tbl_resource` (`id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String id;// int(11) NOT NULL COMMENT '主键ID',
	private String resource_id;// int(11) DEFAULT NULL COMMENT '资源ID',
	private String set_number;// varchar(10) DEFAULT NULL COMMENT '动画片集数',
	private String level_id;// int(11) DEFAULT NULL COMMENT '阶段ID',
	private String property_id;// int(11) DEFAULT NULL COMMENT '属性ID',
	private String language_level;// int(11) DEFAULT NULL COMMENT '语言难度ID',
	private String resource_url;// varchar(10) DEFAULT NULL COMMENT '资源地址',
	private String resource_lyrics;// varchar(50) DEFAULT NULL COMMENT '歌词地址',
	private String create_date;// datetime DEFAULT NULL COMMENT '创建时间',
	private String create_adminuser;// varchar(30) DEFAULT NULL COMMENT '创建管理员',
	private String update_date;// datetime DEFAULT NULL COMMENT '修改时间',
	private String update_adminuser;// varchar(30) DEFAULT NULL COMMENT '修改管理员',
	private String resource_type;//资源详情分类
	private String resource_info_type;//(1-阶段 2-属性 3-语言难度 4-资源)
	private String resource_sort;//排序
	
	private String resource_url2;
	
	
	private String baby_id;
	
	
	public ResourceInfo() {
		super();
	}
	public ResourceInfo(String id, String resource_id, String set_number,
			String level_id, String property_id, String language_level,
			String resource_url, String resource_lyrics, String create_date,
			String create_adminuser, String update_date,
			String update_adminuser, String resource_type,
			String resource_info_type, String resource_sort,
			String resource_url2, String baby_id) {
		super();
		this.id = id;
		this.resource_id = resource_id;
		this.set_number = set_number;
		this.level_id = level_id;
		this.property_id = property_id;
		this.language_level = language_level;
		this.resource_url = resource_url;
		this.resource_lyrics = resource_lyrics;
		this.create_date = create_date;
		this.create_adminuser = create_adminuser;
		this.update_date = update_date;
		this.update_adminuser = update_adminuser;
		this.resource_type = resource_type;
		this.resource_info_type = resource_info_type;
		this.resource_sort = resource_sort;
		this.resource_url2 = resource_url2;
		this.baby_id = baby_id;
	}


	/**
	 * @return the resource_url2
	 */
	public String getResource_url2() {
		return resource_url2;
	}


	/**
	 * @param resource_url2 the resource_url2 to set
	 */
	public void setResource_url2(String resource_url2) {
		this.resource_url2 = resource_url2;
	}


	public String getBaby_id() {
		return baby_id;
	}


	public void setBaby_id(String baby_id) {
		this.baby_id = baby_id;
	}


	/**
	 * @return the resource_type
	 */
	public String getResource_type() {
		return resource_type;
	}


	/**
	 * @param resource_type the resource_type to set
	 */
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}


	/**
	 * @return the resource_info_type
	 */
	public String getResource_info_type() {
		return resource_info_type;
	}


	/**
	 * @param resource_info_type the resource_info_type to set
	 */
	public void setResource_info_type(String resource_info_type) {
		this.resource_info_type = resource_info_type;
	}


	/**
	 * @return the resource_sort
	 */
	public String getResource_sort() {
		return resource_sort;
	}


	/**
	 * @param resource_sort the resource_sort to set
	 */
	public void setResource_sort(String resource_sort) {
		this.resource_sort = resource_sort;
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
	 * @return the resource_id
	 */
	public String getResource_id() {
		return resource_id;
	}
	/**
	 * @param resource_id the resource_id to set
	 */
	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}
	/**
	 * @return the set_number
	 */
	public String getSet_number() {
		return set_number;
	}
	/**
	 * @param set_number the set_number to set
	 */
	public void setSet_number(String set_number) {
		this.set_number = set_number;
	}
	/**
	 * @return the level_id
	 */
	public String getLevel_id() {
		return level_id;
	}
	/**
	 * @param level_id the level_id to set
	 */
	public void setLevel_id(String level_id) {
		this.level_id = level_id;
	}
	/**
	 * @return the property_id
	 */
	public String getProperty_id() {
		return property_id;
	}
	/**
	 * @param property_id the property_id to set
	 */
	public void setProperty_id(String property_id) {
		this.property_id = property_id;
	}
	/**
	 * @return the language_level
	 */
	public String getLanguage_level() {
		return language_level;
	}
	/**
	 * @param language_level the language_level to set
	 */
	public void setLanguage_level(String language_level) {
		this.language_level = language_level;
	}
	/**
	 * @return the resource_url
	 */
	public String getResource_url() {
		return resource_url;
	}
	/**
	 * @param resource_url the resource_url to set
	 */
	public void setResource_url(String resource_url) {
		this.resource_url = resource_url;
	}
	/**
	 * @return the resource_lyrics
	 */
	public String getResource_lyrics() {
		return resource_lyrics;
	}
	/**
	 * @param resource_lyrics the resource_lyrics to set
	 */
	public void setResource_lyrics(String resource_lyrics) {
		this.resource_lyrics = resource_lyrics;
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
