package com.manager.function.entity;

import java.io.Serializable;

public class TopicReply implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;        	  //int(11)     主键ID               
	private String sort;          //int(11)     楼层ID               
	private String content;       //text        回复内容             
	private String ip;            //varchar(20) 回复IP地址           
	private String ip_address;    //varchar(30) IP所在地             
	private String topic_id;      //int(11)     帖子ID               
	private String uid;           //int(11)     用户ID               
	private String quote_content; //tinytext    引用回复内容         
	private String createtime;    //datetime    创建时间             
	private String status;        //char(1)     状态（0-对内 1-对外）
	
	private String topic_name;
	private String user_avatar;
	private String user_nickname;//
	
	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public TopicReply() {
		super();
	}
	
	public TopicReply(String id, String sort, String content, String ip,
			String ip_address, String topic_id, String uid,
			String quote_content, String createtime, String status,
			String topic_name) {
		super();
		this.id = id;
		this.sort = sort;
		this.content = content;
		this.ip = ip;
		this.ip_address = ip_address;
		this.topic_id = topic_id;
		this.uid = uid;
		this.quote_content = quote_content;
		this.createtime = createtime;
		this.status = status;
		this.topic_name = topic_name;
	}

	/**
	 * @return the topic_name
	 */
	public String getTopic_name() {
		return topic_name;
	}

	/**
	 * @param topic_name the topic_name to set
	 */
	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
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
	 * @return the topic_id
	 */
	public String getTopic_id() {
		return topic_id;
	}
	/**
	 * @param topic_id the topic_id to set
	 */
	public void setTopic_id(String topic_id) {
		this.topic_id = topic_id;
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
	 * @return the quote_content
	 */
	public String getQuote_content() {
		return quote_content;
	}
	/**
	 * @param quote_content the quote_content to set
	 */
	public void setQuote_content(String quote_content) {
		this.quote_content = quote_content;
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

	public String getUser_avatar() {
		return user_avatar;
	}

	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}

	
}
