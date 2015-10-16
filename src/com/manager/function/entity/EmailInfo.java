package com.manager.function.entity;

import java.util.List;

public class EmailInfo implements java.io.Serializable {

	public String emailType;//类型
	
	public String send_id;//发送编号
	
	public String email;//接受人email
	
	public String sender;
	
	public String title;//标题
	
	public String context;//正文
	
	public String subject; //主题
	
	public List FileName;//附件路径
	
	public boolean WithSlave;//是否带有附件
	
	public String checkCode;//验证码
	
	public String binding_EmailUrl;//绑定email url
	
	public String new_EmailUrl;//填写新email url
	
	public String reset_PasswordUrl;//重置密码 url
	
	public String sure_EmailUrl;//确认新email url    修改注册/绑定 的邮箱后 确认的链接
	
	
	


	
	public void setEmail(String email) {
		this.email = email;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	

	public String getSend_id() {
		return send_id;
	}


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getEmail() {
		return email;
	}

	public void setReceiveEmail(String email) {
		this.email = email;
	}

	public String getSender() {
		return sender;
	}

	public void setSenderEmail(String sender) {
		this.sender = sender;
	}

	public String getTitle() {
		return title;
	}

	public void setSenderName(String title) {
		this.title = title;
	}

	public List getFileName() {
		return FileName;
	}

	public void setFileName(List fileName) {
		FileName = fileName;
	}


	public String getBinding_EmailUrl() {
		return binding_EmailUrl;
	}


	public void setBinding_EmailUrl(String binding_EmailUrl) {
		this.binding_EmailUrl = binding_EmailUrl;
	}


	public String getCheckCode() {
		return checkCode;
	}


	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}


	public String getNew_EmailUrl() {
		return new_EmailUrl;
	}


	public void setNew_EmailUrl(String new_EmailUrl) {
		this.new_EmailUrl = new_EmailUrl;
	}


	public String getReset_PasswordUrl() {
		return reset_PasswordUrl;
	}


	public void setReset_PasswordUrl(String reset_PasswordUrl) {
		this.reset_PasswordUrl = reset_PasswordUrl;
	}


	public String getSure_EmailUrl() {
		return sure_EmailUrl;
	}


	public void setSure_EmailUrl(String sure_EmailUrl) {
		this.sure_EmailUrl = sure_EmailUrl;
	}


	public boolean isWithSlave() {
		return WithSlave;
	}


	public void setWithSlave(boolean withSlave) {
		WithSlave = withSlave;
	}


	
}
