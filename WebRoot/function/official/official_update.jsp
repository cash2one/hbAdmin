<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.manager.util.Constant"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String version=Constant.version;
	String appid=Constant.APPID;
	String[] neirong=null;
	if(request.getAttribute("neirong")!=null){
		neirong=(String[]) request.getAttribute("neirong");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  <jsp:include page="/include/base.jsp" />
  <jsp:include page="/include/date.jsp" />
   <link href="<%=basePath %>js/uploadify.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="<%=basePath %>js/swfobject.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/jquery.uploadify.v2.1.4.min.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/jquery.uploadify.v2.1.0.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/jquery.form.min.js"></script>
  <script type="text/javascript">
  
  $(function () {
        $("#uploadify1").uploadify({
		   'uploader'       : '<%=basePath %>js/uploadify.swf',
		   'script'         : '<%=basePath %>scripts/uploadify',//servlet的路径或者.jsp 这是访问servlet 'scripts/uploadif' 
		   'method'         : 'GET',  //如果要传参数，就必须改为GET
		   'cancelImg'      : '<%=basePath %>js/cancel.png',
		   'folder'         : 'uploads', //要上传到的服务器路径，
		   'queueID'        : 'fileQueue1',
		   'auto'           : false, //选定文件后是否自动上传，默认false
		   'multi'          : true, //是否允许同时上传多文件，默认false
		   'simUploadLimit' : 1, //一次同步上传的文件数目  
		   'sizeLimit'      : 19871202, //设置单个文件大小限制，单位为byte  
		   'queueSizeLimit' : 1, //限制在一次队列中的次数（可选定几个文件）。默认值= 999，而一次可传几个文件有 simUploadLimit属性决定。
		   'fileDesc'       : '支持格式:jpg或gif或png', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
		   'fileExt'        : '*.jpg;*.gif;*.png',//允许的格式
		   'scriptData'     :{'name':'unlimited'},
		   　onComplete: function (event, queueID, fileObj, response, data) {
		    	var value = response ;
			  		$("#img_url").val(value); 
			  		$("#see_img").attr("src",value);  
	                $("#see_img").show();
		   　},  
		   　onError: function(event, queueID, fileObj) {  
		   　 alert("文件:" + fileObj.name + "上传失败");  
		   　},  
		   　onCancel: function(event, queueID, fileObj){  
		   　 alert("取消了" + fileObj.name);  
		   　　　} 
		  });
		  
    })
    
    
    
  function uploasFile(num){
 	  //上传
 	  jQuery('#uploadify'+num).uploadifyUpload();
  }
  
  function updateOfficial(){
  	var id=$("#id").val();
  	var yonghuid=$("#yonghuid").val();
  	var buluoid=$("#buluoid").val();
  	var biaoti=$("#biaoti").val();
 	var str="";
  	$(".neirong").each(function(index,element){
  		var nm=$(element).attr("name");
  		var va=$(element).val();
  		if(nm=='ct_txt'){
  			str+=va+"$$";
  		}
  		if(nm=="ct_img_title"){
  			str+="[img][ititle]"+va+"[/ititle]";
  		}
  		if(nm=="ct_img_url"){
  			str+="[iurl]"+va+"[/iurl][/img]$$";
  		}
  		
  		if(nm=='ct_kan_img'){
  			str+="[video][vimg]"+va+"[/vimg]";
  		}
  		if(nm=='ct_kan_title'){
  			str+="[vtitle]"+va+"[/vtitle]";
  		}
  		if(nm=="ct_kan_url"){
  			str+="[vurl]"+va+"[/vurl]";
  		}
  		if(nm=="ct_kan_id"){
  			str+="[vid]"+va+"[/vid][/video]$$";
  		}
  		
  		if(nm=='ct_ting_img'){
  			str+="[audio][aimg]"+va+"[/aimg]";
  		}
  		if(nm=='ct_ting_title'){
  			str+="[atitle]"+va+"[/atitle]";
  		}
  		if(nm=="ct_ting_url"){
  			str+="[aurl]"+va+"[/aurl]";
  		}
  		if(nm=="ct_ting_id"){
  			str+="[aid]"+va+"[/aid][/audio]$$";
  		}
  		
  		
  		if(nm=='ct_du_img'){
  			str+="[read][rimg]"+va+"[/rimg]";
  		}
  		if(nm=='ct_du_title'){
  			str+="[rtitle]"+va+"[/rtitle]";
  		}
  		if(nm=="ct_du_url"){
  			str+="[rurl]"+va+"[/rurl]";
  		}
  		if(nm=="ct_du_id"){
  			str+="[rid]"+va+"[/rid][/read]$$";
  		}
  		
  		if(nm=='ct_sc_img'){
  			str+="[shop][simg]"+va+"[/simg]";
  		}
  		if(nm=='ct_sc_title'){
  			str+="[stitle]"+va+"[/stitle]";
  		}
  		if(nm=="ct_sc_url"){
  			str+="[surl]"+va+"[/surl]";
  		}
  		if(nm=="ct_sc_id"){
  			str+="[sid]"+va+"[/sid][/shop]$$";
  		}
  		
  		if(nm=='ct_wl_img'){
  			str+="[link][limg]"+va+"[/limg]";
  		}
  		if(nm=='ct_wl_title'){
  			str+="[ltitle]"+va+"[/ltitle]";
  		}
  		if(nm=="ct_wl_url"){
  			str+="[lurl]"+va+"[/lurl]";
  		}
  		if(nm=="ct_wl_id"){
  			str+="[lid]"+va+"[/lid][/link]$$";
  		}
  	});
  	
  	var but=document.getElementById('close');
		but.disabled=true;
		but.value='修改中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>official/doupdateofficial",
             data: {
             		'id':id,
             		'yonghuid':yonghuid,
             		'buluoid':buluoid,
             		'biaoti':biaoti,
             		'neirong':str
             	   },
             dataType: "json",
             async:	false,
             success: function(data,textStatus){
	             	var msg=data.res;
	             	if(msg['jsonObj']!=null && msg['jsonObj'][0]['result']=='1'){
	             		alert("修改成功");
	             	}else{
	             	 	alert("修改失败："+msg);
	             	}
             },
             error: function(XMLHttpRequest, textStatus, errorThrown){
             	alert("异常:"+errorThrown);
             }
         });
       but.disabled=false;
	   but.value=' 修 改 ';
  }
  
  function addText(){
  		var arr=new Array();
  		$(".neirong").each(function(index,element){
  			arr[index]=$(element).val();
  		});
  		var div_centent=document.getElementById("div_centent");
  		div_centent.innerHTML=div_centent.innerHTML+'<li name="li_txt"><label>文本：<b>*</b></label><input type="text" id="" name="ct_txt" value="" class="dfinput neirong" /></li>';
  		
  		$(".neirong").each(function(index,element){
  			$(element).val(arr[index]);
  		});
  }

  function addImg1(){
  		var arr=new Array();
  		$(".neirong").each(function(index,element){
  			arr[index]=$(element).val();
  		});
 		var div_centent=document.getElementById("div_centent");
 		div_centent.innerHTML=div_centent.innerHTML+'<li name="li_img"><label>图片：<b>*</b></label>'
 		+'标题：<input type="text" id="" name="ct_img_title" value="" class="dfinput200 neirong" />'
 		+'图片地址：<input type="text" id="" name="ct_img_url" value="" class="dfinput200 neirong" /></li>';
  		$(".neirong").each(function(index,element){
  			$(element).val(arr[index]);
  		});
  }
  
  function addziyuan(num){
  		var arr=new Array();
  		$(".neirong").each(function(index,element){
  			arr[index]=$(element).val();
  		});
  	    var div_centent=document.getElementById("div_centent");
  	    if(num=='1'){
  	    	div_centent.innerHTML=div_centent.innerHTML+'<li name="li_kan"><label>看：<b>*</b></label>'
  	    	+'图片地址：<input type="text" id="" name="ct_kan_img" value="" class="dfinput200 neirong" />'
  	    	+'标题：<input type="text" id="" name="ct_kan_title" value="" class="dfinput200 neirong" />'
  	    	+'链接：<input type="text" id="" name="ct_kan_url" value="" class="dfinput200 neirong" />'
  	    	+'ID：<input type="text" id="" name="ct_kan_id" value="" class="dfinput200 neirong" />'
  	    	+'</li>';
  	    }else if(num=='2'){
  	    	div_centent.innerHTML=div_centent.innerHTML+'<li name="li_ting"><label>听：<b>*</b></label>'
  	    	+'图片地址：<input type="text" id="" name="ct_ting_img" value="" class="dfinput200 neirong" />'
  	    	+'标题：<input type="text" id="" name="ct_ting_title" value="" class="dfinput200 neirong" />'
  	    	+'链接：<input type="text" id="" name="ct_ting_url" value="" class="dfinput200 neirong" />'
  	    	+'ID：<input type="text" id="" name="ct_ting_id" value="" class="dfinput200 neirong" />'
  	    	+'</li>';
  	    }else if(num=='3'){
  	    	div_centent.innerHTML=div_centent.innerHTML+'<li name="li_du"><label>读：<b>*</b></label>'
  	    	+'图片地址：<input type="text" id="" name="ct_du_img" value="" class="dfinput200 neirong" />'
  	    	+'标题：<input type="text" id="" name="ct_du_title" value="" class="dfinput200 neirong" />'
  	    	+'链接：<input type="text" id="" name="ct_du_url" value="" class="dfinput200 neirong" />'
  	    	+'ID：<input type="text" id="" name="ct_du_id" value="" class="dfinput200 neirong" />'
  	    	+'</li>';
  	    }else if(num=='4'){
  	    	div_centent.innerHTML=div_centent.innerHTML+'<li name="li_sc"><label>商城：<b>*</b></label>'
 	    	+'图片地址：<input type="text" id="" name="ct_sc_img" value="" class="dfinput200 neirong" />'
 	    	+'标题：<input type="text" id="" name="ct_sc_title" value="" class="dfinput200 neirong" />'
 	    	+'链接：<input type="text" id="" name="ct_sc_url" value="" class="dfinput200 neirong" />'
 	    	+'ID：<input type="text" id="" name="ct_sc_id" value="" class="dfinput200 neirong" />'
 	    	+'</li>';
  	    }else if(num=='5'){
  	    	div_centent.innerHTML=div_centent.innerHTML+'<li name="li_sc"><label>外链：<b>*</b></label>'
 	    	+'图片地址：<input type="text" id="" name="ct_wl_img" value="" class="dfinput200 neirong" />'
 	    	+'标题：<input type="text" id="" name="ct_wl_title" value="" class="dfinput200 neirong" />'
 	    	+'链接：<input type="text" id="" name="ct_wl_url" value="" class="dfinput200 neirong" />'
 	    	+'ID：<input type="text" id="" name="ct_wl_id" value="" class="dfinput200 neirong" />'
 	    	+'</li>';
  	    }
  	    $(".neirong").each(function(index,element){
  			$(element).val(arr[index]);
  		});
  }
  </script>
  </head>
  
  <body>
  <!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath%>index.jsp">首页</a></li>
	   <li>官方贴管理</li>
	    <li>官方贴修改</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	  	<li><a href="<%=basePath %>official/doofficiallist">官方贴查询</a></li> 
	    <li><a  class="selected">官方贴修改</a></li> 
	  	</ul>
	</div> 
	
	<div id="tab1" class="tabson">
	    <form method="post" id="myform" action="<%=basePath%>domain">
	    <ul class="forminfo">
	    	<li><label>帖子ID：<b>*</b></label><input type="text" id="id" name="id" value="${tie.id }" class="dfinput" readonly="readonly"/></li>
		    <li><label>发帖用户ID：<b>*</b></label><input type="text" id="yonghuid" name="yonghuid" value="${tie.yonghuid }" class="dfinput"/></li>
		    <li><label>部落ID：<b>*</b></label><input type="text" id="buluoid" name="buluoid" value="${tie.buluoid }" class="dfinput"/></li>
		    <li><label>帖子标题：<b>*</b></label><input type="text" id="biaoti" name="biaoti" value="${tie.biaoti }" class="dfinput" /></li>
		    <li><label>上传图片：<b></b></label>
				<div style="padding-left:118px;">
				<input type="text" id="img_url" name="img_url" value="" class="dfinput" />
				<div id="fileQueue1"></div> </br>
					<input type="file" name="uploadify1" id="uploadify1" />
					<p>
						<a href="javascript:uploasFile(1)">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify1').uploadifyClearQueue()">取消所有</a>
					</p>
				</div>
				<div style="padding-left:118px;">
					<img  style="display: none" id = "see_img" src="" width="150" height="90"/>
				</div>
			</li>
		    <li><label>功能：<b>*</b></label>
		    	<input type="button" value="添加文本" name="addTxt" id="addTxt" onclick="addText()" class="btn"/>
		    	<input type="button" value="添加图片" name="addImg" id="addImg" onclick="addImg1();" class="btn"/>
		    	<input type="button" value="添加看" name="addkan" id="addkan" onclick="addziyuan(1);" class="btn"/>
		    	<input type="button" value="添加听" name="addting" id="addting" onclick="addziyuan(2);" class="btn"/>
		    	<input type="button" value="添加读" name="adddu" id="adddu" onclick="addziyuan(3);" class="btn"/>
		    	<input type="button" value="添加商城" name="addsc" id="addsc" onclick="addziyuan(4);" class="btn"/>
		     </li>
		     <div id="div_centent">
		     </div>
	     	<script type="text/javascript">
	     		var div_centent=document.getElementById("div_centent");
	     		//if(neirong!=null && neirong.length>0){
	     			
	     			//for(var i=0;i<neirong.length;i++){
 					//alert(1);
	     			<% 
					if(neirong!=null && neirong.length>0){
					for(int i=0;i<neirong.length;i++){ 
 					%>
 					var neirong2="<%=neirong[i] %>";
 				
	     			
	     				//var neirong2=neirong[i];
	     				//alert(neirong2);
	     				if(neirong2 != null && neirong2 != ""){
	     					var vin=neirong2.substring(0,1);
	     					if(vin=='['){
	     						if(neirong2.substring(0,5)=='[img]'){
		     					
			     					var end=neirong2.indexOf('[/img]');
		     						var img=neirong2.substring(5,end);
		     						
		     						var end_ititle=img.indexOf('[/ititle]');
		     						var ititle=img.substring(8,end_ititle);
		     						
		     						var end_iurl=img.indexOf('[/iurl]');
		     						var iurl=img.substring(end_ititle+9+6,end_iurl);
			     					
		     						div_centent.innerHTML=div_centent.innerHTML+'<li name="li_img"><label>图片：<b>*</b></label>'
		     						+'标题：<input type="text" id="" name="ct_img_title" value="'+ititle+'" class="dfinput200 neirong" />'
	 								+'图片地址：<input type="text" id="" name="ct_img_url" value="'+iurl+'" class="dfinput200 neirong" /></li>';
		     					}else if(neirong2.substring(0,7)=='[video]'){
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
		     						
		     						div_centent.innerHTML=div_centent.innerHTML+'<li name="li_kan"><label>看：<b>*</b></label>'
						  	    	+'图片地址：<input type="text" id="" name="ct_kan_img" value="'+vimg+'" class="dfinput200 neirong" />'
						  	    	+'标题：<input type="text" id="" name="ct_kan_title" value="'+vtitle+'" class="dfinput200 neirong" />'
						  	    	+'链接：<input type="text" id="" name="ct_kan_url" value="'+vurl+'" class="dfinput200 neirong" />'
						  	    	+'ID：<input type="text" id="" name="ct_kan_id" value="'+vid+'" class="dfinput200 neirong" />'
						  	    	+'</li>';
						  	    	
		     					}else if(neirong2.substring(0,7)=='[audio]'){
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
		     						
		     						div_centent.innerHTML=div_centent.innerHTML+'<li name="li_ting"><label>听：<b>*</b></label>'
						  	    	+'图片地址：<input type="text" id="" name="ct_ting_img" value="'+aimg+'" class="dfinput200 neirong" />'
						  	    	+'标题：<input type="text" id="" name="ct_ting_title" value="'+atitle+'" class="dfinput200 neirong" />'
						  	    	+'链接：<input type="text" id="" name="ct_ting_url" value="'+aurl+'" class="dfinput200 neirong" />'
						  	    	+'ID：<input type="text" id="" name="ct_ting_id" value="'+aid+'" class="dfinput200 neirong" />'
						  	    	+'</li>';
						  	    	
		     					}else if(neirong2.substring(0,6)=='[read]'){
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
		     						
		     						div_centent.innerHTML=div_centent.innerHTML+'<li name="li_du"><label>读：<b>*</b></label>'
						  	    	+'图片地址：<input type="text" id="" name="ct_du_img" value="'+rimg+'" class="dfinput200 neirong" />'
						  	    	+'标题：<input type="text" id="" name="ct_du_title" value="'+rtitle+'" class="dfinput200 neirong" />'
						  	    	+'链接：<input type="text" id="" name="ct_du_url" value="'+rurl+'" class="dfinput200 neirong" />'
						  	    	+'ID：<input type="text" id="" name="ct_du_id" value="'+rid+'" class="dfinput200 neirong" />'
						  	    	+'</li>';
		     					}else if(neirong2.substring(0,6)=='[shop]'){
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
		     						
		     						div_centent.innerHTML=div_centent.innerHTML+'<li name="li_sc"><label>商城：<b>*</b></label>'
						 	    	+'图片地址：<input type="text" id="" name="ct_sc_img" value="'+simg+'" class="dfinput200 neirong" />'
						 	    	+'标题：<input type="text" id="" name="ct_sc_title" value="'+stitle+'" class="dfinput200 neirong" />'
						 	    	+'链接：<input type="text" id="" name="ct_sc_url" value="'+surl+'" class="dfinput200 neirong" />'
						 	    	+'ID：<input type="text" id="" name="ct_sc_id" value="'+sid+'" class="dfinput200 neirong" />'
						 	    	+'</li>';
		     					}else if(neirong2.substring(0,6)=='[link]'){
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
		     						
		     						div_centent.innerHTML=div_centent.innerHTML+'<li name="li_wl"><label>外链：<b>*</b></label>'
						 	    	+'图片地址：<input type="text" id="" name="ct_wl_img" value="'+limg+'" class="dfinput200 neirong" />'
						 	    	+'标题：<input type="text" id="" name="ct_wl_title" value="'+ltitle+'" class="dfinput200 neirong" />'
						 	    	+'链接：<input type="text" id="" name="ct_wl_url" value="'+lurl+'" class="dfinput200 neirong" />'
						 	    	+'ID：<input type="text" id="" name="ct_wl_id" value="'+lid+'" class="dfinput200 neirong" />'
						 	    	+'</li>';
						  	    	
		     					}
	     					}else{
		     					div_centent.innerHTML=div_centent.innerHTML+'<li name="li_txt"><label>文本：<b>*</b></label><input type="text" id="" name="ct_txt" value="'+neirong2+'" class="dfinput neirong" /></li>';
	     					}
	     				}
	     			
	     			//}
	     			<%} }%>
	     			
	     			/*
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
	  								div_centent.innerHTML=div_centent.innerHTML+'<li name="li_txt"><label>文本：<b>*</b></label><input type="text" id="" name="ct_txt" value="'+txt+'" class="dfinput neirong" /></li>';
		     						txt='';
		     					}
		     					
		     					var end=neirong2.indexOf('[/img]');
	     						var img=neirong2.substring(5,end);
	     						
	     						var end_ititle=img.indexOf('[/ititle]');
	     						var ititle=img.substring(8,end_ititle);
	     						
	     						var end_iurl=img.indexOf('[/iurl]');
	     						var iurl=img.substring(end_ititle+9+6,end_iurl);
		     					
	     						div_centent.innerHTML=div_centent.innerHTML+'<li name="li_img"><label>图片：<b>*</b></label>'
	     						+'标题：<input type="text" id="" name="ct_img_title" value="'+ititle+'" class="dfinput200 neirong" />'
 								+'图片地址：<input type="text" id="" name="ct_img_url" value="'+iurl+'" class="dfinput200 neirong" /></li>';
	     						neirong2=neirong2.substring(end+6,neirong2.length);
	     					}else if(neirong2.substring(0,7)=='[video]'){
	     						//不为空添加文本
		     					if(txt!=''){
	  								div_centent.innerHTML=div_centent.innerHTML+'<li name="li_txt"><label>文本：<b>*</b></label><input type="text" id="" name="ct_txt" value="'+txt+'" class="dfinput neirong" /></li>';
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
	     						
	     						div_centent.innerHTML=div_centent.innerHTML+'<li name="li_kan"><label>看：<b>*</b></label>'
					  	    	+'图片地址：<input type="text" id="" name="ct_kan_img" value="'+vimg+'" class="dfinput200 neirong" />'
					  	    	+'标题：<input type="text" id="" name="ct_kan_title" value="'+vtitle+'" class="dfinput200 neirong" />'
					  	    	+'链接：<input type="text" id="" name="ct_kan_url" value="'+vurl+'" class="dfinput200 neirong" />'
					  	    	+'ID：<input type="text" id="" name="ct_kan_id" value="'+vid+'" class="dfinput200 neirong" />'
					  	    	+'</li>';
					  	    	
					  	    	neirong2=neirong2.substring(end+8,neirong2.length);
	     					}else if(neirong2.substring(0,7)=='[audio]'){
	     						//不为空添加文本
		     					if(txt!=''){
	  								div_centent.innerHTML=div_centent.innerHTML+'<li name="li_txt"><label>文本：<b>*</b></label><input type="text" id="" name="ct_txt" value="'+txt+'" class="dfinput neirong" /></li>';
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
	     						
	     						div_centent.innerHTML=div_centent.innerHTML+'<li name="li_ting"><label>听：<b>*</b></label>'
					  	    	+'图片地址：<input type="text" id="" name="ct_ting_img" value="'+aimg+'" class="dfinput200 neirong" />'
					  	    	+'标题：<input type="text" id="" name="ct_ting_title" value="'+atitle+'" class="dfinput200 neirong" />'
					  	    	+'链接：<input type="text" id="" name="ct_ting_url" value="'+aurl+'" class="dfinput200 neirong" />'
					  	    	+'ID：<input type="text" id="" name="ct_ting_id" value="'+aid+'" class="dfinput200 neirong" />'
					  	    	+'</li>';
					  	    	
					  	    	neirong2=neirong2.substring(end+8,neirong2.length);
	     					}else if(neirong2.substring(0,6)=='[read]'){
	     						//不为空添加文本
		     					if(txt!=''){
	  								div_centent.innerHTML=div_centent.innerHTML+'<li name="li_txt"><label>文本：<b>*</b></label><input type="text" id="" name="ct_txt" value="'+txt+'" class="dfinput neirong" /></li>';
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
	     						
	     						div_centent.innerHTML=div_centent.innerHTML+'<li name="li_du"><label>读：<b>*</b></label>'
					  	    	+'图片地址：<input type="text" id="" name="ct_du_img" value="'+rimg+'" class="dfinput200 neirong" />'
					  	    	+'标题：<input type="text" id="" name="ct_du_title" value="'+rtitle+'" class="dfinput200 neirong" />'
					  	    	+'链接：<input type="text" id="" name="ct_du_url" value="'+rurl+'" class="dfinput200 neirong" />'
					  	    	+'ID：<input type="text" id="" name="ct_du_id" value="'+rid+'" class="dfinput200 neirong" />'
					  	    	+'</li>';
					  	    	neirong2=neirong2.substring(end+7,neirong2.length);
	     					}else if(neirong2.substring(0,6)=='[shop]'){
	     						//不为空添加文本
		     					if(txt!=''){
	  								div_centent.innerHTML=div_centent.innerHTML+'<li name="li_txt"><label>文本：<b>*</b></label><input type="text" id="" name="ct_txt" value="'+txt+'" class="dfinput neirong" /></li>';
		     						txt='';
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
	     						
	     						div_centent.innerHTML=div_centent.innerHTML+'<li name="li_sc"><label>商城：<b>*</b></label>'
					 	    	+'图片地址：<input type="text" id="" name="ct_sc_img" value="'+simg+'" class="dfinput200 neirong" />'
					 	    	+'标题：<input type="text" id="" name="ct_sc_title" value="'+stitle+'" class="dfinput200 neirong" />'
					 	    	+'链接：<input type="text" id="" name="ct_sc_url" value="'+surl+'" class="dfinput200 neirong" />'
					 	    	+'ID：<input type="text" id="" name="ct_sc_id" value="'+sid+'" class="dfinput200 neirong" />'
					 	    	+'</li>';
					  	    	
					  	    	neirong2=neirong2.substring(end+7,neirong2.length);
	     					}else if(neirong2.substring(0,6)=='[link]'){
	     						//不为空添加文本
		     					if(txt!=''){
	  								div_centent.innerHTML=div_centent.innerHTML+'<li name="li_txt"><label>文本：<b>*</b></label><input type="text" id="" name="ct_txt" value="'+txt+'" class="dfinput neirong" /></li>';
		     						txt='';
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
	     						
	     						div_centent.innerHTML=div_centent.innerHTML+'<li name="li_wl"><label>外链：<b>*</b></label>'
					 	    	+'图片地址：<input type="text" id="" name="ct_wl_img" value="'+limg+'" class="dfinput200 neirong" />'
					 	    	+'标题：<input type="text" id="" name="ct_wl_title" value="'+ltitle+'" class="dfinput200 neirong" />'
					 	    	+'链接：<input type="text" id="" name="ct_wl_url" value="'+lurl+'" class="dfinput200 neirong" />'
					 	    	+'ID：<input type="text" id="" name="ct_wl_id" value="'+lid+'" class="dfinput200 neirong" />'
					 	    	+'</li>';
					  	    	
					  	    	neirong2=neirong2.substring(end+7,neirong2.length);
	     					}else{
	     						txt+=vin;
	     						neirong2=neirong2.substring(1,neirong2.length);
	     						continue;
	     					}
     						i=neirong.length-neirong2.length-1;
     						continue;
	     				}
	     				//else if(vin == '\\$')
	     				//{
	     					//if(txt!=''){
	  							//div_centent.innerHTML=div_centent.innerHTML+'<li name="li_txt"><label>文本：<b>*</b></label><input type="text" id="" name="ct_txt" value="'+txt+'" class="dfinput neirong" /></li>';
	  							//txt = '';
		     				//}
	     					//neirong2=neirong2.substring(1,neirong2.length);
	     				//}
	     				else{
	     					txt+=vin;
	     					neirong2=neirong2.substring(1,neirong2.length);
	     				}
		     			if(i==neirong.length-1 && txt!=''){
							div_centent.innerHTML=div_centent.innerHTML+'<li name="li_txt"><label>文本：<b>*</b></label><input type="text" id="" name="ct_txt" value="'+txt+'" class="dfinput neirong" /></li>';
	   						txt='';
	   					}
	     			}
	     		*/
	     		//}
	     		
  		/*
  		
  		
  		if(nm=='ct_sc_img'){
  			str+="[shop][vimg]"+va+"[/vimg]";
  		}
  		if(nm=='ct_sc_title'){
  			str+="[vtitle]"+va+"[/vtitle]";
  		}
  		if(nm=="ct_sc_url"){
  			str+="[vurl]"+va+"[/vurl][/shop]";
  		}
	   */ 		
	     	</script>
		     
	        <li><label><b></b></label>
	    	<input type="button" value=" 修 改 " name="close" id="close" onclick="updateOfficial();" class="btn"/>&nbsp;&nbsp; 
	    	<span id="msg"></span></li>
	    </ul>
	    </form>
	</div>
	</div>
  </body>
</html>

