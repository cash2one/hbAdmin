package com.manager.function.entity;

import java.io.Serializable;

public class WeekdayResource implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;//int(11)        
	private String baby_id;
	private String resource_id;//int(11)           
	private String weekday_id;//int(11)           
	private String backup;//varchar(50)       
	private String create_date;//datetime          
	private String create_adminuser;//varchar(50)       
	private String update_date;//datetime          
	private String update_adminuser;//varchar(50)
	
	private String start_date;
	private String end_date;
	private String audio;
	private String video;
	private String animation;
	private String picturebook;
	private String game;
	private String resource_content;
	private String type_name;
	private String resource_type_id;
	private String baby_nickname;
	
	private String spend_minute;
	private String status;
	
	public String getSpend_minute() {
		return spend_minute;
	}

	public void setSpend_minute(String spend_minute) {
		this.spend_minute = spend_minute;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public WeekdayResource() {
		super();
	}
	
	public WeekdayResource(String id, String baby_id, String resource_id,
			String weekday_id, String backup, String create_date,
			String create_adminuser, String update_date,
			String update_adminuser, String start_date, String end_date,
			String audio, String video, String animation, String picturebook,
			String game, String resource_content, String type_name,
			String resource_type_id, String baby_nickname) {
		super();
		this.id = id;
		this.baby_id = baby_id;
		this.resource_id = resource_id;
		this.weekday_id = weekday_id;
		this.backup = backup;
		this.create_date = create_date;
		this.create_adminuser = create_adminuser;
		this.update_date = update_date;
		this.update_adminuser = update_adminuser;
		this.start_date = start_date;
		this.end_date = end_date;
		this.audio = audio;
		this.video = video;
		this.animation = animation;
		this.picturebook = picturebook;
		this.game = game;
		this.resource_content = resource_content;
		this.type_name = type_name;
		this.resource_type_id = resource_type_id;
		this.baby_nickname = baby_nickname;
	}

	/**
	 * @return the baby_nickname
	 */
	public String getBaby_nickname() {
		return baby_nickname;
	}

	/**
	 * @param baby_nickname the baby_nickname to set
	 */
	public void setBaby_nickname(String baby_nickname) {
		this.baby_nickname = baby_nickname;
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
	 * @return the start_date
	 */
	public String getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return the end_date
	 */
	public String getEnd_date() {
		return end_date;
	}

	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	/**
	 * @return the audio
	 */
	public String getAudio() {
		return audio;
	}

	/**
	 * @param audio the audio to set
	 */
	public void setAudio(String audio) {
		this.audio = audio;
	}

	/**
	 * @return the video
	 */
	public String getVideo() {
		return video;
	}

	/**
	 * @param video the video to set
	 */
	public void setVideo(String video) {
		this.video = video;
	}

	/**
	 * @return the animation
	 */
	public String getAnimation() {
		return animation;
	}

	/**
	 * @param animation the animation to set
	 */
	public void setAnimation(String animation) {
		this.animation = animation;
	}

	/**
	 * @return the picturebook
	 */
	public String getPicturebook() {
		return picturebook;
	}

	/**
	 * @param picturebook the picturebook to set
	 */
	public void setPicturebook(String picturebook) {
		this.picturebook = picturebook;
	}

	/**
	 * @return the game
	 */
	public String getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(String game) {
		this.game = game;
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
	 * @return the backup
	 */
	public String getBackup() {
		return backup;
	}
	/**
	 * @param backup the backup to set
	 */
	public void setBackup(String backup) {
		this.backup = backup;
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

	public String getBaby_id() {
		return baby_id;
	}

	public void setBaby_id(String baby_id) {
		this.baby_id = baby_id;
	}
	
}
