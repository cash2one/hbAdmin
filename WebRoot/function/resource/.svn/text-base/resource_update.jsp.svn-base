<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  <jsp:include page="/include/base.jsp" />
   <link href="<%=basePath %>js/uploadify.css" rel="stylesheet" type="text/css" />
   <style type="text/css">
   table td{
   		border-bottom: 1px solid #eaeaea;
   }
   </style>
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
		   'fileDesc'       : '支持格式:jpg或gif或png', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
		   'fileExt'        : '*.jpg;*.gif;*.png',//允许的格式
		   'scriptData'     :{'name':1},
		   　onComplete: function (event, queueID, fileObj, response, data) {
		    	var value = response ;
			   　//   alert("文件:" + fileObj.name + "上传成功");
		    	var file=value.substring(0,value.indexOf("-"));
		    	var filesize=value.substring(value.indexOf("-")+1);
		    	$("#img_index_size").val(filesize); 
		  		$("#img_index").val(file); 
		  		$("#see_img_1").attr("src",file);  
                $("#see_img_1").show();
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
		   'fileDesc'       : '支持格式:jpg或gif或png', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
		   'fileExt'        : '*.jpg;*.gif;*.png',//允许的格式
		   'scriptData'     :{'name':1},
		   　onComplete: function (event, queueID, fileObj, response, data) {
		    	var value = response ;
			   　 //  alert("文件:" + fileObj.name + "上传成功");
			   	var file=value.substring(0,value.indexOf("-"));
		    	var filesize=value.substring(value.indexOf("-")+1);
		    	$("#img_list_size").val(filesize); 
		  		$("#img_list").val(file); 
		  		$("#see_img_2").attr("src",file);  
                $("#see_img_2").show();
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
		   'scriptData'     :{'name':1},
		   　onComplete: function (event, queueID, fileObj, response, data) {
		    	var value = response ;
		    	var file=value.substring(0,value.indexOf("-"));
		    	var filesize=value.substring(value.indexOf("-")+1);
		    	$("#img_main_size").val(filesize); 
			   　//   alert("文件:" + fileObj.name + "上传成功");
		  		$("#img_main").val(file); 
		  		$("#see_img_3").attr("src",file);  
                $("#see_img_3").show();
		   　},  
		   　onError: function(event, queueID, fileObj) {  
		   　 alert("文件:" + fileObj.name + "上传失败");  
		   　},  
		   　onCancel: function(event, queueID, fileObj){  
		   　 alert("取消了" + fileObj.name);  
		   　　　} 
		  });
		  
		   $("#uploadify4").uploadify({
		   'uploader'       : '<%=basePath %>js/uploadify.swf',
		   'script'         : '<%=basePath %>scripts/uploadify',//servlet的路径或者.jsp 这是访问servlet 'scripts/uploadif' 
		   'method'         : 'GET',  //如果要传参数，就必须改为GET
		   'cancelImg'      : '<%=basePath %>js/cancel.png',
		   'folder'         : 'uploads', //要上传到的服务器路径，
		   'queueID'        : 'fileQueue4',
		   'auto'           : false, //选定文件后是否自动上传，默认false
		   'multi'          : true, //是否允许同时上传多文件，默认false
		   'simUploadLimit' : 1, //一次同步上传的文件数目  
		   'sizeLimit'      : 19871202, //设置单个文件大小限制，单位为byte  
		   'queueSizeLimit' : 1, //限制在一次队列中的次数（可选定几个文件）。默认值= 999，而一次可传几个文件有 simUploadLimit属性决定。
		   'fileDesc'       : '支持格式:jpg或gif或png', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
		   'fileExt'        : '*.jpg;*.gif;*.png',//允许的格式
		   'scriptData'     :{'name':1},
		   　onComplete: function (event, queueID, fileObj, response, data) {
		    	var value = response ;
		    	var file=value.substring(0,value.indexOf("-"));
		    	var filesize=value.substring(value.indexOf("-")+1);
		    	$("#read_img_size").val(filesize); 
			   　//alert("文件:" + fileObj.name + "上传成功");
		  		$("#read_img").val(file); 
		  		$("#see_img_4").attr("src",file);  
                $("#see_img_4").show();
		   　},  
		   　onError: function(event, queueID, fileObj) {  
		   　 alert("文件:" + fileObj.name + "上传失败");  
		   　},  
		   　onCancel: function(event, queueID, fileObj){  
		   　 alert("取消了" + fileObj.name);  
		   　　　} 
		  });
		  
		  $("#uploadify5").uploadify({
		   'uploader'       : '<%=basePath %>js/uploadify.swf',
		   'script'         : '<%=basePath %>scripts/uploadify',//servlet的路径或者.jsp 这是访问servlet 'scripts/uploadif' 
		   'method'         : 'GET',  //如果要传参数，就必须改为GET
		   'cancelImg'      : '<%=basePath %>js/cancel.png',
		   'folder'         : 'uploads', //要上传到的服务器路径，
		   'queueID'        : 'fileQueue5',
		   'auto'           : false, //选定文件后是否自动上传，默认false
		   'multi'          : true, //是否允许同时上传多文件，默认false
		   'simUploadLimit' : 1, //一次同步上传的文件数目  
		   'sizeLimit'      : 19871202, //设置单个文件大小限制，单位为byte  
		   'queueSizeLimit' : 1, //限制在一次队列中的次数（可选定几个文件）。默认值= 999，而一次可传几个文件有 simUploadLimit属性决定。
		   'fileDesc'       : '支持格式:jpg或gif或png', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
		   'fileExt'        : '*.jpg;*.gif;*.png',//允许的格式
		   'scriptData'     :{'name':1},
		   　onComplete: function (event, queueID, fileObj, response, data) {
		    	var value = response ;
		    	var file=value.substring(0,value.indexOf("-"));
		    	var filesize=value.substring(value.indexOf("-")+1);
		    	$("#lian_img_size").val(filesize); 
			   　//alert("文件:" + fileObj.name + "上传成功");
		  		$("#lian_img").val(file); 
		  		$("#see_img_5").attr("src",file);  
                $("#see_img_5").show();
		   　},  
		   　onError: function(event, queueID, fileObj) {  
		   　 alert("文件:" + fileObj.name + "上传失败");  
		   　},  
		   　onCancel: function(event, queueID, fileObj){  
		   　 alert("取消了" + fileObj.name);  
		   　　　} 
		  });
		  
		  $("#uploadify6").uploadify({
		   'uploader'       : '<%=basePath %>js/uploadify.swf',
		   'script'         : '<%=basePath %>scripts/uploadify',//servlet的路径或者.jsp 这是访问servlet 'scripts/uploadif' 
		   'method'         : 'GET',  //如果要传参数，就必须改为GET
		   'cancelImg'      : '<%=basePath %>js/cancel.png',
		   'folder'         : 'uploads', //要上传到的服务器路径，
		   'queueID'        : 'fileQueue6',
		   'auto'           : false, //选定文件后是否自动上传，默认false
		   'multi'          : true, //是否允许同时上传多文件，默认false
		   'simUploadLimit' : 1, //一次同步上传的文件数目  
		   'sizeLimit'      : 19871202, //设置单个文件大小限制，单位为byte  
		   'queueSizeLimit' : 1, //限制在一次队列中的次数（可选定几个文件）。默认值= 999，而一次可传几个文件有 simUploadLimit属性决定。
		   'fileDesc'       : '支持格式:jpg或gif或png', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
		   'fileExt'        : '*.jpg;*.gif;*.png',//允许的格式
		   'scriptData'     :{'name':1},
		   　onComplete: function (event, queueID, fileObj, response, data) {
		    	var value = response ;
		    	var file=value.substring(0,value.indexOf("-"));
		    	var filesize=value.substring(value.indexOf("-")+1);
		    	$("#start_img_size").val(filesize); 
			   　//alert("文件:" + fileObj.name + "上传成功");
		  		$("#start_img").val(file); 
		  		$("#see_img_6").attr("src",file);  
                $("#see_img_6").show();
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
  		var id =document.getElementById("id").value;
  		if(id==null || ''==id){
  	 		alert("序列号不能为空！");
  	 		return;
  	 	}
  	 	var resource_content =document.getElementById("resource_content").value;
  	 	if(resource_content==null || ''==resource_content){
  	 		alert("名称不能为空！");
  	 		return;
  	 	}
  	 	if(resource_content.length>50){
  	 		alert("名称长度不能超过50");
  	 		return;
  	 	}
		var resource_summary =document.getElementById("resource_summary").value;
		if(resource_summary==null || ''==resource_summary){
  	 		alert("简介不能为空！");
  	 		return;
  	 	}
  	 	if(resource_summary.length>1000){
  	 		alert("简介长度不能超过1000");
  	 		return;
  	 	}
		var resource_type_id =document.getElementById("resource_type_id").value;
		if(resource_type_id==null || ''==resource_type_id){
  	 		alert("资源类型不能为空！");
  	 		return;
  	 	}
  	 	
  	 	var level_id ='';
		var level_obj=document.getElementsByName("level_id");
		for(var i=0 ;i<level_obj.length;i++ ){
			if(level_obj[i].checked){
				var v=level_obj[i].value;
				level_id+=v+",";
			}
		}
		if(level_id==null || ''==level_id){
  	 		alert("请选择阶段！");
  	 		return;
  	 	}
		
		/*var hobby_id ='';
		var hobby_obj=document.getElementsByName("hobby_id");
		for(var i=0 ;i<hobby_obj.length;i++ ){
			if(hobby_obj[i].checked){
				var v=hobby_obj[i].value;
				hobby_id+=v+",";
			}
		}
		if(hobby_id==null || ''==hobby_id){
  	 		alert("请选择爱好！");
  	 		return;
  	 	}
		*/
  	 	
  	 	var property_id ='';
		var property_obj=document.getElementsByName("property_id");
		for(var i=0 ;i<property_obj.length;i++ ){
			if(property_obj[i].checked){
				var v=property_obj[i].value;
				property_id+=v.substring(v.indexOf("_")+1)+",";
			}
		}
		if(property_id==null || ''==property_id){
  	 		alert("请勾选属性！");
  	 		return;
  	 	}
  	 	
  	 	for(var i=0 ;i<level_obj.length;i++ ){
			if(level_obj[i].checked){
				var v=level_obj[i].value;
				var level_str='';
				for(var j=0 ;j<property_obj.length;j++ ){
					var v2=property_obj[j].value;
					var level=v2.substring(0,v2.indexOf("_"));
					if(level==v &&  property_obj[j].checked){
						level_str+=v2.substring(v2.indexOf("_")+1)+",";
					}
				}
				if(level_str==null || level_str==''){
					alert("请勾选 第"+v+"阶段 的属性！");
  	 				return;
				}
			}
		}
  	 	
  	 	var resource_author=document.getElementById("resource_author").value;
 	 	if(resource_author==null || ''==resource_author){
  	 		alert("作者不能为空！");
  	 		return;
  	 	}
  	 	if(resource_author.length>50){
  	 		alert("作者长度不能超过50");
  	 		return;
  	 	}
  	 	
		//关键词
  	 	var resource_keyword=document.getElementById("resource_keyword").value;
  	 	if(resource_keyword.length>30){
  	 		alert("关键词长度不能超过30");
  	 		return;
  	 	}
  	 	
  	 	var img_index =document.getElementById("img_index").value;
		if(img_index==null || ''==img_index){
  	 		alert("请选择要上传的首页展示图片");
  	 		return;
  	 	}
		var img_index_size =document.getElementById("img_index_size").value;
		
		var img_list =document.getElementById("img_list").value;
		if(resource_type_id!='2'){
			if(img_list==null || ''==img_list){
  	 			alert("请选择要上传的列表展示图片");
  	 			return;
  	 		}
  	 	}
		var img_list_size =document.getElementById("img_list_size").value;
		
		var img_main =document.getElementById("img_main").value;
		var img_main_size =document.getElementById("img_main_size").value;
  	 	if(resource_type_id=='1'||resource_type_id=='4'||resource_type_id=='2'){
			if(img_main==null || ''==img_main){
	  	 		alert("请选择要上传的主界面背景图片");
	  	 		return;
	  	 	}
  	 	}else{
  	 		img_main=null;
  	 		img_main_size=null;
  	 	}
  	 	
  	 	var read_content=document.getElementById("read_content").value;
  	 	var read_img=document.getElementById("read_img").value;
  	 	var read_img_size=document.getElementById("read_img_size").value;
  	 	var lian_content=document.getElementById("lian_content").value;
  	 	var lian_img=document.getElementById("lian_img").value;
  	 	var lian_img_size=document.getElementById("lian_img_size").value;
  	 	var start_content=document.getElementById("start_content").value;
  	 	var start_img=document.getElementById("start_img").value;
  	 	var start_img_size=document.getElementById("start_img_size").value;
  	 	var book_content=document.getElementById("book_content").value;
  	 	var between_age=document.getElementById("between_age").value;
  	 	if(resource_type_id=='4'){
  	 	}else{
  	 		read_content=null;
  	 		read_img=null;
  	 		read_img_size=null;
  	 		lian_content=null;
  	 		lian_img=null;
  	 		lian_img_size=null;
  	 		start_content=null;
  	 		start_img=null;
  	 		start_img_size=null;
  	 		book_content=null;
  	 	}
  	 	
		//var img_book =document.getElementById("img_book").value;
  	 	//var img_book_size =document.getElementById("img_book_size").value;
		//if(resource_type_id=='4'){
		//	if(img_book==null || ''==img_book){
	  	// 		alert("请选择要上传的书架封面图片");
	  	// 		return;
		//	}
  	 	//}else{
  	 	//	img_book=null;
  	 	//	img_book_size=null;
  	 	//}
  	 	
  	 	
  	 	var language_level=document.getElementById("language_level").value;
  	 	if(language_level==null || ''==language_level){
  	 		alert("语言难度不能为空！");
  	 		return;
  	 	}
  	 	
  	 	var resource_status =document.getElementById("resource_status").value;
		if(resource_status==null || ''==resource_status){
  	 		alert("资源状态不能为空！");
  	 		return;
  	 	}
  	 	
  	 	var but=document.getElementById('close');
		but.disabled=true;
		but.value='修改中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>resource/doupdateresource",
             data: {
             		'id':id,
             		'resource_content':resource_content,
					'resource_summary':resource_summary,
					'resource_type_id':resource_type_id,
					'img_index':img_index,
					'img_index_size':img_index_size,
					'img_list':img_list,
					'img_list_size':img_list_size,
					'img_main':img_main,
					'img_main_size':img_main_size,
					//'img_book':img_book,
					//'img_book_size':img_book_size,
					'resource_status':resource_status,
					'language_level':language_level,
					'level_id':level_id,
					'property_id':property_id,
					'resource_author':resource_author,
					'resource_keyword':resource_keyword,
					"read_content":read_content,
  	 				"read_img":read_img,
  	 				"read_img_size":read_img_size,
  	 				"lian_content":lian_content,
  	 				"lian_img":lian_img,
  	 				"lian_img_size":lian_img_size,
  	 				"start_content":start_content,
  	 				"start_img":start_img,
  	 				"start_img_size":start_img_size,
  	 				"book_content":book_content,
  	 				"between_age":between_age
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
  function changeTypeId(){
  		var resource_type_id=document.getElementById("resource_type_id").value;
  		//$("[name=fengmian]").hide();
  		$("[name=beijing]").hide();
  		if(resource_type_id=='4'){
  			$("#tzjs").show();
  			$("#tzjs2").show();
  		}else{
  			$("#tzjs").hide();
  			$("#tzjs2").hide();
  		}
  		if(resource_type_id=='1'||resource_type_id=='4'||resource_type_id=='2'){
  			$("[name=beijing]").show();
  		}
  		
  		if(resource_type_id=='2'){
  			$("[name=liebiao]").hide();
  		}
  		$("#tzjs3").show();
  }
  
  function 	onclickLevel(old_property_id){
  
  		var level_id ='';
		var level_obj=document.getElementsByName("level_id");
		var property_obj=document.getElementsByName("property_id");
		for(var i=0 ;i<level_obj.length;i++ ){
			var v=level_obj[i].value;
			if(level_obj[i].checked){
				document.getElementById("property_"+v).style.display="";
			}else{
				document.getElementById("property_"+v).style.display="none";
				//去除一个阶段的勾选属性
				for(var j=0;j<property_obj.length;j++){
					var property=property_obj[j].value;
					var level=property.substring(0,property.indexOf("_"));
					if(level==v && property_obj[j].checked){
						property_obj[j].checked=false;
					}
				}
			}
		}
  
  		/*
  		var level_id ='';
		var level_obj=document.getElementsByName("level_id");
		for(var i=0 ;i<level_obj.length;i++ ){
			if(level_obj[i].checked){
				var v=level_obj[i].value;
				level_id+=v+";";
			}
		}
		
		
		var hobby_html=document.getElementById("hobby_html");
  	 	var property_html=document.getElementById("property_html");
		
		if(level_id==null || ''==level_id){
  	 		hobby_html.innerHTML="";
  	 		property_html.innerHTML="";
  	 	}else{
  	 		
  	 		var hobby_arr=null;
  	 		var property_arr=null;
	  		$.ajax({
	             type: "post",
	             url: "<%=basePath%>globalLevel/get_div_hobby",
	             data: {
	             		'level_id':level_id
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
		             	var msg=data.res;
		             	hobby_arr=data.su;
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	             	alert("异常:"+errorThrown);
	             }
	         });
	         
	        $.ajax({
	             type: "post",
	             url: "<%=basePath%>globalLevel/get_div_peoperty",
	             data: {
	             		'level_id':level_id
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
		             	var msg=data.res;
		             	property_arr=data.su;
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	             	alert("异常:"+errorThrown);
	             }
	         });
	         
	         var hid=old_hobby_id==null?'':old_hobby_id.split(";");
	         var gid=old_property_id==null?'':old_property_id.split(";");
	         
	         
	         var hobby='';
	         var property='';
	         if(hobby_arr!=null && hobby_arr.length>0){
	         	for(var i=0;i<hobby_arr.length;i++){
	         		var h=hobby_arr[i];
	         		hobby+='<input  type="checkbox"  name="hobby_id" value="'+h["hobby_id"]+'" ';
	         		if(hid!=null && hid!=''){
	         			for (var j = 0; j < hid.length; j++) {
	         				if(hobby_arr[i]['hobby_id']==hid[j]){
	         					hobby+=' checked="checked" ';
	         				}
	         			}
	         		}
	         		hobby+='/>'+h["hobby_content"]+'&nbsp;&nbsp;&nbsp;&nbsp;'
	         	}
	         }
	         
	         if(property_arr!=null && property_arr.length>0){
	         	for(var i=0;i<property_arr.length;i++){
	         		var p=property_arr[i];
	         		property+='<input  type="checkbox"  name="property_id" value="'+p["property_id"]+'" ';
	         		if(gid!=null && gid!=''){
	         			for (var j = 0; j < gid.length; j++) {
	         				if(property_arr[i]['property_id']==gid[j]){
	         					property+=' checked="checked" ';
	         				}
	         			}
	         		}
	         		property+='/>'+p["property_name"]+'&nbsp;&nbsp;&nbsp;&nbsp;'
	         	}
	         }
	        hobby_html.innerHTML=hobby;
  	 		property_html.innerHTML=property;
  		}
  		*/
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
	    <li>资源修改</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath%>resource/doresourcelist?id=<c:out value='${resource.id}'/>">资源查询</a></li> 
	    <li><a href="<%=basePath %>resource/toupdateresource?id=<c:out value='${resource.id}'/>" class="selected">资源修改</a></li> 
	    <li><a href="<%=basePath %>resourceinfo/doresourceinfolist?resource_id=${resource.id }">资源详情查询</a></li> 
	  	
	  	</ul>
	</div> 
	
	<div id="tab1" class="tabson">
		<div class="formtext">
			提示：红色-必选项 蓝色-兴趣项
		</div>
	    <ul class="forminfo">
	    	<input type="hidden" id="id" name="id" value="${resource.id }"  class="dfinput" />
	    	<li><label>名称：<b>*</b></label><input type="text" id="resource_content" name="resource_content" value="${resource.resource_content }" maxlength="50"  class="dfinput" /></li>
		    <li><label>简介：<b>*</b></label>
		    	<textarea class="textinput" cols="25" rows="2" name="resource_summary" id="resource_summary" maxlength="250">${resource.resource_summary }</textarea>
		    </li>
		    <li><label>状态：<b>*</b></label>
		    <select id="resource_status" name="resource_status" class="select select151">
		    	<<c:if test="${status_info=='1'}"><option value="1" <c:if test="${resource.resource_status=='1' }">selected="selected"</c:if>>对外</option></c:if>
				<option value="0" <c:if test="${resource.resource_status=='0' }">selected="selected"</c:if>>对内</option>
				<option value="9" <c:if test="${resource.resource_status=='9' }">selected="selected"</c:if>>作废</option>
		    </select>
		    </li>
		    <li><label>类型：<b>*</b></label>
		    	<select id="resource_type_id" name="resource_type_id" class="select select151" disabled>
			   		<c:if test="${noabolish_resourcetype!=null}">
			   			<c:forEach var="resourcetype" items="${noabolish_resourcetype}">
			   				<option value="${resourcetype.id }" <c:if test="${resource.resource_type_id==resourcetype.id }">selected="selected"</c:if>>${resourcetype.type_name }</option>
			   			</c:forEach>
			   		</c:if>
			    </select>
			</li>
			<li id="li_zuozhe"><label>作者：<b>*</b></label>
		    	<input type="text" id="resource_author" name="resource_author" value="${resource.resource_author }"   class="dfinput" />
		    </li>
		    <script>changeTypeId();</script>
		    <li><label>关键词：<b></b></label>
		    	<input type="text" id="resource_keyword" name="resource_keyword" value="${resource.resource_keyword }"   class="dfinput" />
		    </li>
		     <li><label>语言难度：<b>*</b></label>
		    	<select id="language_level" name="language_level" class="select select151">
			   		<c:if test="${noabolish_globallanguage!=null}">
		   				<c:forEach var="globallanguage" items="${noabolish_globallanguage}">
		   				<option value="${globallanguage.id }" <c:if test="${globallanguage.id==resource.language_level }">selected="selected"</c:if>>${globallanguage.lan_level_content }</option>
		   				</c:forEach>
	   				</c:if>
			    </select>
			</li>
		    <li><label>阶段：<b>*</b></label>
				<fieldset style="padding:15px;width:77%;border:1px solid #c7c7c7;">
				    <legend>勾选阶段</legend>
				   	<c:if test="${noabolish_globallevel!=null}">
						<c:forEach var="globallevel" items="${noabolish_globallevel}">
							<input  type="checkbox" onclick="onclickLevel('${property_id}')"   name="level_id" value="${globallevel.id }" 
							<c:if test="${resourceInfoList!=null}">
								<c:forEach var="resourceInfo" items="${resourceInfoList}">
									<c:if test="${globallevel.id==resourceInfo.level_id}"> checked="checked"</c:if>
								</c:forEach>
							</c:if>
							/>${globallevel.level_content }&nbsp;&nbsp;&nbsp;&nbsp;
			   			</c:forEach>
						
					</c:if>
				</fieldset>
			</li>
			<li><label>属性：<b>*</b></label>
				<c:if test="${noabolish_globalproperty!=null && noabolish_globallevel!=null}">
						<c:forEach var="globallevel" items="${noabolish_globallevel}">
						<fieldset id="property_${globallevel.id}" style="padding:15px;margin-left:120px;width:77%;border:1px solid #c7c7c7;display:none;">
						    <legend>第${globallevel.id}阶段 ${globallevel.level_content }</legend>
							   <c:forEach var="globalproperty" items="${noabolish_globalproperty}">
							   		<c:if test="${globallevel.id== globalproperty.level_id }">
							   		<input  type="checkbox"  name="property_id" value="${globalproperty.level_id}_${globalproperty.id }"
							   		<c:if test="${resourceInfoList!=null}">
										<c:forEach var="resourceInfo" items="${resourceInfoList}">
											<c:if test="${globalproperty.id==resourceInfo.property_id}"> checked="checked"</c:if>
										</c:forEach>
									</c:if>
							   		/>
							   		<font style="color:
							   		<c:if test='${globalproperty.property_type=="1" }'>red;</c:if>
							   		<c:if test='${globalproperty.property_type=="2" }'>blue;</c:if>
							   		">${globalproperty.property_name }&nbsp;&nbsp;&nbsp;&nbsp;
							   		</font>
				   			  		</c:if>
				   			   </c:forEach>
						</fieldset>
						
						</c:forEach>
	   			</c:if>
	   				
			</li>
			
			<script>
				onclickLevel('${property_id}');
			</script>
			<!-- 
			<li><label>兴趣：<b>*</b></label>
				<fieldset style="padding:15px;width:77%;border:1px solid #c7c7c7;">
				    <legend>勾选兴趣</legend>
				    <c:if test="${noabolish_globalhobby!=null}">
						<c:forEach var="globalhobby" items="${noabolish_globalhobby}">
							<input  type="checkbox"  name="hobby_id" value="${globalhobby.id }"
							<c:if test="${resourceInfoList!=null}">
								<c:forEach var="resourceInfo" items="${resourceInfoList}">
								<c:if test="${globalhobby.id==resourceInfo.hobby_id}"> checked="checked"</c:if>
								</c:forEach>
							</c:if>
							 />${globalhobby.hobby_content }&nbsp;&nbsp;&nbsp;&nbsp;
			   			</c:forEach>
						
					</c:if>
			  	</fieldset>
			</li>
			
			<li><label>属性：<b>*</b></label>
				<fieldset style="padding:15px;width:77%;border:1px solid #c7c7c7;">
				    <legend>勾选属性</legend>
					  <c:if test="${noabolish_globalproperty!=null}">
							<c:forEach var="globalproperty" items="${noabolish_globalproperty}">
								<input  type="checkbox"  name="property_id" value="${globalproperty.id }" 
								<c:if test="${resourceInfoList!=null}">
									<c:forEach var="resourceInfo" items="${resourceInfoList}">
									<c:if test="${globalproperty.id==resourceInfo.property_id}"> checked="checked"</c:if>
									</c:forEach>
								</c:if>
								/>${globalproperty.property_name }&nbsp;&nbsp;&nbsp;&nbsp;
				   			</c:forEach>
							
						</c:if>
				</fieldset>
			</li>
			 -->
		    <li><label>图片：<b>*</b></label>
		    <div id="tab1" class="tabson" style="width:80%;float:left;">
		    	<table class="formtab tablelist " style="border: 1;">
		    	<tr>
		    		<td>图片类型</td>
		    		<td>首页展示</td>
		    		<td name="liebiao">列表展示</td>
		    		<td name="beijing">主界面背景</td>
		    		<!-- <td name="fengmian">书架封面图</td> -->
		    	</tr>
		    	<tr>
		    		<td>尺寸(宽|高)</td>
		    		<td>
		    			<input type="text" id="img_index_size" name="img_index_size" value="${resource.img_index_size }" maxlength="30"  class="dfinput150" readonly="readonly"/>
		    		</td>
		    		<td name="liebiao">
		    			<input type="text" id="img_list_size" name="img_list_size" value="${resource.img_list_size }" maxlength="30"  class="dfinput150" readonly="readonly"/>
		    		</td>
		    		<td name="beijing">
		    			<input type="text" id="img_main_size" name="img_main_size" value="${resource.img_main_size }" maxlength="30"  class="dfinput150" readonly="readonly"/>
		    		</td>
		    		<!-- 
		    		<td name="fengmian">
		    			<input type="text" id="img_book_size" name="img_book_size" value="${resource.img_book_size }" maxlength="30"  class="dfinput150" readonly="readonly"/>
		    		</td>
		    		 -->
		    	</tr>
		    	<tr>
		    		<td>上传图片</td>
		    		<td>
		    			<input type="hidden" id="img_index" name="img_index" value="${resource.img_index }" class="dfinput" readonly >
						<div>
						<div id="fileQueue1"></div> </br>
							<input type="file" name="uploadify1" id="uploadify1" />
							<p style="">
								<a href="javascript:uploasFile(1)">开始上传</a>&nbsp;
								<a href="javascript:jQuery('#uploadify1').uploadifyClearQueue()">取消上传</a>
							</p>
						</div>
						<div id = "img_1">
							<img  id="see_img_1" src="${resource.img_index }" width="150" height="90">
						</div>
					</td>
		    		<td name="liebiao">
		    			<input type="hidden" id="img_list" name="img_list" value="${resource.img_list }" class="dfinput" readonly >
						<div>
						<div id="fileQueue2"></div> </br>
							<input type="file" name="uploadify2" id="uploadify2" />
							<p style="">
								<a href="javascript:uploasFile(2)">开始上传</a>&nbsp;
								<a href="javascript:jQuery('#uploadify2').uploadifyClearQueue()">取消上传</a>
							</p>
						</div>
						<div id = "img_2">
							<img  id="see_img_2" src="${resource.img_list }" width="150" height="90">
						</div>
		    		</td>
		    		<td name="beijing">
		    			<input type="hidden" id="img_main" name="img_main" value="${resource.img_main }" class="dfinput" readonly >
						<div>
						<div id="fileQueue3"></div> </br>
							<input type="file" name="uploadify3" id="uploadify3" />
							<p style="">
								<a href="javascript:uploasFile(3)">开始上传</a>&nbsp;
								<a href="javascript:jQuery('#uploadify3').uploadifyClearQueue()">取消上传</a>
							</p>
						</div>
						<div id = "img_3">
							<img  id="see_img_3" src="${resource.img_main }" width="150" height="90">
						</div>
		    		</td>
		    		<!-- 
		    		<td name="fengmian">
		    			<input type="hidden" id="img_book" name="img_book" value="${resource.img_book }" class="dfinput" readonly >
						<div>
						<div id="fileQueue4"></div> </br>
							<input type="file" name="uploadify4" id="uploadify4" />
							<p style="">
								<a href="javascript:uploasFile(4)">开始上传</a>&nbsp;
								<a href="javascript:jQuery('#uploadify4').uploadifyClearQueue()">取消上传</a>
							</p>
						</div>
						<div id = "img_4">
							<img  id="see_img_4" src="${resource.img_book }" width="150" height="90">
						</div>
		    		</td>
		    		 -->
		    	</tr>
		    	</table>
		    	</div>
		   	</li>
		   	
		   	
		   	
		   	
		   	<li  id="tzjs2" style="display: none"><label>绘本文字说明：<b></b></label>
		    	<textarea class="textinput" cols="25" rows="2" name="book_content" id="book_content" maxlength="250">${resource.book_content }</textarea>
		    </li>
		    
		    <li  id="tzjs3" style="display: none"><label>适合年龄：<b></b></label>
		    	<textarea class="textinput" cols="25" rows="2" name="between_age" id="between_age" maxlength="50">${resource.between_age }</textarea>
		    </li>
		   	
		   	
		   	<li id="tzjs" style="display: none"><label>拓展介绍：<b></b></label>
		    	<div id="tab2" class="tabson" style="width:80%;float:left;">
		    	<table class="formtab tablelist " style="border: 1;">
		    	<tr>
		    		<td>图片类型</td>
		    		<td>开始读</td>
		    		<td>开始练</td>
		    		<td>起始值</td>
		    	</tr>
		    	<tr>
		    		<td>尺寸(宽|高)</td>
		    		<td>
		    			<input type="text" id="read_img_size" name="read_img_size" value="${resource.read_img_size }" maxlength="30"  class="dfinput150" readonly="readonly"/>
		    		</td>
		    		<td>
		    			<input type="text" id="lian_img_size" name="lian_img_size" value="${resource.lian_img_size }" maxlength="30"  class="dfinput150" readonly="readonly"/>
		    		</td>
		    		<td>
		    			<input type="text" id="start_img_size" name="start_img_size" value="${resource.start_img_size }" maxlength="30"  class="dfinput150" readonly="readonly"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>介绍文字</td>
		    		<td>
		    			<textarea class="textinput200" cols="25" rows="2" id="read_content" maxlength="500" name="read_content">${resource.read_content }</textarea>
		    		</td>
		    		<td>
		    			<textarea class="textinput200" cols="25" rows="2" id="lian_content" maxlength="500" name="lian_content">${resource.lian_content }</textarea>
		    		</td>
		    		<td>
		    			<textarea class="textinput200" cols="25" rows="2" id="start_content" maxlength="500" name="start_content">${resource.start_content }</textarea>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>上传图片</td>
		    		<td>
		    			<input type="hidden" id="read_img" name="read_img" value="${resource.read_img }" class="dfinput" readonly />
						<div>
						<div id="fileQueue4"></div> </br>
							<input type="file" name="uploadify4" id="uploadify4" />
							<p style="">
								<a href="javascript:uploasFile(4)">开始上传</a>&nbsp;
								<a href="javascript:jQuery('#uploadify4').uploadifyClearQueue()">取消上传</a>
							</p>
						</div>
						<div id = "img_4">
							<img style="display: <c:if test="${empty resource.read_img}">none</c:if>" id="see_img_4" src="${resource.read_img }" width="150" height="90">
						</div>
					</td>
		    		<td>
		    			<input type="hidden" id="lian_img" name="lian_img" value="${resource.lian_img }" class="dfinput" readonly />
						<div>
						<div id="fileQueue5"></div> </br>
							<input type="file" name="uploadify5" id="uploadify5" />
							<p style="">
								<a href="javascript:uploasFile(5)">开始上传</a>&nbsp;
								<a href="javascript:jQuery('#uploadify5').uploadifyClearQueue()">取消上传</a>
							</p>
						</div>
						<div id = "img_5">
							<img style="display: <c:if test="${empty resource.lian_img }">none</c:if>" id="see_img_5" src="${resource.lian_img }" width="150" height="90">
						</div>
		    		</td>
		    		<td >
		    			<input type="hidden" id="start_img" name="start_img" value="${resource.start_img }" class="dfinput" readonly/ >
						<div>
						<div id="fileQueue6"></div> </br>
							<input type="file" name="uploadify6" id="uploadify6" />
							<p style="">
								<a href="javascript:uploasFile(6)">开始上传</a>&nbsp;
								<a href="javascript:jQuery('#uploadify6').uploadifyClearQueue()">取消上传</a>
							</p>
						</div>
						<div id = "img_6">
							<img style="display: <c:if test="${empty resource.start_img}">none</c:if>" id="see_img_6" src="${resource.start_img }" width="150" height="90">
						</div>
		    		</td>
		    	</tr>
		    	</table>
		    	</div>
		   	</li>
		   	
		   	
		   	
		   	
		   	
		   	<script>changeTypeId()</script>
		    <li><label><b></b></label>
		    	<input type="button" value=" 修 改 " name="close" id="close" onclick="update();" class="btn"/>&nbsp;&nbsp;
		    	<span id="msg"></span></li>
		    
	    </ul>
	</div>
	</div>
  </body>
</html>

