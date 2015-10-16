package com.manager.function.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.manager.function.dao.FavoriteDao;
import com.manager.function.entity.Favorite;
import com.manager.function.service.FavoriteService;
import com.manager.init.InitDataPool;
import com.manager.util.Constant;

public class FavoriteServiceImpl implements FavoriteService {
	
	private Logger logger = Logger.getLogger(FavoriteServiceImpl.class);
	
	private InitDataPool initDataPool;
	
	private FavoriteDao favoriteDao;

	public InitDataPool getInitDataPool() {
		return initDataPool;
	}

	public void setInitDataPool(InitDataPool initDataPool) {
		this.initDataPool = initDataPool;
	}

	public FavoriteDao getFavoriteDao() {
		return favoriteDao;
	}

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	public Map add(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		Map hsm = new LinkedHashMap();
		boolean flag = false;
		try{
			 String user_id = (String) request.getParameter("uid");
			 String resource_id = (String) request.getParameter("resource_id");
			 if(user_id==null||"".equals(user_id)||resource_id==null||"".equals(resource_id)){
					result = "0";
					message = initDataPool.getSP("2-4-203");
				}else{
					flag = true;
				}
			 if(flag){
				 Favorite f = new Favorite();
				 f.setResource_id(resource_id);
				 f.setUser_id(user_id);
				 
				 int num = this.favoriteDao.findNum(f);
				 if(num==0){
					 this.favoriteDao.add(f);
					 result = "1";
					 message = initDataPool.getSP("2-4-226");
				 }else{
					 result = "0";
					 message = initDataPool.getSP("2-4-233");
				 }
				 
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
        logger.info("FavoriteServiceImpl.add执行了"+diff+"毫秒");
        
		return hsm;
	}

	public Map delete(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));

		String result  = "0";
		String message = "";
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
				this.favoriteDao.delete(user_id);
				message = initDataPool.getSP("2-4-232");
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
        
        Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("FavoriteServiceImpl.delete执行了"+diff+"毫秒");
        
		return hsm;
	
	}

	public Map delete1(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		String resource_id = "";
		try{
			resource_id = (String) request.getParameter("resource_id");
			String user_id = (String) request.getParameter("uid");
			Favorite f = new Favorite();
			f.setResource_id(resource_id);
			f.setUser_id(user_id);
			this.favoriteDao.delete1(f);
			message = initDataPool.getSP("2-4-235");
			result  = "1";
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
        logger.info("FavoriteServiceImpl.delete1执行了"+diff+"毫秒");
        
		return hsm;
	}

}
