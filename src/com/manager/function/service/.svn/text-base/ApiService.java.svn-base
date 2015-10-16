package com.manager.function.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

/**
 * API接口管理类
 * @author Jevon
 *
 */
@Service
public interface ApiService {
	
	/**
	 * API接口验证
	 * @return error-接口异常 0-参数不符合规则 1-通过 2-操作太频繁请稍后再试
	 */
	public Map CheckRequest(HttpServletRequest request);
	
	/**
	 * API接口执行
	 * @return
	 */
	public Map ExcuteRequest(HttpServletRequest request,HttpServletResponse response);
	
	
	public String md5(Map parmsmap,String appkey);
	
}
