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
  function update(id){
  	 	var lan_level_content=document.getElementById("lan_level_content").value;
  	 	var lan_level_summary=document.getElementById("lan_level_summary").value;
  	 	if(id==null || ''==id){
  	 		alert("序列号不能为空！");
  	 		return;
  	 	}
  	 	if(lan_level_content==null || ''==lan_level_content){
  	 		alert("难度名称不能为空！");
  	 		return;
  	 	}
  	 	if(lan_level_content.length>15){
  	 		alert("难度名称长度不能超过15");
  	 		return;
  	 	}
  	 	
  	 	if(lan_level_summary.length>25){
  	 		alert("难度简介长度不能超过25");
  	 		return;
  	 	}
  	 	
  	 	
  	 	var but=document.getElementById('close');
		but.disabled=true;
		but.value='修改中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>globallanguage/doupdategloballanguage",
             data: {
             		'id':id,
             		'lan_level_content':lan_level_content,
             		'lan_level_summary':lan_level_summary
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
  </script>
  </head>
  
  <body>
  <!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath%>index.jsp">首页</a></li>
	   <li>全局配置</li>
	    <li>语言难度修改</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath%>globallanguage/dogloballanguagelist">语言难度查询</a></li> 
	    <li><a class="selected">语言难度修改</a></li> 
	  	</ul>
	</div> 
	
	<div id="tab1" class="tabson">
	    <ul class="forminfo">
	    	<li><label>难度名称：<b>*</b></label><input type="text" id="lan_level_content" name="lan_level_content" value="${globallanguage.lan_level_content }" maxlength="15"  class="dfinput" /></li>
		   <!-- 
		    <li><label>状态：<b>*</b></label>
		    <select id="status" name="status" class="select select151">
		    	<option value="1" selected="selected">对外</option>
				<option value="0">对内</option>
				<option value="9">作废</option>
		    </select>
		    </li>
		     -->
		    <li><label>难度简介：<b></b></label><input type="text" id="lan_level_summary" name="lan_level_summary" value="${globallanguage.lan_level_summary }" maxlength="25"  class="dfinput" /></li>
		    <li><label><b></b></label>
		    	<input type="button" value=" 修 改 " name="close" id="close" onclick="update('${globallanguage.id }');" class="btn"/>&nbsp;&nbsp;
		    	<span id="msg"></span></li>
		    
	    </ul>
	</div>
	</div>
  </body>
</html>

