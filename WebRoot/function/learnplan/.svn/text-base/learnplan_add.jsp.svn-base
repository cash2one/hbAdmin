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
  	 	var plan_content=document.getElementById("plan_content").value;
  	 	var plan_summary=document.getElementById("plan_summary").value;
  	 	var plan_weekday=document.getElementById("plan_weekday").value;
  	 	
  	 	var listen=document.getElementById("listen").value;
		var watch=document.getElementById("watch").value;
		var read=document.getElementById("read").value;
		var play=document.getElementById("play").value;
		
		//正整数
  	 	var tt="^(0|[1-9][0-9]*)$";
  	 	var re=new RegExp(tt);
		
  	 	if(plan_content==null || ''==plan_content){
  	 		alert("计划内容不能为空！");
  	 		return;
  	 	}
  	 	if(plan_weekday==null || ''==plan_weekday){
  	 		alert("执行周期不能为空！");
  	 		return;
  	 	}else if(plan_weekday.match(re)==null || plan_weekday.length>11){
       		alert("执行周期必须为正整数，且长度不能超过11！");
       		document.getElementById("plan_weekday").focus();
  	 		return;
        }
		
  	 	
  	 	if(listen==null || ''==listen){
  	 		alert("听数目不能为空！");
  	 		document.getElementById("listen").focus();
	 			return;
  	 	}else if(listen.match(re)==null || listen.length>11){
       		alert("听数目必须为正整数，且长度不能超过11！");
       		document.getElementById("listen").focus();
  	 		return;
        }
        
        if(watch==null || ''==watch){
  	 		alert("看数目不能为空！");
  	 		document.getElementById("watch").focus();
	 			return;
  	 	}else if(watch.match(re)==null || watch.length>11){
       		alert("看数目必须为正整数，且长度不能超过11！");
       		document.getElementById("watch").focus();
  	 		return;
        }
        
        if(read==null || ''==read){
  	 		alert("读数目不能为空！");
  	 		document.getElementById("read").focus();
	 			return;
  	 	}else if(read.match(re)==null || read.length>11){
       		alert("读数目必须为正整数，且长度不能超过11！");
       		document.getElementById("read").focus();
  	 		return;
        }
        
        if(play==null || ''==play){
  	 		alert("玩数目不能为空！");
  	 		document.getElementById("play").focus();
	 			return;
  	 	}else if(play.match(re)==null || play.length>11){
       		alert("玩数目必须为正整数，且长度不能超过11！");
       		document.getElementById("play").focus();
  	 		return;
        }
	
  	 	var but=document.getElementById('close');
		but.disabled=true;
		but.value='添加中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>learnplan/doinsertlearnplan",
             data: {
             		'plan_content':plan_content,
             		'plan_summary':plan_summary,
             		'plan_weekday':plan_weekday,
             		'listen':listen,
					'watch':watch,
					'read':read,
					'play':play
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
	   <li>学习计划</li>
	    <li>计划配置添加</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>learnplan/dolearnplanlist">计划配置查询</a></li> 
	    <li><a href="<%=basePath %>learnplan/toinsertlearnplan" class="selected">计划配置添加</a></li> 
	  	</ul>
	</div> 
	
	<div id="tab1" class="tabson">
	    <ul class="forminfo">
	    	<li><label>计划内容：<b>*</b></label>
	    	<input type="text" id="plan_content" name="plan_content" value="" maxlength="25"  class="dfinput" />
	    	</li>
		    <li><label>计划说明：<b></b></label>
	    		<textarea class="textinput" cols="25" rows="2" name="plan_summary" id="plan_summary" maxlength="100"></textarea>
	    	</li>
	    	<li><label>执行周期：<b>*</b></label>
	    	<input type="text" id="plan_weekday" name="plan_weekday" value="" maxlength="25"  class="dfinput150" />
	    	</li>
	    	<li><label>听数目：<b>*</b></label>
	    		<input type="text" id="listen" name="listen" value="" maxlength="25"  class="dfinput150"/>
	    	</li>
	    	<li><label>看数目：<b>*</b></label>
	    		<input type="text" id="watch" name="watch" value="" maxlength="25"  class="dfinput150"/>
	    	</li>
	    	<li><label>读数目：<b>*</b></label>
	    		<input type="text" id="read" name="read" value="" maxlength="25"  class="dfinput150"/>
	    	</li>
	    	<li><label>玩数目：<b>*</b></label>
	    		<input type="text" id="play" name="play" value="" maxlength="25"  class="dfinput150"/>
	    	</li>
	    	
		    <li><label><b></b></label>
		    	<input type="button" value=" 添 加 " name="close" id="close" onclick="add();" class="btn"/>&nbsp;&nbsp;
		    	<span id="msg"></span></li>
		    
	    </ul>
	</div>
	</div>
  </body>
</html>

