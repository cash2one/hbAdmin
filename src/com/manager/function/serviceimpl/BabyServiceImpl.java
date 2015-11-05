package com.manager.function.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.manager.function.dao.BabyDao;
import com.manager.function.dao.BabyInfoDao;
import com.manager.function.dao.UserDao;
import com.manager.function.entity.Baby;
import com.manager.function.entity.BabyInfo;
import com.manager.function.entity.User;
import com.manager.function.service.BabyService;
import com.manager.init.InitDataPool;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;
import com.manager.util.ImgDownload;
import com.manager.util.Xml;

public class BabyServiceImpl implements BabyService {
	
	private Logger logger = Logger.getLogger(BabyServiceImpl.class);
	
	private BabyDao babyDao;
	
	private UserDao userDao;
	
	private BabyInfoDao babyInfoDao;
	
	private InitDataPool initDataPool;

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
			
			Baby baby1 = new Baby();
			baby1.setUser_id(uid);
			baby1.setBaby_status("1");
			baby1.setBaby_nickname(baby_nickname);
			
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
			String language_id = (String) request.getParameter("language_id");
			
			String[] hobby_ids = hobby_id.split(",");
			BabyInfo babyInfo = new BabyInfo();
			babyInfo.setBaby_id(baby_id);
			babyInfo.setLevel_id(level_id);
			babyInfo.setBaby_language(language_id);
			
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
				List<Baby> ls = this.babyDao.findByUserId(user_id);
				List<Baby> list1 = new ArrayList<Baby>();
				if(ls!=null){
					for(Baby baby:ls){
						String idl = baby.getId();
						List<BabyInfo> list = this.babyInfoDao.findByBabyId(idl);
						String hobbyIds = "";
						String Level_ids = "";
						String languageid = "";
						if(list!=null){
							for(int i = 0;i<list.size();i++){
								if(idl.equals(list.get(i).getBaby_id())){
									Level_ids = list.get(i).getLevel_id();
									languageid = list.get(i).getBaby_language();
									if(i==0){
										hobbyIds = list.get(i).getProperty_id();
									}else{
										hobbyIds +=","+list.get(i).getProperty_id();
									}
								}
							}
						}
						baby.setProperty_id(hobbyIds);
						baby.setLevel_id(Level_ids);
						baby.setBaby_language(languageid);
						list1.add(baby);
						
					}
				}
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
