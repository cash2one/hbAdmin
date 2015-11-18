package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.Baby;

public interface BabyDao {
	
	/**
	 * 创建baby
	 */
	public void add(Baby baby);
	
	/**
	 * 根据ID获取BABY信息
	 * @param id
	 * @return
	 */
	public Baby findOne(String id);
	
	/**
	 * 查询用户下的BABY信息
	 * @param User_id
	 * @return
	 */
	public List<Baby> findByUserId(String user_id);
	
	/**
	 * 获取id
	 */
	public int getId();
	
	public void update(Baby baby);

	public int getRank(String id);
}
