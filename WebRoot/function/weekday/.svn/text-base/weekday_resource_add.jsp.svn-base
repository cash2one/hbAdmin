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
  function sel(){
    	$('#formsubmit').attr('action','<%=basePath %>weekdayresource/toinsertweekdayresource');   
    	$('#formsubmit').submit();
    }
    
  function add(){
  		
  		var check=document.getElementsByName("buyersbean");
		var id_str='';
		for(var i=0 ;i<check.length;i++ ){
			if(check[i].checked){
				var v=check[i].value;
				id_str+=v+";";
			}
		}
 	 
  		if(id_str==''){
  			alert("请勾选要添加到计划的资源");
  			return false;
  		}
  		var weekday_id2=document.getElementById("weekday_id2").value;
  	 	var weekday_id=document.getElementById("weekday_id").value;
  	 	if(weekday_id==null || ''==weekday_id){
  	 		alert("周期不能为空！");
  	 		return;
  	 	}
  	 	if(weekday_id!=weekday_id2){
  	 		alert("请先查询后，在勾选添加内容！");
  	 		return;
  	 	}
  	 	
  	 	var but=document.getElementById('close');
  	 	var succ=0;
		but.disabled=true;
		but.value='添加中...';
		$.ajax({
             type: "post",
             url: "<%=basePath%>weekdayresource/doinsertweekdayresource",
             data: {
             		'resource_id_list':id_str,
             		'weekday_id':weekday_id
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
       but.disabled=false;
	   but.value=' 添 加 ';
	   if(succ==1){
         	window.location.reload();
       }  
				
  }
  
  function deleteweekdayresource(id){
  		if(id==null || ''==id){
  			alert("序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+id+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>weekdayresource/dodeleteweekdayresource",
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
		var check=document.getElementsByName("buyersbean");
		for(var i=0 ;i<check.length;i++ ){
			check[i].checked=obj.checked;
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
	   <li>每周推荐</li>
	    <li>每周推荐编辑</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
   	<div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>weekdayresource/doweekdayresourceinfolist?weekday_id=${weekday_id}" >每周推荐查询</a></li> 
	    <li><a href="<%=basePath %>weekdayresource/toinsertweekdayresource?weekday_id=${weekday_id}" class="selected">每周推荐编辑</a></li> 
	  	</ul>
	</div> 
	
	<form id="formsubmit" action="" method="post" >
	<div id="tab1" class="tabson">
	    
	    <fieldset style="padding:5px;width:45%;border:1px solid #c7c7c7;float:left;padding-bottom:20px;">
		<legend>所有资源</legend>
	    
	    
	     <div class="formbody" >
	    	<label>状态：</label>
	    	<select id="resource_status" name="resource_status" class="select select151">
		    	<option value="all">全部</option>
			   		<option value="0" <c:if test="${'0'==resource_status}">selected</c:if> >对内</option>
			   		<option value="1" <c:if test="${'1'==resource_status}">selected</c:if> >对外</option>
			   		<option value="9" <c:if test="${'9'==resource_status}">selected</c:if> >作废</option>
		    </select>
		    <label>类型：</label>
	    	<select id="resource_type_id" name="resource_type_id" class="select select151">
	    		<option value="all">全部</option>
		   		<c:if test="${noabolish_resourcetype!=null}">
		   			<c:forEach var="resourcetype" items="${noabolish_resourcetype}">
		   				<option value="${resourcetype.id }" <c:if test="${resourcetype.id==resource_type_id}">selected</c:if> >${resourcetype.type_name }</option>
		   			</c:forEach>
		   		</c:if>
		    </select>
		    <br/>
		    <label>兴趣：</label>
	    	<select id="hobby_id" name="hobby_id" class="select select151">
	    		<option value="all">全部</option>
		   		<c:if test="${noabolish_globalhobby!=null}">
		   			<c:forEach var="globalhobby" items="${noabolish_globalhobby}">
		   				<option value="${globalhobby.id }" <c:if test="${globalhobby.id==hobby_id}">selected</c:if> >${globalhobby.hobby_content }</option>
		   			</c:forEach>
		   		</c:if>
		    </select>
		    
		    <label>阶段：</label>
	    	<select id="level_id" name="level_id" class="select select151">
		    	<option value="all">全部</option>
		   		<c:if test="${noabolish_globallevel!=null}">
		   			<c:forEach var="globallevel" items="${noabolish_globallevel}">
		   				<option value="${globallevel.id }" <c:if test="${globallevel.id==level_id}">selected</c:if> >${globallevel.level_content }</option>
		   			</c:forEach>
		   		</c:if>
		    </select>
		    <br/>
		    <label>属性：</label>
	    	<select id="property_id" name="property_id" class="select select151">
		    	<option value="all">全部</option>
		   		<c:if test="${noabolish_globalproperty!=null}">
		   			<c:forEach var="globalproperty" items="${noabolish_globalproperty}">
		   				<option value="${globalproperty.id }" <c:if test="${globalproperty.id==property_id}">selected</c:if> >${globalproperty.property_name }</option>
		   			</c:forEach>
		   		</c:if>
		    </select>
		    <label>序列号：</label><input id="id" name="id" type="text" class="dfinput150" value="${id }"/>
	    	<br/>
	    	<label>名称：</label><input id="resource_content" name="resource_content" type="text" class="dfinput150" value="${resource_content }"/>
	    	<label>&nbsp;</label><input name="" type="button" onclick="sel()" class="btn" value=" 查 询 "/>
	    	<input type="button" value=" 添 加 " name="close" id="close" onclick="add();" class="btn"/>
	    </div>
	    
	    <!-- 查询列表 -->
	    <table id="myTable" class="tablelist display">
	    	<thead>
	    	<tr>
	    	<th><input type="checkbox" id="checkall" onclick="oncheckboxRadio(this)" name="checkall" /></th>
	        <th>序列号</th>
	    	<th>资源名称</th>
	    	<th>资源类型</th>
	        <th>状态</th>
	        </tr>
	        </thead>
	        <tbody>
	    	<c:if test="${resourceList!=null}">
	    	<c:forEach var="resource" items="${resourceList}">
	    		<tr>
	    			<td><input type="checkbox" value="${resource.id}" name="buyersbean"/></td>
	    			<td><c:out value="${resource.id}"/></td>
					<td><c:out value="${resource.resource_content}"/></td>
					<td><c:out value="${resource.type_name}"/></td>
	    			<td>
	    				<c:if test="${resource.resource_status=='0'}"><font style="color:red;">对内</font></c:if>
	    				<c:if test="${resource.resource_status=='1'}"><font style="color:green;">对外</font></c:if>
	    				<c:if test="${resource.resource_status=='9'}"><font style="color:grey;">作废</font></c:if>
	    			</td>
	    		</tr>
	    	</c:forEach>
	    	</c:if>
	    	</tbody>
	    </table>
	    ${pageinfo}
	    
    
	    </fieldset>
	    
	    <!-- ------------------------------------------------------------------------------------ -->
	    
	     <fieldset style="margin-left:15px;padding:5px;width:45%;border:1px solid #c7c7c7;float:left;">
		<legend>当前周期资源</legend>
		     <div class="formbody" >
			    <input id="weekday_id2" name="weekday_id2" type="hidden" class="dfinput150" value="${weekday_id }"/>
		    	<label>周期：</label>
		    	<select id="weekday_id" name="weekday_id" class="select select180">
			   		<c:if test="${wrlist!=null}">
			   			<c:forEach var="weekday" items="${wrlist}">
			   				<option value="${weekday.id }" <c:if test="${weekday.id==weekday_id}">selected</c:if> >${weekday.start_date }至${weekday.end_date }</option>
			   			</c:forEach>
			   		</c:if>
			    </select>
			    <label>&nbsp;</label><input name="" type="button" onclick="sel()" class="btn" value=" 查 询 "/>
		     </div>
		    <!-- 查询列表 -->
		    <table id="myTable" class="tablelist display">
		    	<thead>
		    	<tr>
		    	<th>开始时间</th>
		    	<th>结束时间</th>
		    	<th>资源类型</th>
		    	<th>资源ID</th>
		        <th>资源名称</th>
		        <th>操作</th>
		        </tr>
		        </thead>
		        <tbody>
		    	<c:if test="${weekdayresourceList!=null}">
		    	<c:forEach var="weekdayresource" items="${weekdayresourceList}">
		    		<tr>
						<td><c:out value="${weekdayresource.start_date}"/></td>
						<td><c:out value="${weekdayresource.end_date}"/></td>
						<td><c:out value="${weekdayresource.type_name}"/></td>
						<td><c:out value="${weekdayresource.resource_id}"/></td>
						<td><c:out value="${weekdayresource.resource_content}"/></td>
		    			<td>
		    				<a href="<%=basePath %>resource/toupdateresource?id=${weekdayresource.resource_id}" class="tablelink">详情</a>   
		    				<a href="javascript:deleteweekdayresource('<c:out value="${weekdayresource.id}"/>')" class="tablelink">删除</a>
		    			</td>
		    		</tr>
		    	</c:forEach>
		    	</c:if>
		    	<c:if test="${requestScope.msg!=null}">
					<tr >
						<td colspan="6" style="color:red;">${requestScope.msg}</td>
					</tr>
				</c:if>
		    	</tbody>
		    </table>	
			    	
	    </fieldset>
	</div>
	
	</form>
	
	
	</div>
	<script type="text/javascript" src="<%=basePath %>js/css.js"></script>
  </body>
</html>

