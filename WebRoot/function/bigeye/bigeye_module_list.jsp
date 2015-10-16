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
    	$('#formsubmit').attr('action','<%=basePath %>bigeyemodule/dobigeyemodulelist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>bigeyemodule/expbigeyemodulelist');   
    	$('#formsubmit').submit();
    }
    
    function updatestatus(module_id,status){
  		if(module_id==null || ''==module_id){
  			alert("序列号不能为空！");
  			return false;
  		}
  		var succ=0;
  		$.ajax({
             type: "post",
             url: "<%=basePath %>bigeyemodule/doupdatestatusbigeyemodule",
             data: {
             		'module_id':module_id,
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
  
  function deletebean(module_id){
  		if(module_id==null || ''==module_id){
  			alert("模块序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+module_id+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>bigeyemodule/dodeletebigeyemodule",
	             data: {
	             		'module_id':module_id
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
	    <li>广告图管理</li>
	    <li>广告图模块查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>bigeyemodule/dobigeyemodulelist" class="selected">广告图模块查询</a></li> 
	    <li><a href="<%=basePath %>bigeyemodule/toinsertbigeyemodule">广告图模块添加</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
    	<label>序列号：</label><input id="module_id" name="module_id" type="text" class="dfinput150" value="${module_id }"/>
    	<label>状态：</label>
    	<select id="status" name="status" class="select select151">
	    	<option value="all">全部</option>
		   		<option value="0" <c:if test="${'0'==status}">selected</c:if> >对内</option>
		   		<option value="1" <c:if test="${'1'==status}">selected</c:if> >对外</option>
		   		<option value="9" <c:if test="${'9'==status}">selected</c:if> >作废</option>
	    </select>
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
        <th>模块序列号</th>
    	<th>名称</th>
    	<th>宽</th>
        <th>高</th>
        <th>状态</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${bigeyemoduleList!=null}">
    	<c:forEach var="bigeyemodule" items="${bigeyemoduleList}">
    		<tr>
    			<td><c:out value="${bigeyemodule.module_id}"/></td>
				<td><c:out value="${bigeyemodule.module_name}"/></td>
				<td><c:out value="${bigeyemodule.img_width}"/></td>
				<td><c:out value="${bigeyemodule.img_height}"/></td>
    			<td>
    				<c:if test="${bigeyemodule.status=='0'}"><font style="color:red;">对内</font></c:if>
    				<c:if test="${bigeyemodule.status=='1'}"><font style="color:green;">对外</font></c:if>
    				<c:if test="${bigeyemodule.status=='9'}"><font style="color:grey;">作废</font></c:if>
    			</td>
    			<td>
    				<a href="<%=basePath %>bigeyemodule/toupdatebigeyemodule?module_id=<c:out value='${bigeyemodule.module_id}'/>" class="tablelink">修改</a>     
			        <a style="display: <c:if test='${bigeyemodule.status=="0"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${bigeyemodule.module_id}"/>','0');" class="tablelink">对内</a>
			        <a style="display: <c:if test='${bigeyemodule.status=="1"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${bigeyemodule.module_id}"/>','1');" class="tablelink">对外</a>
			        <a style="display: <c:if test='${bigeyemodule.status=="9"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${bigeyemodule.module_id}"/>','9');" class="tablelink">作废</a>
			        <a href="javascript:deletebean('<c:out value="${bigeyemodule.module_id}"/>')" class="tablelink">删除</a>
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
