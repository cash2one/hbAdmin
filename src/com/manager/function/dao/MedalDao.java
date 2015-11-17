package com.manager.function.dao;

//import java.util.List;
import com.manager.function.entity.Medal;

public interface MedalDao {
	
	/**
	 * 创建
	 */
	public void add(Medal medal);
	
	/**
	 * 获取数据
	 */
	public int getMedal(Medal medal);
}
