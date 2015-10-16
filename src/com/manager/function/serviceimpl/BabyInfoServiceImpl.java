package com.manager.function.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;

import com.manager.function.dao.BabyDao;
import com.manager.function.dao.BabyInfoDao;
import com.manager.function.dao.UserDao;
import com.manager.function.entity.Baby;
import com.manager.function.entity.BabyInfo;
import com.manager.function.entity.User;
import com.manager.function.service.BabyInfoService;
import com.manager.init.InitDataPool;
import com.manager.util.Constant;

public class BabyInfoServiceImpl implements BabyInfoService {
	
	private Logger logger = Logger.getLogger(BabyInfoServiceImpl.class);
	
	private BabyInfoDao babyInfoDao;
	
	private InitDataPool initDataPool;
	
	private BabyDao babyDao;
	
	private UserDao userDao;

	public BabyDao getBabyDao() {
		return babyDao;
	}

	public void setBabyDao(BabyDao babyDao) {
		this.babyDao = babyDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public InitDataPool getInitDataPool() {
		return initDataPool;
	}

	public void setInitDataPool(InitDataPool initDataPool) {
		this.initDataPool = initDataPool;
	}

	public BabyInfoDao getBabyInfoDao() {
		return babyInfoDao;
	}

	public void setBabyInfoDao(BabyInfoDao babyInfoDao) {
		this.babyInfoDao = babyInfoDao;
	}

	@Transactional
	public Map add(HttpServletRequest request) {
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
			String level_id = (String) request.getParameter("level_id");
			String hobby_id = (String) request.getParameter("hobby_id");
			String user_id = (String) request.getParameter("uid");
			
			String[] hobby_ids = hobby_id.split(",");
			BabyInfo babyInfo = new BabyInfo();
			babyInfo.setBaby_id(baby_id);
			babyInfo.setLevel_id(level_id);
			
			boolean flag = false;
			
			if(baby_id==null||level_id==null||hobby_id==null||user_id==null||"".equals(baby_id)||"".equals(level_id)||"".equals(hobby_id)||"".equals(user_id)){
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
						String id = baby.getId();
						List<BabyInfo> list = this.babyInfoDao.findByBabyId(id);
						String hobbyIds = "";
						String Level_ids = "";
						if(list!=null){
							for(int i = 0;i<list.size();i++){
								if(id.equals(list.get(i).getBaby_id())){
									Level_ids = list.get(i).getLevel_id();
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
				obj.put("baby", ls!=null?list1:"");
				data.put("userinfo", obj);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
			new RuntimeException("添加babyinfo出错");
		}
		
		Map hsm = new LinkedHashMap();
        hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);
        hsm.put("data", data);
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("BabyInfoServiceImpl.add执行了"+diff+"毫秒");
		return hsm;
	}

	public Map findByBabyId(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map findOne(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
