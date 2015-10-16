package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.BabyInfo;

public interface BabyInfoDao {
	
	/**
	 * 创建babyinfo
	 */
	public void add(BabyInfo babyInfo);
	
	/**
	 * 查询一条babyinfo 信息
	 * @param id
	 * @return
	 */
	public BabyInfo findOne(String id);
	
	/**
	 * 根据baby_id查询babyinfo信息
	 * @param baby_id
	 * @return
	 */
	public List<BabyInfo> findByBabyId(String baby_id);

}
