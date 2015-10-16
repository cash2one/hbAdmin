package com.manager.function.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.manager.function.entity.EmailInfo;

@Service
public interface CcmsService {

	/**
	 * 创建邮件对象
	 * @param email
	 * @param content
	 * @return
	 */
	public EmailInfo sendEmail(String email,String title,String content);
	
	/**
	 * 发送邮件
	 * @param emailinfo
	 * @return
	 */
	public boolean sendMail(EmailInfo emailinfo);
	
	
	/**
	 * 接口发送邮件
	 * @param request
	 * @return
	 */
	public Map getPwdBackSendEmail(HttpServletRequest request);
}
