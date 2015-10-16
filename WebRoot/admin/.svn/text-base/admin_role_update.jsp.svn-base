<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.manager.admin.entity.AdminRight" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <jsp:include page="/include/base.jsp" />
  <head>
  <style type="text/css">
  a:hover{text-decoration:none !important;color:#000 !important;}
  a{text-decoration:none !important;color:#000 !important;}
  input[type="checkbox"]{margin-right:5px;}
  </style>
  <script type="text/javascript" src="<%=basePath %>js/dtree_right.js" ></script>
  <script type="text/javascript">
			
			function editrole(roleId){
				var rolename=document.getElementById('rolename').value;
				var adminRights=document.getElementById('adminRights').value;
				var rolestate=document.getElementById('roleState').value;
				var admin=document.getElementById('admin').value;
				if(roleId==''){
					alert('角色ID不能为空');
					return;
				}
				if(rolename==''){
					alert('角色名不能为空');
					return;
				}
				if(adminRights==''){
					alert('未设置权限');
					return;
				}
				var but=document.getElementById('close');
				but.disabled=true;
				but.value='修改中...';
				$.ajax({
		             type: "post",
		             url: "<%=basePath %>adminRole/doupdateadminrole?callback=?",
		             data: {
		             		'roleId':roleId,
		             		'adminAccount':admin,
		             		'roleName':rolename,
		             		'roleState':rolestate,
		             		'adminRights':adminRights
		             	   },
		             dataType: "json",
		             async:	false,
		             success: function(data,textStatus){
			             	var msg=data.res;
			             	alert(msg);
			             	//$("#msg").html(msg);
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
	    <li><a href="<%=basePath %>index.jsp">首页</a></li>
	    <li>角色管理</li>
	    <li>角色修改</li>
	</ul>
    </div>
    <!-- 页面位置 -->
    
    <div class="rightinfo">
    <div class="itab">
	  	<ul> 
	    <li><a href="<%=basePath %>adminRole/toadminrolelist">角色列表</a></li> 
	    <li><a href="<%=basePath %>adminRole/toaddadminrole">角色添加</a></li> 
	  	</ul>
	</div> 
	
	<div id="tab1" class="tabson">
	    <ul class="forminfo">
	    	<li><label>角色名：<b>*</b></label><input type="text" id="rolename" name="rolename" value="${AdminRole.roleName }" size="31" class="dfinput150"/></li>
		    <li><label>角色状态：<b>*</b></label>
		    <select id="roleState" name="roleState" class="select select151">
		    	<option value="1" <c:if test="${AdminRole.roleState=='1' }">selected</c:if> >启用</option>
				<option value="0" <c:if test="${AdminRole.roleState=='0' }">selected</c:if>>停用</option>
		    </select>
		    </li>
		    <li><label>操作员：<b>*</b></label><input type="text" id="admin" name="admin" value="<%=session.getAttribute("admin_account") %>" class="dfinput150" readonly="readonly" /></li>
		    <li><label>设置权限：<b></b></label>
		    <div id="tdtree" style="vertical-align: top;">
		    	<div style="min-height:200px;overflow-y:auto;"> 						
								<script type="text/javascript">
				   				 	d = new dTree('d');
				   				 	d.add('',-1,"<input type=\"checkbox\" name=\"checkall\" id=\"checkall\" onclick=\"selectall(this);\"/>全选 &nbsp;&nbsp;<a href='javascript: d.openAll();'>全部展开</a> | <a href='javascript: d.closeAll();'>全部收缩</a>");
				   				 	<%
				   				 		Map<String ,List<AdminRight>> mp = (Map<String ,List<AdminRight>>)request.getAttribute("rights_mp");
				   				 		List<AdminRight> l0 = (List<AdminRight>)mp.get("r0");
										List<AdminRight> l1 = (List<AdminRight>)mp.get("r1");
										List<AdminRight> l2 = (List<AdminRight>)mp.get("r2");
										List<AdminRight> l3 = (List<AdminRight>)mp.get("r3");

				   				 		if(l0!=null && l0.size()>0){
				   				 			for(int j=0;j<l0.size();j++){
				   				 				//Object[] obj1 = (Object[]) l0.get(j);
				   				 				AdminRight ar0=new AdminRight();
				   				 				ar0=l0.get(j);
				   				 	%>
				   				 	//right_id,right_name
				   				 	d.add("r_"+<%=ar0.getRightId()%>,'',
				   				 		"<input type='checkbox' id='<%=ar0.getRightId()%>' name='p0' style='zoom:100%' value='<%=ar0.getRightId()%>' onclick='checkSubAll(this);'/><font color='blue'><%=ar0.getRightName()%></font>");
				   				 	<%
				   				 			}
				   				 		}
				   				 	%>
				   				 	<%
				   				 		if(l1!=null && l1.size()>0){
				   				 			for(int j=0;j<l1.size();j++){
				   				 				AdminRight ar1=new AdminRight();
				   				 				ar1=l1.get(j);
				   				 	%>
				   				 	//right_id,right_name,parent_id,parent_name
				   				 	d.add("r1_"+<%=ar1.getRightId()%>,"r_"+<%=ar1.getParentId()%>,
				   				 		"<input type='checkbox' id='<%=ar1.getRightId()%>' name='p<%=ar1.getParentId()%>' style='zoom:100%' value='<%=ar1.getRightId()%>,<%=ar1.getParentId()%>'  onclick='checkSubAll(this);'/><font color='brown'><%=ar1.getRightName()%></font>");
				   				 	<%
				   				 			}
				   				 		}
				   				 	%>
				   				 	<%
				   				 		if(l2!=null && l2.size()>0){
				   				 			for(int j=0;j<l2.size();j++){
				   				 				AdminRight ar2=new AdminRight();
				   				 				ar2=l2.get(j);
				   				 				//System.out.println(">>>right_id:"+obj3[0]+">>>right_name:"+obj3[1]+">>>parent_id:"+obj3[2]+">>>parent_name:"+obj3[3]);
				   				 	%>
				   				 	//right_id,right_name,parent_id,parent_name
				   				 	d.add("r2_"+<%=ar2.getRightId()%>,"r1_"+<%=ar2.getParentId()%>,
				   				 		"<input type='checkbox' id='<%=ar2.getRightId()%>' name='p<%=ar2.getParentId()%>' style='zoom:100%' value='<%=ar2.getRightId()%>,<%=ar2.getParentId()%>' onclick='checkSubAll(this);' /><font color='DarkViolet'><%=ar2.getRightName()%></font>");
				   				 	<%
				   				 			}
				   				 		}
				   				 	%>
				   				 	<%
				   				 		if(l3!=null && l3.size()>0){
				   				 			for(int j=0;j<l3.size();j++){
				   				 				AdminRight ar3=new AdminRight();
				   				 				ar3=l3.get(j);
				   				 	%>
				   				 	//right_id,right_name,parent_id,parent_name
				   				 	d.add("r3_"+<%=ar3.getRightId()%>,"r2_"+<%=ar3.getParentId()%>,
				   				 		"<input type='checkbox' id='<%=ar3.getRightId()%>' name='p<%=ar3.getParentId()%>' style='zoom:100%' value='<%=ar3.getRightId()%>,<%=ar3.getParentId()%>'  /><font color='Orange'><%=ar3.getRightName()%></font>");
				   				 	<%
				   				 			}
				   				 		}
				   				 	%>
				   				 	document.write(d);
				   				 </script>
				   				 <input type="hidden" id="strRight" name="strRight" value="${strRight}"/>
				   				 <input type="hidden" id="strShcendRight" name="strShcendRight" value="${strShendRight}"/>
								 <input type="hidden" name="myselectvalue" id="myselectvalue"/>
 								 <input type="hidden" id="adminRights" name="adminRights" value="" />
								 <span id="error_adminRights" class="text_14"></span>
								 <script type="text/javascript">
				   				 		var selectValue=document.getElementById('strRight').value;
										var str=selectValue.split(';');
										
										 var chkobj = document.getElementById("tdtree").getElementsByTagName("input");        
										 for(var k = 0, len = chkobj.length; k < len; k++)    
										 {        
											  var t = chkobj[k].type;        
											  if(chkobj[k].id!='checkall' && t=="checkbox")        
											  {
											  	for(var i=0;i<str.length;i++){
											  		if(str[i]==chkobj[k].id)
											  		{
														chkobj[k].checked=true;
														checkParent(chkobj[k]);
													}
											  	}
											  }
										 }
										
										function checkParent(chkobj)
										{
											if(chkobj.name!=null && chkobj.name!='p0')
											{
												var parentobj = document.getElementById(chkobj.name.substr(1,chkobj.name.length-1));
												parentobj.checked = true;
												checkParent(parentobj);
											}
										}
									</script>
							</div>
		    </div>
		    </li>
		    
		    <li><label><b></b></label><input type="button" value=" 修 改 " name="close" id="close" onclick="javascript:toConfirmRights();editrole('${AdminRole.roleId }');" class="btn"/>&nbsp;&nbsp;<input type="button" value="返 回" onclick="javascript:history.go(-1);" class="btn"/><span id="msg"></span></li>
		    
	    </ul>
	</div>
	</div>
  </body>
</html>
