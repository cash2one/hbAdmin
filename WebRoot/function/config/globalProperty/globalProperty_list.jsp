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
  function   cli(Obj){
	  var collid = document.getElementById("all");
	  var coll = document.getElementsByName(Obj)
	  if (collid.checked){
	     for(var i = 0; i < coll.length; i++)
	       coll[i].checked = true;
	  }else{
	     for(var i = 0; i < coll.length; i++)
	       coll[i].checked = false;
	  }
  }
    function updateStatus(reg){
    	var box = document.getElementsByName("id");//将这个复选框声明成一个对象
        var country = box.length; //得到checkbox个数
        var newsid =""; //new Array();
        for(var i=0;i<country;i++){   
        if(box[i].checked){   
	        newsid+=box[i].value+"-"; 
	        }
        }
        newsid = newsid.substring(0,newsid.lastIndexOf("-"));
        if(newsid==null || ''==newsid){
  			alert("请勾选要修改的列！");
  			return false;
  		}
        var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>globalProperty/updateStatus?callback=?",
	             data: {
	             		'property_status':reg,
	             		'id':newsid
	             	   },
	             dataType: "json",
	             async:	false,
	             success: function(data,textStatus){
		             	var msg=data.res;
		             	if(msg=='1'){
		             		succ=1;
		             		alert(data.su);
		             	}else{
		             		alert(data.su);
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
    
  
  function deleteMessage(mid){
  		if(mid==null || ''==mid){
  			alert("类型序列号不能为空！");
  			return false;
  		}
  		if(confirm("您确定要删除吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>globalProperty/delete?callback=?",
	             data: {
	             		'id':mid,
	             		'reg':1
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
 	 
 	 function deleteSelect(){
  		var box = document.getElementsByName("id");//将这个复选框声明成一个对象
        var country = box.length; //得到checkbox个数
        var faq_id =""; //new Array();
        for(var i=0;i<country;i++){   
        if(box[i].checked){   
        faq_id+=box[i].value+"-"; 
        }
        }
        faq_id = faq_id.substring(0,faq_id.lastIndexOf("-"));
        if(faq_id==null || ''==faq_id){
  			alert("请勾选要删除的问题");
  			return false;
  		}
  		if(confirm("您确定要删除吗？")){
  			var succ=0;
			$.ajax({
	             type: "post",
	             url: "<%=basePath %>globalProperty/delete?callback=?",
	             data: {
	             		'id':faq_id,
	             		'reg':2
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
	    <li>全局配置</li>
	    <li>属性查询</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>globalProperty/search" class="selected">属性查询</a></li> 
	    <li><a href="<%=basePath %>globalProperty/toadd?index=${index }">属性添加</a></li> 
	  	</ul>
	</div> 
    
    <!-- 查询条件 -->    
    <div class="formbody" >
    	 <form id="formsubmit" action="" method="post" >
	    <input id="all" name="all" type="checkbox" onclick="cli('id');" />选中全部/取消
	    &nbsp;<input name="" type="button" onclick="javascript:deleteSelect();" class="btn" value=" 全部删除 "/>
	    &nbsp;<input name="" type="button" onclick="updateStatus(1)" class="btn" value=" 显示 "/>
	    &nbsp;<input name="" type="button" onclick="updateStatus(0)" class="btn" value=" 隐藏 "/>
	</form>
    </div>
    <!-- 查询条件 -->
    
    <!-- 查询列表 -->
    <table id="myTable" class="tablelist display">
    	<thead>
    	<tr>
    	<th></th>
    	<th>主键ID</th>
        <th>属性名称</th>
        <th>属性状态</th>
        <th>阶段</th>
        <th>属性类型</th>
    	<th>创建时间</th>
    	<th>排序</th>
    	<th>数量</th>
    	<th>创建管理员账号</th>
    	<th>更新管理员账号</th>
    	<th>更新时间</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
    	<c:if test="${gpList!=null}">
    	<c:forEach var="gp" items="${gpList}">
    		<tr>
    			<td><input type="checkbox" name="id" value="${gp.id }"></input></td>
    			<td><c:out value="${gp.id}"/></td>
    			<td title="<c:out value="${gp.property_name}"/>"><c:out value="${gp.property_name}"/></td>
    			<td>
    				<c:if test="${gp.property_status=='0'}"><font style="color:red;">不显示</font></c:if>
    				<c:if test="${gp.property_status==1}"><font style="color:green;">显示</font></c:if>
    				<c:if test="${gp.property_status==9}"><font style="color:green;">作废</font></c:if>
    			</td>
    			<td><c:out value="${gp.level_id}"/></td>
    			<td>
    				<c:if test="${gp.property_type=='1'}"><font style="color:red;">必选项</font></c:if>
    				<c:if test="${gp.property_type==2}"><font style="color:green;">兴趣项</font></c:if>
    			</td>
				<td><c:out value="${gp.create_date}"/></td>
				<td>
					<c:if test="${gp.sort==-9999999}"><font style="color:green;">未排序</font></c:if>
    				<c:if test="${gp.sort!=-9999999}"><c:out value="${gp.sort}"/></c:if>
				</td>
				<td><c:out value="${gp.property_num}"/></td>
				<td><c:out value="${gp.create_adminuser}"/></td>
				<td>
					<c:out value="${gp.update_adminuser}"/>
				</td>
				<td>
					<c:out value="${gp.update_date}"/>
				</td>
    			<td>
    				<a href="<%=basePath %>globalProperty/toupdate?id=<c:out value='${gp.id}'/>&index=${index }" class="tablelink">修改</a>     
			        <a href="javascript:deleteMessage('<c:out value="${gp.id}"/>')" class="tablelink">删除</a>
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
