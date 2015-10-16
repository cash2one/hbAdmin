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
    	$('#formsubmit').attr('action','<%=basePath %>resource/doresourcelist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>resource/expresourcelist');   
    	$('#formsubmit').submit();
    }
    
    function updatestatus(id,resource_status){
  		if(id==null || ''==id){
  			alert("序列号不能为空！");
  			return false;
  		}
  		var succ=0;
  		$.ajax({
             type: "post",
             url: "<%=basePath %>resource/doupdatestatusresource",
             data: {
             		'id':id,
             		'resource_status':resource_status
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
  
  function deleteresource(id){
  		if(id==null || ''==id){
  			alert("类型序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+id+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>resource/dodeteleresource",
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
  
  
  function deleteOptions(id){
  		var ls_OptionsCount = document.getElementById(id).options.length; 
		for (ls_Count = ls_OptionsCount - 1; ls_Count >= 0; ls_Count--) {
			document.getElementById(id).remove(ls_Count);
		}
  }
  
  function createOption(obj_id,arr,pid,pname,old_id){
  		
  		var ls_Option = document.createElement("OPTION");
		ls_Option.innerHTML = '全部';
		ls_Option.value = 'all';
		document.getElementById(obj_id).appendChild(ls_Option);
		
	  	for (var i = 0; i < arr.length; i++) {
	  		ls_Option = document.createElement("OPTION");
	  		ls_Option.innerHTML = decodeURI(arr[i][pname]);
	  		ls_Option.value = arr[i][pid];
	  		if(old_id!=null){
	  			if(ls_Option.value==old_id) {
	  				ls_Option.selected = true;
	  			}
	  		}
			document.getElementById(obj_id).appendChild(ls_Option);
		}
		document.getElementById(obj_id).focus();
  }
  
  
  function changelevel(old_property_id){
  		var propertyObj=document.getElementById("property_id");
  		var level_id=document.getElementById("level_id").value;
 		
 	 	var property_arr=null;
  		if(level_id=='all'){
	        $.ajax({
	             type: "post",
	             url: "<%=basePath%>globalLevel/get_select_all_peoperty",
	             data: {
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
		             	var msg=data.res;
		             	property_arr=data.su;
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	             	alert("异常:"+errorThrown);
	             }
	         });
  		}else{
	        $.ajax({
	             type: "post",
	             url: "<%=basePath%>globalLevel/get_div_peoperty",
	             data: {
	             		'level_id':level_id
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
		             	var msg=data.res;
		             	property_arr=data.su;
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown){
	             	alert("异常:"+errorThrown);
	             }
	         });
  		}
         deleteOptions('property_id');
         createOption('property_id',property_arr,'property_id','property_name',old_property_id);
  }
    </script>
  </head>
  
  <body>
    <!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath %>index.jsp">首页</a></li>
	    <li>资源管理</li>
	    <li>资源查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>resource/doresourcelist" class="selected">资源查询</a></li> 
	    <li><a href="<%=basePath %>resource/toinsertresource">资源添加</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
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
	    <label>语言难度：</label>
    	<select id="language_level" name="language_level" class="select select151" >
    		<option value="all">全部</option>
    		<c:if test="${noabolish_globallanguage!=null}">
	   			<c:forEach var="globallanguage" items="${noabolish_globallanguage}">
	   				<option value="${globallanguage.id }" <c:if test="${globallanguage.id==language_level}">selected</c:if> >${globallanguage.lan_level_content }</option>
	   			</c:forEach>
	   		</c:if>
	    </select>
	    <label>阶段：</label>
    	<select id="level_id" name="level_id" class="select select151" onchange="changelevel(null,null)">
	    	<option value="all">全部</option>
	   		<c:if test="${noabolish_globallevel!=null}">
	   			<c:forEach var="globallevel" items="${noabolish_globallevel}">
	   				<option value="${globallevel.id }" <c:if test="${globallevel.id==level_id}">selected</c:if> >${globallevel.id }-${globallevel.level_content }</option>
	   			</c:forEach>
	   		</c:if>
	    </select>
	     <label>属性：</label>
    	<select id="property_id" name="property_id" class="select select151" >
	    	<option value="all">全部</option>
	    </select>
	    <script>
	    changelevel('${property_id}');
	    </script>
	    <br />
	    <label>序列号：</label><input id="id" name="id" type="text" class="dfinput150" value="${id }"/>
    	<label>名称：</label><input id="resource_content" name="resource_content" type="text" class="dfinput150" value="${resource_content }"/>
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
        <th>序列号</th>
    	<th>资源名称</th>
    	<th>资源简介</th>
    	<th>资源类型</th>
    	<th>关键词</th>
    	<th>创建时间</th>
    	<th>创建管理员</th>
        <th>更新时间</th>
        <th>更新管理员</th>
        <th>状态</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${resourceList!=null}">
    	<c:forEach var="resource" items="${resourceList}">
    		<tr>
    			<td><c:out value="${resource.id}"/></td>
				<td>
					<c:out value="${resource.resource_content}"/>
				</td>
				<td width="15%" title="<c:out value="${resource.resource_summary}"/>">
					<div class="div_textflow150">
						<c:out value="${resource.resource_summary}"/>
					</div>
				</td>		
				<td><c:out value="${resource.type_name}"/></td>
				<td><c:out value="${resource.resource_keyword}"/></td>
				<td><c:out value="${resource.create_date}"/></td>
				<td><c:out value="${resource.create_adminuser}"/></td>
				<td><c:out value="${resource.update_date}"/></td>
				<td><c:out value="${resource.update_adminuser}"/></td>
    			<td>
    				<c:if test="${resource.resource_status=='0'}"><font style="color:red;">对内</font></c:if>
    				<c:if test="${resource.resource_status=='1'}"><font style="color:green;">对外</font></c:if>
    				<c:if test="${resource.resource_status=='9'}"><font style="color:grey;">作废</font></c:if>
    			</td>
    			<td>
    				<a href="<%=basePath %>resource/toupdateresource?id=<c:out value='${resource.id}'/>" class="tablelink">修改</a>     
			        <a style="display: <c:if test='${resource.resource_status=="0"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${resource.id}"/>','0');" class="tablelink">对内</a>
			        <a style="display: <c:if test='${resource.resource_status=="1"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${resource.id}"/>','1');" class="tablelink">对外</a>
			        <a style="display: <c:if test='${resource.resource_status=="9"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${resource.id}"/>','9');" class="tablelink">作废</a>
			        
			        <!-- <a href="javascript:deleteType('<c:out value="${resource.id}"/>')" class="tablelink">删除</a> -->
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
