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
    	$('#formsubmit').attr('action','<%=basePath %>searchkeyword/dosearchkeywordlist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>searchkeyword/expsearchkeywordlist');   
    	$('#formsubmit').submit();
    }
    
    function updatetypestatus(id,status){
  		if(id==null || ''==id){
  			alert("序列号不能为空！");
  			return false;
  		}
  		//if(status=='1'){
  		//	status='0';
  		//}else{
  		//	status='1';
  		//}
  		var succ=0;
  		$.ajax({
             type: "post",
             url: "<%=basePath %>searchkeyword/doupdatestatussearchkeyword",
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
  
  function deletesearchkeyword(id){
  		if(id==null || ''==id){
  			alert("类型序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+id+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>searchkeyword/dodeletesearchkeyword",
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
  
    </script>
  </head>
  
  <body>
    <!-- 页面位置 -->
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="<%=basePath %>index.jsp">首页</a></li>
	    <li>搜索管理</li>
	    <li>搜索关键字查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>searchkeyword/dosearchkeywordlist" class="selected">搜索关键字查询</a></li> 
	    <li><a href="<%=basePath %>searchkeyword/toinsertsearchkeyword">搜索关键字添加</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
    	<label>序列号：</label><input id="id" name="id" type="text" class="dfinput150" value="${id }"/>
    	<label>状态：</label>
    	<select id="status" name="status" class="select select151">
	    	<option value="all">全部</option>
		   		<option value="0" <c:if test="${'0'==status}">selected</c:if> >对内</option>
		   		<option value="1" <c:if test="${'1'==status}">selected</c:if> >对外</option>
		   		<option value="9" <c:if test="${'9'==type_status}">selected</c:if> >作废</option>
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
        <th>序列号</th>
    	<th>关键字</th>
        <th>排序</th>
        <th>状态</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${searchKeywordList!=null}">
    	<c:forEach var="searchkeyword" items="${searchKeywordList}">
    		<tr>
    			<td><c:out value="${searchkeyword.id}"/></td>
				<td><c:out value="${searchkeyword.keyword_name}"/></td>
				<td><c:out value="${searchkeyword.sort}"/></td>
    			<td>
    				<c:if test="${searchkeyword.status=='0'}"><font style="color:red;">对内</font></c:if>
    				<c:if test="${searchkeyword.status=='1'}"><font style="color:green;">对外</font></c:if>
    				<c:if test="${searchkeyword.status=='9'}"><font style="color:grey;">作废</font></c:if>
    			</td>
    			<td>
    				<a href="<%=basePath %>searchkeyword/toupdatesearchkeyword?id=<c:out value='${searchkeyword.id}'/>" class="tablelink">修改</a>     
			        <a style="display: <c:if test='${searchkeyword.status=="0"}'>none</c:if>" href="javascript:updatetypestatus('<c:out value="${searchkeyword.id}"/>','0');" class="tablelink">对内</a>
			        <a style="display: <c:if test='${searchkeyword.status=="1"}'>none</c:if>" href="javascript:updatetypestatus('<c:out value="${searchkeyword.id}"/>','1');" class="tablelink">对外</a>
			        <a style="display: <c:if test='${searchkeyword.status=="9"}'>none</c:if>" href="javascript:updatetypestatus('<c:out value="${searchkeyword.id}"/>','9');" class="tablelink">作废</a>
			        <a href="javascript:deletesearchkeyword('<c:out value="${searchkeyword.id}"/>')" class="tablelink">删除</a>
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
