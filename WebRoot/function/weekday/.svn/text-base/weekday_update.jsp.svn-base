<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <jsp:include page="/include/base.jsp" />
  <jsp:include page="/include/date.jsp" />
  <head>
  <script type="text/javascript">
  $(function () {
        $(".ui_timepicker").datetimepicker({
            changeYear: true,
            showSecond: true,
            timeFormat: 'hh:mm:ss',
            stepHour: 1,
            stepMinute: 1,
            stepSecond: 1
        })
    })
  function update(){
  		var id=document.getElementById("id").value;
  	 	var start_date=document.getElementById("start_date").value;
  	 	var end_date=document.getElementById("end_date").value;
  	 	if(id==null || ''==id){
  	 		alert("周期ID不能为空！");
  	 		return;
  	 	}
  	 	if(start_date==null || ''==start_date){
  	 		alert("周期起始时间不能为空！");
  	 		return;
  	 	}
  	 	if(end_date==null || ''==end_date){
  	 		alert("周期结束时间不能为空！");
  	 		return;
  	 	}
  	 	
  	 	start_date=start_date.substring(0,10);
  	 	end_date=end_date.substring(0,10);
  	 	
  	 	var start = start_date.replace(/-/g,"/");
		var date1 = new Date(start );
		var end = end_date.replace(/-/g,"/");
		var date2 = new Date(end );
		
		var beginDate =  new Date(document.getElementById("start_date").value.replace(/-/g,"/"));
	    var endDate = new Date(document.getElementById("end_date").value.replace(/-/g,"/"));
	    if(beginDate >= endDate){
	        alert("结束时间必须大于起始时间！");
	        return false;
	    }
		var iDays = parseInt(Math.abs(endDate - beginDate ) / 1000 / 60 / 60 /24);//把相差的毫秒数转换为天数
  	 	if((iDays+1)!=7){
  	 		alert("两个日期差必须为7天！");
	        return false;
  	 	}
  	 	
  	 	var listen=document.getElementById("listen").value;
		var watch=document.getElementById("watch").value;
		var read=document.getElementById("read").value;
		var play=document.getElementById("play").value;
		
		//正整数
  	 	var tt="^(0|[1-9][0-9]*)$";
  	 	var re=new RegExp(tt);
  	 	
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
		but.value='修改中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>weekday/doupdateweekday",
             data: {
             		'id':id,
             		'start_date':start_date,
             		'end_date':end_date,
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
	   <li>每周推荐</li>
	    <li>周期修改</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>weekday/doweekdaylist">周期查询</a></li> 
	    <li><a class="selected">周期修改</a></li> 
	  	</ul>
	</div> 
	
	<div id="tab1" class="tabson">
	    <ul class="forminfo">
	    	<li><label>ID：<b>*</b></label>
	    	<input type="text" id="id" name="id" value="${weekday.id }" maxlength="25"  class="dfinput150" readonly/>
	    	</li>
	    	<li><label>周期起始时间：<b>*</b></label>
	    	<input type="text" id="start_date" name="start_date" value="${weekday.start_date }" maxlength="25"  class="dfinput150 ui_timepicker"  readonly/>
	    	</li>
		   <li><label>周期结束时间：<b>*</b></label>
	    	<input type="text" id="end_date" name="end_date" value="${weekday.end_date }" maxlength="25"  class="dfinput150 ui_timepicker"  readonly/>
	    	</li>
	    	<li><label>听数目：<b>*</b></label>
	    		<input type="text" id="listen" name="listen" value="${weekday.listen }" maxlength="25"  class="dfinput150"/>
	    	</li>
	    	<li><label>看数目：<b>*</b></label>
	    		<input type="text" id="watch" name="watch" value="${weekday.watch }" maxlength="25"  class="dfinput150"/>
	    	</li>
	    	<li><label>读数目：<b>*</b></label>
	    		<input type="text" id="read" name="read" value="${weekday.read }" maxlength="25"  class="dfinput150"/>
	    	</li>
	    	<li><label>玩数目：<b>*</b></label>
	    		<input type="text" id="play" name="play" value="${weekday.play }" maxlength="25"  class="dfinput150"/>
	    	</li>
		    <li><label><b></b></label>
		    	<input type="button" value=" 添 加 " name="close" id="close" onclick="update();" class="btn"/>&nbsp;&nbsp;
		    	<span id="msg"></span></li>
		    
	    </ul>
	</div>
	</div>
  </body>
</html>

