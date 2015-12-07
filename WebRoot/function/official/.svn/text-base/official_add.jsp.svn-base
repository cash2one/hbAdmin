<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.manager.util.Constant"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String version=Constant.version;
	String appid=Constant.APPID;
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
  
  function addOfficial(){
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
		but.value='添加中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>official/doinsertofficial",
             data: {
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
	             		alert("添加成功");
	             	}else{
	             	 	alert("添加失败："+msg);
	             	}
             },
             error: function(XMLHttpRequest, textStatus, errorThrown){
             	alert("异常:"+errorThrown);
             }
         });
       but.disabled=false;
	   but.value=' 添 加 ';
  }
  
  function addText(){
  		var arr=new Array();
  		$(".neirong").each(function(index,element){
  			arr[index]=$(element).val();
  		});
  		var div_centent=document.getElementById("div_centent");
  		div_centent.innerHTML=div_centent.innerHTML+'<li name="li_txt"><label>文本：<b>*</b></label>'
  		+'<input type="text" id="" name="ct_txt" value="" class="dfinput neirong" /></li>';
  		
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
  	    	div_centent.innerHTML=div_centent.innerHTML+'<li name="li_wl"><label>外链：<b>*</b></label>'
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
	    <li>官方贴添加</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath%>official/toinsertofficial" class="selected">官方贴添加</a></li> 
	  	</ul>
	</div> 
	
	<div id="tab1" class="tabson">
	    <form method="post" id="myform" action="<%=basePath%>domain">
	    <ul class="forminfo">
		    <li><label>发帖用户ID：<b>*</b></label><input type="text" id="yonghuid" name="yonghuid" value="" class="dfinput"/></li>
		    <li><label>部落ID：<b>*</b></label><input type="text" id="buluoid" name="buluoid" value="" class="dfinput"/></li>
		    <li><label>帖子标题：<b>*</b></label><input type="text" id="biaoti" name="biaoti" value="" class="dfinput" /></li>
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
		    	<input type="button" value="添加外联" name="addwl" id="addwl" onclick="addziyuan(5);" class="btn"/>
		     </li>
		     <div id="div_centent">
		     </div>
	        <li><label><b></b></label>
	    	<input type="button" value=" 添 加 " name="close" id="close" onclick="addOfficial();" class="btn"/>&nbsp;&nbsp; 
	    	<span id="msg"></span></li>
	    </ul>
	    </form>
	</div>
	</div>
  </body>
</html>

