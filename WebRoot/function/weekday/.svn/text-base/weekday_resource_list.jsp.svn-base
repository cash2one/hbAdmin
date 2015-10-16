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
    	$('#formsubmit').attr('action','<%=basePath %>weekdayresource/doweekdayresourcelist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>weekdayresource/expweekdayresourcelist');   
    	$('#formsubmit').submit();
    }
    
    
  function deleteweekdayresource(id){
  		if(id==null || ''==id){
  			alert("类型序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+id+"吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>weekdayresource/dodeteleweekdayresource",
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
	    <li>每周推荐</li>
	    <li>推荐统计查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>weekdayresource/doweekdayresourcelist" class="selected">推荐统计查询</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
    	<label>周期：</label><input id="weekday_id" name="weekday_id" type="text" class="dfinput150" value="${weekday_id }"/>
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
        <th>周期</th>
    	<th>开始时间</th>
    	<th>结束时间</th>
    	<th>音频</th>
        <th>视频</th>
        <th>动画</th>
        <th>绘本</th>
        <th>游戏</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${weekdayresourceList!=null}">
    	<c:forEach var="weekdayresource" items="${weekdayresourceList}">
    		<tr>
    			<td><c:out value="${weekdayresource.weekday_id}"/></td>
				<td><c:out value="${weekdayresource.start_date}"/></td>
				<td><c:out value="${weekdayresource.end_date}"/></td>
				<td>
					<a href="<%=basePath %>weekdayresource/doweekdayresourceinfolist?weekday_id=<c:out value='${weekdayresource.weekday_id}'/>&resource_type_id=1" class="tablelink">
						<c:out value="${weekdayresource.audio}"/>
					</a>
				</td>
				<td>
					<a href="<%=basePath %>weekdayresource/doweekdayresourceinfolist?weekday_id=<c:out value='${weekdayresource.weekday_id}'/>&resource_type_id=2" class="tablelink">
						<c:out value="${weekdayresource.video}"/>
					</a>
				</td>
				<td>
					<a href="<%=basePath %>weekdayresource/doweekdayresourceinfolist?weekday_id=<c:out value='${weekdayresource.weekday_id}'/>&resource_type_id=3" class="tablelink">
						<c:out value="${weekdayresource.animation}"/>
					</a>
				</td>
				<td>
					<a href="<%=basePath %>weekdayresource/doweekdayresourceinfolist?weekday_id=<c:out value='${weekdayresource.weekday_id}'/>&resource_type_id=4" class="tablelink">
						<c:out value="${weekdayresource.picturebook}"/>
					</a>
				</td>
				<td>
					<a href="<%=basePath %>weekdayresource/doweekdayresourceinfolist?weekday_id=<c:out value='${weekdayresource.weekday_id}'/>&resource_type_id=5" class="tablelink">
						<c:out value="${weekdayresource.game}"/>
					</a>
				</td>
    			<td>
    				<a href="<%=basePath %>weekdayresource/doweekdayresourceinfolist?weekday_id=<c:out value='${weekdayresource.weekday_id}'/>" class="tablelink">详情</a>     
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
