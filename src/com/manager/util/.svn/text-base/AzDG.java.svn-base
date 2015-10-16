package com.manager.util;



import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

//import com.sun.java_cup.internal.parse_action;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AzDG {
											
	private final static String PASSWORD_CRYPT_KEY = "123456";//数据库密码key	
	private final static String TENGREN_AREA_KEY = "123456";//区服密码key
	private final static String TengRenCardZipKey = "123456";//点卡导出xls时zip包密码	
	private final static String GiftItem16CodeKey="123456";//礼包道具内容key 
	private final static String PAYTYPE_MERCHANTKEY = "123456";//充值方式的KEY
	/**
	* encrypt
	*/
	public static byte[] encrypt(byte[] txt, byte[] key) {
		
		int rand = new Double(Math.random()*32000).intValue();

		byte[] encrypt_key = null;
		try {
			encrypt_key = new Md5().getMd5ByString(rand+"").toLowerCase().getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}

		byte ctr = 0;

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		
		for (int i = 0, j = 0; i < txt.length; i++,j+=2) {
			ctr = ctr == encrypt_key.length ? 0 : ctr;
			byteOut.write(encrypt_key[ctr]);
			byteOut.write(txt[i] ^ encrypt_key[ctr++]);
		}

		try {
			return getBASE64(new String(encodeKey(byteOut.toByteArray(), key))).getBytes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static String encrypt(String txt,String key){
		try {
			return new String(encrypt(txt.getBytes(),key.getBytes()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public static String encrypt(String txt,String key,String encoding){
		String str = null;
		try{
			str = new String(encrypt(txt.getBytes(encoding),key.getBytes()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}


	public static String getBASE64(String s) {
		if (s == null) return null;
		try {
			return (new BASE64Encoder()).encode( s.getBytes() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

//		 将 BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String s) {
		if (s == null) return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
		byte[] b = decoder.decodeBuffer(s);
		return new String(b);
		} catch (Exception e) {
		return null;
		}
	}
		
	/**
	* decrypt
	*/
	public static byte[] decrypt(byte[] txt, byte[] key) {
		try {
			txt = encodeKey(getFromBASE64(new String(txt)).getBytes(), key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		for (int i = 0; i < txt.length; i++) {
			byte md5 = txt[i];
			byteOut.write(txt[++i] ^ md5);
		}
		return byteOut.toByteArray();
	}

	/**
	*
	*/
	public static String decrypt(String txt, String key){
		try {
			return new String(decrypt(txt.getBytes(),key.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	*
	*/
	public static String decrypt(String txt){
		try {
			return new String(decrypt(txt.getBytes("utf-8"),PASSWORD_CRYPT_KEY.getBytes()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * zip密码解密
	 */
	public static String Zipdecrypt(String txt){
		try{
			return new String(decrypt(txt.getBytes("utf-8"),TengRenCardZipKey.getBytes()));
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * zip密码加密
	 */
	public static String Zipencrypt(String txt){
		try{
			return new String(encrypt(txt.getBytes("utf-8"),TengRenCardZipKey.getBytes()));
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 游戏区服 数据库密码解密
	 */
	public static String Areadecrypt(String txt){
		try {
			return new String(decrypt(txt.getBytes("utf-8"),TENGREN_AREA_KEY.getBytes()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	public static String encrypt(String txt){
		try {
			return new String(encrypt(txt.getBytes("utf-8"),PASSWORD_CRYPT_KEY.getBytes()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 游戏区服 数据库密码 加密算法
	 */
	public static String Areaencrypt(String txt){
		try {
			return new String(encrypt(txt.getBytes("utf-8"),TENGREN_AREA_KEY.getBytes()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 礼包编码加密
	 */
	public static String GiftItemEncrypt(String txt,String key2) throws Exception{
		try{
			if(key2!=null){
				return new String(encrypt(txt.getBytes("utf-8"),(GiftItem16CodeKey+key2).getBytes()));
			}else{
				return new String(encrypt(txt.getBytes("utf-8"),(GiftItem16CodeKey).getBytes()));
			}
		}catch(Exception e){
			throw e;
		}
	}
	/**
	 * 礼包编码解密
	 */
	public static String GiftItemDecrypt(String txt,String key2) throws Exception{
		try{
			if(key2!=null){
				return new String(decrypt(txt.getBytes("utf-8"),(GiftItem16CodeKey+key2).getBytes()));
			}else{
				return new String(decrypt(txt.getBytes("utf-8"),(GiftItem16CodeKey).getBytes()));
			}
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * 充值方式接口密钥加密	key2可以为null
	 */
	public static String PaytypeKeyEncrypt(String txt, String key2) throws Exception{
		try {
			if(key2!=null){
				return new String(encrypt(txt.getBytes("utf-8"),(PAYTYPE_MERCHANTKEY+key2).getBytes()));
			}else{
				return new String(encrypt(txt.getBytes("utf-8"),(PAYTYPE_MERCHANTKEY).getBytes()));
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 充值方式接口密钥解密	key2可以为null
	 */
	public static String PaytypeKeyDecrypt(String txt,String key2) throws Exception{
		try {
			if(key2!=null){
				return new String(decrypt(txt.getBytes("utf-8"),(PAYTYPE_MERCHANTKEY+key2).getBytes()));
			}else{
				return new String(decrypt(txt.getBytes("utf-8"), (PAYTYPE_MERCHANTKEY).getBytes()));
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	*
	*/
	public static byte[] encodeKey(byte[] txt, byte[] encrypt_key) {
		try {
			encrypt_key = new Md5().getMd5ByString(new String(encrypt_key)).toLowerCase().getBytes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte ctr = 0;
		byte[] tmp = new byte[txt.length];
		//	ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		for (int i = 0; i < txt.length; i++) {
			ctr = ctr == encrypt_key.length ? 0 : ctr;
			tmp[i] = (byte) (txt[i] ^ encrypt_key[ctr++]);
			//	byteOut.write(txt[i] ^ encrypt_key[ctr++]);
		}
		return tmp;
	}
	
	public static void main(String args[]){
		/**
		String cardPassword="VTdceVVkAjZaalUxVDUHNVQyUTAMO1diUzFabwdlCWsDYQpnAjZWZlJjVDlUNQMxAGUAYQUyCT0O"+
			"bAA1AGJVN1U3XDFVZAI2WmpVMVQ1BzVUMlEwDDtXYlMxWm8HZQlrA2EKZwIzVmJSYlQwVDUDMQBm"+
			"AGEFMgk8DmwANQBiVTdVN1wxVWQCNlpqVTFUNQc1VDJRMAxNVxRTR1oZBxMJHQMXChECRVYUUhRU"+
			"RlRDA0cAEAAXBUQJSg4aAEMAFFVBVUFcR1USAkBaHFVHVEMHQ1REUUYMO1diUzFabwdlCWsDYQpn";
		*/
		

    	//long time=System.currentTimeMillis();
//    	String s=encrypt("root"+"123456");
    	String s = EncoderHandler.encodeByMD5("123456");
    	System.out.println(s);
		try {
			//System.out.println("解密后="+GiftItemDecrypt(cardPassword,"5547"));
//			String paykey = "o4l65cciuovcddqa7vt9lycjit86rqp4csypbyvsixqvs5mececy75jjeqvfpkrrfckl27uo6frj26yxmiutt907zh3r8h7atiwhroeh6hu1bo4lu09zbc7awfvvstn6";
//			String encrypt_azdgkey = Areaencrypt(paykey); //PaytypeKeyEncrypt(paykey, null);
//			System.out.println("加密后："+encrypt_azdgkey);
//			String dncrypt_azdgkey = Areadecrypt(encrypt_azdgkey); //PaytypeKeyDecrypt(encrypt_azdgkey, null);
//			System.out.println("解密后："+dncrypt_azdgkey);
//			System.out.println("原  码："+paykey);
//			String str = "BDlWNVE+C2IENQExDGNUO1F9UmIEfVE1XTIONldxUjZSN1R1AHcPaVY+U3kGaQRoU2MELwcwCzpeJ11wBCMENQQ1VnJRKwskBGIBKwx2VCFRYVJ1BHpRIF0lDmdXbVIyUmNUZgBgDylWZVM1BmAEaFNvBCoHfgtqXiVdagQhBHMEMFZiUTkLOAQyAWUMdVQ9UT5SawR5UTxdZA5kV3lSL1JtVGoAdg8kViZTOQY6BDVTcAQzBzsLfl5tXWkEZARgBCJWaFElCzwEcgE9DGVUOlE+UmUEflFnXTQOPVc0UjtSdVQzADoPKlYwU2MGPQRjU30EPQd+C3peJl11BD0ENw==";
//			System.out.println(str.length());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch 111block
			e.printStackTrace();
		}
	
		
	}
}
