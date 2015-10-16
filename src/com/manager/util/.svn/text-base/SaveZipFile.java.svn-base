package com.manager.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;



public class SaveZipFile extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SaveZipFile.class);

	/**
	 * 实现多文件的同时上传
	 */ 
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//设置接收的编码格式
		request.setCharacterEncoding("UTF-8");
		
		//名称  界面编码 必须 和request 保存一致..否则乱码  
        String name = request.getParameter("name"); 
//        String size = request.getParameter("size");
        String id=request.getParameter("id");
        System.out.println(name);
        
		String path = "";
		
		String playpathzip = "";
		String playpathfile = "";
		String playpathurl = "";
		String playpathurlpage = "";
		
		String newFileName = "";
		String versionFile="";
		String reg = "";
		
		try {
			path = Xml.class.getClassLoader().getResource("").toURI().getPath();
			path = path.split("WEB-INF")[0] + "WEB-INF"+File.separator+"Config.xml";
			playpathzip = Xml.getXmlTagValue(path, "playpathzip");//游戏资源zip保存目录
			playpathfile = Xml.getXmlTagValue(path, "playpathfile");//游戏资源解压后保存目录
			playpathurl = Xml.getXmlTagValue(path, "playpathurl");// 游戏资源url前段配置
			playpathurlpage = Xml.getXmlTagValue(path, "playpathurlpage");//游戏资源页面名称
			
		} catch (Exception e) {
			logger.error("SaveZipFile获取配置异常：", e);
		} 
		
		//文件保存目录路径
		String savePath = playpathzip;
		
//		//文件保存目录URL
//		String savefile  = playpathurl;
		
		
		//检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		File uploadDir2 = new File(playpathfile);
		if (!uploadDir2.exists()) {
			uploadDir2.mkdirs();
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			logger.info("上传目录:"+savePath+",没有写权限。");
			return;
		}
		//检查目录写权限
		if(!uploadDir2.canWrite()){
			logger.info("解压目录:"+playpathfile+",没有写权限。");
			return;
		}
		
		//创建文件夹
		savePath += id + File.separator;
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		playpathfile += id + File.separator;
		File saveDirFile2 = new File(playpathfile);
		if (!saveDirFile2.exists()) {
			saveDirFile2.mkdirs();
		}
		playpathurl += id + "/";
		
		
		try {
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			upload.setHeaderEncoding("UTF-8");
			// 获取多个上传文件
			List fileList = fileList = upload.parseRequest(request);
			// 遍历上传文件写入磁盘
			Iterator it = fileList.iterator();
			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();
				
				// 如果item是文件上传表单域   
				// 获得文件名及路径   
				String fileName = item.getName();
				if (fileName != null) {
					//检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
					versionFile= df.format(new Date()) ;//+ "_" + new Random().nextInt(1000);
					newFileName =versionFile + "." + fileExt;
					
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
//					System.out.println("绝对路径:"+playpathurl + newFileName);
//					reg = playpathurl + newFileName;
				} 
			} 
			unZipFiles(savePath + newFileName,playpathfile);
			reg = playpathurl +  playpathurlpage;
			logger.info("文件访问页面路径:"+reg);
			
		} catch (FileUploadException ex) {
			ex.printStackTrace();
			logger.info("没有上传文件");
			return;
	   } catch (Exception e) {
//			e.printStackTrace();
			logger.error("上传压缩文件异常："+e.getMessage(),e);
		} 
	   response.getWriter().print(reg);
		
	}
	
	/** 
     * 解压到指定目录 
     * @param zipPath 
     * @param descDir 
     * @author isea533 
     */  
    public static void unZipFiles(String zipPath,String descDir)throws IOException{  
        unZipFiles(new File(zipPath), descDir);  
    }  
    /** 
     * 解压文件到指定目录 
     * @param zipFile 
     * @param descDir 
     * @author isea533 
     */  
    @SuppressWarnings("rawtypes")  
    public static void unZipFiles(File zipFile,String descDir)throws IOException{  
        File pathFile = new File(descDir);  
        if(!pathFile.exists()){  
            pathFile.mkdirs();  
        }  
        ZipFile zip = new ZipFile(zipFile);  
        logger.info("******************开始解压********************");
        for(Enumeration entries = zip.entries();entries.hasMoreElements();){  
            ZipEntry entry = (ZipEntry)entries.nextElement();  
            String zipEntryName = entry.getName(); 
            if(countStr(zipEntryName,"/")>0){
            	zipEntryName = zipEntryName.substring(zipEntryName.indexOf("/")+1);
            }else{
            	 continue; 
            }
            InputStream in = zip.getInputStream(entry);  
            String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;  
            //判断路径是否存在,不存在则创建文件路径  
            if(outPath.indexOf("/")>0){
            	File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));  
            	if(!file.exists()){  
            		file.mkdirs();  
            	}  
            }
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压  
            if(new File(outPath).isDirectory()){  
                continue;  
            }  
            //输出文件路径信息  
            logger.info(outPath);
              
            OutputStream out = new FileOutputStream(outPath);  
            byte[] buf1 = new byte[1024];  
            int len;  
            while((len=in.read(buf1))>0){  
                out.write(buf1,0,len);  
            }  
            in.close();  
            out.close();  
        }  
        logger.info("******************解压完毕********************");
    }  
    
    /** 
     * 判断str1中包含str2的个数 
      * @param str1 
     * @param str2 
     * @return counter 
     */  
    public static int countStr(String str1, String str2) {  
    	String[] sss=str1.split(str2);
    	if(sss.length>1){
    		return 1;
    	}
           return 0;  
    }  
 
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}