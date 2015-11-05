package com.manager.function.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.manager.function.service.ApiService;
import com.manager.function.service.BabyInfoService;
import com.manager.function.service.BabyService;
import com.manager.function.service.BigeyeService;
import com.manager.function.service.CcmsService;
import com.manager.function.service.FavoriteService;
import com.manager.function.service.GlobalPropertyService;
import com.manager.function.service.LearnplanService;
import com.manager.function.service.ResourceService;
import com.manager.function.service.TopicListService;
import com.manager.function.service.TopicReplyService;
import com.manager.function.service.TopicTypeService;
import com.manager.function.service.UserService;
import com.manager.init.InitDataPool;
import com.manager.util.Constant;
import com.manager.util.EncoderHandler;
import com.manager.util.Utils;

public class ApiServiceImpl implements ApiService {
	
	private Logger logger = Logger.getLogger(ApiServiceImpl.class);
	
	private InitDataPool initDataPool;
	
	private BabyService babyService;
	
	private BabyInfoService babyInfoService;
	
	private UserService userService;
	
	private ResourceService resourceService;
	
	private LearnplanService learnplanService;
	
	private GlobalPropertyService globalPropertyService;
	
	private FavoriteService favoriteService;
	
	private TopicTypeService topicTypeService;
	
	private TopicListService topicListService;
	
	private TopicReplyService topicReplyService;
	
	private BigeyeService BigeyeService;
	
	private CcmsService ccmsService;
	
	

	public CcmsService getCcmsService() {
		return ccmsService;
	}

	public void setCcmsService(CcmsService ccmsService) {
		this.ccmsService = ccmsService;
	}

	public TopicListService getTopicListService() {
		return topicListService;
	}

	public void setTopicListService(TopicListService topicListService) {
		this.topicListService = topicListService;
	}

	public TopicReplyService getTopicReplyService() {
		return topicReplyService;
	}

	public void setTopicReplyService(TopicReplyService topicReplyService) {
		this.topicReplyService = topicReplyService;
	}

	public GlobalPropertyService getGlobalPropertyService() {
		return globalPropertyService;
	}

	public void setGlobalPropertyService(GlobalPropertyService globalPropertyService) {
		this.globalPropertyService = globalPropertyService;
	}

	public LearnplanService getLearnplanService() {
		return learnplanService;
	}

	public void setLearnplanService(LearnplanService learnplanService) {
		this.learnplanService = learnplanService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public InitDataPool getInitDataPool() {
		return initDataPool;
	}

	public void setInitDataPool(InitDataPool initDataPool) {
		this.initDataPool = initDataPool;
	}

	public BabyService getBabyService() {
		return babyService;
	}

	public void setBabyService(BabyService babyService) {
		this.babyService = babyService;
	}

	public BabyInfoService getBabyInfoService() {
		return babyInfoService;
	}

	public void setBabyInfoService(BabyInfoService babyInfoService) {
		this.babyInfoService = babyInfoService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Map CheckRequest(HttpServletRequest request) {
		
		String result = "0";
		String message = "";
		String serverSign = "";
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		
		String sign = (String) request.getParameter("sign");
		String version = (String) request.getParameter("version");
		String doMethod = (String) request.getParameter("do");
		
		try {
			
			if(!Constant.version.equals(version) ){
				Map hsmSign = new LinkedHashMap();
				hsmSign.put("version", version);
				hsmSign.put("result", "0");
				hsmSign.put("message", initDataPool.getSP("2-4-200"));
	        	return hsmSign;
			}
			
			//通过apiId获得apiKey
			String appkey = Constant.APPID_KEY.get(appId);
			if(appkey==null){
				Map hsmSign = new LinkedHashMap();
				hsmSign.put("version", version);
				hsmSign.put("result", "0");
				hsmSign.put("message", initDataPool.getSP("2-4-201"));
	        	return hsmSign;
			}
			
			if(doMethod.equals("getMyTopicList")||doMethod.equals("addTopicList")||doMethod.equals("addTopicReply")||doMethod.equals("findTopicReplyList")||doMethod.equals("getBigEyeList")){
				//校验通过
				Map hsmok = new LinkedHashMap();
				hsmok.put("result", "1");
				return hsmok;
			}
			
			//签名校验
			StringBuffer urlparmsstr = new StringBuffer();
			Map parmsmap = request.getParameterMap();
			parmsmap = Utils.sortMapByKey(parmsmap);
			for (Iterator iter = parmsmap.keySet().iterator(); iter.hasNext();) {
			     String key = (String) iter.next();
			     if("sign".equals(key) )
			    	 continue;
			     String[] values = (String[]) parmsmap.get(key);
			     for (int i = 0; i < values.length; i++) {
					urlparmsstr.append(key).append("=").append(values[i]).append("&");
			     }
			}
			String urlparmsall = urlparmsstr.substring(0, urlparmsstr.length()-1).toString(); //去掉末尾一个&符号
			
			String serverSign1 = EncoderHandler.encodeByMD5(urlparmsall);
			String serverSign0 = EncoderHandler.encodeByMD5(urlparmsall + appkey);
			serverSign = EncoderHandler.encodeByMD5(appkey + serverSign0 ); //全部小写md5
			//System.out.println(serverSign);
			if(!serverSign.equals(sign) ){
				Map hsmSign = new LinkedHashMap();
				result = "0";
				message = initDataPool.getSP("2-4-202");//，调试提示："+serverSign;
			}else{
				result = "1";
				message = "";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
			message = initDataPool.getSP("2-4-000");
		}
		//校验通过
		Map hsmok = new LinkedHashMap();
		hsmok.put("version", version);
		hsmok.put("result", result);
		hsmok.put("message", message);
		hsmok.put("serverSign", serverSign);
    	return hsmok;
	
	}
	
	public Map ExcuteRequest(HttpServletRequest request,HttpServletResponse response){

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());
		JSONObject data = new JSONObject();
		
		String appId = (String) request.getParameter("appid");
		String appKey = Constant.APPID_KEY.get(appId);
		
		String doMethod = (String) request.getParameter("do");
		String sign = (String) request.getParameter("sign");
		String version = (String) request.getParameter("version");
		
		
		logger.info("do="+doMethod);
		int versioncode = 0;
		if(Constant.version.equals(version) ){
			versioncode = 100;
		}
		
		// http://192.168.3.130:8888/hbadmin/domain?do=getdate&version=1.0.0&appid=100&sign=9482445c0056943f2283e886835aefb0
		if("getdate".equals(doMethod) ){
			Date d1 = new Date();
			logger.info("开始："+adf.format(d1));
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: 	String result = "1";
							String message = initDataPool.getSP("2-4-214");
							hsm.put("version", Constant.version);
							hsm.put("result", result);
							hsm.put("message", message);
							hsm.put("date", date);
							hsm.put("datasign", md5(hsm,appKey));
							Date d2 = new Date();
							logger.info("结束："+adf.format(d2));
					        long diff = (d2.getTime() - d1.getTime());
					        logger.info("ApiServiceImpl.getdate执行了"+diff+"毫秒");
							return hsm;
			}
			return null;
		}
		
		//创建宝贝
		// http://192.168.3.130:8888/hbadmin/domain?do=createbaby&uid=1&baby_nickname=adsdsad&version=1.0.0&appid=100&sign=9841976786dcb9dc632d963a1adef93c
		if("createbaby".equals(doMethod) ){
			switch(versioncode){
				case 100: return babyService.add(request);
			}
			return null;
		}
		
		//添加宝贝详情
		// http://192.168.3.130:8888/hbadmin/domain?do=createbabyinfo&baby_id=1&level_id=1&hobby_id=1&version=1.0.0&appid=100&sign=ee42dfd6c5e70314109086a3bdab7e10
		if("createbabyinfo".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100:
				try {
					 return babyInfoService.add(request);
				} catch (Exception e) {
					String result = "error";
					String message = initDataPool.getSP("2-4-000");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("data", "");
				}
			}
			return null;
		}
		//创建宝贝
		// http://192.168.3.130:8888/hbadmin/domain?do=createbabypre&uid=1&baby_nickname=adsdsad&level_id=1&hobby_id=8,11,9&language_id=1&version=1.0.0&appid=100&sign=9841976786dcb9dc632d963a1adef93c
		if("createbabypre".equals(doMethod) ){
			switch(versioncode){
				case 100: return babyService.addpre(request);
			}
			return null;
		}
		//添加宝贝详情pre
		// http://192.168.3.130:8888/hbadmin/domain?do=createbabyinfo&baby_id=1&level_id=1&hobby_id=1&version=1.0.0&appid=100&sign=ee42dfd6c5e70314109086a3bdab7e10
		if("createbabyinfopre".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100:
				try {
					 return babyInfoService.addpre(request);
				} catch (Exception e) {
					String result = "error";
					String message = initDataPool.getSP("2-4-000");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("data", "");
				}
			}
			return null;
		}

		//用户登录
		// http://192.168.3.130:8888/hbadmin/domain?do=login&open_id=test&user_pwd=96e79218965eb72c92a549dd5a330112&version=1.0.0&appid=100&sign=e51cf7fa7aacf80a2fb570522255074a
		if("login".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: try {
					return userService.isExcit(request);
				} catch (Exception e) {
					String result = "error";
					String message = initDataPool.getSP("2-4-000");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("data", "");
				}
			}
			return null;
		}
		//用户登录pre
		// http://192.168.3.130:8888/hbadmin/domain?do=login&open_id=test&user_pwd=96e79218965eb72c92a549dd5a330112&version=1.0.0&appid=100&sign=e51cf7fa7aacf80a2fb570522255074a
		if("loginpre".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: try {
					return userService.isExcitpre(request);
				} catch (Exception e) {
					String result = "error";
					String message = initDataPool.getSP("2-4-000");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("data", "");
				}
			}
			return null;
		}
		//用户注册
		// http://192.168.3.130:8888/hbadmin/domain?do=reg&open_id=test2&source=2&user_nickname=test&user_avatar=http://www.baidu.com&user_pwd=1111&version=1.0.0&appid=100&sign=d2dfe1cf2b7f2b634cc95fd1f5bc51e9
		if("reg".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100:
				try {
					 return userService.add(request);
				} catch (Exception e) {
					String result = "error";
					String message = initDataPool.getSP("2-4-000");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("data", "");
				}
			}
			return null;
		}
		
		//用户注册
		// http://192.168.3.130:8888/hbadmin/domain?do=reg&open_id=test2&source=2&user_nickname=test&user_avatar=http://www.baidu.com&user_pwd=1111&version=1.0.0&appid=100&sign=d2dfe1cf2b7f2b634cc95fd1f5bc51e9
		if("regpre".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100:
				try {
					 return userService.addpre(request);
				} catch (Exception e) {
					String result = "error";
					String message = initDataPool.getSP("2-4-000");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("data", "");
				}
			}
			return null;
		}
		
		//获取每周推荐
		// http://192.168.3.130:8888/hbadmin/domain?do=publicresource&baby_id=11&version=1.0.0&appid=100&sign=c8ef9586f4856aaea637df56797811ee
		if("publicresource".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: try {
						return resourceService.publicresource(request);
					} catch (Exception e) {
						String result = "error";
						String message = initDataPool.getSP("2-4-000");
						hsm.put("version", Constant.version);
						hsm.put("result", result);
						hsm.put("message", message);
						hsm.put("date", date);
						hsm.put("data", "");
						hsm.put("datasign", md5(hsm,appKey));
					}
			}
			return hsm;
		}
		
		//更新每周推荐
		// http://192.168.3.130:8888/hbadmin/domain?do=updatepublicresource&id=11&uid=9&spend_minute=5555&version=1.0.0&appid=100&sign=8ef0a0016711bdf69232251acdf6d3e0
		if("updatepublicresource".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: 
				try {
					return resourceService.updatepublicresource(request);
				} catch (Exception e) {
					String result = "error";
					String message = initDataPool.getSP("2-4-000");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message); 
				}
			}
			return hsm;
		}
		
		//开启学习计划
		// http://192.168.3.130:8888/hbadmin/domain?do=learnplan&baby_id=11&version=1.0.0&appid=100&sign=73870e23a4dae8ba2796ed38171ac5dc
		if("learnplan".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: try {
						return learnplanService.learnplan(request);
					} catch (Exception e) {
						String result = "error";
						String message = initDataPool.getSP("2-4-000");
						hsm.put("version", Constant.version);
						hsm.put("result", result);
						hsm.put("message", message);
						hsm.put("date", date);
						hsm.put("data", "");
						hsm.put("datasign", md5(hsm,appKey));
					}
			}
			return hsm;
		}
		//开启学习计划pre
		// http://192.168.3.130:8888/hbadmin/domain?do=learnplan&baby_id=11&version=1.0.0&appid=100&sign=73870e23a4dae8ba2796ed38171ac5dc
		if("learnplanpre".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: try {
						return learnplanService.learnplanpre(request);
					} catch (Exception e) {
						String result = "error";
						String message = initDataPool.getSP("2-4-000");
						hsm.put("version", Constant.version);
						hsm.put("result", result);
						hsm.put("message", message);
						hsm.put("date", date);
						hsm.put("data", "");
						hsm.put("datasign", md5(hsm,appKey));
					}
			}
			return hsm;
		}
		//重置学习计划
		// http://192.168.3.130:8888/hbadmin/domain?do=relearnplan&baby_id=11&version=1.0.0&appid=100&sign=729b37127736bb6891d27da223acc5f0
		if("relearnplan".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: try {
						return learnplanService.relearnplan(request);
					} catch (Exception e) {
						String result = "error";
						String message = initDataPool.getSP("2-4-000");
						hsm.put("version", Constant.version);
						hsm.put("result", result);
						hsm.put("message", message); 
						hsm.put("date", date);
						hsm.put("data", "");
						hsm.put("datasign", md5(hsm,appKey));
					}
			}
			return hsm;
		}
		
		//重置学习计划pre
		// http://192.168.3.130:8888/hbadmin/domain?do=relearnplan&baby_id=11&version=1.0.0&appid=100&sign=729b37127736bb6891d27da223acc5f0
		if("relearnplanpre".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: try {
						return learnplanService.relearnplanpre(request);
					} catch (Exception e) {
						String result = "error";
						String message = initDataPool.getSP("2-4-000");
						hsm.put("version", Constant.version);
						hsm.put("result", result);
						hsm.put("message", message); 
						hsm.put("date", date);
						hsm.put("data", "");
						hsm.put("datasign", md5(hsm,appKey));
					}
			}
			return hsm;
		}
		
		//跟新阅读计划
		// http://192.168.3.130:8888/hbadmin/domain?do=updatelearnplan&id=11&spend_minute=5555&version=1.0.0&appid=100&sign=73870e23a4dae8ba2796ed38171ac5dc
		if("updatelearnplan".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: 
				try {
					return learnplanService.updatelearnplan(request);
				} catch (Exception e) {
					String result = "error";
					String message = initDataPool.getSP("2-4-000");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message); 
				}
			}
			return hsm;
		}
		
		// 获取属性
		//http://192.168.3.130:8888/hbadmin/domain?do=getproperty&baby_id=44&version=1.0.0&appid=100&sign=ffc36d73927c5b919131c39e46f935c4
		if("getproperty".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: 	return globalPropertyService.getProperty(request);
			}
			return null;
		}
		
		
		//搜索
		// http://192.168.3.130:8888/hbadmin/domain?do=search&search_key=Me&uid=9&version=1.0.0&appid=100&sign=2b3b3684930c9472acd75daafc7a71ae
		if("search".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: try {
						return resourceService.search(request);
					} catch (Exception e) {
						String result = "error";
						String message = initDataPool.getSP("2-4-000");
						hsm.put("version", Constant.version);
						hsm.put("result", result);
						hsm.put("message", message);
						hsm.put("date", date);
						hsm.put("data", "");
						hsm.put("datasign", md5(hsm,appKey));
					}
			}
			return hsm;
		}
		
		//搜索pre
		// http://192.168.3.130:8888/hbadmin/domain?do=search&search_key=Me&uid=9&version=1.0.0&appid=100&sign=2b3b3684930c9472acd75daafc7a71ae
		if("searchpre".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: try {
						return resourceService.searchpre(request);
					} catch (Exception e) {
						String result = "error";
						String message = initDataPool.getSP("2-4-000");
						hsm.put("version", Constant.version);
						hsm.put("result", result);
						hsm.put("message", message);
						hsm.put("date", date);
						hsm.put("data", "");
						hsm.put("datasign", md5(hsm,appKey));
					}
			}
			return hsm;
		}
		
		//获取用户地址信息
		// http://192.168.3.130:8888/hbadmin/domain?do=getAddress&uid=9&version=1.0.0&appid=100&sign=ffc36d73927c5b919131c39e46f935c4
		if("getAddress".equals(doMethod) ){
			switch(versioncode){
				case 100: return userService.findById(request);
			}
			return null;
		}
		
		//获取用户属性
		// http://192.168.3.130:8888/hbadmin/domain?do=getScore&uid=9&version=1.0.0&appid=100&sign=ffc36d73927c5b919131c39e46f935c4
		if("getScore".equals(doMethod) ){
			switch(versioncode){
				case 100: return userService.getScore(request);
			}
			return null;
		}
		
		//修改用户地址
		// http://192.168.3.130:8888/hbadmin/domain?do=updateAddress&uid=9&user_address=%E4%B8%8A%E6%B5%B7%E5%B8%82%E5%BC%A0%E6%B1%9F%E8%B7%AF665%E5%8F%B7&user_tel=1&user_truename=%E5%BC%A0%E4%B8%89&version=1.0.0&appid=100&sign=0fc5ade37541772a11087a231f83e53d
		if("updateAddress".equals(doMethod) ){
			switch(versioncode){
				case 100: return userService.updateaddress(request);
			}
			return null;
		}
		
		//获取热门搜索列表
		// http://192.168.3.130:8888/hbadmin/domain?do=getKeyword&version=1.0.0&appid=100&sign=c443b3a9bfce61a7ebe4694fdf1e847e
		if("getKeyword".equals(doMethod) ){
			switch(versioncode){
				case 100: return resourceService.getKeyword(request);
			}
			return null;
		}
		
		//获取热门搜索列表pre
		// http://192.168.3.130:8888/hbadmin/domain?do=getKeyword&version=1.0.0&appid=100&sign=c443b3a9bfce61a7ebe4694fdf1e847e
		if("getKeywordpre".equals(doMethod) ){
			switch(versioncode){
				case 100: return resourceService.getKeywordpre(request);
			}
			return null;
		}
		
		//点赞
		// http://192.168.3.130:8888/hbadmin/domain?do=dianzan&uid=9&resource_id=15&version=1.0.0&appid=100&sign=c443b3a9bfce61a7ebe4694fdf1e847e
		if("dianzan".equals(doMethod) ){
			switch(versioncode){
				case 100: return favoriteService.add(request);
			}
			return null;
		}
		
		//进入游戏页面
		// http://192.168.3.130:8888/hbadmin/domain?do=getPlay&uid=9&version=1.0.0&appid=100&sign=c443b3a9bfce61a7ebe4694fdf1e847e
		if("getPlay".equals(doMethod) ){
			switch(versioncode){
				case 100: return resourceService.getPlay(request);
			}
			return null;
		}
		
		//获取点赞列表
		//http://192.168.3.130:8888/hbadmin/domain?do=dianzanList&uid=9&version=1.0.0&appid=100&sign=c443b3a9bfce61a7ebe4694fdf1e847e
		if("dianzanList".equals(doMethod) ){
			switch(versioncode){
				case 100: return resourceService.fList(request);
			}
			return null;
		}
		
		//获取点赞列表pre
		//http://192.168.3.130:8888/hbadmin/domain?do=dianzanList&uid=9&version=1.0.0&appid=100&sign=c443b3a9bfce61a7ebe4694fdf1e847e
		if("dianzanListpre".equals(doMethod) ){
			switch(versioncode){
				case 100: return resourceService.fListpre(request);
			}
			return null;
		}
		
		//清空点赞列表
		// http://192.168.3.130:8888/hbadmin/domain?do=qingkongdianzan&uid=9&version=1.0.0&appid=100&sign=c443b3a9bfce61a7ebe4694fdf1e847e
		if("qingkongdianzan".equals(doMethod) ){
			switch(versioncode){
				case 100: return favoriteService.delete(request);
			}
			return null;
		}
		
		//取消点赞
		// http://192.168.3.130:8888/hbadmin/domain?do=quxiaodianzan&resource_id=45&uid=10&version=1.0.0&appid=100&sign=be70a657d53366c67414933bc90aaf61
		if("quxiaodianzan".equals(doMethod) ){
			switch(versioncode){
				case 100: return favoriteService.delete1(request);
			}
			return null;
		}
		
		//宝贝头像上传
		// http://192.168.3.130:8888/hbadmin/domain?do=uploadImg&baby_id=9&version=1.0.0&appid=100&sign=c443b3a9bfce61a7ebe4694fdf1e847e
		if("uploadImg".equals(doMethod) ){
			switch(versioncode){
				case 100: return babyService.updateAvatar(request);
			}
			return null;
		}
		
		//删除宝贝
		// http://192.168.3.130:8888/hbadmin/domain?do=deleteBaby&baby_id=9&version=1.0.0&appid=100&sign=c443b3a9bfce61a7ebe4694fdf1e847e
		if("deleteBaby".equals(doMethod) ){
			switch(versioncode){
				case 100: return babyService.deleteBaby(request);
			}
			return null;
		}
		
		//获取帖子类型
		// http://192.168.3.130:8888/hbadmin/domain?do=getTopicType&version=1.0.0&appid=100&sign=f87c2392b99be1dadf9c68eca840662c
		if("getTopicType".equals(doMethod) ){
			switch(versioncode){
				case 100: return topicTypeService.getTopicType(request);
			}
			return null;
		}
		
		//发帖子
		// http://192.168.3.130:8888/hbadmin/domain?do=addTopicList&topic_typeId=1&title=%E6%B5%8B%E8%AF%95&content=asdasdadasdasdasdasda&uid=1&label=111&version=1.0.0&appid=100&sign=3854df25b5ed0fda39f196f660cab619
		if("addTopicList".equals(doMethod) ){
			switch(versioncode){
				case 100: return topicListService.add(request);
			}
			return null;
		}
		
		// 帖子列表
		//http://192.168.3.130:8888/hbadmin/domain?do=getTopicList&topic_typeId=1&index=1&version=1.0.0&appid=100&sign=c30be0365450242b92080e9f2e602dcc
		if("getTopicList".equals(doMethod) ){
			switch(versioncode){
				case 100: return topicListService.findTopicListListByType(request,response);
			}
			return null;
		}
		
		// 我的帖子列表
		//http://192.168.3.130:8888/hbadmin/domain?do=getMyTopicList&uid=1&index=1&version=1.0.0&appid=100&sign=c30be0365450242b92080e9f2e602dcc
		if("getMyTopicList".equals(doMethod) ){
			switch(versioncode){
				case 100: return topicListService.findByBabyId(request);
			}
			return null;
		}
		
		//获取广告图片列表
		// http://192.168.3.130:8888/hbadmin/domain?do=getBigEyeList&module_id=1&len=2&version=1.0.0&appid=100&sign=c30be0365450242b92080e9f2e602dcc
		if("getBigEyeList".equals(doMethod) ){
			switch(versioncode){
				case 100: return BigeyeService.getBigEyeList(request);
			}
			return null;
		}
		
		//回复帖子
		// http://192.168.3.130:8888/hbadmin/domain?do=addTopicReply&content=asdas&topic_id=4&uid=1&quote_content=测试&version=1.0.0&appid=100&sign=c30be0365450242b92080e9f2e602dcc
		if("addTopicReply".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: 
				try {
					return topicReplyService.addReplyList(request);
				} catch (Exception e) {
					String result = "error";
					String message = initDataPool.getSP("2-4-000");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("data", "");
				}
			}
			return null;
		}
		
		//查询帖子详情以及回帖信息
		// http://192.168.3.130:8888/hbadmin/domain?do=findTopicReplyList&topic_id=1&index=1&version=1.0.0&appid=100&sign=c30be0365450242b92080e9f2e602dcc
		if("findTopicReplyList".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: 
				try {
					return topicReplyService.findTopicReplyList(request);
				} catch (Exception e) {
					String result = "error";
					String message = initDataPool.getSP("2-4-000");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("data", "");
				}
			}
			return null;
		}
		
		//发送密码找回邮件
		//http://192.168.3.111:8888/hbAdmin/domain?sign=4d5fee03447cffa065b1087f898c5fb7&appid=100&do=getPwdBackSendEmail&email=973712300@qq.com&version=1.0.0
		if("getPwdBackSendEmail".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: 
				try {
					return ccmsService.getPwdBackSendEmail(request);
				} catch (Exception e) {
					String result = "error";
					String message = initDataPool.getSP("2-4-000");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("data", "");
				}
			}
			return null;
		}
		
		//获取账号头像昵称
		if("getuserhead".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: 
				try {
					return userService.getuserhead(request);
				} catch (Exception e) {
					String result = "error";
					String message = initDataPool.getSP("2-4-000");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("data", "");
				}
			}
			return null;
		}
		
		
		
		//密码找回修改
		//
		if("getPwdBack".equals(doMethod) ){
			Map hsm = new LinkedHashMap();
			switch(versioncode){
				case 100: 
				try {
					return userService.getPwdBack(request);
				} catch (Exception e) {
					String result = "error";
					String message = initDataPool.getSP("2-4-000");
					hsm.put("version", Constant.version);
					hsm.put("result", result);
					hsm.put("message", message);
					hsm.put("data", "");
				}
			}
			return null;
		}
		
		Map hsmSign = new LinkedHashMap();
		hsmSign.put("version", version); 
		hsmSign.put("result", "0");
		hsmSign.put("message", "API方法不存在");
    	return hsmSign;
	
	}
	
	public String md5(Map parmsmap,String appkey){
		//签名校验
		StringBuffer urlparmsstr = new StringBuffer();
		parmsmap = Utils.sortMapByKey(parmsmap);
		for (Iterator iter = parmsmap.keySet().iterator(); iter.hasNext();) {
		     String key = (String) iter.next();
		     if("datasign".equals(key) )
		    	 continue;
		    Object values = (Object) parmsmap.get(key);
			urlparmsstr.append(key).append("=").append(values).append("&");
		}
		String urlparmsall = urlparmsstr.substring(0, urlparmsstr.length()-1).toString(); //去掉末尾一个&符号
		
		String serverSign1 = EncoderHandler.encodeByMD5(urlparmsall);
		String serverSign0 = EncoderHandler.encodeByMD5(urlparmsall + appkey);
		String serverSign = EncoderHandler.encodeByMD5(appkey + serverSign0 ); //全部小写md5
		return serverSign;
	}
	

	public FavoriteService getFavoriteService() {
		return favoriteService;
	}

	public void setFavoriteService(FavoriteService favoriteService) {
		this.favoriteService = favoriteService;
	}

	public TopicTypeService getTopicTypeService() {
		return topicTypeService;
	}

	public void setTopicTypeService(TopicTypeService topicTypeService) {
		this.topicTypeService = topicTypeService;
	}

	public BigeyeService getBigeyeService() {
		return BigeyeService;
	}

	public void setBigeyeService(BigeyeService bigeyeService) {
		BigeyeService = bigeyeService;
	}

}
