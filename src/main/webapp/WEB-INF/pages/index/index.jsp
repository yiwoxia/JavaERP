<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function openTab(text, url, iconCls) {
		if ($("#tabs").tabs("exists", text)) {
			$("#tabs").tabs("select", text);
		} else {
			var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='"
					+ url + "'></iframe>";
			$("#tabs").tabs("add", {
				title : text,
				iconCls : iconCls,
				closable : true,
				content : content
			});
		}
	}
	

	function logout(){
		$.messager.progress();	// 显示进度条
		$.messager.confirm('确认','您确认想要退出吗？',function(r){    
		    if (r){ 
		    	$.post(
						"${ctx}/logOut/logOut.action",
						function(data) {
							$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
							if(data.status == 0) {
								$.messager.show({
									title:'系统提示',
									msg:data.msg,
									timeout:3000,
									showType:'fade'
								});
								window.location.href="${ctx}/index/log.action";
							}
							else{
								$.messager.alert(data.msg);
							}
						},
						"json"
					);
		    }
		    else{
		    	$.messager.progress('close');
		    }
		}); 
	}

	//检查密码是否相同    
	$.extend($.fn.validatebox.defaults.rules, {    
	    equals: {    
	        validator: function(value,param){    
	            return value == $(param[0]).val();    
	        },    
	        message: '两次输入的密码不相同！'   
	    }    
	}); 
	//检查名字
	$.extend($.fn.validatebox.defaults.rules, {    
	    nameEquals: {
	        validator: function(value,param){
	        	var flag;
	        	$.ajax({
	        			 type : 'post',
	                     async : false,
	                     url : "${ctx}/index/checkName.action", //url
	                     data :{"name":value}, //data
	                     success : function(data) { //callback
	                    	if(data) {
	                    		flag = true; 
	                        } else {
	                        	flag = false; 
	                        }
	                    }
	        	});
	            return flag;    
	        },    
	        message: '请输入正确的用户名！'   
	    }    
	}); 
	//检查密码
	$.extend($.fn.validatebox.defaults.rules, {    
	    passwordEquals: {
	        validator: function(value,param){
	        	var flag;
	        	$.ajax({
	        			 type : 'post',
	                     async : false,
	                     url : "${ctx}/index/checkPassword.action", //url
	                     data :{
	                    	  "password":$(this).val(),
	                    	  "name" : $("#name").val()
	                    	 }, //data
	                     success : function(data) { //callback
	                    	if(data) {
	                    		flag = true; 
	                        } else {
	                        	flag = false; 
	                        }
	                    }
	        	});
	            return flag;    
	        },    
	        message: '请输入正确的密码！'   
	    }    
	}); 

	$(function(){
		
		/*修改密码弹出的dialog */
		$("#dialog").dialog({
			closed:'true',
			buttons:[
				{
					text:'保存',
					iconCls:'icon-ok',
					handler:function(){
						doSave();
					}
				},
				{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#dialog").dialog("close");
					}
				}
				
			]
			
		});
	});
	function doSave(){
		$.messager.progress();	// 显示进度条
		$('#form').form('submit', {    
		    url:'${ctx}/index/updatePassword.action',    
		    onSubmit: function(){    
		        // do some check  
		        if(!$(this).form("validate")){
		        	$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
		       		 return $(this).form("validate");
		        }
		        //validate none 做表单字段验证，当所有字段都有效的时候返回true。该方法使用validatebox(验证框)插件。 
		        // return false to prevent submit;  
		    },    
		    success:function(data){//正常返回ServerResponse
		    	$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
		    	var data = eval('(' + data + ')');
		    	if(data.status == Util.SUCCESS) {
		    		$.messager.show({
						title:'系统提示',
						msg:data.msg,
						timeout:3000,
						showType:'fade'
					});
		    		$("#dialog").dialog("close");
		    	}
		    	else {
					$.messager.alert('系统提示',data.msg);
				}
		    }    
		});  
	}

	function openPasswordModifyDialog(){
		$("#dialog").dialog("open").dialog("setTitle","修改密码");
		$('#form').form("clear");
	}
	
</script>
</head>
<body class="easyui-layout">
	<div region="north" style="height: 78px; background-color: #E0ECFF">
		<table style="padding: 5px" width="100%">
			<tr>
				<td width="50%"><img alt="logo"
					src="${pageContext.request.contextPath}/images/bglogo.png"></td>
				<td valign="bottom" align="right" width="50%"><font size="3">&nbsp;&nbsp;<strong>欢迎：</strong>${backuser.name }</font>【${backuser.trueName }】【${backuser.roleName }】
				</td>
			</tr>
		</table>
	</div>
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" data-options="iconCls:'icon-home'">
				<div align="center" style="padding-top: 100px">
					<font color="red" size="10">欢迎使用</font>
				</div>
			</div>
		</div>
	</div>
	<div region="west" style="width: 200px" title="导航菜单" split="true">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="库房管理" data-options="selected:true,iconCls:'icon-yxgl'"
				style="padding: 10px">
				<a
					href="javascript:openTab('营销机会管理','${ctx }/saleChance/index.action','icon-yxjhgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-yxjhgl'"
					style="width: 150px">营销机会管理</a> 
				<a
					href="javascript:openTab('客户开发计划','${ctx }/customerPlan/index.action','icon-khkfjh')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khkfjh'"
					style="width: 150px">客户开发计划</a>
			</div>
			<div title="产品管理" data-options="iconCls:'icon-khgl'"	style="padding: 10px;">
				<a	href="javascript:openTab('客户信息管理','${ctx }/customer/index.action','icon-khxxgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khxxgl'"
					style="width: 150px;">客户信息管理</a> 
					
				<a	href="javascript:openTab('客户流失管理','${ctx }/customerLoss/index.action','icon-khlsgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khlsgl'"
					style="width: 150px;">客户流失管理</a>
			</div>
			<div title="订单管理" data-options="iconCls:'icon-fwgl'"
				style="padding: 10px">
				<a
					href="javascript:openTab('服务创建','${ctx }/customerServiceCreate/index.action','icon-fwcj')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwcj'" style="width: 150px;">服务创建</a>
				<a
					href="javascript:openTab('服务分配','${ctx }/customerServiceAssign/index.action','icon-fwfp')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwfp'" style="width: 150px;">服务分配</a>
				<a
					href="javascript:openTab('服务处理','${ctx }/customerServiceDispose/index.action','icon-fwcl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwcl'" style="width: 150px;">服务处理</a>
				<a
					href="javascript:openTab('服务反馈','${ctx }/customerServiceFeedback/index.action','icon-fwfk')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwfk'" style="width: 150px;">服务反馈</a>
				<a
					href="javascript:openTab('服务归档','${ctx }/customerServiceFile/index.action','icon-fwgd')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwgd'" style="width: 150px;">服务归档</a>
			</div>
			<div title="财务管理" data-options="iconCls:'icon-tjbb'"
				style="padding: 10px">
				<a href="javascript:openTab('客户贡献分析','${ctx}/customer/indexContribute.action','icon-khgxfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khgxfx'"
					style="width: 150px;">客户贡献分析</a> <a
					href="javascript:openTab('客户构成分析','${ctx}/customer/indexConstitute.action','icon-khgcfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khgcfx'"
					style="width: 150px;">客户构成分析</a> <a
					href="javascript:openTab('客户服务分析','${ctx}/customer/indexService.action','icon-khfwfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khfwfx'"
					style="width: 150px;">客户服务分析</a> <a
					href="javascript:openTab('客户流失分析','${ctx}/customer/indexLose.action','icon-khlsfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khlsfx'"
					style="width: 150px;">客户流失分析</a>
			</div>
			<div title="客户管理" data-options="iconCls:'icon-jcsjgl'"
				style="padding: 10px">
				<a
					href="javascript:openTab('数据字典管理','${ctx}/dataDic/index.action','icon-sjzdgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-sjzdgl'"
					style="width: 150px;">数据字典管理</a> <a
					href="javascript:openTab('产品信息查询','${ctx}/product/index.action','icon-cpxxgl','icon-cpxxgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-cpxxgl'"
					style="width: 150px;">产品信息查询</a> 
					<a href="javascript:openTab('用户信息管理','${ctx}/page_staff_list.action','icon-user')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">用户信息管理</a>
			</div>
			<div title="员工管理" data-options="iconCls:'icon-jcsjgl'"
				style="padding: 10px">
				<a href="javascript:openTab('员工信息管理','${ctx}/page_staff_list.action','icon-user')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">员工信息管理</a>
			</div>
			<div title="系统" data-options="iconCls:'icon-item'"
				style="padding: 10px">
				<a href="javascript:openPasswordModifyDialog()"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-modifyPassword'"
					style="width: 150px;">修改密码</a> 
				<a href="javascript:logout()"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
			</div>
		</div>
	</div>
	<div region="south" style="height: 25px; padding: 5px" align="center">
		Java1707CRM管理系统
	</div>
	
	
	<!-- 修改密码的dialog 开始 -->
	<div id="dialog" class="easyui-dialog" closed="true" modal="true";
		style="width:480;height:380,padding: 10px 20px" buttons="#dialog-button">
		<form action="" id="form" method="post">
			<table cellspacing="8px">
				<tr>
					<td>用户名：</td>
					<td><input type="text" validType="nameEquals['#name']" id="name" missingMessage="请输入用户名" name="name" class="easyui-validatebox" required="true"/><font color="red" id="nameCheck">*</font></td>
				</tr>
				<tr>
					<td>原密码：</td>
					<td><input type="password" validType="passwordEquals['#password']" id="password" missingMessage="请输入原密码" class="easyui-validatebox" required="true"/><font color="red" id="passwordCheck">*</font></td>
				</tr>
				<tr>
					<td>新密码：</td>
					<td><input type="password" id="newPassword1" missingMessage="请输入新密码" name="password" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td>确认密码：</td>
					<td><input type="password" validType="equals['#newPassword1']"  id="newPpassword2" missingMessage="请确认新密码" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 修改密码的dialog 结束 -->

</body>
</html>