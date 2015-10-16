package com.manager.function.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;

import com.manager.function.dao.GlobalPropertyAttrDao;
import com.manager.function.dao.GlobalPropertyDao;
import com.manager.function.entity.GlobalProperty;
import com.manager.function.service.GlobalPropertyService;
import com.manager.init.InitDataPool;
import com.manager.util.Constant;
import com.manager.util.EncoderHandler;
import com.manager.util.Utils;

public class GlobalPropertyServiceImpl implements GlobalPropertyService {
	
	private Logger logger = Logger.getLogger(GlobalPropertyServiceImpl.class);

	private GlobalPropertyDao globalPropertyDao;

	private GlobalPropertyAttrDao globalPropertyAttrDao;

	private InitDataPool initDataPool;

	public InitDataPool getInitDataPool() {
		return initDataPool;
	}

	public void setInitDataPool(InitDataPool initDataPool) {
		this.initDataPool = initDataPool;
	}

	@Transactional
	public void add(GlobalProperty gp) {
			this.globalPropertyDao.add(gp);
	}

	public int count(GlobalProperty gp) {
		return this.globalPropertyDao.count(gp);
	}

	public List<GlobalProperty> NoAbolish_GlobalPropertyList(
			HttpServletRequest request) {
		List<GlobalProperty> globalPropertyList = this.globalPropertyDao.NoAbolish_GlobalPropertyList();
		request.setAttribute("noabolish_globalproperty", globalPropertyList);
		return globalPropertyList;
	}

	@Transactional
	public void delete(GlobalProperty gp, String reg) {
		try {
			if (reg.equals("2")) {
				String[] ids = gp.getId().split("-");
				for (int i = 0; i < ids.length; i++) {
					if (!ids[i].equals("on")) {
						GlobalProperty glModel = new GlobalProperty();
						glModel.setId(ids[i]);
						this.globalPropertyDao.delete(glModel);
					}
				}
			} else if (reg.equals("1")) {
				this.globalPropertyDao.delete(gp);
			}
		} catch (Exception e) {
			throw new RuntimeException("GlobalProperty删除不成功");
		}
	}

	public List<GlobalProperty> get(GlobalProperty gp, int pageNo, int pageSize) {
		return this.globalPropertyDao.get(gp, pageNo, pageSize);
	}

	public int getId() {
		return this.globalPropertyDao.getId();
	}

	public GlobalProperty getOne(String id) {
		return this.globalPropertyDao.getOne(id);
	}

	@Transactional
	public void update(GlobalProperty gp, String reg) {
		try{
			// 1：update一个，2：批量update
			if (reg.equals("2")) {
				String[] ids = gp.getId().split("-");
				for (int i = 0; i < ids.length; i++) {
					if (!ids[i].equals("on")) {
						GlobalProperty glModel = new GlobalProperty();
						glModel.setId(ids[i]);
						glModel.setProperty_status(gp.getProperty_status());
						glModel.setUpdate_adminuser(gp.getUpdate_adminuser());
						this.globalPropertyDao.update(glModel);
					}
				}
			} else if (reg.equals("1")) {
				this.globalPropertyDao.update(gp);
			}
		} catch (Exception e) {
			throw new RuntimeException("GlobalProperty修改不成功");
		}
	}

	public GlobalPropertyDao getGlobalPropertyDao() {
		return globalPropertyDao;
	}

	public void setGlobalPropertyDao(GlobalPropertyDao globalPropertyDao) {
		this.globalPropertyDao = globalPropertyDao;
	}

	public int sortcount(String sort) {
		return this.globalPropertyDao.sortcount(sort);
	}

	public Map getProperty(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));

		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);

		Map hsm = new LinkedHashMap();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		JSONObject data = new JSONObject();

		String result = "";
		String message = "";

		try {
			String baby_id = (String) request.getParameter("baby_id");
			GlobalProperty gp = new GlobalProperty();
			gp.setBaby_id(baby_id);
			List<GlobalProperty> ls = this.globalPropertyDao.shudan(gp);
			if(ls==null){
				ls = this.globalPropertyDao.findByBabyId(gp);
			}

			result = "1";
			message = initDataPool.getSP("2-4-214");
			hsm.put("version", Constant.version);
			hsm.put("result", result);
			hsm.put("message", message);
			hsm.put("date", date);
			hsm.put("data", ls);
			hsm.put("datasign", md5(hsm, appKey));
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
			message = initDataPool.getSP("2-4-000");
			hsm.put("version", Constant.version);
			hsm.put("result", result);
			hsm.put("message", message);
			hsm.put("date", date);
			hsm.put("data", "");
			hsm.put("datasign", md5(hsm, appKey));
		}
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("GlobalPropertyServiceImpl.getProperty执行了"+diff+"毫秒");
		
		return hsm;
	}

	public String md5(Map parmsmap, String appkey) {
		// 签名校验
		StringBuffer urlparmsstr = new StringBuffer();
		parmsmap = Utils.sortMapByKey(parmsmap);
		for (Iterator iter = parmsmap.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			if ("datasign".equals(key))
				continue;
			Object values = (Object) parmsmap.get(key);
			urlparmsstr.append(key).append("=").append(values).append("&");
		}
		String urlparmsall = urlparmsstr.substring(0, urlparmsstr.length() - 1)
				.toString(); // 去掉末尾一个&符号

		String serverSign1 = EncoderHandler.encodeByMD5(urlparmsall);
		String serverSign0 = EncoderHandler.encodeByMD5(urlparmsall + appkey);
		String serverSign = EncoderHandler.encodeByMD5(appkey + serverSign0); // 全部小写md5
		return serverSign;
	}

	public GlobalPropertyAttrDao getGlobalPropertyAttrDao() {
		return globalPropertyAttrDao;
	}

	public void setGlobalPropertyAttrDao(
			GlobalPropertyAttrDao globalPropertyAttrDao) {
		this.globalPropertyAttrDao = globalPropertyAttrDao;
	}

}
