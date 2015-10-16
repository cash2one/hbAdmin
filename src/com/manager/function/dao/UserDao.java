package com.manager.function.dao;

import java.util.List;

import com.manager.function.entity.User;

public interface UserDao {
	
	/**
	 * 获取id
	 */
	public int getId();
	
	/**
	 * 用户注册
	 * @param user
	 */
	public void add(User user);
	
	/**
	 * 用户修改资料
	 */
	public void updateaddress(User user);
	
	/**
	 * 获得用户资料
	 */
	public User findById(String user_id);
	
	/**
	 * 获取账号昵称头像
	 * @param ulist
	 * @return
	 */
	public List<User> getuserhead(List<User> ulist);
	
	/**
	 * 登录验证
	 */
	public User isExcit(User user);
	
	/**
	 * 统计
	 * @param user
	 * @return
	 */
	public List<User> statistics(User user,int pageNo,int pageSize);
	
	public int statistics_count(User user);
	
	public List<User> findUserList(User User,int pageNo,int pageSize);
	
	public int findUserCount(User User);
	
	public int updateUser(User User);
	
	public int updateUserStatus(User User);
	
	public int deleteUser(User User);
	
	public int insertUser(User User);
	
	public List<User> findUserList(User User);
	
	public int updateUserPwdByOpenId(User User);
	
}
