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
			}
		}
	}
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
				<img src="./rs/gft/img/BtnAdd@3x.png"><span>上海市</span>
            </li>
            
		</ul>
	</div>
    <!--用户头像信息 结束-->
    
    <div id="div_centent">
    </div>
    
    <script type="text/javascript">
	     		
	     		//alert(0);
	     	</script>
    
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
    	var isComplete = false;
   		var neirong="<%=t.getNeirong()%>";
	     		var div_centent=document.getElementById("div_centent");
	     		if(neirong!=null && neirong.length>0){
	     			var arr=new Array();
	     			var txt="";
	     			var biaoqian="";
	     			var html="";
	     			var neirong2=neirong;
	     			for(var i=0;i<neirong.length;i++){
	     				var j=i+1;
	     				var vin=neirong.substring(i,j);
	     				if(vin=='['){
	     				
	     					if(neirong2.substring(0,5)=='[img]'){
	     						//不为空添加文本
		     					if(txt!=''){
	  								div_centent.innerHTML=div_centent.innerHTML+'<p>'+txt+'</p>';
		     						txt='';
		     					}
	     						var end=neirong2.indexOf('[/img]');
	     						var img=neirong2.substring(5,end);
	     						
	     						var end_ititle=img.indexOf('[/ititle]');
	     						var ititle=img.substring(8,end_ititle);
	     						
	     						var end_iurl=img.indexOf('[/iurl]');
	     						var iurl=img.substring(end_ititle+9+6,end_iurl);
	     						
	     						div_centent.innerHTML=div_centent.innerHTML+'<img class="photo" title="'+ititle+'" alt="'+ititle+'" src="'+iurl+'"/>';
	     						neirong2=neirong2.substring(end+6,neirong2.length);
	     					}else if(neirong2.substring(0,7)=='[video]'){
	     						//不为空添加文本
		     					if(txt!=''){
	  								div_centent.innerHTML=div_centent.innerHTML+'<p>'+txt+'</p>';
		     						txt='';
		     					}
	     					
	     						var end=neirong2.indexOf('[/video]');
	     						var video=neirong2.substring(7,end);
	     						
	     						var end_vimg=video.indexOf('[/vimg]');
	     						var vimg=video.substring(6,end_vimg);
	     						
	     						var end_vtitle=video.indexOf('[/vtitle]');
	     						var vtitle=video.substring(end_vimg+7+8,end_vtitle);
	     						
	     						var end_vurl=video.indexOf('[/vurl]');
	     						var vurl=video.substring(end_vtitle+9+6,end_vurl);
	     						
	     						var end_vid=video.indexOf('[/vid]');
	     						var vid=video.substring(end_vurl+7+5,end_vid);
	     						
    
    							div_centent.innerHTML=div_centent.innerHTML+'<div class="ShareSee">'
    							+'<img title="'+vtitle+'" alt="'+vtitle+'"  src="'+vimg+'"/>'
    							+'<div class="SeeText">'+vtitle+'</div>'
    							+'<div class="SeePlay" onclick="javascript:window.open(\''+vurl+'\')"></div></div>';
	     						
					  	    	neirong2=neirong2.substring(end+8,neirong2.length);
	     					}else if(neirong2.substring(0,7)=='[audio]'){
	     						//不为空添加文本
		     					if(txt!=''){
	  								div_centent.innerHTML=div_centent.innerHTML+'<p>'+txt+'</p>';
		     						txt='';
		     					}
	     					
	     						var end=neirong2.indexOf('[/audio]');
	     						var audio=neirong2.substring(7,end);
	     						
	     						var end_aimg=audio.indexOf('[/aimg]');
	     						var aimg=audio.substring(6,end_aimg);
	     						
	     						var end_atitle=audio.indexOf('[/atitle]');
	     						var atitle=audio.substring(end_aimg+7+8,end_atitle);
	     						
	     						var end_aurl=audio.indexOf('[/aurl]');
	     						var aurl=audio.substring(end_atitle+9+6,end_aurl);
	     						
	     						var end_aid=audio.indexOf('[/aid]');
	     						var aid=audio.substring(end_aurl+7+5,end_aid);
	     						
    							div_centent.innerHTML=div_centent.innerHTML+'<div class="ShareListen">'
    							+'<img title="'+atitle+'" alt="'+atitle+'" src="'+aimg+'"/>'
    							+'<div class="ListenText">'+atitle+'</div>'
    							+'<div class="ListenPlay" onclick="javascript:window.open(\''+aurl+'\')"></div></div>';
    							
					  	    	neirong2=neirong2.substring(end+8,neirong2.length);
	     					}else if(neirong2.substring(0,6)=='[read]'){
	     						//不为空添加文本
		     					if(txt!=''){
	  								div_centent.innerHTML=div_centent.innerHTML+'<p>'+txt+'</p>';
		     						txt='';
		     					}
	     					
	     						var end=neirong2.indexOf('[/read]');
	     						var read=neirong2.substring(6,end);
	     						
	     						var end_rimg=read.indexOf('[/rimg]');
	     						var rimg=read.substring(6,end_rimg);
	     						
	     						var end_rtitle=read.indexOf('[/rtitle]');
	     						var rtitle=read.substring(end_rimg+7+8,end_rtitle);
	     						
	     						var end_rurl=read.indexOf('[/rurl]');
	     						var rurl=read.substring(end_rtitle+9+6,end_rurl);
	     						
	     						var end_rid=read.indexOf('[/rid]');
	     						var rid=read.substring(end_rurl+7+5,end_rid);
	     						
	     						div_centent.innerHTML=div_centent.innerHTML+'<div class="ShareRead">'
	     						+'<img title="'+rtitle+'" alt="'+rtitle+'" src="'+rimg+'"/>'
	     						+'<div class="ReadText">'+rtitle+'</div>'
	     						+'<div class="ReadPlay" onclick="javascript:window.open(\''+rurl+'\')"></div></div>';
	     						
					  	    	neirong2=neirong2.substring(end+7,neirong2.length);
	     					}else if(neirong2.substring(0,6)=='[shop]'){
	     						//不为空添加文本
		     					if(txt!=''){
	  								div_centent.innerHTML=div_centent.innerHTML+'<p>'+txt+'</p>';
		     					}
	     					
	     						var end=neirong2.indexOf('[/shop]');
	     						var shop=neirong2.substring(6,end);
	     						
	     						var end_simg=shop.indexOf('[/simg]');
	     						var simg=shop.substring(6,end_simg);
	     						
	     						var end_stitle=shop.indexOf('[/stitle]');
	     						var stitle=shop.substring(end_simg+7+8,end_stitle);
	     						
	     						var end_surl=shop.indexOf('[/surl]');
	     						var surl=shop.substring(end_stitle+9+6,end_surl);
	     						
	     						var end_sid=shop.indexOf('[/sid]');
	     						var sid=shop.substring(end_surl+7+5,end_sid);
	     						
	     						div_centent.innerHTML=div_centent.innerHTML+'<p>'+stitle+'</p>'
	     						+'<a href="'+surl+'" target="_blank">'
	     						+'<img class="photo" title="'+stitle+'" alt="'+stitle+'"  src="'+simg+'"></a>';
					  	    	
					  	    	neirong2=neirong2.substring(end+7,neirong2.length);
	     					}else if(neirong2.substring(0,6)=='[link]'){
	     							//不为空添加文本
		     					if(txt!=''){
	  								div_centent.innerHTML=div_centent.innerHTML+'<p>'+txt+'</p>';
		     					}
	     					
	     						var end=neirong2.indexOf('[/link]');
	     						var link=neirong2.substring(6,end);
	     						
	     						var end_limg=link.indexOf('[/limg]');
	     						var limg=link.substring(6,end_limg);
	     						
	     						var end_ltitle=link.indexOf('[/ltitle]');
	     						var ltitle=link.substring(end_limg+7+8,end_ltitle);
	     						
	     						var end_lurl=link.indexOf('[/lurl]');
	     						var lurl=link.substring(end_ltitle+9+6,end_lurl);
	     						
	     						var end_lid=link.indexOf('[/lid]');
	     						var lid=link.substring(end_lurl+7+5,end_lid);
	     						
					 	    	div_centent.innerHTML=div_centent.innerHTML+'<div class="SharePost">'
	     						+'<img title="'+ltitle+'" alt="'+ltitle+'" src="'+limg+'"/>'
	     						+'<div class="PostText">'+ltitle+'</div>'
	     						+'<div class="PostPlay" onclick="javascript:window.open(\''+lurl+'\')"></div></div>';
					  	    	
					  	    	neirong2=neirong2.substring(end+7,neirong2.length);
	     					
	     					}else{
	     						txt+=vin;
	     						neirong2=neirong2.substring(1,neirong2.length);
	     						continue;
	     					}
     						i=neirong.length-neirong2.length-1;
     						continue;
	     				}else{
	     					txt+=vin;
	     					neirong2=neirong2.substring(1,neirong2.length);
	     				}
		     			if(i==neirong.length-1 && txt!=''){
							div_centent.innerHTML=div_centent.innerHTML+'<p>'+txt+'</p>';
	   						txt='';
	   					}
	     			}
	     		isComplete = true;
	     		}
	     	var index_ = 0;
	     	var setInte=setInterval(function(){
	     		if(isComplete){
	     			//列表滚动
	     			new iScroll($('.innerlist')[0], {desktopCompatibility:true});
	     			clearInterval(setInte);
	     		}else if(index_ === 20){
	     			clearInterval(setInte);
	     		}
	     		index_++;
	     	}, 100);
        

       // $('[name=huifu]').click(function(){
        //    $('[name=huifutxt]').focus();
       // });

    });

</script>

</html>