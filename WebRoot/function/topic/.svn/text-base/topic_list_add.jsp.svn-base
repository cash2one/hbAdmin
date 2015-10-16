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
  		$(".ui_timepicker").datetimepicker({
            //showOn: "button",
            //buttonImage: "./css/images/icon_calendar.gif",
            //buttonImageOnly: true,
            changeYear: true,showSecond: true,
            timeFormat: 'hh:mm:ss',
            stepHour: 1,
            stepMinute: 1,
            stepSecond: 1
        })
        
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
  
  
  function add(){
  	 	var topic_typeId=document.getElementById("topic_typeId").value;
  	 	var title=document.getElementById("title").value;
  	 	var content=document.getElementById("content").value;
  	 	var ip=document.getElementById("ip").value;
  	 	//var ip_address=document.getElementById("ip_address").value;
  	 	var uid=document.getElementById("uid").value;
  	 	//var createtime=document.getElementById("createtime").value;
  	 	var countback=document.getElementById("countback").value;
  	 	var label_obj=document.getElementsByName("label");
  	 	var countbrowse=document.getElementById("countbrowse").value;
  	 	var affix=document.getElementById("img_url").value;
  	 	var status=document.getElementById("status").value;
  	 	
  	 	//正整数
  	 	var tt="^(0|[1-9][0-9]*)$";
  	 	var re=new RegExp(tt);
  	 	
  	 	if(topic_typeId==null || ''==topic_typeId){
  	 		alert("请选择帖子类型！");
  	 		return;
  	 	}
  	 	if(title==null || ''==title){
  	 		alert("帖子标题不能为空！");
  	 		return;
  	 	}
  	 	if(title.length>50){
  	 		alert("帖子标题长度不能超过50");
  	 		return;
  	 	}
  	 	if(content==null || ''==content){
  	 		alert("帖子内容不能为空！");
  	 		return;
  	 	}
  	 	if(ip==null || ''==ip){
  	 		alert("IP不能为空！");
  	 		return;
  	 	}
  	 	/*if(ip_address==null || ''==ip_address){
  	 		alert("IP所在地不能为空！");
  	 		return;
  	 	}
  	 	if(ip_address.length>15){
  	 		alert("IP所在地长度不能超过15");
  	 		return;
  	 	}
  	 	*/
  	 	if(uid==null || ''==uid){
  	 		alert("发帖用户ID不能为空！");
  	 		return;
  	 	}
  	 	if(uid.match(re)==null || uid.length>11){
       		alert("发帖用户ID必须为正整数，且长度不能超过11！");
  	 		return;
        }
  	 	/*if(createtime==null || ''==createtime){
  	 		alert("发帖时间不能为空！");
  	 		return;
  	 	}
  	 	*/
  	 	if(countback.match(re)==null || countback.length>11){
       		alert("评论数必须为正整数，且长度不能超过11！");
  	 		return;
        }
        if(countback==null || ''==countback){
  	 		countback='0';
  	 	}
  	 	
  	 	var label='';
  	 	for(var i=0;i<label_obj.length;i++){
  	 		if(label_obj[i].checked){
  	 			label+="1";
  	 		}else{
  	 			label+="0";
  	 		}
  	 	}
  	 	if(countbrowse.match(re)==null || countbrowse.length>11){
       		alert("阅读数必须为正整数，且长度不能超过11！");
  	 		return;
        }
        if(countbrowse==null || ''==countbrowse){
  	 		countbrowse='0';
  	 	}
  	 	if(affix==null || ''==affix){
  	 		affix='';
  	 	}
        
  	 	if(status==null || ''==status){
  	 		alert("状态不能为空！");
  	 	} 
  	 	
  	 	var but=document.getElementById('close');
		but.disabled=true;
		but.value='添加中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>topiclist/doinserttopiclist",
             data: {
             		'topic_typeId': topic_typeId,
					'title': title,              
					'content': content,          
					'ip': ip,                    
					//'ip_address': ip_address,    
					'uid': uid,                  
					//'createtime': createtime,    
					'countback': countback,      
					'label': label,              
					'countbrowse': countbrowse,  
					'affix': affix,              
					'status': status         
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
  
  function add2(){
  	 	var topic_typeId=document.getElementById("topic_typeId").value;
  	 	var title=document.getElementById("title").value;
  	 	var content=document.getElementById("content").value;
  	 	var uid=document.getElementById("uid").value;
  	 	//正整数
  	 	var tt="^(0|[1-9][0-9]*)$";
  	 	var re=new RegExp(tt);
  	 	
  	 	if(topic_typeId==null || ''==topic_typeId){
  	 		alert("请选择帖子类型！");
  	 		return;
  	 	}
  	 	if(uid==null || ''==uid){
  	 		alert("发帖用户ID不能为空！");
  	 		return;
  	 	}
  	 	if(uid.match(re)==null || uid.length>11){
       		alert("发帖用户ID必须为正整数，且长度不能超过11！");
  	 		return;
        }
  	 	if(title==null || ''==title){
  	 		alert("帖子标题不能为空！");
  	 		return;
  	 	}
  	 	if(title.length>50){
  	 		alert("帖子标题长度不能超过50");
  	 		return;
  	 	}
  	 	if(content==null || ''==content){
  	 		alert("帖子内容不能为空！");
  	 		return;
  	 	}
  	 	var uid_check='0';
  	 	$.ajax({
             type: "post",
             url: "<%=basePath%>user/checkuserid",
             data: {
					'id': uid                 
             	   },
             dataType: "json",
             async:	false,
             success: function(data,textStatus){
	             	uid_check=data.res;
             },
             error: function(XMLHttpRequest, textStatus, errorThrown){
             	alert("异常:"+errorThrown);
             }
         });
         
  	 	if(uid_check=='1'){
	  	 	$('#myform').ajaxSubmit(function(data){
			  		alert(data['message']);
			  });
  	 	}else{
  	 		alert("用户ID不存在，请从新输入！");
  	 		return;
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
	   <li>帖子管理</li>
	    <li>主贴添加</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath%>topiclist/dotopiclistlist">主贴查询</a></li> 
	    <li><a href="<%=basePath%>topiclist/toinserttopiclist" class="selected">主贴添加</a></li> 
	  	</ul>
	</div> 
	
	<div id="tab1" class="tabson">
	    <form method="post" id="myform" action="<%=basePath%>domain" enctype="multipart/form-data">
	    <ul class="forminfo">
	    	<li><label>帖子类型：<b>*</b></label>
		    <select id="topic_typeId" name="topic_typeId" class="select select151" >
			   	<c:if test="${noabolish_topictype!=null}">
		   			<c:forEach var="topictype" items="${noabolish_topictype}">
		   				<option value="${topictype.id }">${topictype.name }</option>
		   			</c:forEach>
		   		</c:if>
		    </select>
		    </li>
		    <li><label>发帖用户ID：<b>*</b></label><input type="text" id="uid" name="uid" value="" class="dfinput"/></li>
		    <li><label>帖子标题：<b>*</b></label><input type="text" id="title" name="title" value="" class="dfinput" /></li>
		    <li><label>帖子内容：<b>*</b></label>
		    	<textarea class="textinput" cols="25" rows="2" name="content" id="content"></textarea>
		    </li>
		    <li><label>附件：<b></b></label>
				<input id="file1" type="file" name="file1">
			</li>
	        <input type="hidden" name="do" id="do" value="addTopicList"/>
	        <input type="hidden" name="appid" id="appid" value="<%=appid %>"/>
	        <input type="hidden" name="version" id="version" value="<%=version %>"/>
	        <li><label><b></b></label>
	    	<input type="button" value=" 添 加 " name="close" id="close" onclick="add2();" class="btn"/>&nbsp;&nbsp; 
	    	<span id="msg"></span></li>
	       <!-- 
	    	<li><label>帖子类型：<b>*</b></label>
		    <select id="topic_typeId" name="topic_typeId" class="select select151" >
			   	<c:if test="${noabolish_topictype!=null}">
		   			<c:forEach var="topictype" items="${noabolish_topictype}">
		   				<option value="${topictype.id }">${topictype.name }</option>
		   			</c:forEach>
		   		</c:if>
		    </select>
		    </li>
		    <li><label>帖子标题：<b>*</b></label><input type="text" id="title" name="title" value="" class="dfinput" /></li>
		    <li><label>帖子内容：<b>*</b></label>
		    	<textarea class="textinput" cols="25" rows="2" name="content" id="content"></textarea>
		    </li>
		   
		    <li><label>附件：<b></b></label>
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
		    <li><label>发帖IP：<b>*</b></label><input type="text" id="ip" name="ip" value="" class="dfinput"/></li>
		    <li><label>发帖用户ID：<b>*</b></label><input type="text" id="uid" name="uid" value="" class="dfinput"/></li>
		    <li><label>评论数：<b>*</b></label><input type="text" id="countbrowse" name="countbrowse" value="" class="dfinput"/></li>
		    <li><label>阅读数：<b>*</b></label><input type="text" id="countback" name="countback" value="" class="dfinput"/></li>
		    <li><label>标签：<b></b></label>
		    	<div>
		    	<br />
		    	<input  type="checkbox"  name="label" value="1"/>精&nbsp;&nbsp;&nbsp;&nbsp;
		    	<input  type="checkbox"  name="label" value="1"/>顶&nbsp;&nbsp;&nbsp;&nbsp;
		    	<input  type="checkbox"  name="label" value="1" checked/>新&nbsp;&nbsp;&nbsp;&nbsp;
		    	</div>
		    </li>
		    <li><label>状态：<b>*</b></label>
		    <select id="status" name="status" class="select select151">
				<option value="0">对内</option>
		    	<option value="1">对外</option>
		    	<option value="9">作废</option>
		    </select>
		    </li>
		    <li><label><b></b></label>
		    	<input type="button" value=" 添 加 " name="close" id="close" onclick="add();" class="btn"/>&nbsp;&nbsp; 
		    	<span id="msg"></span></li>
		     -->
	    </ul>
	    </form>
	</div>
	</div>
  </body>
</html>

