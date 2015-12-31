package com.bbs.bean;

public class Tiezi {

	/*
	 * "id": 63,
            "buluoid": 1,
            "biaoti": 1,
            "leixing": 0,
            "neirong": 1,
            "chakanshu": 1,
            "pinglunshu": 0,
            "didian": "涓婃捣甯�",
            "yonghuid": 1,
            "zhuangtai": 0,
            "tupian1": "",
            "tupian2": "",
            "tupian3": "",
            "tupian4": "",
            "tupian5": "",
            "tupian6": "",
            "jinghua": 0,
            "created": "2015-11-28 19:19:40",
            "yirubuluo": 1,
            "yishoucang": 0,
            "buluomingzi": "娴嬭瘯"
	 */
	private String id;
	private String buluoid;
	private String biaoti;
	private String leixing;
	private String neirong;
	private String chakanshu;
	private String pinglunshu;
	private String didian;
	private String yonghuid;
	private String zhuangtai;
	private String tupian1;
	private String tupian2;
	private String tupian3;
	private String tupian4;
	private String tupian5;
	private String tupian6;
	private String jinghua;
	private String created;
	private String yirubuluo;
	private String yishoucang;
	private String buluomingzi;
	
	private String page;

	private String userName;
	private String userHead;
	public Tiezi() {
		super();
	}
	
	public Tiezi(String id, String buluoid, String biaoti, String leixing,
			String neirong, String chakanshu, String pinglunshu, String didian,
			String yonghuid, String zhuangtai, String tupian1, String tupian2,
			String tupian3, String tupian4, String tupian5, String tupian6,
			String jinghua, String created, String yirubuluo,
			String yishoucang, String buluomingzi, String page,String name,String head) {
		super();
		this.id = id;
		this.buluoid = buluoid;
		this.biaoti = biaoti;
		this.leixing = leixing;
		this.neirong = neirong;
		this.chakanshu = chakanshu;
		this.pinglunshu = pinglunshu;
		this.didian = didian;
		this.yonghuid = yonghuid;
		this.zhuangtai = zhuangtai;
		this.tupian1 = tupian1;
		this.tupian2 = tupian2;
		this.tupian3 = tupian3;
		this.tupian4 = tupian4;
		this.tupian5 = tupian5;
		this.tupian6 = tupian6;
		this.jinghua = jinghua;
		this.created = created;
		this.yirubuluo = yirubuluo;
		this.yishoucang = yishoucang;
		this.buluomingzi = buluomingzi;
		this.page = page;
		this.userName = name;
		this.userHead = head;
	}

	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String name)
	{
		this.userName = name;
	}
	
	public String getUserHead()
	{
		return userHead;
	}
	public void setUserHead(String head)
	{
		this.userHead = head;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBuluoid() {
		return buluoid;
	}
	public void setBuluoid(String buluoid) {
		this.buluoid = buluoid;
	}
	public String getBiaoti() {
		return biaoti;
	}
	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}
	public String getLeixing() {
		return leixing;
	}
	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}
	public String getNeirong() {
		return neirong;
	}
	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}
	public String getChakanshu() {
		return chakanshu;
	}
	public void setChakanshu(String chakanshu) {
		this.chakanshu = chakanshu;
	}
	public String getPinglunshu() {
		return pinglunshu;
	}
	public void setPinglunshu(String pinglunshu) {
		this.pinglunshu = pinglunshu;
	}
	public String getDidian() {
		return didian;
	}
	public void setDidian(String didian) {
		this.didian = didian;
	}
	public String getYonghuid() {
		return yonghuid;
	}
	public void setYonghuid(String yonghuid) {
		this.yonghuid = yonghuid;
	}
	public String getZhuangtai() {
		return zhuangtai;
	}
	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	public String getTupian1() {
		return tupian1;
	}
	public void setTupian1(String tupian1) {
		this.tupian1 = tupian1;
	}
	public String getTupian2() {
		return tupian2;
	}
	public void setTupian2(String tupian2) {
		this.tupian2 = tupian2;
	}
	public String getTupian3() {
		return tupian3;
	}
	public void setTupian3(String tupian3) {
		this.tupian3 = tupian3;
	}
	public String getTupian4() {
		return tupian4;
	}
	public void setTupian4(String tupian4) {
		this.tupian4 = tupian4;
	}
	public String getTupian5() {
		return tupian5;
	}
	public void setTupian5(String tupian5) {
		this.tupian5 = tupian5;
	}
	public String getTupian6() {
		return tupian6;
	}
	public void setTupian6(String tupian6) {
		this.tupian6 = tupian6;
	}
	public String getJinghua() {
		return jinghua;
	}
	public void setJinghua(String jinghua) {
		this.jinghua = jinghua;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getYirubuluo() {
		return yirubuluo;
	}
	public void setYirubuluo(String yirubuluo) {
		this.yirubuluo = yirubuluo;
	}
	public String getYishoucang() {
		return yishoucang;
	}
	public void setYishoucang(String yishoucang) {
		this.yishoucang = yishoucang;
	}
	public String getBuluomingzi() {
		return buluomingzi;
	}
	public void setBuluomingzi(String buluomingzi) {
		this.buluomingzi = buluomingzi;
	}
	
}
