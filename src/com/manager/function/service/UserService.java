package com.manager.function.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.manager.function.entity.ResourceType;
import com.manager.function.entity.User;

@Service
public interface UserService {

	/**
	 * 用户注册
	 * @param user
	 */
	public Map add(HttpServletRequest request);
	/**
	 * 用户注册pre
	 * @param user
	 */
	public Map addpre(HttpServletRequest request);
	
	/**
	 * 用户修改资料
	 */
	public Map updateaddress(HttpServletRequest request);
	
	/**
	 * 修改用户密码
	 * @param request
	 * @return
	 */
	public Map getPwdBack(HttpServletRequest request);
	
	/**
	 * 获得用户资料
	 */
	public Map findById(HttpServletRequest request);
	
	/**
	 * 登录验证
	 */
	public Map isExcit(HttpServletRequest request);
	
	/**
	 * 登录验证pre
	 */
	public Map isExcitpre(HttpServletRequest request);
	
	/**
	 * 获取积分
	 */
	public Map getScore(HttpServletRequest request);
	
	public int checkId(String id);
	
	/**
	 * 统计
	 * @param user
	 * @return
	 */
	public List<User> statistics(User user,int pageNo,int pageSize);
	
	public int statistics_count(User user);
	
	/**
	 * 注册用户查询
	 * @param user
	 * @return
	 */
	public List<User> findUserList(User user,int pageNo,int pageSize);
	
	/**
	 * 获取账号头像昵称
	 * @param request
	 * @return
	 */
	public Map getuserhead(HttpServletRequest request);
	
	public int findUserCount(User User);
	
	public int updateUser(User User);
	
	public int updateUserStatus(User User);
	
	public int deleteUser(String user_id);
	
	public int insertUser(User User);
	
	public List<User> findUserList();
	
	public User findUserOne(String user_id);
	
	public int checkUserNickname(String user_nickname);
	
	public int checkUserNickname(String user_id,String user_nickname);
	
}
