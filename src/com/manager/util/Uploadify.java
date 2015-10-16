package com.manager.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jdom.JDOMException;
 

public class Uploadify extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;

	/**
	 * 实现多文件的同时上传
	 */ 
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//设置接收的编码格式
		request.setCharacterEncoding("UTF-8");
		
		//名称  界面编码 必须 和request 保存一致..否则乱码  
        String name = request.getParameter("name"); 
        String size = request.getParameter("size");
        System.out.println(name);
        
		String path = "";
		String editorImageSave = "";
		String messageimagebereplace = "";
		String newFileName = "";
		String reg = "";
		
		try {
			path = Xml.class.getClassLoader().getResource("").toURI().getPath();
			path = path.split("WEB-INF")[0] + "WEB-INF"+File.separator+"Config.xml";
			editorImageSave = Xml.getXmlTagValue(path, "editorImageSave");
			messageimagebereplace = Xml.getXmlTagValue(path, "messageimagebereplace");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//文件保存目录路径
		String savePath = editorImageSave;
		
		//文件保存目录URL
		String saveUrl  = messageimagebereplace;
		
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			System.out.println("上传目录不存在。");
			return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			System.out.println("上传目录没有写权限。");
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if(!dirName.equals("image")){
			System.out.println("目录名不正确。");
			return;
		}
		//创建文件夹
		savePath += dirName + File.separator;
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}


		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat msdf = new SimpleDateFormat("MM");
		SimpleDateFormat dsdf = new SimpleDateFormat("dd");
		SimpleDateFormat hsdf = new SimpleDateFormat("hh");

		String y = ysdf.format(new Date());
		String m = msdf.format(new Date());
		String d = dsdf.format(new Date());
		String h = hsdf.format(new Date());

		savePath += y + File.separator+ m + File.separator + d + File.separator + h + File.separator;
		saveUrl +=  y + "/" + m + "/" + d + "/" + h + "/" ;
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
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
					
					SimpleDateFormat df = new SimpleDateFormat("mmssSS");
					newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
					
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
					System.out.println("绝对路径:"+saveUrl + newFileName);
					reg = saveUrl + newFileName;
				} 
			} 
			File f = new File(savePath + newFileName);
			BufferedImage sourceImg =ImageIO.read(f);
			int width = "file".equals(name)?0:sourceImg.getWidth();
			int height = "file".equals(name)?0:sourceImg.getHeight();
			System.out.println("宽："+width+"----长："+height);
			if(CollectionUtil.checkNull(name)){
				if(name.equals("1")){
					reg += "-"+width+"|"+height;
				}else if(name.equals("2")){
					int X = width - 120;
					int Y = height - 80;
					if(X!=0||Y!=0){
						reg = "2";
						f.delete();
					}
				}else if(name.equals("bigeye")){
					if(CollectionUtil.checkNull(size)){
						int img_width=Integer.parseInt(size.substring(0, size.indexOf("*")));
						int img_height=Integer.parseInt(size.substring(size.indexOf("*")+1));
						int X = width - img_width;
						int Y = height - img_height;
						if(X!=0||Y!=0){
							reg = "2";
							f.delete();
						}
					}
				}else if(name.equals("unlimited")){
					//不限制任何尺寸
				}
			}
		} catch (FileUploadException ex) {
			ex.printStackTrace();
			System.out.println("没有上传文件");
			return;
	   } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	   response.getWriter().print(reg);
		
	}
 
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}