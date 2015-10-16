package com.manager.function.serviceimpl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.manager.function.dao.TopicListDao;
import com.manager.function.dao.UserDao;
import com.manager.function.dao.TopicReplyDao;
import com.manager.function.entity.TopicList;
import com.manager.function.entity.TopicReply;
import com.manager.function.service.TopicListService;
import com.manager.init.InitDataPool;
import com.manager.util.CollectionUtil;
import com.manager.util.Constant;
import com.manager.util.Xml;

public class TopicListServiceImpl implements TopicListService {
	
	private Logger logger = Logger.getLogger(TopicListServiceImpl.class);
	
	@Autowired
	private TopicListDao TopicListDao;
	private InitDataPool initDataPool;
	private UserDao userDao;
	private TopicReplyDao TopicReplyDao;
	
	public InitDataPool getInitDataPool() {
		return initDataPool;
	}

	public void setInitDataPool(InitDataPool initDataPool) {
		this.initDataPool = initDataPool;
	}

	@Transactional
	public int deleteTopicList(String id) {
		TopicReply TopicReply=new TopicReply();
		TopicReply.setTopic_id(id);
		this.TopicReplyDao.deleteTopicReply(TopicReply,2);
		TopicList TopicList=new TopicList();
		TopicList.setId(id);
		int i=this.TopicListDao.deleteTopicList(TopicList);
		return i;
	}

	public int findTopicListCount(TopicList TopicList) {
		return this.TopicListDao.findTopicListCount(TopicList);
	}

	public List<TopicList> findTopicListList(TopicList TopicList, int pageNo, int pageSize) {
		return this.TopicListDao.findTopicListList(TopicList, pageNo, pageSize);
	}

	public List<TopicList> findTopicListList() {
		return this.TopicListDao.findTopicListList(null);
	}
	
	public int insertTopicList(TopicList TopicList) {
		return this.TopicListDao.insertTopicList(TopicList);
	}

	public int updateTopicList(TopicList TopicList) {
		return this.TopicListDao.updateTopicList(TopicList);
	}

	public int updateTopicListStatus(TopicList TopicList) {
		return this.TopicListDao.updateTopicListStatus(TopicList);
	}
	
	public int updateBatchupdateLabel(List<TopicList> topiclistList){
		return this.TopicListDao.updateBatchupdateLabel(topiclistList);
	}
	
	public int updateTopicListLabel(TopicList TopicList){
		return this.TopicListDao.updateTopicListLabel(TopicList);
	}
	
	public TopicList findTopicListOne(String id){
		TopicList TopicList=new TopicList();
		TopicList.setId(id);
		List<TopicList> TopicListList=this.TopicListDao.findTopicListList(TopicList);
		if(TopicListList!=null && TopicListList.size()>0){
			return TopicListList.get(0);
		}
		return null;
	}
	
	public TopicList findTopicListOne(TopicList TopicList){
		List<TopicList> TopicListList=this.TopicListDao.findTopicListList(TopicList);
		if(TopicListList!=null && TopicListList.size()>0){
			return TopicListList.get(0);
		}
		return null;
	}


	/**
	 * @return the TopicListDao
	 */
	public TopicListDao getTopicListDao() {
		return TopicListDao;
	}

	/**
	 * @param TopicListDao the TopicListDao to set
	 */
	public void setTopicListDao(TopicListDao TopicListDao) {
		this.TopicListDao = TopicListDao;
	}

	public Map add(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		Map hsm = new LinkedHashMap();
		try{
			String topic_typeId = (String) request.getParameter("topic_typeId");
			String title = (String) request.getParameter("title");
			String content = (String) request.getParameter("content");
			String uid = (String) request.getParameter("uid");
			String label = "001";
			String affix = this.addImage(request);
			
			if(affix.equals("false")){
				result = "0";
				message = initDataPool.getSP("2-4-228");
			}else{
				String img_size=null;
				if(affix.equals("noimg")){
					affix = "";
				}else{
					img_size=affix.substring(affix.indexOf("&&")+2);
					affix=affix.substring(0,affix.indexOf("&&"));
				}
				String ip = request.getRemoteAddr();
				
				TopicList tl = new TopicList();
				tl.setTitle(title);
				tl.setTopic_typeId(topic_typeId);
				tl.setContent(content);
				tl.setUid(uid);
				tl.setLabel(label);
				tl.setAffix(affix);
				tl.setIp(ip);
				tl.setCountback("0");
				tl.setCountbrowse("0");
				tl.setStatus("1");
				tl.setImg_size(img_size);
				
				this.TopicListDao.add(tl);
				
				result = "1";
				message = initDataPool.getSP("2-4-230");
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
        logger.info("TopicListServiceImpl.add执行了"+diff+"毫秒");
		
		return hsm;
	}

	public Map findTopicListListByType(HttpServletRequest request,HttpServletResponse response) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));
		
		String result  = "0";
		String message = "";
		Map hsm = new LinkedHashMap();
		List<TopicList> ls = new ArrayList<TopicList>();
		List<TopicList> lsModel = new ArrayList<TopicList>();
		try{
			String topic_typeId = (String) request.getParameter("topic_typeId");
			String index = (String) request.getParameter("index");
			
			int currentIndex = 1;
			int PAGECOUNT = 10;
			
			if (CollectionUtil.checkNull(index)) {
				currentIndex = Integer.parseInt(index);
			}
			
			TopicList tl = new TopicList();
			tl.setTopic_typeId(topic_typeId);
			ls = this.TopicListDao.getByType(tl, (currentIndex - 1)*PAGECOUNT, PAGECOUNT);
			if(ls!=null){
				for(TopicList tl1:ls){
					String affix = tl1.getAffix()!=null?tobereplace(tl1.getAffix(), 0):"";
					tl1.setAffix(affix);
					lsModel.add(tl1);
				}
				result = "1";
				message = initDataPool.getSP("2-4-214");
			}else{
				result = "0";
				message = initDataPool.getSP("2-4-213");
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
		hsm.put("data", lsModel!=null?lsModel:"");
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("TopicListServiceImpl.findTopicListListByType执行了"+diff+"毫秒");
		
		return hsm;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Map findByBabyId(HttpServletRequest request) {
		
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date();
		logger.info("开始："+adf.format(d1));

		String result  = "0";
		String message = "";
		Map hsm = new LinkedHashMap();
		List<TopicList> ls = new ArrayList<TopicList>();
		List<TopicList> lsModel = new ArrayList<TopicList>();
		try{
			String topic_typeId = (String) request.getParameter("topic_typeId");
			String index = (String) request.getParameter("index");
			String user_id = (String) request.getParameter("uid");
			
			int currentIndex = 1;
			int PAGECOUNT = 10;
			
			if (CollectionUtil.checkNull(index)) {
				currentIndex = Integer.parseInt(index);
			}
			
			ls = this.TopicListDao.findBabyId(user_id, (currentIndex - 1)*PAGECOUNT, PAGECOUNT);
			
			if(ls!=null){
				for(TopicList tl1:ls){
					String affix = tl1.getAffix()!=null?tobereplace(tl1.getAffix(), 0):"";
					tl1.setAffix(affix);
					lsModel.add(tl1);
				}
				result = "1";
				message = initDataPool.getSP("2-4-214");
			}else{
				result = "0";
				message = initDataPool.getSP("2-4-213");
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
		hsm.put("data", lsModel!=null?lsModel:"");
		
		Date d2 = new Date();
		logger.info("结束："+adf.format(d2));
        long diff = (d2.getTime() - d1.getTime());
        logger.info("TopicListServiceImpl.findByBabyId执行了"+diff+"毫秒");
		
		return hsm;
	}
	/**
	 * @return the topicReplyDao
	 */
	public TopicReplyDao getTopicReplyDao() {
		return TopicReplyDao;
	}

	/**
	 * @param topicReplyDao the topicReplyDao to set
	 */
	public void setTopicReplyDao(TopicReplyDao topicReplyDao) {
		TopicReplyDao = topicReplyDao;
	}
	
	public String addImage(HttpServletRequest request) throws FileUploadBase.SizeLimitExceededException {  
        //转型为MultipartHttpRequest(重点的所在)  
         MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
         //  获得第1张图片（根据前台的name名称得到上传的文件）   
         MultipartFile imgFile1  =  multipartRequest.getFile("file1");
         if(imgFile1==null || imgFile1.isEmpty() || imgFile1.getSize()<=0){
        	 return "noimg";
         }
          
       //文件保存目录URL
 		String saveUrl  = Constant.bbs_url;
	     
	     //定义一个数组，用于保存可上传的文件类型  
	     List fileTypes = new ArrayList();  
	     fileTypes.add("jpg");  
	     fileTypes.add("png"); 
         
 		String filePath = Constant.bbs_path;
 		
 		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
 		SimpleDateFormat msdf = new SimpleDateFormat("MM");
 		SimpleDateFormat dsdf = new SimpleDateFormat("dd");
 		SimpleDateFormat hsdf = new SimpleDateFormat("hh");

 		String y = ysdf.format(new Date());
 		String m = msdf.format(new Date());
 		String d = dsdf.format(new Date());
 		String h = hsdf.format(new Date());

 		filePath += y + File.separator+ m + File.separator + d + File.separator + h + File.separator;
 		saveUrl +=  y + "/" + m + "/" + d + "/" + h + "/" ;
 		
 		File dirPath = new File(filePath);//文件保存路径
 		
 		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

        //获取文件的后缀名  
		String fileName = imgFile1.getOriginalFilename();//得到文件的名字  
		String fileExt = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());  

		if(!(fileName ==null || "".equals(fileName))){  
			
			//对扩展名进行小写转换  
			fileExt = fileExt.toLowerCase();  
		      
//		    if(!fileTypes.contains(fileExt)) {
//		    	return "false";//判断文件格式
//		    }  
			
		    try {  
		        //将文件上传到项目的upload目录并命名，getRealPath可以得到该web项目下包含/upload的绝对路径  
//                                    fileItem.write(new File(request.getServletContext().getRealPath("/upload")+"/"  
//                                            + UUID.randomUUID().toString()+"."+fileExt)); 
		    	SimpleDateFormat df = new SimpleDateFormat("mmssSS");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				filePath += newFileName;
				saveUrl += newFileName;
				saveUrl = tobereplace(saveUrl, 1);
				
				imgFile1.transferTo(new File(filePath));
				
				
				BufferedImage sourceImg =ImageIO.read(new File(filePath));
				int width = sourceImg.getWidth();
				int height = sourceImg.getHeight();
				
		        return saveUrl+"&&"+width+"|"+height;
		    } catch (Exception e) {  
		        e.printStackTrace();  
		        return "false";
		    }  
		}else{  
		    System.out.println("该文件类型不能够上传");
		    return "false";
		} 
          
    } 
	public static String tobereplace(String message, int in) throws Exception {
		String path = TopicListServiceImpl.class.getClassLoader().getResource("").toURI().getPath();
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
