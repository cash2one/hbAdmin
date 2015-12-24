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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.manager.function.controller.GlobalHobbyController;
import com.manager.function.dao.BabyDao;
import com.manager.function.dao.BabyInfoDao;
import com.manager.function.dao.UserBillingDao;
import com.manager.function.dao.UserDao;
import com.manager.function.entity.Baby;
import com.manager.function.entity.BabyInfo;
import com.manager.function.entity.ResourceType;
import com.manager.function.entity.User;
import com.manager.function.entity.UserBilling;
import com.manager.function.service.UserService;
import com.manager.init.InitDataPool;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;
import com.manager.util.Xml;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
	
	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	private UserDao userDao;
	
	private BabyDao babyDao;
	
	private BabyInfoDao babyInfoDao;
	
	private InitDataPool initDataPool;
	
	private UserBillingDao userBillingDao;
	
	public int checkId(String id){
		User u=this.userDao.findById(id);
		if(u!=null && u.getUser_id()!=null){
			return 1;
		}
		return 0;
		
	}
	
	/**
	 * 统计
	 * @param user
	 * @return
	 */
	public List<User> statistics(User user,int pageNo,int pageSize){
		return this.userDao.statistics(user, pageNo, pageSize);
	}
	
	public int statistics_count(User user){
		return this.userDao.statistics_count(user);
	}
	
	/**
	 * 获取账号头像昵称
	 * @param request
	 * @return
	 */
	public Map getuserhead(HttpServletRequest request){
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		JSONArray array=new JSONArray();
		String user_id = "";
		boolean flag = false;
		try{
			user_id = (String) request.getParameter("uid");
			if(user_id==null||"".equals(user_id)){
				result = "0";
				message = initDataPool.getSP("2-4-208");
			}else{
				flag = true;
			}
			String[] arr_str=user_id.split(",");
			List<User> ulist=new ArrayList<User>();
			User user=null;
			for(int i=0;i<arr_str.length;i++){
				if(CollectionUtil.checkNull(arr_str[i])){
					user=new User();
					user.setUser_id(arr_str[i]);
					ulist.add(user);
				}
			}
			
			if(flag){
				List<User> uulist = this.userDao.getuserhead(ulist);
				if(uulist!=null)
				{
					for(int k=0;k<ulist.size();k++)
					{
						for(int i=0;i<uulist.size();i++){
							
							if(ulist.get(k).getUser_id().equals(uulist.get(i).getUser_id()))
							{
								JSONObject j = new JSONObject();
								User u=uulist.get(i);
								j.put("user_id", u.getUser_id()!=null?u.getUser_id():"" );
								j.put("user_nickname", u.getUser_nickname()!=null?u.getUser_nickname():"");
								j.put("user_avatar", u.getUser_avatar()!=null?u.getUser_avatar():"");
								array.add(j);
							}
						}
					}
				}
//				data.put("user_tel", user.getUser_tel()!=null?user.getUser_tel():"");
//				data.put("user_truename", user.getUser_truename()!=null?user.getUser_truename():"");
//				data.put("user_address", user.getUser_address()!=null?user.getUser_address():"");
				message = initDataPool.getSP("2-4-214");
				result  = "1";
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
        //hsm.put("data", !array.isEmpty()?array:"");
        hsm.put("data", array);
        
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("UserServiceImpl.getuserhead执行了"+diff+"毫秒");
        
		return hsm;
	}
	
	/**
	 * 注册用户查询
	 * @param user
	 * @return
	 */
	public List<User> findUserList(User User,int pageNo,int pageSize){
		return this.userDao.findUserList(User, pageNo, pageSize);
	}
	
	public int deleteUser(String user_id) {
		User User=new User();
		User.setUser_id(user_id);
		return this.userDao.deleteUser(User);
	}

	public int findUserCount(User User) {
		return this.userDao.findUserCount(User);
	}

	public List<User> findUserList() {
		return this.userDao.findUserList(null);
	}
	
	public int insertUser(User User) {
		return this.userDao.insertUser(User);
	}

	public int updateUser(User User) {
		return this.userDao.updateUser(User);
	}

	public int updateUserStatus(User User) {
		return this.userDao.updateUserStatus(User);
	}
	
	public User findUserOne(String user_id){
		User User=new User();
		User.setUser_id(user_id);
		List<User> UserList=this.userDao.findUserList(User);
		if(UserList!=null && UserList.size()>0){
			return UserList.get(0);
		}
		return null;
	}
	
	public int checkUserNickname(String user_nickname){
		User User=new User();
		User.setUser_nickname(user_nickname);
		List<User> bewsTypeList=this.userDao.findUserList(User);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
		}
		return ii;
	}
	
	public int checkUserNickname(String user_id,String user_nickname){
		User User=new User();
		User.setUser_nickname(user_nickname);
		List<User> bewsTypeList=this.userDao.findUserList(User);
		int ii=0;
		if(bewsTypeList!=null && bewsTypeList.size()>0){
			ii=bewsTypeList.size();
			if(user_id!=null){
				for(int i=0;i<bewsTypeList.size();i++){
					User bmt=bewsTypeList.get(i);
					if(user_id.equals(bmt.getUser_id())){
						ii=ii-1;
					}
				}
			}
		}
		return ii;
	}

	
	


	public UserBillingDao getUserBillingDao() {
		return userBillingDao;
	}

	public void setUserBillingDao(UserBillingDao userBillingDao) {
		this.userBillingDao = userBillingDao;
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

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional
	public Map add(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		JSONObject data = new JSONObject();
		String user_id = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		try{
			String uid = (String) request.getParameter("open_id");
			String source = (String) request.getParameter("source");
			String user_pwd = (String) request.getParameter("user_pwd");
			String user_avatar = (String) request.getParameter("user_avatar");
			String user_nickname = (String) request.getParameter("user_nickname");
			
			User user = new User();
			user.setOpen_id(uid);
			
			boolean flag = false;
			
			if(uid==null||source==null||"".equals(uid)||"".equals(source)||"".equals(user_pwd)||"".equals(user_pwd)){
				result = "0";
				message = initDataPool.getSP("2-4-203");
			}else{
				flag = true;
			}
			
			if(flag){
				User userModel = this.userDao.isExcit(user);
				if(userModel!=null){
					result = "2";
					message = initDataPool.getSP("2-4-205");
				}else{
					result = "1";
					message = initDataPool.getSP("2-4-204");
					user_id = this.userDao.getId()+"";
					user.setReg_source(source);
					user.setUser_id(user_id);
					user.setUser_pwd(user_pwd);
					user.setUser_nickname(user_nickname);
					user.setUser_avatar(user_avatar);
					this.userDao.add(user);
					UserBilling ub = new UserBilling();
					ub.setUser_id(user_id);
					ub.setUser_money("0");
					ub.setUser_score("0");
					ub.setLastpay_money("0");
					ub.setUser_use_money("0");
					ub.setUser_use_score("0");
					this.userBillingDao.add(ub);
				}
			}
			
			if(result.equals("1")){
				User userModel1 = this.userDao.findById(user_id);
				List<Baby> ls = this.babyDao.findByUserId(user_id);
				List<Baby> list1 = new ArrayList<Baby>();
				if(ls!=null){
				for(Baby baby:ls){
					String id = baby.getId();
					List<BabyInfo> list = this.babyInfoDao.findByBabyId(id);
					String hobby_ids = "";
					String Level_ids = "";
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
					baby.setProperty_id(hobby_ids);
					baby.setLevel_id(Level_ids);
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
				obj.put("baby", list1.size()!=0?list1:"");
				data.put("userinfo", obj);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
			throw new RuntimeException("userServiceImpl.add异常");
		}
		
		Map hsm = new LinkedHashMap();
        hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);
        hsm.put("data", data!=null?data:"");
        
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("UserServiceImpl.add执行了"+diff+"毫秒");
        
		return hsm;
	}

	@Transactional
	public Map addpre(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		JSONObject data = new JSONObject();
		String user_id = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		try{
			String uid = (String) request.getParameter("open_id");
			String source = (String) request.getParameter("source");
			String user_pwd = (String) request.getParameter("user_pwd");
			String user_avatar = (String) request.getParameter("user_avatar");
			String user_nickname = (String) request.getParameter("user_nickname");
			
			boolean flag = false;
			
		    // 邮箱验证规则
		    String regEx = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$";
		    // 编译正则表达式
		    Pattern pattern = Pattern.compile(regEx);
		    // 忽略大小写的写法
		    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(user_nickname);
		    // 字符串是否与正则表达式相匹配
		    boolean rs = matcher.matches();
		    if(rs)
		    {
		    	flag = true;
		    }else
		    {
		    	result = "0";
				message = "昵称只能用字母数字中文字符和下划线组成";
		    }
	
		    
		    
			User user = new User();
			user.setOpen_id(uid);
			
			if(flag)
			{
				if(uid==null||source==null||"".equals(uid)||"".equals(source)||"".equals(user_pwd)||"".equals(user_pwd)){
					result = "0";
					message = initDataPool.getSP("2-4-203");
					flag = false;
				}else{
					flag = true;
				}
			}
			if(flag){
				User userModel = this.userDao.isExcit(user);
				if(userModel!=null){
					result = "2";
					message = initDataPool.getSP("2-4-205");
				}else{
					result = "1";
					message = initDataPool.getSP("2-4-204");
					user_id = this.userDao.getId()+"";
					user.setReg_source(source);
					user.setUser_id(user_id);
					user.setUser_pwd(user_pwd);
					user.setUser_nickname(user_nickname);
					user.setUser_avatar(user_avatar);
					this.userDao.add(user);
					UserBilling ub = new UserBilling();
					ub.setUser_id(user_id);
					ub.setUser_money("0");
					ub.setUser_score("0");
					ub.setLastpay_money("0");
					ub.setUser_use_money("0");
					ub.setUser_use_score("0");
					this.userBillingDao.add(ub);
				}
			}
			
			if(result.equals("1")){
				User userModel1 = this.userDao.findById(user_id);
				List<Baby> ls = this.babyDao.findByUserId(user_id);
				List<Baby> list1 = new ArrayList<Baby>();
				if(ls!=null){
				for(Baby baby:ls){
					String id = baby.getId();
					List<BabyInfo> list = this.babyInfoDao.findByBabyId(id);
					String hobby_ids = "";
					String Level_ids = "";
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
					baby.setProperty_id(hobby_ids);
					baby.setLevel_id(Level_ids);
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
				obj.put("baby", list1);
				data.put("userinfo", obj);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
			throw new RuntimeException("userServiceImpl.add异常");
		}
		
		Map hsm = new LinkedHashMap();
        hsm.put("version", Constant.version);
        hsm.put("result", result);
        hsm.put("message", message);
        hsm.put("data", data);
        
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("UserServiceImpl.add执行了"+diff+"毫秒");
        
		return hsm;
	}
	public Map findById(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		JSONObject data = new JSONObject();
		String user_id = "";
		boolean flag = false;
		try{
			user_id = (String) request.getParameter("uid");
			if(user_id==null||"".equals(user_id)){
				result = "0";
				message = initDataPool.getSP("2-4-208");
			}else{
				flag = true;
			}
			
			if(flag){
				User user = this.userDao.findById(user_id);
				data.put("user_tel", user.getUser_tel()!=null?user.getUser_tel():"");
				data.put("user_truename", user.getUser_truename()!=null?user.getUser_truename():"");
				data.put("user_address", user.getUser_address()!=null?user.getUser_address():"");
				message = initDataPool.getSP("2-4-214");
				result  = "1";
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
        logger.info("UserServiceImpl.findById执行了"+diff+"毫秒");
        
		return hsm;
	}

	public Map isExcit(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		JSONObject data = new JSONObject();
		String user_id = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		try{
			String uid = (String) request.getParameter("open_id");
			String user_pwd = (String) request.getParameter("user_pwd");
			
			User user = new User();
			user.setOpen_id(uid);
			user.setUser_pwd(user_pwd);
			
			boolean flag = false;
			
			if(uid==null||"".equals(uid)||"".equals(user_pwd)||"".equals(user_pwd)){
				result = "0";
				message = initDataPool.getSP("2-4-203");
			}else{
				flag = true;
			}
			
			if(flag){
				User userModel = this.userDao.isExcit(user);
				if(userModel!=null){
					user_id = userModel.getUser_id();
					result = "1";
					message = initDataPool.getSP("2-4-206");
				}else{
					result = "2";
					message = initDataPool.getSP("2-4-207");
				}
			}
			
			if(result.equals("1")){
				User userModel1 = this.userDao.findById(user_id);
				List<Baby> ls = this.babyDao.findByUserId(user_id);
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
				obj.put("baby", list1.size()!=0?list1:"");
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
        hsm.put("data", data!=null?data:"");
        
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("UserServiceImpl.isExcit执行了"+diff+"毫秒");
        
		return hsm;
	}
	
public Map isExcitpre(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		JSONObject data = new JSONObject();
		String user_id = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		try{
			String uid = (String) request.getParameter("open_id");
			String user_pwd = (String) request.getParameter("user_pwd");
			
			User user = new User();
			user.setOpen_id(uid);
			user.setUser_pwd(user_pwd);
			
			boolean flag = false;
			
			if(uid==null||"".equals(uid)||"".equals(user_pwd)||"".equals(user_pwd)){
				result = "0";
				message = initDataPool.getSP("2-4-203");
			}else{
				flag = true;
			}
			
			if(flag){
				User userModel = this.userDao.isExcit(user);
				if(userModel!=null){
					user_id = userModel.getUser_id();
					result = "1";
					message = initDataPool.getSP("2-4-206");
				}else{
					result = "2";
					message = initDataPool.getSP("2-4-207");
				}
			}
			
			if(result.equals("1")){
				User userModel1 = this.userDao.findById(user_id);
				List<Baby> ls = this.babyDao.findByUserId(user_id);
				List<Baby> list1 = new ArrayList<Baby>();
				if(ls!=null){
					for(Baby baby:ls){
						if(baby.getLison_count() == null)
							baby.setLison_count("0");
						if(baby.getRead_count() == null)
							baby.setRead_count("0");
						
						String id = baby.getId();
						
						int rank = this.babyDao.getRank(id);
						baby.setBaby_rank(rank+"");
						
						List<BabyInfo> list = this.babyInfoDao.findByBabyId(id);
						String hobby_ids = "";
						String Level_ids = "";
						//String languageid = "";
						if(list!=null){
							for(int i = 0;i<list.size();i++){
								if(id.equals(list.get(i).getBaby_id())){
									Level_ids = list.get(i).getLevel_id();
									//languageid = list.get(i).getBaby_language();
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
						//baby.setBaby_language(languageid);
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
        logger.info("UserServiceImpl.isExcit执行了"+diff+"毫秒");
        
		return hsm;
	}


public Map getUserInfo(HttpServletRequest request) {
	
	SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date d1 = new Date();
	logger.info("开始："+adf.format(d1));
	
	String result  = "0";
	String message = "";
	JSONObject data = new JSONObject();
	String user_id = "";
	
	String appId = (String) request.getParameter("appid");
	String appKey = Constant.APPID_KEY.get(appId);
	try{
		user_id = (String) request.getParameter("uid");
		
		
		//User user = new User();
		//user.setUser_id(uid);
		
		boolean flag = false;
		
		if(user_id==null||"".equals(user_id)){
			result = "0";
			message = initDataPool.getSP("2-4-203");
		}else{
			flag = true;
		}
		
		if(flag){
			//User userModel = this.userDao.isExcit(user);
			//if(userModel!=null){
				//user_id = userModel.getUser_id();
				result = "1";
				message = initDataPool.getSP("2-4-206");
			//}else{
				//result = "2";
				//message = initDataPool.getSP("2-4-207");
			//}
		}
		
		if(result.equals("1")){
			User userModel1 = this.userDao.findById(user_id);
			List<Baby> ls = this.babyDao.findByUserId(user_id);
			List<Baby> list1 = new ArrayList<Baby>();
			if(ls!=null){
				for(Baby baby:ls){
					if(baby.getLison_count() == null)
						baby.setLison_count("0");
					if(baby.getRead_count() == null)
						baby.setRead_count("0");
					String id = baby.getId();
					
					int rank = this.babyDao.getRank(id);
					baby.setBaby_rank(rank+"");
					
					List<BabyInfo> list = this.babyInfoDao.findByBabyId(id);
					String hobby_ids = "";
					String Level_ids = "";
					//String languageid = "";
					if(list!=null){
						for(int i = 0;i<list.size();i++){
							if(id.equals(list.get(i).getBaby_id())){
								Level_ids = list.get(i).getLevel_id();
								//languageid = list.get(i).getBaby_language();
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
					//baby.setBaby_language(languageid);
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
    logger.info("UserServiceImpl.isExcit执行了"+diff+"毫秒");
    
	return hsm;
}
	/**
	 * 修改用户密码
	 * @param request
	 * @return
	 */
	public Map getPwdBack(HttpServletRequest request){
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		Map hsm = new LinkedHashMap();
		boolean flag = false;
		try{
			String email = (String) request.getParameter("email");
			String pwd = (String) request.getParameter("pwd");
			if(email==null||"".equals(email)){
				result = "0";
				message = initDataPool.getSP("2-4-239");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        return hsm;
			}
			if(pwd==null||"".equals(pwd)){
				result = "0";
				message = initDataPool.getSP("2-4-240");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        return hsm;
			}
			User user=new User();
			user.setOpen_id(email);
			if(this.userDao.findUserCount(user)<1){
				result = "0";
				message = initDataPool.getSP("2-4-241");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        return hsm;
			}
			
			user.setUser_pwd(pwd);
			this.userDao.updateUserPwdByOpenId(user);
			
			result = "1";
			message = initDataPool.getSP("2-4-242");
			
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
        logger.info("UserServiceImpl.getPwdBack执行了"+diff+"毫秒");
        
		return hsm;
	}

	public Map updateaddress(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		Map hsm = new LinkedHashMap();
		boolean flag = false;
		try{
			String user_id = (String) request.getParameter("uid");
			String user_tel = (String) request.getParameter("user_tel");
			String user_truename = (String) request.getParameter("user_truename");
			String user_address = (String) request.getParameter("user_address");
			if(user_id==null||"".equals(user_id)){
				result = "0";
				message = initDataPool.getSP("2-4-208");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        return hsm;
			}
			if(user_tel==null||"".equals(user_tel)){
				result = "0";
				message = initDataPool.getSP("2-4-223");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        return hsm;
			}
			if(user_address==null||"".equals(user_address)){
				result = "0";
				message = initDataPool.getSP("2-4-224");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        return hsm;
			}
			if(user_truename==null||"".equals(user_truename)){
				result = "0";
				message = initDataPool.getSP("2-4-225");
				hsm.put("version", Constant.version);
		        hsm.put("result", result);
		        hsm.put("message", message);
		        return hsm;
			}
			
			
			User user = new User();
			user.setUser_address(user_address);
			user.setUser_truename(user_truename);
			user.setUser_tel(user_tel);
			user.setUser_id(user_id);
			this.userDao.updateaddress(user);
			
			result = "1";
			message = initDataPool.getSP("2-4-219");
			
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
        logger.info("UserServiceImpl.updateaddress执行了"+diff+"毫秒");
        
		return hsm;
	}

	public BabyInfoDao getBabyInfoDao() {
		return babyInfoDao;
	}

	public void setBabyInfoDao(BabyInfoDao babyInfoDao) {
		this.babyInfoDao = babyInfoDao;
	}

	public Map getScore(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		JSONObject data = new JSONObject();
		String user_id = "";
		boolean flag = false;
		try{
			user_id = (String) request.getParameter("uid");
			if(user_id==null||"".equals(user_id)){
				result = "0";
				message = initDataPool.getSP("2-4-208");
			}else{
				flag = true;
			}
			
			if(flag){
				UserBilling ub = this.userBillingDao.findOne(user_id);
				data.put("user_money", ub.getUser_money()!=null?ub.getUser_money():0);
				data.put("user_use_money", ub.getUser_use_money()!=null?ub.getUser_use_money():0);
				data.put("user_score", ub.getUser_score()!=null?ub.getUser_score():0);
				data.put("user_use_score", ub.getUser_use_score()!=null?ub.getUser_use_score():0);
				result  = "1";
				message = initDataPool.getSP("2-4-214");
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
        logger.info("UserServiceImpl.getScore执行了"+diff+"毫秒");
        
		return hsm;
	}
	
	public String tobereplace(String message, int in) throws Exception {
		String path = GlobalHobbyController.class.getClassLoader().getResource("").toURI().getPath();
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

}
