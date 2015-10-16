<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<jsp:include page="/include/base.jsp" />
  <head>
  <script type="text/javascript">
  	function toupdate(rightId){
  		if(rightId==null || ''==rightId){
  			alert("权限ID不能为空");
  			return false;
  		}
  		window.location.href="<%=basePath %>adminRole/toupdateadminright?rightId="+rightId;
  	}
  	
  	function deleteRight(rightId,rightName){
  		if(rightId==null || ''==rightId){
  			alert("权限ID不能为空");
  			return false;
  		}
  		if(rightName==null || ''==rightName){
  			alert("权限名不能为空");
  			return false;
  		}
  		if(confirm("您确定要删除权限为："+rightName+"吗？")){
  			var succ=0;
  			$.ajax({
	             type: "post",
	             url: "<%=basePath %>adminRole/todeleteadminright?callback=?",
	             data: {
	             		'rightId':rightId
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
		             	var msg=data.res;
		             	if(msg==1){
		             		alert("删除权限成功！");
		             		succ=1;
		             	}else{
		             		alert(msg);
		             	}
		             	//$("#msg").html(msg);
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
  	
  	function sel(){
  		$("#formsubmit").attr("action","<%=basePath %>adminRole/doadminrightlist");
  		$("#formsubmit").submit();
  	}
  	
  	function exp(){
  		$("#formsubmit").attr("action","<%=basePath %>adminRole/expadminrightlist");
  		$("#formsubmit").submit();
  	}
  	
  	function updateState(rightId,rightState){
  		if(rightId==null || ''==rightId){
  			alert("权限ID不能为空！");
  			return false;
  		}
  		if(rightState=='1'){
  			rightState='0';
  		}else{
  			rightState='1';
  		}
  		var succ=0;
  		$.ajax({
             type: "post",
             url: "<%=basePath %>adminRole/doupdateadminrightstatus?callback=?",
             data: {
             		'rightId':rightId,
             		'rightState':rightState
             	   },
             dataType: "json",
             async:	false,
             success: function(data,textStatus){
	             	var msg=data.res;
	             	if(msg=='1'){
	             		succ=1;
	             		alert("状态修改成功！");
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
  </script>
  </head>
  
  <body>
    <!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath %>index.jsp">首页</a></li>
	    <li>权限管理</li>
	    <li>权限列表</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>adminRole/toadminrightlist" class="selected">权限列表</a></li> 
	    <li><a href="<%=basePath %>adminRole/toaddadminright">权限添加</a></li> 
	  	</ul>
	</div> 
    
    <!-- 查询条件 -->    
    <div class="formbody" >
    	<form id="formsubmit" action="" method="post">
	    <label>权限ID：</label><input id="rightId" name="rightId" type="text" class="dfinput150" value="${rightId }"/><i></i>
	    <label>&nbsp;</label><input name="" type="button" onclick="sel()" class="btn" value=" 查 询 "/>
	    <c:if test="1!=1">
	    <label>&nbsp;</label><input name="" type="button" onclick="exp()" class="btn" value=" 导 出 "/>
	    </c:if>
	    </form>
    </div>
    <!-- 查询条件 -->
    
    <!-- 查询列表 -->
    <table id="myTable" class="tablelist display">
    	<thead>
    	<tr>
    	<th>权限ID</th>
        <th>权限名</th>
        <th>父ID</th> 
        <th>权限状态</th>
        <th>顺序</th> 
        <th>等级</th>
        <th>操作</th>
        </tr>
        </thead>
        
        <tbody>
    	<c:if test="${rightList!=null}">
    	<c:forEach var="right" items="${rightList}">
    		<tr>
    			<td><c:out value="${right.rightId}"/></td>
    			<td><c:out value="${right.rightName}"/></td>
    			<td><c:out value="${right.parentId}"/></td>
    			<td>
    				<c:if test="${right.rightState=='1'}"><font style="color:red;">停用</font></c:if>
    				<c:if test="${right.rightState=='0'}"><font style="color:green;">启用</font></c:if>
    			</td>
    			<td><c:out value="${right.showOrder}"/></td>
    			<td><c:out value="${right.rightLevel}"/></td>
    			<td>
    				<a href="javascript:toupdate('<c:out value="${right.rightId}"/>');" class="tablelink">
    					修改
    				</a>  
    				<a href="javascript:updateState('<c:out value="${right.rightId}"/>','<c:out value="${right.rightState}"/>');" class="tablelink" >
    					<c:if test="${right.rightState=='0'}">停用</c:if>
    					<c:if test="${right.rightState=='1'}">启用</c:if>
    				</a>
    				<a href="javascript:deleteright('<c:out value="${right.rightId}"/>','<c:out value="${right.rightName}"/>');" class="tablelink">
    					删除
    				</a>
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
