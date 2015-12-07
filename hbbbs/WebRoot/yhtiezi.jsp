<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bbs.util.Constant"%>
<%@ page import="com.bbs.util.HttpConnectionUtil,com.bbs.bean.Tiezi,java.net.URLEncoder,net.sf.json.JSONObject"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String tieziid = request.getParameter("tieziid");
	Tiezi t=null;
	if(tieziid!=null){
		//String yonghuid=URLEncoder.encode("1","UTF-8");
		String url = Constant.TIEAPI+"?yonghuid=1&tieziid="+tieziid;
		String json_data=HttpConnectionUtil.getData(url);
		JSONObject json=JSONObject.fromObject(json_data);
		Object data=json.get("jsonObj");
		if(data!=null && !String.valueOf(data).equals("")){
			JSONObject j=JSONObject.fromObject(data);
			if(j!=null){
				t=new Tiezi();
				t.setId(j.getString("id"));
				t.setBuluoid(j.getString("buluoid"));
				t.setBiaoti(j.getString("biaoti"));
				t.setLeixing(j.getString("leixing"));
				t.setNeirong(j.getString("neirong")!=null?j.getString("neirong").replaceAll("\\n\\r","").replaceAll("\\$\\$",""):null);
				t.setChakanshu(j.getString("chakanshu"));
				t.setPinglunshu(j.getString("pinglunshu"));
				t.setDidian(j.getString("didian"));
				t.setYonghuid(j.getString("yonghuid"));
				t.setZhuangtai(j.getString("zhuangtai"));
				t.setJinghua(j.getString("jinghua"));
				t.setCreated(j.getString("created"));
				t.setTupian1(j.getString("tupian1"));
				t.setTupian2(j.getString("tupian2"));
				t.setTupian3(j.getString("tupian3"));
				t.setTupian4(j.getString("tupian4"));
				t.setTupian5(j.getString("tupian5"));
				t.setTupian6(j.getString("tupian6"));
			}
		}
	}
	
	String imgurl=Constant.IMGURL;
	int imgIndex = 0;
%>

<!DOCTYPE html>
<html>
<head>
   <title>主题帖子</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no,minimal-ui">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="./rs/gft/css/Main.css" type="text/css">
    <link rel="stylesheet" href="./rs/gft/css/SharePost.css" type="text/css">
</head>

<body class="bodypage">

<!--绘本树App下 开始-->
<header id="head">

<img src="./rs/gft/img/banner.png" class="share_Logo">
<img src="./rs/gft/img/bannerBtn.png" class="share_btn">
</header>
<!--绘本树App下载 结束-->

<div id="body">
    <div class="innerlist">
<%if(t!=null && t.getId()!=null){ %>
<div class="Topics">
	<h1 id="TextTitlel"><%=t.getBiaoti()%></h1>
    
    <!--用户头像信息 开始-->
    <div class="list">
		<ul class="info row">
			<li class="left">
                <img src="./rs/gft/img/pic_userHead_1.jpg">
                <span class="MameStyle">绘本树</span>
                <span class="TimeStyle"><%=t.getCreated().substring(0,10) %></span>
            </li>
			<li class="right">
				<img src="./rs/gft/img/BtnAdd@3x.png"><span><%=t.getDidian() %></span>
            </li>
            
		</ul>
	</div>
    <!--用户头像信息 结束-->
    
    <div id="div_centent">
    	<%if(t.getNeirong()!=null){ %>
    		<p><%=t.getNeirong() %></p>
    	<%} imgIndex++;%>
    	<%if(t.getTupian1()!=null && !t.getTupian1().equals("")){ %>
    		<img class="photo" src="<%=imgurl+t.getTupian1() %>">
    	<%} imgIndex++;%>
    	<%if(t.getTupian2()!=null && !t.getTupian2().equals("")){ %>
    		<img class="photo" src="<%=imgurl+t.getTupian2() %>">
    	<%} imgIndex++;%>
    	<%if(t.getTupian3()!=null && !t.getTupian3().equals("")){ %>
    		<img class="photo" src="<%=imgurl+t.getTupian3() %>">
    	<%} imgIndex++;%>
    	<%if(t.getTupian4()!=null && !t.getTupian4().equals("")){ %>
    		<img class="photo" src="<%=imgurl+t.getTupian4() %>">
    	<%} imgIndex++;%>
    	<%if(t.getTupian5()!=null && !t.getTupian5().equals("")){ %>
    		<img class="photo" src="<%=imgurl+t.getTupian5() %>">
    	<%} imgIndex++;%>
    	<%if(t.getTupian6()!=null && !t.getTupian6().equals("")){ %>
    		<img class="photo" src="<%=imgurl+t.getTupian6() %>">
    	<%}imgIndex++;%>
    </div>
    
    <!-- 
    <p id="TextContent">首先上宣传图大家看看！</p>
    <img class="photo" src="./rs/gft/img/1.jpg">
    <p>大家好，今天Janice老师和大家分享的绘本故事是“Whose Food Is This?”绘本故事书的魅力在于没有语言的隔阂，通过图片就能够了解故事的大意。来听一下Janice老师用英文讲中文故事吧！</p>
     -->
    
    <!--读分享 开始
    <div class="ShareRead">
    	<img src="img/Read1.png">
		<div class="ReadText">Rock-A-Bye Baby</div>
        <div class="ReadPlay"></div>
    </div>-->
    <!--读分享 结束-->
    
    <!--看分享 开始
    <div class="ShareSee">
    	<img src="img/See5.png">
		<div class="SeeText">Rock-A-Bye Baby</div>
        <div class="SeePlay"></div>
    </div>-->
    <!--看分享 结束-->
    
    <!--听分享 开始
    <div class="ShareListen">
    	<img src="img/ListenSmall1.png">
		<div class="ListenText">Rock-A-Bye Baby</div>
        <div class="ListenPlay"></div>
    </div>-->
    <!--听分享 结束-->
    
    <!--热门推荐贴 开始
    <div class="SharePost">
    	<img src="img/pic_userHead.jpg">
		<div class="PostText">美国校长Janice读故事：Whose Food Is Thie?</div>
        <div class="PostPlay"></div>
    </div>-->
    <!--热门推荐贴 结束-->
    
    <!--热门推荐贴 开始
    <div class="SharePost">
    	<img src="img/pic_userHead_1.jpg">
		<div class="PostText">美国校长Janice读故事：Whose Food Is Thie?</div>
        <div class="PostPlay"></div>
    </div>-->
    <!--热门推荐贴 结束-->
    
    <!-- 
	<p>下面分享一下这本绘本的微店链接，喜欢的朋友们可以在线购买哦！</p>
    <a href="#" target="_blank"><img class="photo" src="./rs/gft/img/menu_list-5.png"></a>
    <img class="photo" src="./rs/gft/img/3.png"> -->
	
</div>
<%} %>
<!--用户回复 开始-->
<div class="Topics">
	<div class="list" style="display:none">
		<ul class="row">
			<li class="left">
				<img src="./rs/gft/img/pic_userHead.jpg">
				<span>LuLu</span>
				<span class="TimeStyle">10月10日</span>
			</li>
			<li class="Reply">
				<img src="./rs/gft/img/icon_pl.png">
			</li>
		</ul>
	</div>
        
	<div class="list"   style="display:none">
		<ul class="row">
			<li class="left">
				<img src="./rs/gft/img/pic_userHead.jpg">
				<span>LuLu</span>
				<span class="TimeStyle">10月10日</span>
			</li>
			<li class="Reply">
				<img src="./rs/gft/img/icon_pl.png">
			</li>
		</ul>
	</div> 

</div>
<!--用户回复 开始-->

	</div>
</div>
</body>
<script src="./rs/gft/js/jquery-1.9.1.min.js"></script>
<script src="./rs/gft/js/iscroll.js"></script>
<script>
    $(function(){
    	var isComplete=<%=imgIndex%>;
        var index_ = 0;
		var setInte=setInterval(function(){
    		if(isComplete==7){
    			//列表滚动
    			new iScroll($('.innerlist')[0], {desktopCompatibility:true});
    			clearInterval(setInte);
    		}else if(index_ === 20){
    			clearInterval(setInte);
    		}
    		index_++;
    	}, 100);
    });

</script>

</html>