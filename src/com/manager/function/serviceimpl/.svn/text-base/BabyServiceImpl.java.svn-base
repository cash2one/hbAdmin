package com.manager.function.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.manager.function.dao.BabyDao;
import com.manager.function.dao.BabyInfoDao;
import com.manager.function.dao.UserDao;
import com.manager.function.dao.MedalDao;
import com.manager.function.dao.TokenDao;
import com.manager.function.dao.UserLearnPlanDao;
import com.manager.function.entity.Baby;
import com.manager.function.entity.BabyInfo;
import com.manager.function.entity.User;
import com.manager.function.entity.Medal;
import com.manager.function.entity.Token;
import com.manager.function.entity.UserLearnplan;
import com.manager.function.service.BabyService;
import com.manager.init.InitDataPool;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;
import com.manager.util.ImgDownload;
import com.manager.util.Xml;

/**
 * 推送
 */

//import javapns.back.PushNotificationManager;
//import javapns.back.SSLConnectionHelper;
//import javapns.data.Device;
//import javapns.data.PayLoad;

public class BabyServiceImpl implements BabyService {
	
	private Logger logger = Logger.getLogger(BabyServiceImpl.class);
	
	private BabyDao babyDao;
	
	private UserDao userDao;
	
	private BabyInfoDao babyInfoDao;
	
	private InitDataPool initDataPool;
	
	private MedalDao medalDao;
	
	private TokenDao tokenDao;
	
	private UserLearnPlanDao userLearnplanDao;

	public UserLearnPlanDao getUserLearnplanDao() {
		return userLearnplanDao;
	}

	public void setUserLearnplanDao(UserLearnPlanDao userLearnplanDao) {
		this.userLearnplanDao = userLearnplanDao;
	}

	public TokenDao getTokenDao() {
		return tokenDao;
	}

	public void setTokenDao(TokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}

	public MedalDao getMedalDao() {
		return medalDao;
	}

	public void setMedalDao(MedalDao medalDao) {
		this.medalDao = medalDao;
	}

	public InitDataPool getInitDataPool() {
		return initDataPool;
	}

	public void setInitDataPool(InitDataPool initDataPool) {
		this.initDataPool = initDataPool;
	}

	public BabyDao getBabyDao() {
		return babyDao;
	}

	public void setBabyDao(BabyDao babyDao) {
		this.babyDao = babyDao;
	}

	public Map add(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		int id = 0;
		try{
			String uid = (String) request.getParameter("uid");
			String baby_nickname = (String) request.getParameter("baby_nickname");
			
			Baby baby = new Baby();
			baby.setUser_id(uid);
			baby.setBaby_status("1");
			baby.setBaby_nickname(baby_nickname);
			
			boolean flag = false;
			
			if(uid==null||"".equals(uid)){
				result = "2";
				message = initDataPool.getSP("2-4-208");
			}else if(baby_nickname==null||"".equals(baby_nickname)){
				result = "3";
				message = initDataPool.getSP("2-4-209");
			}else{
				flag = true;
			}
			
			if(flag){
				id = this.babyDao.getId();
				baby.setId(id+"");
				this.babyDao.add(baby);
				result = "1";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
		}
		
		Map hsm = new LinkedHashMap();
        hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);
        if(result.equals("1")){
        	hsm.put("data", id+"");
        }
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("BabyServiceImpl.add执行了"+diff+"毫秒");
		return hsm;
	}
public Map addpre(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		
		JSONObject data = new JSONObject();
		int id = 0;
		try{
			String uid = (String) request.getParameter("uid");
			String baby_nickname = (String) request.getParameter("baby_nickname");
			String language_id = (String) request.getParameter("language_id");
			
			Baby baby1 = new Baby();
			baby1.setUser_id(uid);
			baby1.setBaby_status("1");
			baby1.setBaby_nickname(baby_nickname);
			baby1.setBaby_language(language_id);
			
			boolean flag = false;
			
			if(uid==null||"".equals(uid)){
				result = "2";
				message = initDataPool.getSP("2-4-208");
			}else if(baby_nickname==null||"".equals(baby_nickname)){
				result = "3";
				message = initDataPool.getSP("2-4-209");
			}else{
				flag = true;
			}
			
			if(flag){
				id = this.babyDao.getId();
				baby1.setId(id+"");
				this.babyDao.add(baby1);
				//result = "1";
			}
			
			String baby_id = baby1.getId();//(String) request.getParameter("baby_id");
			String level_id = (String) request.getParameter("level_id");
			String hobby_id = (String) request.getParameter("hobby_id");
			String user_id = (String) request.getParameter("uid");
			
			String[] hobby_ids = hobby_id.split(",");
			BabyInfo babyInfo = new BabyInfo();
			babyInfo.setBaby_id(baby_id);
			babyInfo.setLevel_id(level_id);
			//babyInfo.setBaby_language(language_id);
			
			flag = false;
			
			if(baby_id==null||level_id==null||hobby_id==null||user_id==null||language_id==null||"".equals(baby_id)||"".equals(level_id)||"".equals(hobby_id)||"".equals(user_id)||"".equals(language_id)){
				result = "0";
				message = initDataPool.getSP("2-4-203");
			}else{
				flag = true;
			}
			
			if(flag){
				for(int i = 0;i<hobby_ids.length;i++ ){
					babyInfo.setProperty_id(hobby_ids[i]);
					this.babyInfoDao.add(babyInfo);
				}
				result = "1";
			}
			if(result.equals("1")){
				User userModel1 = this.userDao.findById(user_id);
				//List<Baby> ls = this.babyDao.findByUserId(user_id);
				List<Baby> list1 = new ArrayList<Baby>();
				//if(ls!=null){
					//for(Baby baby:ls){
						String idl = baby1.getId();
						List<BabyInfo> list = this.babyInfoDao.findByBabyId(idl);
						String hobbyIds = "";
						String Level_ids = "";
						//String languageid = "";
						if(list!=null){
							for(int i = 0;i<list.size();i++){
								if(idl.equals(list.get(i).getBaby_id())){
									Level_ids = list.get(i).getLevel_id();
									//languageid = list.get(i).getBaby_language();
									if(i==0){
										hobbyIds = list.get(i).getProperty_id();
									}else{
										hobbyIds +=","+list.get(i).getProperty_id();
									}
								}
							}
						}
						baby1.setProperty_id(hobbyIds);
						baby1.setLevel_id(Level_ids);
						//baby1.setBaby_language(language_id);
						baby1.setLison_count("0");
						baby1.setRead_count("0");
						
						int rank = this.babyDao.getRank(idl);
						baby1.setBaby_rank(rank+"");
						
						list1.add(baby1);
						
					//}
				//}
				JSONObject obj = new JSONObject();
				obj.put("uid", userModel1.getUser_id()!=null?userModel1.getUser_id():"");
				obj.put("user_avatar", userModel1.getUser_avatar()!=null?userModel1.getUser_avatar():"");
				obj.put("user_email", userModel1.getUser_email()!=null?userModel1.getUser_email():"");
				obj.put("user_nickname", userModel1.getUser_nickname()!=null?userModel1.getUser_nickname():"");
				obj.put("user_title", userModel1.getUser_title()!=null?userModel1.getUser_title():"");
				obj.put("user_age", userModel1.getUser_age()!=null?userModel1.getUser_age():"");
				obj.put("province", userModel1.getProvince_id()!=null?userModel1.getProvince_id():"");
				obj.put("city", userModel1.getCity_id()!=null?userModel1.getCity_id():"");
				obj.put("district", userModel1.getDistrict_id()!=null?userModel1.getDistrict_id():"");
				obj.put("backup", userModel1.getBackup()!=null?userModel1.getBackup():"");
				obj.put("baby", list1);
				data.put("userinfo", obj);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
		}
		
		Map hsm = new LinkedHashMap();
        hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);
        hsm.put("data", data);
        
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("BabyServiceImpl.add执行了"+diff+"毫秒");
		return hsm;
	}
	public Map findByUserId(HttpServletRequest request) {
		return null;
	}

	public Map findOne(HttpServletRequest request) {
		return null;
	}

	public Map addToken(HttpServletRequest request)
	{
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
	
		try{
			String token_str = (String) request.getParameter("token");
			List<Token> tokenls = tokenDao.getToken();
			
			boolean flag = false;
			
			if(token_str==null||"".equals(token_str)){
				result = "2";
				message = "token为空";
			}else{
				flag = true;
			}
			int id = 0;
			if(tokenls!=null)
				id = tokenls.size()+1;
			else
				id = 1;
			Token medal = new Token();
			medal.setToken_str(token_str);
			medal.setToken_id(id+"");
			
			int isLook = tokenDao.getTokenCount(medal);
			
			if(isLook==0)
			{
				tokenDao.add(medal);
				
				result = "1";
				message = "添加Token成功";
			}else
			{
				result = "2";
				message = "已经存在,不需添加";
			}
	
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
		}
		
		Map hsm = new LinkedHashMap();
        hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);
        
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("BabyServiceImpl.updateAvatar执行了"+diff+"毫秒");
		return hsm;
	}
	
	public Map pushToken(HttpServletRequest request)
	{
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		String content = (String) request.getParameter("push_content");
		if(content.length()>256)
		{
			Map hsm = new LinkedHashMap();
	        hsm.put("version", Constant.version);
	        hsm.put("result", "error");
	        hsm.put("message", "内容太长了，发不了");
	        return hsm;
		}
		JSONObject obj = new JSONObject();
		
		List<Token> tokenls = tokenDao.getToken();
		if(tokenls!=null)
		{
			int resut = 0;
			for(Token tok:tokenls)
			{
				String deviceToken = tok.getToken_str();
				String id = tok.getToken_id();
				try{
		
					//被推送的iphone应用程序标示符      
			           
		            PayLoad payLoad = new PayLoad();
		            payLoad.addAlert(content);
		            payLoad.addBadge(1);
		            payLoad.addSound("default");
		            
		            PushNotificationManager pushManager = PushNotificationManager.getInstance();
		            pushManager.addDevice("iPhone", deviceToken);
		            
		            String host= "gateway.sandbox.push.apple.com";  //测试用的苹果推送服务器
		            int port = 2195;
		            String certificatePath = "c:/huibenshu.p12"; //刚才在mac系统下导出的证书
		              
		            String certificatePassword= "820425";
		            
		            pushManager.initializeConnection(host, port, certificatePath,certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
		              
		            //Send Push
		            Device client = pushManager.getDevice("iPhone");
		            pushManager.sendNotification(client, payLoad); 
		            pushManager.stopConnection();
		            pushManager.removeDevice("iPhone");
				}catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
					result = "error";
					message = "数据有错";
					obj.put(id, deviceToken);
					resut = 1;
				}
			}
			if(resut == 0)
			{
				result = "1";
				message = "push成功";
			}
		}
		
		Map hsm = new LinkedHashMap();
        hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);
        hsm.put("data", obj);
        
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("BabyServiceImpl.updateAvatar执行了"+diff+"毫秒");
		return hsm;
	}
	public Map addcount(HttpServletRequest request)
	{
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		int lnum = 0;
		int rnum = 0;
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		
		JSONObject obj = new JSONObject();
		int id = 0;
		try{
			String baby_id = (String) request.getParameter("baby_id");
			String res_type = (String) request.getParameter("res_type");
			String res_id = (String) request.getParameter("res_id");
			String plan_res_id = (String) request.getParameter("inf_id");
			
			boolean flag = false;
			
			if(baby_id==null||"".equals(baby_id)){
				result = "2";
				message = initDataPool.getSP("2-4-211");
			}else{
				flag = true;
			}
			
			if(res_id==null||"".equals(res_id)){
				result = "2";
				message = initDataPool.getSP("2-4-211");
			}else{
				flag = true;
			}
			if(plan_res_id!=null && !"".equals(plan_res_id) && !"0".equals(plan_res_id))
			{
				UserLearnplan upModel = this.userLearnplanDao.findOne(plan_res_id);
				if(upModel.getPlan_status().equals("0")){
			
					//更新状态
					UserLearnplan ulp = new UserLearnplan();
					ulp.setId(plan_res_id);
					//ulp.setSpend_minute(spend_minute);
					ulp.setPlan_status("1");
					this.userLearnplanDao.update(ulp);
				}
			}
			
			Medal medal = new Medal();
			medal.setBaby_id(baby_id);
			medal.setResourse_id(res_id);
			int isLook = medalDao.getMedal(medal);
			Baby baby = babyDao.findOne(baby_id);
			if(isLook==0)
			{
				if(baby.getLison_count()!=null)
					lnum =Integer.parseInt(baby.getLison_count());
				if(baby.getRead_count()!=null)
					rnum =Integer.parseInt(baby.getRead_count());
				if(res_type.equals("1"))
				{
					if(lnum < 120)
					{
						lnum+=1;
						baby.setLison_count(lnum+"");
						result = "1";
						message = initDataPool.getSP("2-4-243");
					}else
					{
						result = "0";
						message = initDataPool.getSP("2-4-244");
					}
				}else if(res_type.equals("2"))
				{
					if(rnum < 120)
					{
						rnum+=1;
						baby.setRead_count(rnum+"");
						result = "1";
						message = initDataPool.getSP("2-4-243");
					}else
					{
						result = "0";
						message = initDataPool.getSP("2-4-244");
					}
				}
				int sum = lnum+rnum;
				baby.setSum_count(sum+"");
				
				if(flag){
					this.babyDao.update(baby);	
					obj.put("lison_count", baby.getLison_count()!=null?baby.getLison_count():"0");
					obj.put("read_count", baby.getRead_count()!=null?baby.getRead_count():"0");
					int rank = this.babyDao.getRank(baby_id);
					obj.put("baby_rank", rank+"");
				}
				
				medalDao.add(medal);
			}else
			{
				obj.put("lison_count", baby.getLison_count()!=null?baby.getLison_count():"0");
				obj.put("read_count", baby.getRead_count()!=null?baby.getRead_count():"0");
				int rank = this.babyDao.getRank(baby_id);
				obj.put("baby_rank", rank+"");
				result = "1";
				message = initDataPool.getSP("2-4-227");
			}
	
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
		}
		
		Map hsm = new LinkedHashMap();
        hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);
        hsm.put("data", obj);
        
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("BabyServiceImpl.updateAvatar执行了"+diff+"毫秒");
		return hsm;
	}
	
	public Map resReadCount(HttpServletRequest request)
	{
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		
		JSONObject obj = new JSONObject();
		int count = 0;
		try{
			String res_id = (String) request.getParameter("res_id");
			boolean flag = false;
			
			
			if(res_id==null||"".equals(res_id)){
				result = "2";
				message = initDataPool.getSP("2-4-211");
			}else{
				flag = true;
			}
			
			
			Medal medal = new Medal();
			medal.setResourse_id(res_id);
			
			count = medalDao.getResCount(medal);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
		}
		
		Map hsm = new LinkedHashMap();
        hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);
        hsm.put("data", count+"");
        
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("BabyServiceImpl.updateAvatar执行了"+diff+"毫秒");
		return hsm;
	}
	
	public Map updateAvatar(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		String url = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		int id = 0;
		try{
			String baby_id = (String) request.getParameter("baby_id");
			
			String baby_avatar = ImgDownload.getPath(request);
			
			
			boolean flag = false;
			
			if(baby_id==null||"".equals(baby_id)){
				result = "2";
				message = initDataPool.getSP("2-4-211");
			}else{
				flag = true;
			}
			
			if(baby_avatar.equals("false")){
				result = "2";
				message = initDataPool.getSP("2-4-228");
				flag = false;
			}
			
			Baby baby = new Baby();
			baby.setId(baby_id);
			baby.setBaby_avatar(baby_avatar);
			
			if(flag){
				this.babyDao.update(baby);
				url = tobereplace(baby_avatar,0);
				result = "1";
				message = initDataPool.getSP("2-4-229");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
		}
		
		Map hsm = new LinkedHashMap();
        hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);
        hsm.put("data", url);
        
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("BabyServiceImpl.updateAvatar执行了"+diff+"毫秒");
		return hsm;
	}
	
	public String tobereplace(String message, int in) throws Exception {
		String path = BabyServiceImpl.class.getClassLoader().getResource("").toURI().getPath();
		path = path.split("WEB-INF")[0] + "WEB-INF/Config.xml";
		String bereplace = Xml.getXmlTagValue(path, "messageimagebereplace");
		String replace = Xml.getXmlTagValue(path, "messageimagereplace");
		if (in == 0) {
			if(CollectionUtil.checkNull(message)){
			message = message.replaceAll(replace, bereplace);
			}else{
				message = "";
			}
		}
		if (in == 1) {
			if(CollectionUtil.checkNull(message)){
				message = message.replaceAll(bereplace, replace);
			}
		}
		return message;
	}

	public Map deleteBaby(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		JSONObject data = new JSONObject();
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		try{
			String baby_id = (String) request.getParameter("baby_id");
			
			boolean flag = false;
			
			if(baby_id==null||"".equals(baby_id)){
				result = "2";
				message = initDataPool.getSP("2-4-211");
			}else{
				flag = true;
			}
			
			Baby baby1 = new Baby();
			baby1.setId(baby_id);
			baby1.setBaby_status("0");
			
			if(flag){
				this.babyDao.update(baby1);
				result = "1";
				message = initDataPool.getSP("2-4-236");
			}
			
			Baby babyModel = this.babyDao.findOne(baby_id);
			
			User userModel1 = this.userDao.findById(babyModel.getUser_id());
			List<Baby> ls = this.babyDao.findByUserId(babyModel.getUser_id());
			List<Baby> list1 = new ArrayList<Baby>();
			if(ls!=null){
				for(Baby baby:ls){
					String id = baby.getId();
					List<BabyInfo> list = this.babyInfoDao.findByBabyId(id);
					String hobby_ids = "";
					String Level_ids = "";
					if(list!=null){
						for(int i = 0;i<list.size();i++){
							if(id.equals(list.get(i).getBaby_id())){
								Level_ids = list.get(i).getLevel_id();
								if("".equals(hobby_ids)){
									hobby_ids = list.get(i).getProperty_id();
								}else{
									hobby_ids +=","+list.get(i).getProperty_id();
								}
							}
						}
					}
					baby.setProperty_id(hobby_ids);
					baby.setLevel_id(Level_ids);
					String url = baby.getBaby_avatar()!=null?tobereplace(baby.getBaby_avatar(), 0):"";
					baby.setBaby_avatar(url);
					list1.add(baby);
					
				}
			}
			JSONObject obj = new JSONObject();
			obj.put("uid", userModel1.getUser_id()!=null?tobereplace(userModel1.getUser_id(), 0):"");
			obj.put("user_avatar", userModel1.getUser_avatar()!=null?tobereplace(userModel1.getUser_avatar(), 0):"");
			obj.put("user_email", userModel1.getUser_email()!=null?userModel1.getUser_email():"");
			obj.put("user_nickname", userModel1.getUser_nickname()!=null?userModel1.getUser_nickname():"");
			obj.put("user_title", userModel1.getUser_title()!=null?userModel1.getUser_title():"");
			obj.put("user_age", userModel1.getUser_age()!=null?userModel1.getUser_age():"");
			obj.put("province", userModel1.getProvince_id()!=null?userModel1.getProvince_id():"");
			obj.put("city", userModel1.getCity_id()!=null?userModel1.getCity_id():"");
			obj.put("district", userModel1.getDistrict_id()!=null?userModel1.getDistrict_id():"");
			obj.put("backup", userModel1.getBackup()!=null?userModel1.getBackup():"");
			obj.put("baby", ls!=null?list1:"");
			data.put("userinfo", obj);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
		}
		
		Map hsm = new LinkedHashMap();
        hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);
        hsm.put("data", data);
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("BabyServiceImpl.deleteBaby执行了"+diff+"毫秒");
		return hsm;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public BabyInfoDao getBabyInfoDao() {
		return babyInfoDao;
	}

	public void setBabyInfoDao(BabyInfoDao babyInfoDao) {
		this.babyInfoDao = babyInfoDao;
	}

}
