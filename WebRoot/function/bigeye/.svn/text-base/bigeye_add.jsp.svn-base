<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <jsp:include page="/include/base.jsp" />
  <link href="<%=basePath %>js/uploadify.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="<%=basePath %>js/swfobject.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/jquery.uploadify.v2.1.4.min.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/jquery.uploadify.v2.1.0.js"></script>
  <head>
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
		   'scriptData'     :{'name':'bigeye','size':$('#bigeye_size').val()},
		   　onComplete: function (event, queueID, fileObj, response, data) {
		    	var value = response ;
		    	if(value=="2"){
		    		alert("图片尺寸需为"+$('#bigeye_size').val()+",图片尺寸不正确");
		    	}else{
				    // alert("文件:" + fileObj.name + "上传成功");
			  		$("#img_url").val(value); 
			  		$("#see_img").attr("src",value);  
	                $("#see_img").show();
		    	}
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
 	  //设置 scriptData 的参数	
 	  $('#uploadify1').uploadifySettings('scriptData',{'name':'bigeye','size':$('#bigeye_size').val()}); 		 
 	  //上传
 	  jQuery('#uploadify'+num).uploadifyUpload();
  }
  
  
  function add(){
  		var img_url=document.getElementById("img_url").value;
  		var link_url='';
  		var link_type=document.getElementById("link_type").value;
  		var content=document.getElementById("content").value;
  	 	var module_id=document.getElementById("module_id").value;
  		var sort=document.getElementById("sort").value;
  	 	var status=document.getElementById("status").value;
  	 	
  	 	
  	 	if(img_url==null || ''==img_url){
  	 		alert("请选择规定尺寸的图片！");
  	 		return;
  	 	}
  	 	if(module_id==null || ''==module_id){
  	 		alert("请选择模块！");
  	 		return;
  	 	}else{
  	 		module_id=module_id.substring(0,module_id.indexOf("_"));
  	 	}
  	 	
  	 	//正整数
  	 	var tt="^(0|[1-9][0-9]*)$";
  	 	var re=new RegExp(tt);
  	 	
  	 	if(link_type=='1'){
  	 		link_url=document.getElementById("link_url_topic").value;
  	 		if(link_url==null || ''==link_url){
	  	 		alert("帖子ID不能为空！");
	  	 		return;
	  	 	} else if(link_url.match(re)==null || link_url.length>11){
	       		alert("帖子ID必须为正整数，且长度不能超过11！");
	  	 		return;
	        }
  	 	}else if(link_type=='2'){
  	 		link_url=document.getElementById("link_url_resource").value;
  	 		if(link_url==null || ''==link_url){
	  	 		alert("资源ID不能为空！");
	  	 		return;
	  	 	} else if(link_url.match(re)==null || link_url.length>11){
	       		alert("资源ID必须为正整数，且长度不能超过11！");
	  	 		return;
	        }
  	 	}else{
  	 		link_url=document.getElementById("link_url_url").value;
  	 	}
  	 	
  	 	
  	 	if(sort==null || ''==sort){
  	 		//alert("宽度不能为空！");
  	 		//return;
  	 	} else if(sort.match(re)==null || sort.length>11){
       		alert("排序必须为正整数，且长度不能超过11！");
  	 		return;
        }
        
        
        if(status==null || ''==status){
  	 		alert("请选择状态！");
  	 		return;
  	 	}
       
  	 	
  	 	var but=document.getElementById('close');
		but.disabled=true;
		but.value='添加中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>bigeye/doinsertbigeye",
             data: {
             		'img_url':img_url,
             		'link_url':link_url,
             		'content':content,
             		'module_id':module_id,
             		'sort':sort,
             		'link_type':link_type,
             		'status':status
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
	   but.value=' 添 加 ';
				
  }
  
  function changeSize(){
  		var module_id=document.getElementById("module_id").value;
  		var bigeye_size=module_id.substring(module_id.indexOf("_")+1);
  		document.getElementById("bigeye_size").value=bigeye_size;
  		$("#img_url").val(''); 
  		$("#see_img").attr("src",'');  
        $("#see_img").hide();
  }
  
  function changeType(){
  		var link_type=document.getElementById("link_type").value;
  		if(link_type=='1'){
  			document.getElementById("type_topic").style.display="";
  			document.getElementById("type_url").style.display="none";
  			document.getElementById("type_resource").style.display="none";
  		}else if(link_type=='2'){
  			document.getElementById("type_topic").style.display="none";
  			document.getElementById("type_url").style.display="none";
  			document.getElementById("type_resource").style.display="";
  		}else{
  			document.getElementById("type_topic").style.display="none";
  			document.getElementById("type_url").style.display="";
  			document.getElementById("type_resource").style.display="none";
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
	   <li>广告图管理</li>
	    <li>广告图添加</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath%>bigeye/dobigeyelist">广告图查询</a></li> 
	    <li><a href="<%=basePath%>bigeye/toinsertbigeye" class="selected">广告图添加</a></li> 
	  	</ul>
	</div> 
	
	<div id="tab1" class="tabson">
	    <ul class="forminfo">
	    
	    	<li><label>模块：<b>*</b></label>
		    <select id="module_id" name="module_id" class="select" onchange="changeSize();">
			   	<c:if test="${noabolish_bigeyemodule!=null}">
		   			<c:forEach var="bigeyemodule" items="${noabolish_bigeyemodule}">
		   				<option value="${bigeyemodule.module_id}_${bigeyemodule.img_width}*${bigeyemodule.img_height}">${bigeyemodule.module_name} 宽${bigeyemodule.img_width}×高${bigeyemodule.img_height}</option>
		   			</c:forEach>
		   		</c:if>
		    </select>
		    </li>
    		<input type="hidden" id="bigeye_size" name="bigeye_size" value="" class="dfinput" >
    		<script>changeSize();</script>
	    	<li><label>图片：<b>*</b></label>
				<div style="padding-left:118px;">
				<input type="hidden" id="img_url" name="img_url" value="" class="dfinput" >
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
	    	<li><label>连接值类型：<b></b></label>
	    		 <select id="link_type" name="link_type" class="select" onchange="changeType();">
		   		    <option value="0" selected>连接到url</option>
		   		    <option value="1">连接到帖子</option>
		   		    <option value="2">连接到资源</option>
		    	 </select>
	    	</li>
	    	<li id="type_url"><label>图片连接：<b></b></label><input type="text" id="link_url_url" name="link_url" value=""  class="dfinput" /></li>
		    <li id="type_topic" style="display:none"><label>帖子ID：<b>*</b></label><input type="text" id="link_url_topic" name="link_url" value=""  class="dfinput" /></li>
		    <li id="type_resource" style="display:none"><label>资源ID：<b>*</b></label><input type="text" id="link_url_resource" name="link_url" value=""  class="dfinput" /></li>
		    <li><label>图片标题：<b></b></label><input type="text" id="content" name="content" value=""  class="dfinput" /></li>
		    <li><label>排序：<b></b></label><input type="text" id="sort" name="sort" value=""  class="dfinput150" /></li>
		    <li><label>状态：<b>*</b></label>
		    <select id="status" name="status" class="select select151">
				<option value="0" selected="selected">对内</option>
		    	<option value="1">对外</option>
				<option value="9">作废</option>
		    </select>
		    </li>
		    <li><label><b></b></label>
		    	<input type="button" value=" 添 加 " name="close" id="close" onclick="add();" class="btn"/>&nbsp;&nbsp;
		    	<span id="msg"></span></li>
		    
	    </ul>
	</div>
	</div>
  </body>
</html>

