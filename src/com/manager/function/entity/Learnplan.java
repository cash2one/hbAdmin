package com.manager.function.entity;

import java.io.Serializable;

public class Learnplan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String plan_content;
	
	private String plan_summary;
	
	private String plan_config;
	
	private String plan_weekday;
	
	private String create_date;
	
	private String create_adminuser;
	
	private String update_date;
	
	private String update_adminuser;
	
	private String listen;
	private String watch;
	private String read;
	private String play;

	public Learnplan() {
		super();
	}

	public Learnplan(String id, String plan_content, String plan_summary,
			String plan_config, String plan_weekday, String create_date,
			String create_adminuser, String update_date,
			String update_adminuser, String listen, String watch, String read,
			String play) {
		super();
		this.id = id;
		this.plan_content = plan_content;
		this.plan_summary = plan_summary;
		this.plan_config = plan_config;
		this.plan_weekday = plan_weekday;
		this.create_date = create_date;
		this.create_adminuser = create_adminuser;
		this.update_date = update_date;
		this.update_adminuser = update_adminuser;
		this.listen = listen;
		this.watch = watch;
		this.read = read;
		this.play = play;
	}

	/**
	 * @return the listen
	 */
	public String getListen() {
		return listen;
	}

	/**
	 * @param listen the listen to set
	 */
	public void setListen(String listen) {
		this.listen = listen;
	}

	/**
	 * @return the watch
	 */
	public String getWatch() {
		return watch;
	}

	/**
	 * @param watch the watch to set
	 */
	public void setWatch(String watch) {
		this.watch = watch;
	}

	/**
	 * @return the read
	 */
	public String getRead() {
		return read;
	}

	/**
	 * @param read the read to set
	 */
	public void setRead(String read) {
		this.read = read;
	}

	/**
	 * @return the play
	 */
	public String getPlay() {
		return play;
	}

	/**
	 * @param play the play to set
	 */
	public void setPlay(String play) {
		this.play = play;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlan_content() {
		return plan_content;
	}

	public void setPlan_content(String plan_content) {
		this.plan_content = plan_content;
	}

	public String getPlan_summary() {
		return plan_summary;
	}

	public void setPlan_summary(String plan_summary) {
		this.plan_summary = plan_summary;
	}

	public String getPlan_config() {
		return plan_config;
	}

	public void setPlan_config(String plan_config) {
		this.plan_config = plan_config;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getCreate_adminuser() {
		return create_adminuser;
	}

	public void setCreate_adminuser(String create_adminuser) {
		this.create_adminuser = create_adminuser;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getUpdate_adminuser() {
		return update_adminuser;
	}

	public void setUpdate_adminuser(String update_adminuser) {
		this.update_adminuser = update_adminuser;
	}

	public String getPlan_weekday() {
		return plan_weekday;
	}

	public void setPlan_weekday(String plan_weekday) {
		this.plan_weekday = plan_weekday;
	}

}
