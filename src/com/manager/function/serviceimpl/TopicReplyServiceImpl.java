package com.manager.function.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.manager.function.dao.TopicListDao;
import com.manager.function.dao.TopicReplyDao;
import com.manager.function.entity.TopicList;
import com.manager.function.entity.TopicReply;
import com.manager.function.service.TopicReplyService;
import com.manager.init.InitDataPool;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;
import com.manager.util.Xml;

public class TopicReplyServiceImpl implements TopicReplyService {
	
	private Logger logger = Logger.getLogger(TopicReplyServiceImpl.class);
	
	@Autowired
	private TopicReplyDao TopicReplyDao;
	private InitDataPool initDataPool;
	private TopicListDao TopicListDao;

	public TopicListDao getTopicListDao() {
		return TopicListDao;
	}

	public void setTopicListDao(TopicListDao topicListDao) {
		TopicListDao = topicListDao;
	}

	public InitDataPool getInitDataPool() {
		return initDataPool;
	}

	public void setInitDataPool(InitDataPool initDataPool) {
		this.initDataPool = initDataPool;
	}

	public int deleteTopicReply(String id) {
		TopicReply TopicReply=new TopicReply();
		TopicReply.setId(id);
		return this.TopicReplyDao.deleteTopicReply(TopicReply,1);
	}

	public int findTopicReplyCount(TopicReply TopicReply) {
		return this.TopicReplyDao.findTopicReplyCount(TopicReply);
	}

	public List<TopicReply> findTopicReplyList(TopicReply TopicReply, int pageNo, int pageSize) {
		return this.TopicReplyDao.findTopicReplyList(TopicReply, pageNo, pageSize);
	}

	public List<TopicReply> findTopicReplyList() {
		return this.TopicReplyDao.findTopicReplyList(null);
	}
	
	public int insertTopicReply(TopicReply TopicReply) {
		return this.TopicReplyDao.insertTopicReply(TopicReply);
	}

	public int updateTopicReply(TopicReply TopicReply) {
		return this.TopicReplyDao.updateTopicReply(TopicReply);
	}

	public int updateTopicReplyStatus(TopicReply TopicReply) {
		return this.TopicReplyDao.updateTopicReplyStatus(TopicReply);
	}
	
	public TopicReply findTopicReplyOne(String id){
		TopicReply TopicReply=new TopicReply();
		TopicReply.setId(id);
		List<TopicReply> TopicReplyList=this.TopicReplyDao.findTopicReplyList(TopicReply);
		if(TopicReplyList!=null && TopicReplyList.size()>0){
			return TopicReplyList.get(0);
		}
		return null;
	}
	
	public TopicReply findTopicReplyOne(TopicReply TopicReply){
		List<TopicReply> TopicReplyList=this.TopicReplyDao.findTopicReplyList(TopicReply);
		if(TopicReplyList!=null && TopicReplyList.size()>0){
			return TopicReplyList.get(0);
		}
		return null;
	}


	/**
	 * @return the TopicReplyDao
	 */
	public TopicReplyDao getTopicReplyDao() {
		return TopicReplyDao;
	}

	/**
	 * @param TopicReplyDao the TopicReplyDao to set
	 */
	public void setTopicReplyDao(TopicReplyDao TopicReplyDao) {
		this.TopicReplyDao = TopicReplyDao;
	}

	@Transactional
	public Map addReplyList(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		Map hsm = new LinkedHashMap();
		try{
			String content = (String) request.getParameter("content");
//			content=new String(content.getBytes("ISO-8859-1"),"UTF-8");//本地环境乱码须把这个打开
			String topic_id = (String) request.getParameter("topic_id");
			String uid = (String) request.getParameter("uid");
			String quote_content = (String) request.getParameter("quote_content");
			
			int sort = this.TopicReplyDao.getlouceng(topic_id);
			
			String ip = request.getRemoteAddr();
			
			TopicReply tr  = new TopicReply();
			tr.setContent(content);
			tr.setSort(sort+"");
			tr.setIp(ip);
			tr.setTopic_id(topic_id);
			tr.setUid(uid);
			tr.setQuote_content(quote_content);
			tr.setStatus("1");
			this.TopicReplyDao.add(tr);
			
			this.TopicListDao.updateCountback(topic_id);
			
			result = "1";
			message = initDataPool.getSP("2-4-234");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error";
			message = initDataPool.getSP("2-4-000");
			throw new RuntimeException("添加评论出现异常");
		}
		hsm.put("version", Constant.version);
		hsm.put("result", result);
		hsm.put("message", message);
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("TopicReplyServiceImpl.addReplyList执行了"+diff+"毫秒");
		
		return hsm;
	}

	public Map findTopicReplyList(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));

		String result  = "0";
		String message = "";
		Map hsm = new LinkedHashMap();
		List<TopicReply> ls = new ArrayList<TopicReply>();
		JSONObject obj = new JSONObject();
		try{
			int currentIndex = 1;
			int PAGECOUNT = 10;
			String topic_id = (String) request.getParameter("topic_id");
			String index = (String) request.getParameter("index");
			
			if (CollectionUtil.checkNull(index)) {
				currentIndex = Integer.parseInt(index);
			}
			
			if(currentIndex==1){
				this.TopicListDao.updateCountbrowse(topic_id);
			}
			TopicList tlModel = this.TopicListDao.findOne(topic_id);
			
			if(tlModel!=null){
				
				TopicReply tl = new TopicReply();
				tl.setTopic_id(topic_id);
				tl.setStatus("1");
				ls = this.TopicReplyDao.findTopicReplyList1(tl, (currentIndex - 1)*PAGECOUNT, PAGECOUNT);
				
				obj.put("topiclist_id", tlModel.getId());
				obj.put("title", tlModel.getTitle());
				obj.put("content", tlModel.getContent());
				obj.put("createtime", tlModel.getCreatetime());
				obj.put("label", tlModel.getLabel());
				obj.put("countback", tlModel.getCountback());
				obj.put("countbrowse", tlModel.getCountbrowse());
				obj.put("affix", tlModel.getAffix()!=null?tobereplace(tlModel.getAffix(), 0):"");
				obj.put("uid", tlModel.getUid()!=null?tlModel.getUid():"");
				obj.put("user_nickname", tlModel.getUser_nickname()!=null?tlModel.getUser_nickname():"");
				obj.put("img_size", tlModel.getImg_size()!=null?tlModel.getImg_size():"");
				obj.put("data", ls!=null?ls:"");
				
				result = "1";
				message = initDataPool.getSP("2-4-214");
			}else{
				result = "0";
				message = initDataPool.getSP("2-4-231");
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
		hsm.put("data", obj!=null&&!obj.isEmpty()?obj:"");
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("TopicReplyServiceImpl.findTopicReplyList执行了"+diff+"毫秒");
		
		return hsm;
	
	}
	
	public static String tobereplace(String message, int in) throws Exception {
		String path = TopicReplyServiceImpl.class.getClassLoader().getResource("").toURI().getPath();
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
