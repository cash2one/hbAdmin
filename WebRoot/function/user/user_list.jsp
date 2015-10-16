<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<jsp:include page="/include/base.jsp" />
<jsp:include page="/include/date.jsp" />
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
    })
   function sel(){
    	$('#formsubmit').attr('action','<%=basePath %>user/douserlist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>user/expuserlist');   
    	$('#formsubmit').submit();
    }
    
    
    function updatestatus(id,status){
  		if(id==null || ''==id){
  			alert("序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要修改："+id+"的状态吗？")){
	  		var succ=0;
	  		$.ajax({
	             type: "post",
	             url: "<%=basePath %>user/doupdatestatususer",
	             data: {
	             		'user_id':id,
	             		'user_status':status
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
		             	var msg=data.res;
		             	if(msg=='1'){
		             		succ=1;
		             		alert(data.su);
		             	}else{
		             		alert(msg);
		             	}
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	             	alert("异常:"+errorThrown);
	             }
	         });
	         if(succ==1){
	         	window.location.reload();
	         }
	      }
  }
  
  function deleteuser(id){
  		if(id==null || ''==id){
  			alert("类型序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+id+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>user/dodeleteuser",
	             data: {
	             		'user_id':id
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
		             	var msg=data.res;
		             	if(msg=='1'){
		             		succ=1;
		             		alert(data.su);
		             	}else{
		             		alert(msg);
		             	}
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	             	alert("异常:"+errorThrown);
	             }
	         });
	         if(succ==1){
	         	window.location.reload();
	         }  		
  		}
 	 }
   
    </script>
  </head>
  
  <body>
    <!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath %>index.jsp">首页</a></li>
	    <li>用户管理</li>
	    <li>注册用户查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>user/douserlist" class="selected">注册用户查询</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
    	<label>用户ID：</label><input id="user_id" name="user_id" type="text" class="dfinput150" value="${user_id }"/>
    	<label>开放ID：</label><input id="open_id" name="open_id" type="text" class="dfinput150" value="${open_id }"/>
    	<label>邮箱：</label><input id="user_email" name="user_email" type="text" class="dfinput150" value="${user_email }"/>
    	<label>昵称：</label><input id="user_nickname" name="user_nickname" type="text" class="dfinput150" value="${user_nickname }"/>
    	<label>联系电话：</label><input id="user_tel" name="user_tel" type="text" class="dfinput150" value="${user_tel }"/>
    	<br /><br />
    	<label>姓名：</label><input id="user_truename" name="user_truename" type="text" class="dfinput150" value="${user_truename }"/>
    	<label>备注（模糊）：</label><input id="backup" name="backup" type="text" class="dfinput150" value="${backup }"/>
    	<label>注册来源：</label>
    	<select id="source" name="source" class="select select151">
	    	<option value="all">全部</option>
		    <option value="1" <c:if test="${'1'==source}">selected</c:if> >本地注册</option>
		   	<option value="2" <c:if test="${'2'==source}">selected</c:if> >微信注册</option>
		   	<option value="3" <c:if test="${'3'==source}">selected</c:if> >QQ注册</option>
	    </select>
	    
	    <label>用户状态：</label>
    	<select id="user_status" name="user_status" class="select select151">
	    	<option value="all">全部</option>
		   		<option value="0" <c:if test="${'0'==user_status}">selected</c:if> >封停</option>
		   		<option value="1" <c:if test="${'1'==user_status}">selected</c:if> >正常</option>
	    </select>
	    
	    <label>角色：</label>
    	<select id="user_title" name="user_title" class="select select151">
	    	<option value="all">全部</option>
		   		<option value="1" <c:if test="${'1'==user_title}">selected</c:if> >爸爸</option>
		   		<option value="2" <c:if test="${'2'==user_title}">selected</c:if> >妈妈</option>
		   		<option value="3" <c:if test="${'3'==user_title}">selected</c:if> >爷爷</option>
		   		<option value="4" <c:if test="${'4'==user_title}">selected</c:if> >奶奶</option>
		   		<option value="5" <c:if test="${'5'==user_title}">selected</c:if> >外公</option>
		   		<option value="6" <c:if test="${'6'==user_title}">selected</c:if> >外婆</option>
		   		<option value="7" <c:if test="${'7'==user_title}">selected</c:if> >其它</option>
	    </select>
	    
    	
    	<br />
    	<label>时间段：</label>
	    <input type="text" name="startDate" class="ui_timepicker dfinput150" value="${startDate }"/>~
	    <input type="text" name="endDate" class="ui_timepicker dfinput150" value="${endDate }"/>
	    
	    <label>年龄段：</label>
	    <input id="min_age" name="min_age" type="text" class="dfinput150" value="${min_age }"/>~
	    <input id="max_age" name="max_age" type="text" class="dfinput150" value="${max_age }"/>
	    
	    <label>&nbsp;</label><input name="" type="button" onclick="sel()" class="btn" value=" 查 询 "/>
	    &nbsp;
	    <c:if test="1!=1">
	    <input id="btn" type="button" onclick="exp()" class="btn" value="导出"/>
	    </c:if>
	    
	</form>
    </div>
    <!-- 查询条件 -->
    
    
    <!-- 查询列表 -->
    <table id="myTable" class="tablelist display">
    	<thead>
    	<tr>
	        <th>用户ID</th>  
			<th>开放ID</th>  
			<th>注册来源</th>
			<th>邮箱</th>    
			<th>昵称</th>    
			<th>年龄</th>    
			<th>角色</th>    
			<!-- 
			<th>省份</th>    
			<th>城市</th>    
			<th>区</th> 
			 -->     
			<th>用户状态</th>
			<th>联系电话</th>
			<th>姓名</th>    
			<th>备注</th>    
			<th>注册时间</th>
			<th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${userList!=null}">
    	<c:forEach var="user" items="${userList}">
    		<tr>
    			<td><c:out value="${user.user_id}"/></td>
    			<td width="15%" title="<c:out value="${user.open_id}"/>">
					<div class="div_textflow150">
						<c:out value="${user.open_id}"/>
					</div>
				</td>
				<td>
					<c:choose>
					    <c:when test="${user.reg_source =='1'}">
					     	本地注册
					    </c:when>
					    <c:when test="${user.reg_source =='2'}">
					      	微信注册
					    </c:when>
					    <c:when test="${user.reg_source =='3'}">
					      	QQ注册
					    </c:when>
					    <c:otherwise>
					      <c:out value="${user.reg_source}"/>
					    </c:otherwise>   
				  	</c:choose>
				</td>
				<td><c:out value="${user.user_email}"/></td>
				<td><c:out value="${user.user_nickname}"/></td>
				<td><c:out value="${user.user_age}"/></td>
				<td>
					<c:choose>
					    <c:when test="${user.user_title =='1'}">
					     	爸爸
					    </c:when>
					    <c:when test="${user.user_title =='2'}">
					      	妈妈
					    </c:when>
					    <c:when test="${user.user_title =='3'}">
					      	爷爷
					    </c:when>
					     <c:when test="${user.user_title =='4'}">
					      	奶奶
					    </c:when>
					     <c:when test="${user.user_title =='5'}">
					      	外公
					    </c:when>
					     <c:when test="${user.user_title =='6'}">
					      	外婆
					    </c:when>
					     <c:when test="${user.user_title =='7'}">
					      	其它
					    </c:when>
					    <c:otherwise>
					      <c:out value="${user.user_title}"/>
					    </c:otherwise>   
				  	</c:choose>
				</td>
				<!-- 
				<td><c:out value="${user.province_id}"/></td>
				<td><c:out value="${user.city_id}"/></td>
				<td><c:out value="${user.district_id}"/></td>
				 -->
				<td>
					<c:if test="${user.user_status=='0'}"><font style="color:red;">封停</font></c:if>
    				<c:if test="${user.user_status=='1'}"><font style="color:green;">正常</font></c:if>
				</td>
				<td><c:out value="${user.user_tel}"/></td>
				<td><c:out value="${user.user_truename}"/></td>
				<td width="10%" title="<c:out value="${user.backup}"/>">
					<div class="div_textflow100">
						<c:out value="${user.backup}"/>
					</div>
				</td>
				<td><c:out value="${user.reg_date}"/></td>
				<td>
    				<a href="<%=basePath %>user/toupdateuser?user_id=<c:out value='${user.user_id}'/>" class="tablelink">修改</a>     
			        <a style="display: <c:if test='${user.user_status=="0"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${user.user_id}"/>','0');" class="tablelink">封停</a>
			        <a style="display: <c:if test='${user.user_status=="1"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${user.user_id}"/>','1');" class="tablelink">正常</a>
			       <!-- <a href="javascript:deleteuser('<c:out value="${user.user_id}"/>')" class="tablelink">删除</a> --> 
    			</td>
    		</tr>
    	</c:forEach>
    	</c:if>
    	</tbody>
    </table>
    ${pageinfo}
    </div>
    <script type="text/javascript" src="<%=basePath %>js/css.js"></script>
  </body>
</html>
