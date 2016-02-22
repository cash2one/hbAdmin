package com.manager.function.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public interface BabyService {


	/**
	 * 创建baby
	 */
	public Map add(HttpServletRequest request);
	
	/**
	 * 创建babypre
	 */
	public Map addpre(HttpServletRequest request);
	
	/**
	 * 根据ID获取BABY信息
	 * @param id
	 * @return
	 */
	public Map findOne(HttpServletRequest request);
	
	/**
	 * 查询用户下的BABY信息
	 * @param User_id
	 * @return
	 */
	public Map findByUserId(HttpServletRequest request);
	
	/**
	 * 上传头像
	 */
	public Map updateAvatar(HttpServletRequest request);
	
	/**
	 * 删除baby
	 */
	public Map deleteBaby(HttpServletRequest request);
	
	/**
	 * 增加阅读计数
	 */
	public Map addcount(HttpServletRequest request);
	
	/**
	 * 增加token
	 */
	public Map addToken(HttpServletRequest request);
	/**
	 * Push token
	 */
	public Map pushToken(HttpServletRequest request);
	
	public Map resReadCount(HttpServletRequest request);
}
