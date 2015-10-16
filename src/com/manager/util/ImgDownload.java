package com.manager.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.manager.function.controller.GlobalHobbyController;


public class ImgDownload {
	
	public static String getPath(HttpServletRequest request){
        //转型为MultipartHttpRequest(重点的所在)  
        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
        //  获得第1张图片（根据前台的name名称得到上传的文件）   
        MultipartFile imgFile1  =  multipartRequest.getFile("userfile");
        if(imgFile1==null){
       	 	return "false";
        }
         
      //文件保存目录URL
		String saveUrl  = Constant.qdimagebereplace;
	     
	     //定义一个数组，用于保存可上传的文件类型  
	     List fileTypes = new ArrayList();  
	     fileTypes.add("jpg");  
	     fileTypes.add("png"); 
        
		String filePath = Constant.baby_avatar;
		
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
		      
		    if(!fileTypes.contains(fileExt)) {
		    	return "false";//判断文件格式
		    }  
			
		    try {  
		    	SimpleDateFormat df = new SimpleDateFormat("mmssSS");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				filePath += newFileName;
				saveUrl += newFileName;
				saveUrl = tobereplace(saveUrl, 1);
				
				imgFile1.transferTo(new File(filePath));
		        return saveUrl;
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
