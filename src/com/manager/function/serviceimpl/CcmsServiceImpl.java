package com.manager.function.serviceimpl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.manager.function.dao.UserDao;
import com.manager.function.entity.EmailInfo;
import com.manager.function.entity.User;
import com.manager.function.service.ApiService;
import com.manager.function.service.CcmsService;
import com.manager.init.InitDataPool;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;
import com.manager.util.SmtpAuth;
import com.manager.util.Xml;

public class CcmsServiceImpl implements CcmsService {
	
	private Logger logger = Logger.getLogger(CcmsServiceImpl.class);

//	private Object[] obj;
//	private String type;
	private String emailMsg;
	
	private InitDataPool initDataPool;
	
	private ApiService apiService;
	
	private UserDao userDao;
	
	public InitDataPool getInitDataPool() {
		return initDataPool;
	}

	public void setInitDataPool(InitDataPool initDataPool) {
		this.initDataPool = initDataPool;
	}
	
	
	
	/**
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * @return the apiService
	 */
	public ApiService getApiService() {
		return apiService;
	}

	/**
	 * @param apiService the apiService to set
	 */
	public void setApiService(ApiService apiService) {
		this.apiService = apiService;
	}

	public Map getPwdBackSendEmail(HttpServletRequest request) {
		logger.info("CcmsServiceImpl.getPwdBackSendEmail=========");
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("CcmsServiceImpl.getPwdBackSendEmail开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		Map hsm = new LinkedHashMap();
		Map hsm2 = new LinkedHashMap();
		boolean flag = false;
		String appId = (String) request.getParameter("appid");
		String appkey = Constant.APPID_KEY.get(appId);
		try{
			
			 String email = (String) request.getParameter("email");
			 if(email==null||"".equals(email)||!CollectionUtil.checkEmail(email)){
					result = "0";
					message = initDataPool.getSP("2-4-203");
			}else{
				User user=new User();
				user.setOpen_id(email);
				if(this.userDao.findUserCount(user)<1){
					result = "0";
					message = initDataPool.getSP("2-4-241");
					hsm.put("version", Constant.version);
			        hsm.put("result", result);
			        hsm.put("message", message);
			        return hsm;
				}else{
					flag = true;
				}
			}
			 if(flag){
			     
			     String path = Xml.class.getClassLoader().getResource("").toURI().getPath();
				 path = path.split("WEB-INF")[0] + "WEB-INF"+File.separator+"Config.xml";
				 String czmm_url = Xml.getXmlTagValue(path, "czmm_url");
				 String czmm_css = Xml.getXmlTagValue(path, "czmm_css");
					
				 String html=CollectionUtil.readerFileWEBINF("czmm.html");
				 String urltime=adf.format(new Date(d1.getTime() + 2 * 60 * 60 * 1000));
				 String version=Constant.version;
				 hsm2.put("email", email);
				 hsm2.put("time", urltime);
				 hsm2.put("version", version);
				 hsm2.put("appId", appId);
				 String sign=this.apiService.md5(hsm2, appkey);
				 html=html.replace("__bashpath__",czmm_css).replace("__url__", czmm_url+"?email="+email
						 +"&time="+urltime+"&version="+version+"&appId="+appId+"&sign="+sign);
				 EmailInfo emailInfo=this.sendEmail(email, "《绘本树》重置密码", html);
				 if(this.sendMail(emailInfo)){
					 result = "1";
					 message = initDataPool.getSP("2-4-237");
				 }else{
					 result = "0";
					 message = initDataPool.getSP("2-4-238");
				 }
				 logger.info(email+"重置密码："+emailMsg==null?"":emailMsg);
			 }
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
		}
		hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);
        
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("CcmsServiceImpl.getPwdBackSendEmail执行了"+diff+"毫秒");
        
		return hsm;
	}
	
	public EmailInfo sendEmail(String email, String title, String content) {
		try {
			String path = Xml.class.getClassLoader().getResource("").toURI().getPath();
			path = path.split("WEB-INF")[0] + "WEB-INF"+File.separator+"Config.xml";
			String sendname = Xml.getXmlTagValue(path, "email_sendname");
			String from = Xml.getXmlTagValue(path, "email_from");
			
			EmailInfo emailInfo = new EmailInfo();
			emailInfo.setEmailType("SendHTMLMail");
			emailInfo.setSenderEmail(from);
			emailInfo.setSenderName(sendname);
			emailInfo.setReceiveEmail(email);
			emailInfo.setEmail(email);
			emailInfo.setSubject(title);
			emailInfo.setContext(content);
			
			return emailInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean sendMail(EmailInfo emailinfo) {
		Session session=null; // 邮件会话对象
		MimeMessage message=null; // 邮件对象
		Properties props=null;
		
		String Send_id = UUID.randomUUID().toString();
		try {
			
			String path = CcmsServiceImpl.class.getClassLoader().getResource("").toURI().getPath();
			path = path.split("WEB-INF")[0] + "WEB-INF"+File.separator+"Config.xml";
			String host = Xml.getXmlTagValue(path, "email_host");
			String username = Xml.getXmlTagValue(path, "email_username");
			String password = Xml.getXmlTagValue(path, "email_password");
			String sendname = Xml.getXmlTagValue(path, "email_sendname");
			String from = Xml.getXmlTagValue(path, "email_from");
			
			
			//logger.info("start SendMail ");
			SmtpAuth sa = new SmtpAuth();
			sa.getuserinfo(username, password);
			props = System.getProperties(); // 系统属性
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", host); // 设置SMTP邮件服务器
			session = Session.getInstance(props, sa);
			message = new MimeMessage(session);
			MimeBodyPart mbp = new MimeBodyPart();
			
			try {
				
				message.setFrom(new InternetAddress(from, MimeUtility.encodeText(sendname,MimeUtility.mimeCharset("gb2312"), null)));// 发送者地址,名称
			} catch (UnsupportedEncodingException e) {
				emailMsg=e.getMessage();
				return false;
			}
			
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailinfo.getEmail()));// 接受人地址
			message.setSubject(MimeUtility.encodeText(emailinfo.getSubject(),MimeUtility.mimeCharset("gb2312"), null)); 
//			message.setSubject(emailinfo.getSubject()); // 设置邮件主题
			message.setContent(emailinfo.getContext(), "text/html;charset=UTF-8");
			message.setSentDate(new java.util.Date()); // 发送日期
			Transport transport = session.getTransport("smtp");
			try{
				
				transport.connect(host,username,password);
			}catch(Exception e){
				logger.error("send mail error ",e);
				emailMsg = e.getMessage();
				return false;
			}
			Transport.send(message);
			
			return true;
		} catch (Exception e) {
			logger.error("send mail error ",e);
			e.printStackTrace();
			emailMsg = e.getMessage();
			return false;
		}
	}


}
