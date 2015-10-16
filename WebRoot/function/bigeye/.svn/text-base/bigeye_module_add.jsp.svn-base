<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <jsp:include page="/include/base.jsp" />
  <head>
  <script type="text/javascript">
  function add(){
  	 	var module_name=document.getElementById("module_name").value;
  	 	var status=document.getElementById("status").value;
  	 	var img_width=document.getElementById("img_width").value;
  	 	var img_height=document.getElementById("img_height").value;
  	 	if(module_name==null || ''==module_name){
  	 		alert("模块名称不能为空！");
  	 		return;
  	 	}
  	 	if(module_name.length>15){
  	 		alert("模块名称长度不能超过15");
  	 		return;
  	 	}
  	 	
  	 	//正整数
  	 	var tt="^(0|[1-9][0-9]*)$";
  	 	var re=new RegExp(tt);
  	 	
  	 	if(img_width==null || ''==img_width){
  	 		alert("宽度不能为空！");
  	 		return;
  	 	} else if(img_width.match(re)==null || img_width.length>11){
       		alert("宽度必须为正整数，且长度不能超过11！");
  	 		return;
        }
        
        if(img_height==null || ''==img_height){
  	 		alert("高度不能为空！");
  	 		return;
  	 	} else if(img_height.match(re)==null || img_height.length>11){
       		alert("高度必须为正整数，且长度不能超过11！");
  	 		return;
        }
  	 	
  	 	var but=document.getElementById('close');
		but.disabled=true;
		but.value='添加中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>bigeyemodule/doinsertbigeyemodule",
             data: {
             		'module_name':module_name,
             		'img_width':img_width,
             		'img_height':img_height,
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
  </script>
  </head>
  
  <body>
  <!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath%>index.jsp">首页</a></li>
	   <li>广告图管理</li>
	    <li>广告图模块添加</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath%>bigeyemodule/dobigeyemodulelist">广告图模块查询</a></li> 
	    <li><a href="<%=basePath%>bigeyemodule/toinsertbigeyemodule" class="selected">广告图模块添加</a></li> 
	  	</ul>
	</div> 
	
	<div id="tab1" class="tabson">
	    <ul class="forminfo">
	    	<li><label>模块名称：<b>*</b></label><input type="text" id="module_name" name="module_name" value="" maxlength="15"  class="dfinput150" /></li>
		    <li><label>状态：<b>*</b></label>
		    <select id="status" name="status" class="select select151">
		    	<option value="1" selected="selected">对外</option>
				<option value="0">对内</option>
				<option value="9">作废</option>
		    </select>
		    </li>
		    <li><label>图片宽度：<b>*</b></label><input type="text" id="img_width" name="img_width" value="" maxlength="15"  class="dfinput150" /></li>
		    <li><label>图片高度：<b>*</b></label><input type="text" id="img_height" name="img_height" value="" maxlength="15"  class="dfinput150" /></li>
		    <li><label><b></b></label>
		    	<input type="button" value=" 添 加 " name="close" id="close" onclick="add();" class="btn"/>&nbsp;&nbsp;
		    	<span id="msg"></span></li>
		    
	    </ul>
	</div>
	</div>
  </body>
</html>

