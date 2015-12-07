package com.bbs.util;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;


/***
 * MD5 SHA1 加密类
 * @author HongQuan
 */

public class MD5 {
	
	private static Logger logger = Logger.getLogger(MD5.class);

	private static final String ALGORITHM = "MD5";

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	/**
	 * 加密接口参数
	 * @param parmsmap
	 * @param appkey
	 * @return
	 */
	public static String md5Api(Map<String,String> parmsmap){
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
		
		logger.info("md5params==================="+urlparmsall);
		
		//String serverSign1 = MD5.encodeByMD5(urlparmsall);
		String serverSign0 = MD5.encodeByMD5(urlparmsall +Constant.APIKEY);
		String serverSign = MD5.encodeByMD5(Constant.APIKEY + serverSign0 ); //全部小写md5
		return serverSign;
	}

	/**
	 * encode string
	 *
	 * @param algorithm
	 * @param str
	 * @return String
	 */
	public static String encode(String algorithm, String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(str.getBytes("UTF-8"));
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * encode By MD5
	 *
	 * @param str
	 * @return String
	 */
	public static String encodeByMD5(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
			messageDigest.update(str.getBytes("UTF-8"));
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Takes the raw bytes from the digest and formats them correct.
	 *
	 * @param bytes
	 *            the raw bytes from the digest.
	 * @return the formatted bytes.
	 */
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		String str = "account=13166296663&aim=自用&appid=100&avatarurl=http://p5.qhimg.com/t01c8a7a8bdceb36fb4.jpg&birthday=19871020&budget=150万&city=上海&cnname=黄文杰&country=中国&curradd=上海市宝山区沪太路&currhouse=无&district=宝山区&do=perfectinfo&email=271288508@qq.com&emephone=13585560293&emergency=黄文杰2&enname=huangwenjie&expectedtime=2&familyincome=200000&favoritecity=上海&favoriteprovince=上海&gender=男&income=100000&industry=IT&ipaddress=58.247.94.202&job=IT&mobile=13166296663&nickname=哈哈呵呵&province=上海&version=1.0.0&wechat=jevon1020";
		System.out.println("MD5  :" + MD5.encodeByMD5(str));
		System.out.println("MD5  :" + MD5.encode("MD5", str));
		System.out.println("SHA1 :" + MD5.encode("SHA1", str));
		Map<String ,String> pa=new HashMap<String,String>();
		pa.put("uid", "1");
		
		System.out.println(md5Api(pa));
	}

}