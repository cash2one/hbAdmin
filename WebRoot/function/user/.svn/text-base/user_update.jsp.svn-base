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
  function update(user_id){
  	 	var user_status=document.getElementById("user_status").value;
  	 	var backup=document.getElementById("backup").value;
  	 	if(user_id==null || ''==user_id){
  	 		alert("类型序列号不能为空！");
  	 		return;
  	 	}
  	 	if(user_status==null || ''==user_status){
  	 		alert("状态不能为空！");
  	 		return;
  	 	}
  	 	if(backup==null || ''==backup){
  	 		//alert("备注不能为空！");
  	 		//return;
  	 	}else if(backup.length>25){
  	 		alert("备注长度不能超过25");
  	 		return;
  	 	}
  	 	
  	 	var but=document.getElementById('close');
		but.disabled=true;
		but.value='修改中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>user/doupdateuser",
             data: {
             		'user_id':user_id,
             		'user_status':user_status,
             		'backup':backup
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
	   <li>用户管理</li>
	    <li>注册用户修改</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath%>user/douserlist">注册用户查询</a></li> 
	    <li><a class="selected">注册用户修改</a></li> 
	  	</ul>
	</div> 
	
	<div id="tab1" class="tabson">
	    <ul class="forminfo">
	    	<li><label>序列号：<b></b></label><input type="text" id="user_id" name="user_id" value="${user.user_id }"  class="dfinput200" readonly="readonly"/></li>
	    	<li><label>开发ID：<b></b></label><input type="text" id="open_id" name="open_id" value="${user.open_id }"  class="dfinput200" readonly="readonly"/></li>
	    	<li><label>密码：<b></b></label><input type="text" id="user_pwd" name="user_pwd" value="${user.user_pwd }"  class="dfinput200" readonly="readonly"/></li>
	    	<li><label>注册来源：<b></b></label>
	    		<select id="reg_source" name="reg_source" class="select select200" disabled>
				    <option value="1" <c:if test="${'1'==user.reg_source}">selected</c:if> >本地注册</option>
				   	<option value="2" <c:if test="${'2'==user.reg_source}">selected</c:if> >微信注册</option>
				   	<option value="3" <c:if test="${'3'==user.reg_source}">selected</c:if> >QQ注册</option>
			    </select>
	    	</li>
	    	<li><label>头像：<b></b></label>
	    		<c:if test="${user.user_avatar!=null && ''!=user.user_avatar}">
	    			<img src="${user.user_avatar }"/>
	    		</c:if>
	    	</li>
	    	<li><label>邮箱：<b></b></label><input type="text" id="user_email" name="user_email" value="${user.user_email }"  class="dfinput200" readonly="readonly"/></li>
	    	<li><label>昵称：<b></b></label><input type="text" id="user_nickname" name="user_nickname" value="${user.user_nickname }"  class="dfinput200" readonly="readonly"/></li>
	    	<li><label>年龄：<b></b></label><input type="text" id="user_age" name="user_age" value="${user.user_age }"  class="dfinput200" readonly="readonly"/></li>
	    	<li><label>角色：<b></b></label>
	    		<select id="user_title" name="user_title" class="select select200" disabled>
			   		<option value="1" <c:if test="${'1'==user.user_title}">selected</c:if> >爸爸</option>
			   		<option value="2" <c:if test="${'2'==user.user_title}">selected</c:if> >妈妈</option>
			   		<option value="3" <c:if test="${'3'==user.user_title}">selected</c:if> >爷爷</option>
			   		<option value="4" <c:if test="${'4'==user.user_title}">selected</c:if> >奶奶</option>
			   		<option value="5" <c:if test="${'5'==user.user_title}">selected</c:if> >外公</option>
			   		<option value="6" <c:if test="${'6'==user.user_title}">selected</c:if> >外婆</option>
			   		<option value="7" <c:if test="${'7'==user.user_title}">selected</c:if> >其它</option>
			    </select>
	    	</li>
	    	<li><label>省份：<b></b></label><input type="text" id="province_id" name="province_id" value="${user.province_id }"  class="dfinput200" readonly="readonly"/></li>
	    	<li><label>城市：<b></b></label><input type="text" id="city_id" name="city_id" value="${user.city_id }"  class="dfinput200" readonly="readonly"/></li>
	    	<li><label>区：<b></b></label><input type="text" id="district_id" name="district_id" value="${user.district_id }"  class="dfinput200" readonly="readonly"/></li>
	    	<li><label>联系电话：<b></b></label><input type="text" id="user_tel" name="user_tel" value="${user.user_tel }"  class="dfinput200" readonly="readonly"/></li>
	    	<li><label>姓名：<b></b></label><input type="text" id="user_truename" name="user_truename" value="${user.user_truename }"  class="dfinput200" readonly="readonly"/></li>
	    	<li><label>收货地址：<b></b></label><input type="text" id="user_address" name="user_address" value="${user.user_address }"  class="dfinput200" readonly="readonly"/></li>
	    	<li><label>注册时间：<b></b></label><input type="text" id="reg_date" name="reg_date" value="${user.reg_date }"  class="dfinput200" readonly="readonly"/></li>
	    	---------------------------------------------------------------------------------------------------------------------
	    	<br/><br/>
	    	<li><label>用户状态：<b></b></label>
	    		<select id="user_status" name="user_status" class="select select200">
			   		<option value="0" <c:if test="${'0'==user.user_status}">selected</c:if> >封停</option>
			   		<option value="1" <c:if test="${'1'==user.user_status}">selected</c:if> >正常</option>
			    </select>
	    	</li>
		    <li><label>备注：<b></b></label>
		    	<textarea class="textinput" cols="25" rows="2" name="backup" id="backup">${user.backup }</textarea>
		    </li>
		    <li><label><b></b></label>
		    	<input type="button" value=" 修 改 " name="close" id="close" onclick="update('${user.user_id }');" class="btn"/>&nbsp;&nbsp;
		    	<span id="msg"></span></li>
	    </ul>
	</div>
	</div>
  </body>
</html>

