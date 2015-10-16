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
    	$('#formsubmit').attr('action','<%=basePath %>bigeye/dobigeyelist');   
    	$('#formsubmit').submit();
    }
    
    function exp(){
    	$('#formsubmit').type('action','<%=basePath %>bigeye/expbigeyelist');   
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
             url: "<%=basePath %>bigeye/doupdatestatusbigeye",
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
  
  
  function deletebigeye(id){
  		if(id==null || ''==id){
  			alert("序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除序列号为："+id+"的主贴吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>bigeye/dodeletebigeye",
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
	    <li>广告图管理</li>
	    <li>广告图查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>bigeye/dobigeyelist" class="selected">广告图查询</a></li> 
	    <li><a href="<%=basePath %>bigeye/toinsertbigeye">广告图添加</a></li> 
	  	</ul>
	</div> 
    
   <!-- 查询条件 -->    
    <div class="formbody" >
    <form id="formsubmit" action="" method="post" >
    	<label>ID：</label><input id="id" name="id" type="text" class="dfinput150" value="${id }"/>
    	<label>状态：</label>
    	<select id="status" name="status" class="select select151">
	    	<option value="all">全部</option>
		   		<option value="0" <c:if test="${'0'==status}">selected</c:if> >对内</option>
		   		<option value="1" <c:if test="${'1'==status}">selected</c:if> >对外</option>
		   		<option value="9" <c:if test="${'9'==status}">selected</c:if> >作废</option>
	    </select>
	    <label>连接类型：</label>
    	<select id="link_type" name="link_type" class="select select151">
	    	<option value="all">全部</option>
		   		<option value="0" <c:if test="${'0'==link_type}">selected</c:if> >连接到url</option>
		   		<option value="1" <c:if test="${'1'==link_type}">selected</c:if> >连接到帖子</option>
	    </select>
	    <label>模块：</label>
    	<select id="module_id" name="module_id" class="select select151">
	    	<option value="all">全部</option>
		   	<c:if test="${noabolish_bigeyemodule!=null}">
	   			<c:forEach var="bigeyemodule" items="${noabolish_bigeyemodule}">
	   				<option value="${bigeyemodule.module_id }" <c:if test="${bigeyemodule.module_id==module_id}">selected</c:if> >${bigeyemodule.module_name }</option>
	   			</c:forEach>
	   		</c:if>
	    </select>
	    <label>&nbsp;</label><input name="" type="button" onclick="sel()" class="btn" value=" 查 询 "/>
	    &nbsp;
	    <c:if test="1!=1">
	    <input id="btn" type="button" onclick="exp()" class="btn" value="导出"/>
	    </c:if>
	    <br />
	</form>
    </div>
    <!-- 查询条件 -->
    
    
    <!-- 查询列表 -->
    <table id="myTable" class="tablelist display">
    	<thead>
    	<tr>
        <th>ID</th>
    	<th>模块</th>
    	<th>图片</th>
    	<th>连接类型</th>
    	<th>连接/帖子ID/资源ID</th>
    	<th>标题</th>
    	<th>顺序</th>
        <th>状态</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${bigeyeList!=null}">
    	<c:forEach var="bigeye" items="${bigeyeList}">
    		<tr>
    			<td><c:out value="${bigeye.id}"/></td>
    			<td><c:out value="${bigeye.module_name}"/></td>
				<td width="15%" title="<c:out value="${bigeye.img_url}"/>">
						<a href="${bigeye.img_url}" target="_blank">
						<img src='<c:out value="${bigeye.img_url}"/>' width="150" height="100"/>
						</a>
				</td>
				<td>
    				<c:if test="${bigeye.link_type=='0'}"><font style="color:blue;">连接到url</font></c:if>
    				<c:if test="${bigeye.link_type=='1'}"><font style="color:green;">连接到帖子</font></c:if>
    				<c:if test="${bigeye.link_type=='2'}"><font style="color:red;">连接到资源</font></c:if>
    			</td>
				<td width="10%" title="<c:if test="${bigeye.link_type=='0'}"><c:out value="${bigeye.link_url}"/></c:if><c:if test="${bigeye.link_type=='1'}">进入帖子详情</c:if><c:if test="${bigeye.link_type=='2'}">进入资源详情</c:if>">
					<c:if test="${bigeye.link_type=='0'}">
					<div class="div_textflow100">
						<a href="${bigeye.link_url}" target="_blank">
						<c:out value="${bigeye.link_url}"/>
						</a>
					</div>
					</c:if>
    				<c:if test="${bigeye.link_type=='1'}">
    					<a href="<%=basePath %>topiclist/toupdatetopiclist?id=<c:out value='${bigeye.link_url}'/>" class="tablelink"><c:out value="${bigeye.link_url}"/></a> 
    				</c:if>
    				<c:if test="${bigeye.link_type=='2'}">
    					<a href="<%=basePath %>resource/toupdateresource?id=<c:out value='${bigeye.link_url}'/>" class="tablelink"><c:out value="${bigeye.link_url}"/></a> 
    				</c:if>
				</td>
				<td width="10%" title="<c:out value="${bigeye.content}"/>">
					<div class="div_textflow100">
						<c:out value="${bigeye.content}"/>
					</div>
				</td>
				<td><c:out value="${bigeye.sort}"/></td>
    			<td>
    				<c:if test="${bigeye.status=='0'}"><font style="color:red;">对内</font></c:if>
    				<c:if test="${bigeye.status=='1'}"><font style="color:green;">对外</font></c:if>
    				<c:if test="${bigeye.status=='9'}"><font style="color:grey;">作废</font></c:if>
    			</td>
    			<td>
    				<a href="<%=basePath %>bigeye/toupdatebigeye?id=<c:out value='${bigeye.id}'/>" class="tablelink">修改</a>     
			        <a style="display: <c:if test='${bigeye.status=="0"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${bigeye.id}"/>','0');" class="tablelink">对内</a>
			        <a style="display: <c:if test='${bigeye.status=="1"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${bigeye.id}"/>','1');" class="tablelink">对外</a>
			        <a style="display: <c:if test='${bigeye.status=="9"}'>none</c:if>" href="javascript:updatestatus('<c:out value="${bigeye.id}"/>','9');" class="tablelink">作废</a>
			        <a href="javascript:deletebigeye('<c:out value="${bigeye.id}"/>')" class="tablelink">删除</a>
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
