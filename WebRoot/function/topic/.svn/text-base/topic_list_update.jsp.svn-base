<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  <jsp:include page="/include/base.jsp" />
  <style type="text/css">
   .img li{
		clear:none;
		float:left;
		padding:5px;
	}
	
	 .img li img{
		width:140px;
		height:90px;
	}
  </style>
  <script type="text/javascript">
  function update(id){
  	 	var name=document.getElementById("name").value;
  	 	var status=document.getElementById("status").value;
  	 	var sort=document.getElementById("sort").value;
  	 	if(id==null || ''==id){
  	 		alert("类型序列号不能为空！");
  	 		return;
  	 	}
  	 	if(name==null || ''==name){
  	 		alert("类型名称不能为空！");
  	 		return;
  	 	}
  	 	if(name.length>15){
  	 		alert("类型名称长度不能超过15");
  	 		return;
  	 	}
  	 	
  	 	//正整数
  	 	var tt="^(0|[1-9][0-9]*)$";
  	 	var re=new RegExp(tt);
  	 	
  	 	if(sort==null || ''==sort){
  	 		alert("排序不能为空！");
  	 	} 
  	 	if(sort.match(re)==null || sort.length>11){
       		alert("排序必须为正整数，且长度不能超过11！");
  	 		return;
        }
  	 	
  	 	var but=document.getElementById('close');
		but.disabled=true;
		but.value='修改中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>topiclist/doupdatetopiclist",
             data: {
             		'id':id,
             		'name':name,
             		'sort':sort,
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
	   <li>帖子管理</li>
	    <li>主贴修改</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath%>topiclist/dotopiclistlist">主贴查询</a></li> 
	    <li><a class="selected">主贴修改</a></li> 
	  	</ul>
	</div> 
	
	<div id="tab1" class="tabson">
	    <ul class="forminfo">
	    	<li><label>帖子ID：<b></b></label><input type="text" id="id" name="id" value="${topicList.id }"  class="dfinput150" readonly="readonly"/></li>
	    	<li><label>帖子类型：<b></b></label>
		    <select id="topic_typeId" name="topic_typeId" class="select select151" disabled="disabled">
			   	<c:if test="${noabolish_topictype!=null}">
		   			<c:forEach var="topictype" items="${noabolish_topictype}">
		   				<option value="${topictype.id }" <c:if test="${topictype.id==topicList.topic_typeId}">selected</c:if> >${topictype.name }</option>
		   			</c:forEach>
		   		</c:if>
		    </select>
		    </li>
		    <li><label>帖子标题：<b></b></label><input type="text" id="title" name="title" value="${topicList.title }" class="dfinput" readonly="readonly"/></li>
		    <li><label>帖子内容：<b></b></label>
		    	<textarea class="textinput" cols="25" rows="2" name="content" id="content" readonly="readonly">${topicList.content}</textarea>
		    </li>
		    <li><label>附件：<b></b>
		    <c:if test="${topicList.affix!=null && topicList.affix!=''}">
		    <div  style="padding-left:118px;" >
		    	<c:set value="${ fn:split(topicList.affix, ',') }" var="affix_arry" />
		    	<ul id = "img_list" >
		    	<c:forEach items="${affix_arry }" var="affix">
					<li class="listyle" style="clear:none; ">
						<a href="${affix }" target="_blank"><img name="affix" src="${affix }" width="150" height="92"/></a>
		   			</li>
				</c:forEach>
				</ul>
		    </div>
		    </c:if>
		    <!-- 
		    <div  style="padding-left:118px;" class="img">
		    	<ul id = "img_list" >
				   <li>
				    <img name="affix" src="http://static.17qiqu.com/passport/images/base/bnr_pub359bb.png"/>
				   </li>
				   <li>
				    <img name="affix" src="http://static.17qiqu.com/passport/images/base/bnr_pub359bb.png"/>
				   </li>
				   <li>
				    <img name="affix" src="http://static.17qiqu.com/passport/images/base/bnr_pub359bb.png"/>
				   </li>
				   <li>
				    <img name="affix" src="http://static.17qiqu.com/passport/images/base/bnr_pub359bb.png"/>
				   </li>
				   <li>
				    <img name="affix" src="http://static.17qiqu.com/passport/images/base/bnr_pub359bb.png"/>
				   </li>
				   <li>
				    <img name="affix" src="http://static.17qiqu.com/passport/images/base/bnr_pub359bb.png"/>
				   </li>
				   <li>
				    <img name="affix" src="http://static.17qiqu.com/passport/images/base/bnr_pub359bb.png"/>
				   </li>
			    </ul>
			    
		    </div>
		     -->
		    </li>
		    <li><label>发帖IP：<b></b></label><input type="text" id="ip" name="ip" value="${topicList.ip }" class="dfinput" readonly="readonly"/></li>
		    <li><label>所在地：<b></b></label><input type="text" id="ip_address" name="ip_address" value="${topicList.ip_address }" class="dfinput" readonly="readonly"/></li>
		    <li><label>发帖用户ID：<b></b></label><input type="text" id="uid" name="uid" value="${topicList.uid }" class="dfinput" readonly="readonly"/></li>
		    <li><label>发帖时间：<b></b></label><input type="text" id="createtime" name="createtime" value="${topicList.createtime }" class="dfinput" readonly="readonly"/></li>
		    <li><label>评论数：<b></b></label><input type="text" id="countbrowse" name="countbrowse" value="${topicList.countbrowse }" class="dfinput" readonly="readonly"/></li>
		    <li><label>阅读数：<b></b></label><input type="text" id="countback" name="countback" value="${topicList.countback }" class="dfinput" readonly="readonly"/></li>
		    <li><label>标签：<b></b></label><input type="text" id="label" name="label" value="${topicList.label }" class="dfinput" readonly="readonly"/></li>
		    <li><label>状态：<b></b></label>
		    <select id="status" name="status" class="select select151" disabled="disabled">
		    	<option value="1" <c:if test="${topicList.status=='1' }">selected</c:if> >对外</option>
				<option value="0" <c:if test="${topicList.status=='0' }">selected</c:if>>对内</option>
		    	<option value="9" <c:if test="${topicList.status=='9' }">selected</c:if>>作废</option>
		    </select>
		    </li>
		    <li><label><b></b></label>
		    	<!-- <input type="button" value=" 修 改 " name="close" id="close" onclick="update('${topicList.id }');" class="btn"/>&nbsp;&nbsp; -->
		    	<span id="msg"></span></li>
		    
	    </ul>
	</div>
	</div>
  </body>
</html>

