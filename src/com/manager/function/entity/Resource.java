package com.manager.function.entity;

import java.io.Serializable;

public class Resource implements Serializable {

	/*
	 * CREATE TABLE `tbl_resource` (
	  `id` int(8) NOT NULL COMMENT '主键ID',
	  `resource_content` varchar(100) DEFAULT NULL COMMENT '资源内容',
	  `resource_summary` varchar(500) DEFAULT NULL COMMENT '资源简介',
	  `resource_type_id` int(11) NOT NULL COMMENT '资源类型',
	  `img_index` varchar(50) DEFAULT NULL COMMENT '首页展示图片地址',
	  `img_index_size` varchar(30) DEFAULT NULL COMMENT '首页展示图片尺寸',
	  `img_list` varchar(50) DEFAULT NULL COMMENT '列表展示图片地址',
	  `img_list_size` varchar(30) DEFAULT NULL COMMENT '列表展示图片尺寸',
	  `img_main` varchar(50) DEFAULT NULL COMMENT '主界面背景地址',
	  `img_main_size` varchar(30) DEFAULT NULL COMMENT '主界面背景尺寸',
	  `resource_status` char(1) DEFAULT NULL COMMENT '状态（0-对内 1-对外 9-作废）',
	  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
	  `create_adminuser` varchar(30) DEFAULT NULL COMMENT '创建管理员账号',
	  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
	  `update_adminuser` varchar(30) DEFAULT NULL COMMENT '更新管理员账号',
	  PRIMARY KEY (`id`,`resource_type_id`),
	  KEY `tbl_resource_type` (`resource_type_id`),
	  CONSTRAINT `tbl_resource_type` FOREIGN KEY (`resource_type_id`) REFERENCES `tbl_resource_type` (`id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8
	 */
	private String id;// int(8) NOT NULL COMMENT '主键ID',                                          
	private String resource_content;// varchar(100) DEFAULT NULL COMMENT '资源内容',                
	private String resource_summary;// varchar(500) DEFAULT NULL COMMENT '资源简介',                
	private String resource_type_id;// int(11) NOT NULL COMMENT '资源类型',                         
	private String img_index;// varchar(50) DEFAULT NULL COMMENT '首页展示图片地址',                
	private String img_index_size;// varchar(30) DEFAULT NULL COMMENT '首页展示图片尺寸',           
	private String img_list;// varchar(50) DEFAULT NULL COMMENT '列表展示图片地址',                 
	private String img_list_size;// varchar(30) DEFAULT NULL COMMENT '列表展示图片尺寸',            
	private String img_main;// varchar(50) DEFAULT NULL COMMENT '主界面背景地址',                   
	private String img_main_size;// varchar(30) DEFAULT NULL COMMENT '主界面背景尺寸',      
	private String img_book;
	private String img_book_size;
	private String resource_status;// char(1) DEFAULT NULL COMMENT '状态（0-对内 1-对外 9-作废）',  
	private String create_date;// datetime DEFAULT NULL COMMENT '创建时间',                         
	private String create_adminuser;// varchar(30) DEFAULT NULL COMMENT '创建管理员账号',           
	private String update_date;// datetime DEFAULT NULL COMMENT '更新时间',                         
	private String update_adminuser;// varchar(30) DEFAULT NULL COMMENT '更新管理员账号',  
	private String resource_author;//绘本作者
	private String resource_keyword;//关键词
	
	private String ulp_id;//
	private String plan_id;//
	private String nowtime; //当前时间
	private String type_name;//类型名称
	private String language_level;//语言难度
	private String level_id;//阶段
	private String property_id;//属性
	private String baby_id;
	private int num;
	private int len;
	
	private String user_id;
	
	private int rowno;
	
	private String spend_minute;
	private String plan_status;
	private String  replenish;
	
	//read_content      varchar(500)  utf8_general_ci  YES             (NULL)           select,insert,update,references  开始读介绍                         
	//read_img          varchar(100)  utf8_general_ci  YES             (NULL)           select,insert,update,references  开始读封面图                      
	//lian_content      varchar(500)  utf8_general_ci  YES             (NULL)           select,insert,update,references  开始练介绍                         
	//lian_img          varchar(100)  utf8_general_ci  YES             (NULL)           select,insert,update,references  开始练封面图                      
	//start_content     varchar(500)  utf8_general_ci  YES             (NULL)           select,insert,update,references  起始介绍                            
	//start_img         varchar(100)  utf8_general_ci  YES             (NULL)           select,insert,update,references  起始封面图     
	private String read_content;
	private String read_img;
	private String lian_content;
	private String lian_img;
	private String start_content;
	private String start_img;
	
//	read_img_size     varchar(30)   utf8_general_ci  YES             (NULL)           select,insert,update,references  开始读封面图尺寸                
//	lian_img_size     varchar(30)   utf8_general_ci  YES             (NULL)           select,insert,update,references  开始练封面图尺寸                
//	start_img_size    varchar(30)   utf8_general_ci  YES             (NULL)           select,insert,update,references  起始封面图尺寸                   
//	book_content      varchar(500)  utf8_general_ci  YES             (NULL)           select,insert,update,references  绘本文字说明  
	
	private String read_img_size;
	private String lian_img_size;
	private String start_img_size;
	private String book_content;
	
	private String between_age;
	
	public String getBetween_age()
	{
		return between_age;
	}
	
	public void setBetween_age(String between_age) {
		this.between_age = between_age;
	}
	
	public String getRead_img_size() {
		return read_img_size;
	}

	public void setRead_img_size(String read_img_size) {
		this.read_img_size = read_img_size;
	}

	public String getLian_img_size() {
		return lian_img_size;
	}

	public void setLian_img_size(String lian_img_size) {
		this.lian_img_size = lian_img_size;
	}

	public String getStart_img_size() {
		return start_img_size;
	}

	public void setStart_img_size(String start_img_size) {
		this.start_img_size = start_img_size;
	}

	public String getBook_content() {
		return book_content;
	}

	public void setBook_content(String book_content) {
		this.book_content = book_content;
	}

	public String getRead_content() {
		return read_content;
	}

	public void setRead_content(String read_content) {
		this.read_content = read_content;
	}

	public String getRead_img() {
		return read_img;
	}

	public void setRead_img(String read_img) {
		this.read_img = read_img;
	}

	public String getLian_content() {
		return lian_content;
	}

	public void setLian_content(String lian_content) {
		this.lian_content = lian_content;
	}

	public String getLian_img() {
		return lian_img;
	}

	public void setLian_img(String lian_img) {
		this.lian_img = lian_img;
	}

	public String getStart_content() {
		return start_content;
	}

	public void setStart_content(String start_content) {
		this.start_content = start_content;
	}

	public String getStart_img() {
		return start_img;
	}

	public void setStart_img(String start_img) {
		this.start_img = start_img;
	}

	public String getReplenish() {
		return replenish;
	}

	public void setReplenish(String replenish) {
		this.replenish = replenish;
	}

	public String getSpend_minute() {
		return spend_minute;
	}

	public void setSpend_minute(String spend_minute) {
		this.spend_minute = spend_minute;
	}

	public String getPlan_status() {
		return plan_status;
	}

	public void setPlan_status(String plan_status) {
		this.plan_status = plan_status;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getBaby_id() {
		return baby_id;
	}

	public void setBaby_id(String baby_id) {
		this.baby_id = baby_id;
	}

	private String weekday_id;//周期
	public Resource() {
		super();
	}
	
	
	/**
	 * @return the rowno
	 */
	public int getRowno() {
		return rowno;
	}

	/**
	 * @param rowno the rowno to set
	 */
	public void setRowno(int rowno) {
		this.rowno = rowno;
	}

	public Resource(String id, String resource_content,
			String resource_summary, String resource_type_id, String img_index,
			String img_index_size, String img_list, String img_list_size,
			String img_main, String img_main_size, String resource_status,
			String create_date, String create_adminuser, String update_date,
			String update_adminuser, String resource_author,
			String resource_keyword, String type_name, String language_level,
			String level_id, String property_id, String weekday_id) {
		super();
		this.id = id;
		this.resource_content = resource_content;
		this.resource_summary = resource_summary;
		this.resource_type_id = resource_type_id;
		this.img_index = img_index;
		this.img_index_size = img_index_size;
		this.img_list = img_list;
		this.img_list_size = img_list_size;
		this.img_main = img_main;
		this.img_main_size = img_main_size;
		this.resource_status = resource_status;
		this.create_date = create_date;
		this.create_adminuser = create_adminuser;
		this.update_date = update_date;
		this.update_adminuser = update_adminuser;
		this.resource_author = resource_author;
		this.resource_keyword = resource_keyword;
		this.type_name = type_name;
		this.language_level = language_level;
		this.level_id = level_id;
		this.property_id = property_id;
		this.weekday_id = weekday_id;
	}


	/**
	 * @return the weekday_id
	 */
	public String getWeekday_id() {
		return weekday_id;
	}


	/**
	 * @param weekday_id the weekday_id to set
	 */
	public void setWeekday_id(String weekday_id) {
		this.weekday_id = weekday_id;
	}


	/**
	 * @return the resource_keyword
	 */
	public String getResource_keyword() {
		return resource_keyword;
	}

	/**
	 * @param resource_keyword the resource_keyword to set
	 */
	public void setResource_keyword(String resource_keyword) {
		this.resource_keyword = resource_keyword;
	}

	/**
	 * @return the resource_author
	 */
	public String getResource_author() {
		return resource_author;
	}

	/**
	 * @param resource_author the resource_author to set
	 */
	public void setResource_author(String resource_author) {
		this.resource_author = resource_author;
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
	 * @return the resource_content
	 */
	public String getResource_content() {
		return resource_content;
	}
	/**
	 * @param resource_content the resource_content to set
	 */
	public void setResource_content(String resource_content) {
		this.resource_content = resource_content;
	}
	/**
	 * @return the resource_summary
	 */
	public String getResource_summary() {
		return resource_summary;
	}
	/**
	 * @param resource_summary the resource_summary to set
	 */
	public void setResource_summary(String resource_summary) {
		this.resource_summary = resource_summary;
	}
	/**
	 * @return the resource_type_id
	 */
	public String getResource_type_id() {
		return resource_type_id;
	}
	/**
	 * @param resource_type_id the resource_type_id to set
	 */
	public void setResource_type_id(String resource_type_id) {
		this.resource_type_id = resource_type_id;
	}
	/**
	 * @return the img_index
	 */
	public String getImg_index() {
		return img_index;
	}
	/**
	 * @param img_index the img_index to set
	 */
	public void setImg_index(String img_index) {
		this.img_index = img_index;
	}
	/**
	 * @return the img_index_size
	 */
	public String getImg_index_size() {
		return img_index_size;
	}
	/**
	 * @param img_index_size the img_index_size to set
	 */
	public void setImg_index_size(String img_index_size) {
		this.img_index_size = img_index_size;
	}
	/**
	 * @return the img_list
	 */
	public String getImg_list() {
		return img_list;
	}
	/**
	 * @param img_list the img_list to set
	 */
	public void setImg_list(String img_list) {
		this.img_list = img_list;
	}
	/**
	 * @return the img_list_size
	 */
	public String getImg_list_size() {
		return img_list_size;
	}
	/**
	 * @param img_list_size the img_list_size to set
	 */
	public void setImg_list_size(String img_list_size) {
		this.img_list_size = img_list_size;
	}
	/**
	 * @return the img_main
	 */
	public String getImg_main() {
		return img_main;
	}
	/**
	 * @param img_main the img_main to set
	 */
	public void setImg_main(String img_main) {
		this.img_main = img_main;
	}
	/**
	 * @return the img_main_size
	 */
	public String getImg_main_size() {
		return img_main_size;
	}
	/**
	 * @param img_main_size the img_main_size to set
	 */
	public void setImg_main_size(String img_main_size) {
		this.img_main_size = img_main_size;
	}
	/**
	 * @return the resource_status
	 */
	public String getResource_status() {
		return resource_status;
	}
	/**
	 * @param resource_status the resource_status to set
	 */
	public void setResource_status(String resource_status) {
		this.resource_status = resource_status;
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

	public String getNowtime() {
		return nowtime;
	}

	public void setNowtime(String nowtime) {
		this.nowtime = nowtime;
	}

	public String getUlp_id() {
		return ulp_id;
	}

	public void setUlp_id(String ulp_id) {
		this.ulp_id = ulp_id;
	}

	public String getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}

	public String getImg_book() {
		return img_book;
	}

	public void setImg_book(String img_book) {
		this.img_book = img_book;
	}

	public String getImg_book_size() {
		return img_book_size;
	}

	public void setImg_book_size(String img_book_size) {
		this.img_book_size = img_book_size;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	

}
