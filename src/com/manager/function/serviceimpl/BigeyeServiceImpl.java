package com.manager.function.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.manager.function.dao.BigeyeDao;
import com.manager.function.entity.Bigeye;
import com.manager.function.service.BigeyeService;
import com.manager.function.service.ResourceService;
import com.manager.init.InitDataPool;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;
import com.manager.util.Xml;

public class BigeyeServiceImpl implements BigeyeService {
	
	private Logger logger = Logger.getLogger(BigeyeServiceImpl.class);
	
	@Autowired
	private BigeyeDao BigeyeDao;
	
	private InitDataPool initDataPool;
	
	@Autowired
	private ResourceService ResourceService;

	public InitDataPool getInitDataPool() {
		return initDataPool;
	}

	public void setInitDataPool(InitDataPool initDataPool) {
		this.initDataPool = initDataPool;
	}

	public int deleteBigeye(String id) {
		Bigeye Bigeye=new Bigeye();
		Bigeye.setId(id);
		return this.BigeyeDao.deleteBigeye(Bigeye);
	}

	public int findBigeyeCount(Bigeye Bigeye) {
		return this.BigeyeDao.findBigeyeCount(Bigeye);
	}

	public List<Bigeye> findBigeyeList(Bigeye Bigeye, int pageNo, int pageSize) {
		return this.BigeyeDao.findBigeyeList(Bigeye, pageNo, pageSize);
	}

	public List<Bigeye> findBigeyeList() {
		return this.BigeyeDao.findBigeyeList(null);
	}
	
	public int insertBigeye(Bigeye Bigeye) {
		return this.BigeyeDao.insertBigeye(Bigeye);
	}

	public int updateBigeye(Bigeye Bigeye) {
		return this.BigeyeDao.updateBigeye(Bigeye);
	}

	public int updateBigeyeStatus(Bigeye Bigeye) {
		return this.BigeyeDao.updateBigeyeStatus(Bigeye);
	}
	
	public Bigeye findBigeyeOne(String id){
		Bigeye Bigeye=new Bigeye();
		Bigeye.setId(id);
		List<Bigeye> BigeyeList=this.BigeyeDao.findBigeyeList(Bigeye);
		if(BigeyeList!=null && BigeyeList.size()>0){
			return BigeyeList.get(0);
		}
		return null;
	}
	
	/**
	 * @return the BigeyeDao
	 */
	public BigeyeDao getBigeyeDao() {
		return BigeyeDao;
	}

	/**
	 * @param BigeyeDao the BigeyeDao to set
	 */
	public void setBigeyeDao(BigeyeDao BigeyeDao) {
		this.BigeyeDao = BigeyeDao;
	}


	/**
	 * @return the resourceService
	 */
	public ResourceService getResourceService() {
		return ResourceService;
	}

	/**
	 * @param resourceService the resourceService to set
	 */
	public void setResourceService(ResourceService resourceService) {
		ResourceService = resourceService;
	}

	public Map getBigEyeList(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		List<Bigeye> ls = new ArrayList<Bigeye>();
		Map hsm = new LinkedHashMap();
		JSONArray obj = new JSONArray();
		String module_id = (String) request.getParameter("module_id");
		try{
			String num = (String) request.getParameter("len");
			Bigeye b = new Bigeye();
			b.setModule_id(module_id);
			b.setNum(Integer.parseInt(num));
			ls = this.BigeyeDao.getBigEyeList(b);
			if(ls!=null){
				for(Bigeye be :ls){
					JSONObject jb = new JSONObject();
					String link_url=be.getLink_url()!=null?be.getLink_url():"";
					if(be.getLink_type()!=null&&be.getLink_type().equals("2")){
						link_url=this.ResourceService.getResourceByID(link_url).toString();
					}
					jb.put("id", be.getId());
					jb.put("img_url", be.getImg_url()!=null?tobereplace(be.getImg_url(), 0):"");
					jb.put("link_url", link_url);
					jb.put("content", be.getContent()!=null?be.getContent():"");
					jb.put("sort", be.getSort()!=null?be.getSort():"");
					jb.put("link_type", be.getLink_type()!=null?be.getLink_type():"0");
					obj.add(jb);
				}
				result = "1";
				message = initDataPool.getSP("2-4-214");
			}else{
				result = "0";
				message = initDataPool.getSP("2-4-213");
			}
		}catch (Exception e) {
			e.printStackTrace();
			result = "error";
			message = initDataPool.getSP("2-4-000");
		}
		hsm.put("version", Constant.version);
		hsm.put("result", result);
		hsm.put("module_id", module_id);
		hsm.put("message", message);
		hsm.put("data", obj);
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("BigeyeServiceImpl.getBigEyeList执行了"+diff+"毫秒");
		return hsm;
	}
	
public Map getMainImg(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		List<Bigeye> ls = new ArrayList<Bigeye>();
		Map hsm = new LinkedHashMap();
		JSONArray obj = new JSONArray();
		String module_id = "4";//(String) request.getParameter("module_id");
		try{
			String num = "1";//(String) request.getParameter("len");
			Bigeye b = new Bigeye();
			b.setModule_id(module_id);
			b.setNum(Integer.parseInt(num));
			ls = this.BigeyeDao.getBigEyeList(b);
			if(ls!=null){
				for(Bigeye be :ls){
					JSONObject jb = new JSONObject();
					jb.put("img_url", be.getImg_url()!=null?tobereplace(be.getImg_url(), 0):"");					
					obj.add(jb);
				}
				result = "1";
				message = initDataPool.getSP("2-4-214");
			}else{
				result = "0";
				message = initDataPool.getSP("2-4-213");
			}
		}catch (Exception e) {
			e.printStackTrace();
			result = "error";
			message = initDataPool.getSP("2-4-000");
		}
		hsm.put("version", Constant.version);
		hsm.put("result", result);
		hsm.put("message", message);
		hsm.put("data", obj);
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("BigeyeServiceImpl.getBigEyeList执行了"+diff+"毫秒");
		return hsm;
	}
	
	public static String tobereplace(String message, int in) throws Exception {
		String path = BigeyeServiceImpl.class.getClassLoader().getResource("").toURI().getPath();
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
