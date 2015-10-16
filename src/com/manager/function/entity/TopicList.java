package com.manager.function.entity;

import java.io.Serializable;

public class TopicList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;       		//int(11)       帖子ID                 
	private String topic_typeId;    //int(4)        帖子类型ID              
	private String title;           //varchar(100)  帖子标题               
	private String content;         //text          帖子内容               
	private String ip;              //varchar(20)   发帖IP                 
	private String ip_address;      //varchar(30)   所在地                 
	private String uid;             //int(11)       发帖用户ID             
	private String createtime;      //datetime      发帖时间               
	private String countback;       //int(11)       评论数                 
	private String label;           //char(3)       （新 精 顶）           
	private String countbrowse;     //int(11)       阅读数                  
	private String affix;           //varchar(500)  附件（JSON）           
	private String status;          //char(1)       状态（0-对内 1-对外）
	
	private String type_name;//类型名称
	private String user_avatar;//用户头像
	private String user_nickname;//
	
	
	private String img_size;
	
	public TopicList(String id, String topic_typeId, String title,
			String content, String ip, String ip_address, String uid,
			String createtime, String countback, String label,
			String countbrowse, String affix, String status, String type_name,
			String user_avatar, String user_nickname, String img_size) {
		super();
		this.id = id;
		this.topic_typeId = topic_typeId;
		this.title = title;
		this.content = content;
		this.ip = ip;
		this.ip_address = ip_address;
		this.uid = uid;
		this.createtime = createtime;
		this.countback = countback;
		this.label = label;
		this.countbrowse = countbrowse;
		this.affix = affix;
		this.status = status;
		this.type_name = type_name;
		this.user_avatar = user_avatar;
		this.user_nickname = user_nickname;
		this.img_size = img_size;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	
	/**
	 * @return the img_size
	 */
	public String getImg_size() {
		return img_size;
	}

	/**
	 * @param img_size the img_size to set
	 */
	public void setImg_size(String img_size) {
		this.img_size = img_size;
	}

	public String getUser_avatar() {
		return user_avatar;
	}

	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}

	public TopicList() {
		super();
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
	 * @return the topic_typeId
	 */
	public String getTopic_typeId() {
		return topic_typeId;
	}

	/**
	 * @param topic_typeId the topic_typeId to set
	 */
	public void setTopic_typeId(String topic_typeId) {
		this.topic_typeId = topic_typeId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the ip_address
	 */
	public String getIp_address() {
		return ip_address;
	}

	/**
	 * @param ip_address the ip_address to set
	 */
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the createtime
	 */
	public String getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the countback
	 */
	public String getCountback() {
		return countback;
	}

	/**
	 * @param countback the countback to set
	 */
	public void setCountback(String countback) {
		this.countback = countback;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the countbrowse
	 */
	public String getCountbrowse() {
		return countbrowse;
	}

	/**
	 * @param countbrowse the countbrowse to set
	 */
	public void setCountbrowse(String countbrowse) {
		this.countbrowse = countbrowse;
	}

	/**
	 * @return the affix
	 */
	public String getAffix() {
		return affix;
	}

	/**
	 * @param affix the affix to set
	 */
	public void setAffix(String affix) {
		this.affix = affix;
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

	
}
