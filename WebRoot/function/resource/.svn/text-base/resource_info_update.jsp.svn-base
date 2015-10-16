<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.manager.function.entity.Resource" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	//int sort_display=0;
	//int author_display=0; 
	//int set_number_display=0; 
	String resource_type_id="";
	String resource_id="";
	if(request.getAttribute("resource")!=null){
		Resource resource=(Resource)request.getAttribute("resource");
		resource_type_id=resource.getResource_type_id();
		if(resource_type_id!=null && !"".equals(resource_type_id)){
			//动画 绘本
			//if(resource_type_id.equals("3")||resource_type_id.equals("4")){
			//	sort_display=1;
			//}
			//动画
			//if(resource_type_id.equals("3")){
			//	set_number_display=1;
			//}
			//绘本
			//if(resource_type_id.equals("4")){
			//	author_display=1;
			//}
		}
		resource_id=resource.getId();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  <jsp:include page="/include/base.jsp" />
  <link href="<%=basePath %>js/uploadify.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="<%=basePath %>js/swfobject.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/jquery.uploadify.v2.1.4.min.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/jquery.uploadify.v2.1.0.js"></script>
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
		   'fileDesc'       : '支持格式:*.mp3', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
		   'fileExt'        : '*.mp3',//允许的格式
		   'scriptData'     :{'name':'file'},
		   　onComplete: function (event, queueID, fileObj, response, data) {
		    	var value = response ;
			   　   alert("文件:" + fileObj.name + "上传成功");
		  		$("#resource_url_file").val(value); 
		   　},  
		   　onError: function(event, queueID, fileObj) {  
		   　 alert("文件:" + fileObj.name + "上传失败");  
		   　},  
		   　onCancel: function(event, queueID, fileObj){  
		   　 alert("取消了" + fileObj.name);  
		   　　　} 
		  });
		  
		  $("#uploadify2").uploadify({
		   'uploader'       : '<%=basePath %>js/uploadify.swf',
		   'script'         : '<%=basePath %>scripts/uploadify',//servlet的路径或者.jsp 这是访问servlet 'scripts/uploadif' 
		   'method'         : 'GET',  //如果要传参数，就必须改为GET
		   'cancelImg'      : '<%=basePath %>js/cancel.png',
		   'folder'         : 'uploads', //要上传到的服务器路径，
		   'queueID'        : 'fileQueue2',
		   'auto'           : false, //选定文件后是否自动上传，默认false
		   'multi'          : true, //是否允许同时上传多文件，默认false
		   'simUploadLimit' : 1, //一次同步上传的文件数目  
		   'sizeLimit'      : 19871202, //设置单个文件大小限制，单位为byte  
		   'queueSizeLimit' : 1, //限制在一次队列中的次数（可选定几个文件）。默认值= 999，而一次可传几个文件有 simUploadLimit属性决定。
		   'fileDesc'       : '支持格式:*.lrc', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
		   'fileExt'        : '*.lrc',//允许的格式
		   'scriptData'     :{'name':'file'},
		   　onComplete: function (event, queueID, fileObj, response, data) {
		    	var value = response ;
			   　   alert("歌词文件:" + fileObj.name + "上传成功");
		  		$("#resource_lyrics").val(value);
		   　},  
		   　onError: function(event, queueID, fileObj) {  
		   　 alert("文件:" + fileObj.name + "上传失败");  
		   　},  
		   　onCancel: function(event, queueID, fileObj){  
		   　 alert("取消了" + fileObj.name);  
		   　　　} 
		  });
		  
		   $("#uploadify3").uploadify({
		   'uploader'       : '<%=basePath %>js/uploadify.swf',
		   'script'         : '<%=basePath %>scripts/uploadify',//servlet的路径或者.jsp 这是访问servlet 'scripts/uploadif' 
		   'method'         : 'GET',  //如果要传参数，就必须改为GET
		   'cancelImg'      : '<%=basePath %>js/cancel.png',
		   'folder'         : 'uploads', //要上传到的服务器路径，
		   'queueID'        : 'fileQueue3',
		   'auto'           : false, //选定文件后是否自动上传，默认false
		   'multi'          : true, //是否允许同时上传多文件，默认false
		   'simUploadLimit' : 1, //一次同步上传的文件数目  
		   'sizeLimit'      : 19871202, //设置单个文件大小限制，单位为byte  
		   'queueSizeLimit' : 1, //限制在一次队列中的次数（可选定几个文件）。默认值= 999，而一次可传几个文件有 simUploadLimit属性决定。
		   'fileDesc'       : '支持格式:jpg或gif或png', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
		   'fileExt'        : '*.jpg;*.gif;*.png',//允许的格式
		   'scriptData'     :{'name':12},
		   　onComplete: function (event, queueID, fileObj, response, data) {
		    	var value = response ;
	   			
	   			//alert("文件:" + fileObj.name + "上传成功");
		   		var img_list=document.getElementById("img_list");
		   		img_list.innerHTML='<li class="listyle" style="clear:none; "><img  name="resource_url_img_list" src="'+value+'" width="150" height="92"/>'
		   		+'<br/><br/><input type="text" name="resource_sort_img_list" value="" class="dfinput150" /></li>';
		   　},  
		   　onError: function(event, queueID, fileObj) {  
		   　	 alert("文件:" + fileObj.name + "上传失败");  
		   　},  
		   　onCancel: function(event, queueID, fileObj){  
		   　	 //alert("取消了" + fileObj.name);  
		   　　　} 
		  });
		  
		  $("#uploadify4").uploadify({
		   'uploader'       : '<%=basePath %>js/uploadify.swf',
		   'script'         : '<%=basePath %>scripts/zipuploadify',//servlet的路径或者.jsp 这是访问servlet 'scripts/uploadif' 
		   'method'         : 'GET',  //如果要传参数，就必须改为GET
		   'cancelImg'      : '<%=basePath %>js/cancel.png',
		   'folder'         : 'uploads', //要上传到的服务器路径，
		   'queueID'        : 'fileQueue4',
		   'auto'           : false, //选定文件后是否自动上传，默认false
		   'multi'          : true, //是否允许同时上传多文件，默认false
		   'simUploadLimit' : 1, //一次同步上传的文件数目  
		   'sizeLimit'      : 19871202, //设置单个文件大小限制，单位为byte  
		   'queueSizeLimit' : 1, //限制在一次队列中的次数（可选定几个文件）。默认值= 999，而一次可传几个文件有 simUploadLimit属性决定。
		   'fileDesc'       : '支持格式:*.zip', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
		   'fileExt'        : '*.zip',//允许的格式
		   'scriptData'     :{'name':'file','id':'<%=resource_id%>'},
		   　onComplete: function (event, queueID, fileObj, response, data) {
		    	var value = response ;
			   　   alert("HTML5文件:" + fileObj.name + "上传成功");
		  		$("#resource_url_html").val(value);
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
 	  jQuery('#uploadify'+num).uploadifyUpload() 	 		 
  }
  
  function update(){
  		var id=document.getElementById("id").value;
  	 	if(id==null || ''==id){
 	 		alert("资源详情序列号不能为空！");
 	 		return;
	 	}
  		//资源序列号
  		var resource_id=document.getElementById("resource_id").value;
  		if(resource_id==null || ''==resource_id){
 	 		alert("资源序列号不能为空！");
 	 		return;
	 	}
  		//5-游戏、4-绘本、3-动画、2-视频、1-音频
  		//资源类型
  		var resource_type_id='<%=resource_type_id%>';
  		if(resource_type_id==null || ''==resource_type_id){
 	 		alert("资源类型不能为空！");
 	 		return;
	 	}
  		//资源详情类型1-音频  2-视频  3-图片
  		var resource_type=document.getElementById("resource_type").value;
  		if(resource_type==null || ''==resource_type){
 	 		alert("请选择资源详情类型！");
 	 		return;
	 	}
	 	
	 	//正整数
  	 	var tt="^(0|[1-9][0-9]*)$";
  	 	var re=new RegExp(tt);
	 	
  		//动画集数
  	 	var set_number=document.getElementById("set_number").value;
  	 	//视频连接地址
  	 	var resource_url_url=document.getElementById("resource_url_url").value;
  	 	//视频连接地址
  	 	var resource_url_url2=document.getElementById("resource_url_url2").value;
  	 	//资源音频文件
  	 	var resource_url_file=document.getElementById("resource_url_file").value;
  	 	//图片所有文件
  	 	var resource_url_img_list=document.getElementsByName("resource_url_img_list");
  	 	//图片所有排序
  	 	var resource_sort_img_list=document.getElementsByName("resource_sort_img_list");
  	 	//动画排序
  	 	var resource_sort_cartoon=document.getElementById("resource_sort_cartoon").value;
  	 	//音频歌词地址
  	 	var resource_lyrics=document.getElementById("resource_lyrics").value;
  	 	//HTML5
  	 	var resource_url_html=document.getElementById("resource_url_html").value;
  	 	
	 	if(resource_type_id=='3'){
	  	 	if(set_number==null || ''==set_number){
	  	 		alert("动画集数不能为空！");
	  	 		document.getElementById("set_number").focus();
 	 			return;
	  	 	}else if(set_number.match(re)==null || set_number.length>11){
	       		alert("动画集数必须为正整数，且长度不能超过11！");
	       		document.getElementById("set_number").focus();
	  	 		return;
	        }
	 	}else{
	 		set_number==null;
	 	}
	 	
	 	
  	 	if(resource_type=='2'){
	 		if(resource_url_url==null || ''==resource_url_url){
	  	 		alert("视频连接地址不能为空！");
	  	 		document.getElementById("resource_url_url").focus();
  	 			return;
	  	 	}
	  	 	if(resource_url_url2==null || ''==resource_url_url2){
	  	 		alert("视频连接地址不能为空！");
	  	 		document.getElementById("resource_url_url2").focus();
  	 			return;
	  	 	}
	  	 	//else if(resource_url_url.length>150){
	       	//	alert("视频连接地址长度不能超过150！");
	       	//	document.getElementById("resource_url_url").focus();
  	 		//	return;
	        //}
	        resource_url_url=resource_url_url+","+resource_url_url2;
	 	}else{
	 		resource_url_url=null;
	 		resource_url_url2=null;
	 	}
	 	
  	 	if(resource_type=='1'){
  	 		if(resource_url_file==null || ''==resource_url_file){
	  	 		alert("请选择资源音频文件！");
	  	 		document.getElementById("resource_url_file").focus();
	  	 		return;
	  	 	}
	  	 	//else if(resource_url_file.length>150){
	       	//	alert("资源音频文件长度不能超过150！");
		    //    document.getElementById("resource_url_file").focus();
	  	 	//	return;
	        //}
	 	}else{
			resource_url_file=null;	 		
	 	}
	 	
  	 	var resource_url_img=""
  	 	var resource_sort_img="";
	 	if(resource_type=='3'){
	  	 	if(resource_url_img_list==null || resource_url_img_list.length==0){
	  	 		alert("请选择要上传的图片！");
	  	 		return;
	  	 	}else{
		  	 	if(resource_url_img_list!=null && resource_url_img_list.length>0){
		  	 		for(var i=0;i<resource_url_img_list.length;i++){
		  	 			resource_url_img+=resource_url_img_list[i].src+";";
		  	 		}
		  	 	}
		  	 	resource_url_img=resource_url_img.substring(0,resource_url_img.length-1);
		  	 	if(resource_sort_img_list!=null && resource_sort_img_list.length>0){
		  	 		for(var i=0;i<resource_sort_img_list.length;i++){
		  	 			if(resource_sort_img_list[i].value==null || resource_sort_img_list[i].value==''){
		  	 				resource_sort_img+="null;";
		  	 			}else{
		  	 				resource_sort_img+=resource_sort_img_list[i].value+";";
		  	 			}
		  	 		}
		  	 	}
		  	 	resource_sort_img=resource_sort_img.substring(0,resource_sort_img.length-1);
	  	 	}
  	 	}
  	 	if(resource_type=='4'){
  	 		if(resource_url_html==null || ''==resource_url_html){
	  	 		alert("HTML5不能为空！");
	  	 		document.getElementById("resource_url_html").focus();
	  	 		return;
	  	 	}
	 	}else{
			resource_url_html=null;	 		
	 	}
  	 	
  	 	if(resource_type_id=='3'){
	 		//if(resource_sort_cartoon==null || ''==resource_sort_cartoon){
	  	 	//	alert("动画排序不能为空！");
	  	 	//} 
	  	 	if(resource_sort_cartoon.match(re)==null || resource_sort_cartoon.length>11){
	       		alert("动画排序必须为正整数，且长度不能超过11！");
	       		document.getElementById("resource_sort_cartoon").focus();
	  	 		return;
	        }
	 	}else{
	 		resource_sort_cartoon==null;
	 	}
	 	
       /* 
        var resource_url=null;
        var resource_sort=null;
        if(resource_type=='1'){
        	resource_url=resource_url_file;
        }else if(resource_type=='2'){
        	resource_url=resource_url_url;
        	resource_sort=resource_sort_cartoon;
        }else if(resource_type=='3'){
        	resource_url=resource_url_img;
        	resource_sort=resource_sort_img;
        }	
	 	*/
  	 	var but=document.getElementById('close');
		but.disabled=true;
		but.value='修改中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>resourceinfo/doupdateresourceinfo",
             data: {
             		'id':id,
             		'resource_id':resource_id,
             		'resource_type_id':resource_type_id,
             		'resource_type':resource_type,
             		'set_number':set_number,
             		'resource_url_url':resource_url_url,
             		'resource_url_file':resource_url_file,
             		'resource_url_img':resource_url_img,
             		'resource_url_html':resource_url_html,
             		'resource_sort_cartoon':resource_sort_cartoon,
             		'resource_sort_img':resource_sort_img,
             		'resource_lyrics':resource_lyrics
             	   },
             dataType: "json",
             async:	false,
             success: function(data,textStatus){
	             	var msg=data.res;
	             	alert(msg);
             },
             error: function(XMLHttpRequest, textStatus, errorThrown){
             	alert("异常:"+errorThrown);
             }
         });
       but.disabled=false;
	   but.value=' 修 改 ';
  }
  function changeType(){
  		//var li_jishu=document.getElementById("li_jishu");
  		var li_dizhi=document.getElementById("li_dizhi");
  		var li_wenjian=document.getElementById("li_wenjian");
  		var li_tupian=document.getElementById("li_tupian");
  		var li_paixu=document.getElementById("li_paixu");
  		var li_geci=document.getElementById("li_geci");
  		var li_html=document.getElementById("li_html");
  		
  		li_dizhi.style.display="";
  		li_wenjian.style.display="";
  		li_tupian.style.display="";
  		li_paixu.style.display="";
  		li_geci.style.display="";
  		li_html.style.display="";
  		
  		var resource_type=document.getElementById("resource_type").value;
  		var resource_type_id='<%=resource_type_id%>';
  		//音频
  		if(resource_type=='1'){
  			li_dizhi.style.display="none";
  			li_tupian.style.display="none";
  			li_paixu.style.display="none";
  			li_html.style.display="none";
  		//视频
  		}else if(resource_type=='2'){
  			li_wenjian.style.display="none";
  			li_tupian.style.display="none";
  			li_geci.style.display="none";
  			li_html.style.display="none";
  		//图片	
  		}else if(resource_type==3){
  			li_dizhi.style.display="none";
  			li_wenjian.style.display="none";
  			li_paixu.style.display="none";
  			li_geci.style.display="none";
  			li_html.style.display="none";
  		//HTML5
  		}else if(resource_type=='4'){
  			li_dizhi.style.display="none";
  			li_wenjian.style.display="none";
  			li_paixu.style.display="none";
  			li_geci.style.display="none";
  			li_tupian.style.display="none";
  		}
  }
  </script>
  </head>
  
  <body>
  <!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath%>index.jsp">首页</a></li>
	   <li>资源管理</li>
	    <li>资源详情修改</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	   <li><a href="<%=basePath%>resource/doresourcelist?id=<c:out value='${resource.id}'/>">资源信息查询</a></li> 
	    <li><a href="<%=basePath %>resource/toupdateresource?id=<c:out value='${resource.id}'/>" >资源信息修改</a></li> 
	    <li><a href="<%=basePath %>resourceinfo/doresourceinfolist?resource_id=${resource.id }">资源详情查询</a></li> 
	    <li><a class="selected">${resource.type_name } 资源详情修改</a></li> 
	  	</ul>
	</div> 
	
	<!-- 5-游戏、4-绘本、3-动画、2-视频、1-音频-->
	<div id="tab1" class="tabson">
	    <ul class="forminfo">
	    	<input type="hidden" id="id" name="id" value="${resourceinfo.id }"  class="dfinput" />
	   	 	<input type="hidden" id="resource_id" name="resource_id" value="${resource.id }"  class="dfinput" />
	   	 	<c:set var="type_id"  value="${resource.resource_type_id}" /> 
	   	 	<li><label>类型：<b>*</b></label>
		    <select id="resource_type" name="resource_type" class="select select151" onchange="changeType()" disabled>
		    	<!-- 
		    	<c:if test="${type_id=='1' || type_id=='4'}"><option value="1" selected="selected">音频</option></c:if>
				<c:if test="${type_id!='5'}"><option value="2">视频</option></c:if>
				<c:if test="${type_id=='4' || type_id=='5'}"><option value="3">图片</option></c:if>
		    	 -->
		    	<c:if test="${type_id=='1'}"><option value="1" <c:if test="${resourceinfo.resource_type=='1'}">selected="selected"</c:if>>音频</option></c:if>
				<c:if test="${type_id!='5'}"><option value="2" <c:if test="${resourceinfo.resource_type=='2'}">selected="selected"</c:if>>视频</option></c:if>
				<c:if test="${type_id=='00'}"><option value="3" <c:if test="${resourceinfo.resource_type=='3'}">selected="selected"</c:if>>图片</option></c:if>
		    	<c:if test="${type_id=='5'}"><option value="4" <c:if test="${resourceinfo.resource_type=='4'}">selected="selected"</c:if>>HTML5</option></c:if>
		    </select>
		    </li>
		    <div id="li_jishu">
		    	<li style="display:<c:if test="${type_id!='3'}">none</c:if>"><label>动画集数：<b>*</b></label>
	    			<input type="text" id="set_number" name="set_number" value="${resourceinfo.set_number }" maxlength="11"  class="dfinput" />
	    		</li>
	    	</div>
		    
		    <div id="li_dizhi">
		    	<li style="display:<c:if test="${type_id=='5'}">none</c:if>"><label>连接地址(app)：<b>*</b></label>
			    	<input type="text" id="resource_url_url" name="resource_url" value="${resourceinfo.resource_url }"  class="dfinput" />
			    </li>
			    <li style="display:<c:if test="${type_id=='5'}">none</c:if>"><label>连接地址(分享)：<b>*</b></label>
			    	<input type="text" id="resource_url_url2" name="resource_url2" value="${resourceinfo.resource_url2 }"  class="dfinput" />
			    </li>
		    </div>
		    <div id="li_wenjian">
		    <li style="display:<c:if test="${type_id!='4' && type_id!='1'}">none</c:if>"><label>资源音频文件：<b>*</b></label>
		    	<input type="text" id="resource_url_file" name="resource_url" value="${resourceinfo.resource_url }" maxlength="150"  class="dfinput" readonly/>
				<div style="padding-left:118px;">
				<div id="fileQueue1"></div> </br>
					<input type="file" name="uploadify1" id="uploadify1" />
					<p style="">
						<a href="javascript:uploasFile(1)">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify1').uploadifyClearQueue()">取消上传</a>
					</p>
				</div>
		    </li>
			</div>
			<div id="li_tupian">
				<li style="display:<c:if test="${type_id!='4' && type_id!='5'}">none</c:if>">
					<label>
						资源图片/排序：
						<b>*</b>
					</label>
					<div style="padding-left:118px;">
					<div id="fileQueue3"></div> </br>
						<input type="file" name="uploadify3" id="uploadify3" />
						<p>
							<a href="javascript:uploasFile(3)">开始上传</a>&nbsp;
							<a href="javascript:jQuery('#uploadify3').uploadifyClearQueue()">取消所有</a>
						</p>
					</div>
					<div  style="padding-left:118px;">
					<ul id = "img_list" >
						<li class="listyle" style="clear:none; "><img  name="resource_url_img_list" src="${resourceinfo.resource_url }" width="150" height="92"/>
		   				<br/><br/><input type="text" name="resource_sort_img_list" value="${resourceinfo.resource_sort }" class="dfinput150" /></li>
					</ul>
					</div>
				</li>
			</div>
			<div id="li_html">
				<li style="display:<c:if test="${type_id!='5'}">none</c:if>">
					<label>
						HTML5：
						<b>*</b>
					</label>
					<!-- 
		    			<textarea class="textinput700" cols="25" rows="2" name="resource_url_html" id="resource_url_html">${resourceinfo.resource_url }</textarea>
					 -->
					<input type="text" id="resource_url_html" name="resource_url_html" value="${resourceinfo.resource_url }" maxlength="25"  class="dfinput" readonly/>
					<div style="padding-left:118px;">
					<div id="fileQueue4"></div> </br>
						<input type="file" name="uploadify4" id="uploadify4" />
						<p style="">
							<a href="javascript:uploasFile(4)">开始上传</a>&nbsp;
							<a href="javascript:jQuery('#uploadify4').uploadifyClearQueue()">取消上传</a>
						</p>
					</div>
				</li>
			</div>
		    <div id="li_paixu">
			    <li style="display:<c:if test="${type_id!='3'}">none</c:if>"><label>排序：<b></b></label>
			    	<input type="text" id="resource_sort_cartoon" name="resource_sort_cartoon" value="${resourceinfo.resource_sort }" maxlength="11"  class="dfinput" />
			    </li>
		    </div>
		    <div id="li_geci">
			    <li style="display: <c:if test="${type_id!='1'}">none</c:if>"><label>歌词文件：<b></b></label>
			    	<input type="text" id="resource_lyrics" name="resource_lyrics" value="${resourceinfo.resource_lyrics }" maxlength="25"  class="dfinput" readonly/>
					<div style="padding-left:118px;">
					<div id="fileQueue2"></div> </br>
						<input type="file" name="uploadify2" id="uploadify2" />
						<p style="">
							<a href="javascript:uploasFile(2)">开始上传</a>&nbsp;
							<a href="javascript:jQuery('#uploadify2').uploadifyClearQueue()">取消上传</a>
						</p>
					</div>
			    </li>
		    </div>
		    <script>changeType();</script>
		    <li><label><b></b></label>
		    	<input type="button" value=" 修 改 " name="close" id="close" onclick="update();" class="btn"/>&nbsp;&nbsp;
		    	<span id="msg"></span></li>
		    
	    </ul>
	</div>
	</div>
  </body>
</html>

