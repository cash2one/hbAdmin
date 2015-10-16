package com.manager.util;



import javax.mail.PasswordAuthentication;

public class SmtpAuth extends javax.mail.Authenticator {//进行邮件服务器用户认证 

	
		    private String user,password; 
		    public void getuserinfo(String getuser,String getpassword){ 
		      user=getuser; 
		      password=getpassword; 
		    } 
		    protected javax.mail.PasswordAuthentication getPasswordAuthentication(){ 
		      return new javax.mail.PasswordAuthentication(user,password); 
		    } 
		  } 
		  

