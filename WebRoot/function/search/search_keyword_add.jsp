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
  	 	var keyword_name=document.getElementById("keyword_name").value;
  	 	var status=document.getElementById("status").value;
  	 	var sort=document.getElementById("sort").value;
  	 	
  	 	//正整数
  	 	var tt="^(0|[1-9][0-9]*)$";
  	 	var re=new RegExp(tt);
  	 	
  	 	if(keyword_name==null || ''==keyword_name){
  	 		alert("类型名称不能为空！");
  	 		return;
  	 	}
  	 	if(keyword_name.length>25){
  	 		alert("类型名称长度不能超过25");
  	 		return;
  	 	}
  	 	if(sort==null || ''==sort){
  	 		alert("排序不能为空！");
  	 		return;
  	 	}
  	 	if(sort.match(re)==null || sort.length>11){
       		alert("排序必须为正整数，且长度不能超过11！");
       		document.getElementById("sort").focus();
  	 		return;
	    }
  	 	var but=document.getElementById('close');
		but.disabled=true;
		but.value='添加中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>searchkeyword/doinsertsearchkeyword",
             data: {
             		'keyword_name':keyword_name,
             		'status':status,
             		'sort':sort
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
	   <li>搜索管理</li>
	    <li>搜索关键字添加</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath%>searchkeyword/dosearchkeywordlist">搜索关键字查询</a></li> 
	    <li><a href="<%=basePath%>searchkeyword/toinsertsearchkeyword" class="selected">搜索关键字添加</a></li> 
	  	</ul>
	</div> 
	
	<div id="tab1" class="tabson">
	    <ul class="forminfo">
	    	<li><label>关键字：<b>*</b></label><input type="text" id="keyword_name" name="keyword_name" value="" maxlength="25"  class="dfinput150" /></li>
		    <li><label>排序：<b>*</b></label><input type="text" id="sort" name="sort" value="" maxlength="11"  class="dfinput150" /></li>
		    <li><label>状态：<b>*</b></label>
		    <select id="status" name="status" class="select select151">
		    	<option value="1" selected="selected">对外</option>
				<option value="0">对内</option>
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

