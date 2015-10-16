package com.manager.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public  class CollectionUtil {
	
	
	public static Logger logger = Logger.getLogger(CollectionUtil.class);
	
	 /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
                String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                Pattern regex = Pattern.compile(check);
                Matcher matcher = regex.matcher(email);
                flag = matcher.matches();
            }catch(Exception e){
                flag = false;
            }
        return flag;
    }
    
    public static String readerFileWEBINF(String filename){
    	String path;
		try {
			path = CollectionUtil.class.getClassLoader().getResource("").toURI().getPath();
			path = path.split("WEB-INF")[0] + "WEB-INF"+File.separator+filename;
			String line=null;
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));  
			StringBuffer sbuffer=new StringBuffer();
			while((line=br.readLine())!=null){
				sbuffer.append(line);
			}
			return sbuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
     
    /**
     * 验证手机号码
     * @param mobiles
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber){
        boolean flag = false;
        try{
                Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
                Matcher matcher = regex.matcher(mobileNumber);
                flag = matcher.matches();
            }catch(Exception e){
                flag = false;
            }
        return flag;
    }
	
	public static synchronized void writeHtml(String filePath, String info,String code) {

		try {
			File writeFile = new File(filePath);
			boolean isExit = writeFile.exists();
			if (!isExit) {
				writeFile.createNewFile();
			} else {
				writeFile.delete();
				writeFile.createNewFile();
			}
			FileOutputStream fs = new FileOutputStream(filePath, false);
			fs.write(info.getBytes(code));
			fs.close();
		} catch (Exception e) {
			logger.error("���"+filePath+"����",e);
			//throw new Exception("writeHtml:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static String tobereplace(String message, int in) throws Exception {
//		String bereplace = Constant.messageimagebereplace;
//		String replace = Constant.messageimagereplace;
		
		String path = Xml.class.getClassLoader().getResource("").toURI().getPath();
		path = path.split("WEB-INF")[0] + "WEB-INF"+File.separator+"Config.xml";
		String bereplace = Xml.getXmlTagValue(path, "messageimagebereplace");
		String replace = Xml.getXmlTagValue(path, "messageimagereplace");
		if (in == 0) {
			message = message.replaceAll(replace, bereplace);
		}
		if (in == 1) {
			message = message.replaceAll(bereplace, replace);
		}
		return message;
	}
	
	public static String toplaybereplace(String message, int in) throws Exception {
		String path = Xml.class.getClassLoader().getResource("").toURI().getPath();
		path = path.split("WEB-INF")[0] + "WEB-INF"+File.separator+"Config.xml";
		String bereplace = Xml.getXmlTagValue(path, "playpathurl");
		String replace = Xml.getXmlTagValue(path, "playpathurlreplace");
		if (in == 0) {
			message = message.replaceAll(replace, bereplace);
		}
		if (in == 1) {
			message = message.replaceAll(bereplace, replace);
		}
		return message;
	}
	
	public Map ListToMap(List list){
		Map map =new HashMap();
		if(list!=null){
			for(int i=0;i<list.size();i++){
				Object obj[]=(Object[])list.get(i);
				map.put(obj[0]+"", obj[1]);
			}
		}
		return map;
	}
	
	public static boolean checkNull(String str){
		if(str!=null && !"".equals(str) && !"null".equals(str)){
			return true;
		}
		return false;
	}
	
	public  static  String getScale(String s1,String s2){
		Float l1=Float.parseFloat(s1);
		Float l2=Float.parseFloat(s2);
		String Rate=(new DecimalFormat("###,###,###.##").format(l1/l2*100))+"%";
		return Rate;
	}
	
	public static String Proportion(long p1, long p2) {
		try {
			String a = String.valueOf(p1);
			String b = String.valueOf(p2);
			if ("0".equals(a.trim()) || "0".equals(b.trim())) {
				return "0%";
			} else {
				double p3 = ((double) p1 / p2);
				NumberFormat nf = NumberFormat.getPercentInstance();
				nf.setMinimumFractionDigits(2);
				String rate = nf.format(p3);
				return rate;
			}
		} catch (Exception e) {
			return "0%";
		}
	}

}
