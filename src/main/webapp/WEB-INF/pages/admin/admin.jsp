<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script>
$(function(){
	/*展示数据的datagrid表格*/
	$("#datagrid").datagrid({
		url:'${ctx}/adminAction_findAdmin.action',
		method:'get',
		fit:true,
		singleSelect:false,
		toolbar:'#toolbar',
		rownumbers:true,
		fitColumns:true,
		pagination:true,
		columns:[[    
		     {field:'cb',checkbox:true,align:'center'},    
		     {field:'id',title:'编号',width:80,align:'center'},    
		     {field:'name',title:'用户名',width:100,align:'center'},    
		     {field:'password',title:'密码(经MD5加密)',width:180,align:'center'},    
		     {field:'trueName',title:'真实姓名',width:80,align:'center'},    
		     {field:'department',title:'所属部门',width:100,align:'center'},    
		     {field:'role',title:'角色',width:100,align:'center'}    
		]]  
	});
	/*添加和修改弹出的dialog */
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


var url;
/* 打开添加dialog */
function openAddDialog() {
	$("#dialog").dialog("open").dialog("setTitle","添加信息");
	url = "${ctx}/adminAction_addAdmin.action";
	$('#form').form("clear");
	
}
/* 打开修改dialog */
function openUpdateDialog() {
	var selections = $("#datagrid").datagrid("getSelections");
	if(selections.length == 0) {
		$.messager.alert("系统提示", "请选择要修改的数据");
		return;
	}
	var row = selections[0];
	$("#dialog").dialog("open").dialog("setTitle","修改信息");
	url = "${ctx}/adminAction_updateAdmin.action";
	$('#form').form("load", row);
}

function closeDialog(){
	 $("#dialog").dialog("close");
}

function doSave(){
	$.messager.progress();	// 显示进度条
	$('#form').form('submit', {    
	    url:url,    
	    onSubmit: function(){    
	        // do some check  
	        if(!$(this).form("validate")){
	        	$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
	       		 return $(this).form("validate");
	        }
	        if($("#role").combobox("getValue") == "") {
	        	$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
	        	$.messager.alert("系统提示", "请选择用户角色");
	        	return false;
	        }
	        if($("#department").combobox("getValue") == "") {
	        	$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
	        	$.messager.alert("系统提示", "请选择部门");
	        	return false;
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
	    		$("#datagrid").datagrid("reload");
	    	}
	    	else {
				$.messager.alert('系统提示',data.msg);
			}
	    }    
	});  
}


function doSearch(){
	$("#datagrid").datagrid("load",{
		'trueName' : $("#trueNameSearch").val(),
		'role' : $("#roleSearch").val(),
		'department' : $("#departmentSearch").val()
	})
};

function doDelete() {
	$.messager.progress();	// 显示进度条
	var ids = Util.getSelectionsIds("#datagrid");
	if(ids.length == 0) {
		$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
		$.messager.alert('系统提示','请选择要删除的数据！');
		return;
	}
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	    	$.post(
					"${ctx}/adminAction_deleteAdmin.action",
					{ids:ids}, 
					function(data) {
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
						if(data.status == 0) {
							$.messager.show({
								title:'系统提示',
								msg:data.msg,
								timeout:3000,
								showType:'fade'
							});
							$("#datagrid").datagrid("reload");
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

</script>

</head>
<body>
	<table id="datagrid"></table>
	
	<!-- toolbar 开始 -->
	<div id="toolbar">
		<a class="easyui-linkbutton" href="javascript:openAddDialog()" iconCls="icon-add">添加</a>
		<a class="easyui-linkbutton" href="javascript:openUpdateDialog()" iconCls="icon-edit">修改</a>
		<a class="easyui-linkbutton" href="javascript:doDelete()" iconCls="icon-remove">删除</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<div>
		       真实姓名：<input type="text" id="trueNameSearch"></input>
		       角色：<input type="text" id="roleSearch" class="easyui-combobox"
					 data-options="
					 	url:'${ctx}/dataDic_findUserRoledic.action',
					 	valueField: 'value',
					 	textField: 'value',
					 	panelHeight:'auto'
					 	 "/>
	 	  所属部门：<input type="text" id="departmentSearch" class="easyui-combobox"
					 data-options="
					 	url:'${ctx}/dataDic_findDepartmentdic.action',
					 	valueField: 'value',
					 	textField: 'value',
					 	panelHeight:'auto'
					 	 "/>
		  <a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	<!-- toolbar 结束 -->
	
	<!-- 添加和修改的dialog 开始 -->
	<div id="dialog" class="easyui-dialog" closed="true" modal="true"
		style="width:650;height:280,padding: 10px 20px" buttons="#dialog-button">
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id"/>
			<table cellspacing="8px">
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;</td>
					<td>密码：</td>
					<td><input type="text" id="password" name="password" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td>真实姓名：</td>
					<td><input type="text" id="trueName" name="trueName" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;</td>
					<td>角色：</td>
					<td><input type="text" id="role" name="role" class="easyui-combobox"
					 data-options="
					 	url:'${ctx}/dataDic_findUserRoledic.action',
					 	valueField: 'value',
					 	textField: 'value',
					 	panelHeight:'auto'
					 	 "/><font color="red">*</font></td>
				</tr>
				<tr>
					<td>部门：</td>
					<td>
						<input type="text" id="department" name="department" class="easyui-combobox"
							 data-options="
							 	url:'${ctx}/dataDic_findDepartmentdic.action',
							 	valueField: 'value',
							 	textField: 'value',
							 	panelHeight:'auto'
							 	 "/>
						<font color="red">*</font>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 添加和修改的dialog 结束 -->
	

</body>
</html>