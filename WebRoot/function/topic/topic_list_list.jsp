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
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script type="text/javascript">
   function sel(){
    	$('#formsubmit').attr('action','<%=basePath %>topiclist/dotopiclistlist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>topiclist/exptopiclistlist');   
    	$('#formsubmit').submit();
    }
    
    function updatestatus(id,status){
  		if(id==null || ''==id){
  			alert("序列号不能为空！");
  			return false;
  		}
  		var succ=0;
  		$.ajax({
             type: "post",
             url: "<%=basePath %>topiclist/doupdatestatustopiclist",
             data: {
             		'id':id,
             		'status':status
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
  
  
  function deletetopiclist(id){
  		if(id==null || ''==id){
  			alert("序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+id+"的主贴吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>topiclist/dodeletetopiclist",
	             data: {
	             		'id':id
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
  
  	function oncheckboxRadio(obj){
		var check=document.getElementsByName("checkbox_label");
		for(var i=0 ;i<check.length;i++ ){
			check[i].checked=obj.checked;
		}
	}
	
	
	function updateLabelStatus(num,status){
		var check=document.getElementsByName("checkbox_label");
		var id_str='';
		var label_str=''
		for(var i=0 ;i<check.length;i++ ){
			if(check[i].checked){
				var v=check[i].value;
				id_str+=v.substring(0,v.indexOf("-"))+",";
				
				var label_status=v.substring(v.indexOf("-")+1);
		 	 	var start=num==0?'':label_status.substring(0,num);
		 	 	var end=num==2?'':label_status.substring(num+1);
		 	 	var label=start+status+end;
				label_str+=label+",";
			}
		}
 	 	
 	 	if(id_str==''){
 	 		alert("请勾选要修改的主贴");
 	 		return ;
 	 	}
 	 	
 	 	var urlstr="";
 	 	var succ=0;
  		$.ajax({
             type: "post",
             url: "<%=basePath %>topiclist/doupdatelabeltopiclist",
             data: {
             		'id':id_str,
             		'label':label_str
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
         if(succ==1){
			for(var i=0 ;i<check.length;i++ ){
				check[i].checked=false;
			}
         	window.location.reload();
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
	    <li>帖子管理</li>
	    <li>主贴查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>topiclist/dotopiclistlist" class="selected">主贴查询</a></li> 
	    <li><a href="<%=basePath%>topiclist/toinserttopiclist">主贴添加</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
    	<label>帖子ID：</label><input id="id" name="id" type="text" class="dfinput150" value="${id }"/>
    	<label>帖子标题：</label><input id="title" name="title" type="text" class="dfinput150" value="${title }"/>
    	<label>帖子内容：</label><input id="content" name="content" type="text" class="dfinput150" value="${content }"/>
    	<label>发帖IP：</label><input id="ip" name="ip" type="text" class="dfinput150" value="${ip }"/>
    	<br /><br />
    	<label>发帖用户ID：</label><input id="uid" name="uid" type="text" class="dfinput150" value="${uid }"/>
    	<label>状态：</label>
    	<select id="status" name="status" class="select select151">
	    	<option value="all">全部</option>
		   		<option value="0" <c:if test="${'0'==status}">selected</c:if> >对内</option>
		   		<option value="1" <c:if test="${'1'==status}">selected</c:if> >对外</option>
		   		<option value="9" <c:if test="${'9'==status}">selected</c:if> >作废</option>
	    </select>
	    <label>帖子类型：</label>
    	<select id="topic_typeId" name="topic_typeId" class="select select151">
	    	<option value="all">全部</option>
		   	<c:if test="${noabolish_topictype!=null}">
	   			<c:forEach var="topictype" items="${noabolish_topictype}">
	   				<option value="${topictype.id }" <c:if test="${topictype.id==topic_typeId}">selected</c:if> >${topictype.name }</option>
	   			</c:forEach>
	   		</c:if>
	    </select>
	    <label>标签：</label>
    	<select id="label" name="label" class="select select151">
	    	<option value="all">全部</option>
		   		<option value="1" <c:if test="${'1'==label}">selected</c:if> >顶</option>
		   		<option value="2" <c:if test="${'2'==label}">selected</c:if> >精</option>
		   		<option value="3" <c:if test="${'3'==label}">selected</c:if> >新</option>
	    </select>
	    <label>&nbsp;</label><input name="" type="button" onclick="sel()" class="btn" value=" 查 询 "/>
	    &nbsp;
	    <c:if test="1!=1">
	    <input id="btn" type="button" onclick="exp()" class="btn" value="导出"/>
	    </c:if>
	    <br />
	    <input name="" type="button" onclick="updateLabelStatus(0,1)" class="btn" value=" 设为顶 "/>
		<input name="" type="button" onclick="updateLabelStatus(1,1)" class="btn" value=" 设为精 "/>
		<input name="" type="button" onclick="updateLabelStatus(2,1)" class="btn" value=" 设为新 "/>
		<input name="" type="button" onclick="updateLabelStatus(0,0)" class="btn" value=" 取消顶 "/>
		<input name="" type="button" onclick="updateLabelStatus(1,0)" class="btn" value=" 取消精 "/>
		<input name="" type="button" onclick="updateLabelStatus(2,0)" class="btn" value=" 取消新 "/>
	</form>
    </div>
    <!-- 查询条件 -->
    
    
    <!-- 查询列表 -->
    <table id="myTable" class="tablelist display">
    	<thead>
    	<tr>
    	<th><input type="checkbox" id="checkall" onclick="oncheckboxRadio(this)" name="checkall" /></th>
        <th>帖子ID</th>
    	<th>类型名称</th>
    	<th>帖子标题</th>
    	<th>帖子内容</th>
    	<th>发帖IP</th>
    	<th>所在地</th>
    	<th>发帖用户ID</th>
    	<th>发帖时间</th>
    	<th>评论数</th>
    	<th>阅读数</th>
    	<th>标签</th>
        <th>状态</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${topiclistList!=null}">
    	<c:forEach var="topiclist" items="${topiclistList}">
    		<tr>
    			<td><input type="checkbox" id="${topiclist.id}" value="${topiclist.id}-${topiclist.label}" name="checkbox_label"/></td>
    			<td><c:out value="${topiclist.id}"/></td>
				<td><c:out value="${topiclist.type_name}"/></td>
				<td width="15%" title="<c:out value="${topiclist.title}"/>">
					<div class="div_textflow150">
						<c:out value="${topiclist.title}"/>
					</div>
				</td>
				<td width="8%" title="<c:out value="${topiclist.content}"/>">
					<div class="div_textflow100">
						<c:out value="${topiclist.content}"/>
					</div>
				</td>		
				<td><c:out value="${topiclist.ip}"/></td>
				<td><c:out value="${topiclist.ip_address}"/></td>
				<td><c:out value="${topiclist.uid}"/></td>
				<td><c:out value="${topiclist.createtime}"/></td>
				<td><c:out value="${topiclist.countback}"/></td>
				<td><c:out value="${topiclist.countbrowse}"/></td>
				<td>
					<c:set var="xin"  value="${fn:substring(topiclist.label, 0, 1)}" /> 
					<c:set var="jing"  value="${fn:substring(topiclist.label, 1, 2)}" /> 
					<c:set var="ding"  value="${fn:substring(topiclist.label, 2, 3)}" /> 
					<font style='<c:if test="${xin=='1'}">color:red;</c:if>'>顶</font>
					<font style='<c:if test="${jing=='1'}">color:red;</c:if>'>精</font>
					<font style='<c:if test="${ding=='1'}">color:red;</c:if>'>新</font>
				</td>
    			<td>
    				<c:if test="${topiclist.status=='0'}"><font style="color:red;">对内</font></c:if>
    				<c:if test="${topiclist.status=='1'}"><font style="color:green;">对外</font></c:if>
    				<c:if test="${topiclist.status=='9'}"><font style="color:grey;">作废</font></c:if>
    			</td>
    			<td>
    				<a href="<%=basePath %>topiclist/toupdatetopiclist?id=<c:out value='${topiclist.id}'/>" class="tablelink">详情</a>     
    				<a href="<%=basePath %>topicreply/dotopicreplylist?topic_id=<c:out value='${topiclist.id}'/>" class="tablelink">回帖</a>
			        <a style="display: <c:if test='${topiclist.status=="0"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${topiclist.id}"/>','0');" class="tablelink">对内</a>
			        <a style="display: <c:if test='${topiclist.status=="1"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${topiclist.id}"/>','1');" class="tablelink">对外</a>
			        <a style="display: <c:if test='${topiclist.status=="9"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${topiclist.id}"/>','9');" class="tablelink">作废</a>
			        <a href="javascript:deletetopiclist('<c:out value="${topiclist.id}"/>')" class="tablelink">删除</a>
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
