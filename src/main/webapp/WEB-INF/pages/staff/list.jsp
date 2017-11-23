<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	/* 查找 */
	function doSearch(value){
		$("#datagrid").datagrid("load",{
			'name':$("#s_name").val(),
			'num':$("#s_num").val(),
			'role':$("#s_role").val()
		})
	}
	
	/* 删除 */
	function doDelete(){
		var ids = Util.getSelectionsIds("#datagrid");
		if (ids.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据");
			return;
		}
		$.messager.confirm("系统提示", "您确认要删除么", function(r){
			if (r){
				$.post(
					"${ctx}/staffAction_deleteBatch.action",
					{ids:ids}, 
					function(result) {
						$.messager.alert("系统提示", result.msg);
						if(result.status == Util.SUCCESS) {
							$("#datagrid").datagrid("reload");
						}
					},
					"json"
				);
			}
		})
	}
	
	var url;
	/* 打开添加dialog */
	function openAddDialog() {
		$("#dialog").dialog("open").dialog("setTitle","添加信息");
		url = "${ctx}/staffAction_addBatch.action";
		$('#form').form("clear");
		$('#date').val(Util.getCurrentTime());
		
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
		url = "${ctx}/staffAction_updateBatch.action";
		$('#form').form("load", row);
	}
	
	function closeDialog(){
		 $("#dialog").dialog("close");
	}
	/*添加或修改的dialog */
	function doSave(){
		$('#form').form('submit', {    
		    url:url,    
		    onSubmit: function(){    
		        // do some check    
		        //validate none 做表单字段验证，当所有字段都有效的时候返回true。该方法使用validatebox(验证框)插件。 
		        // return false to prevent submit;  
		        return $(this).form("validate");
		    },    
		    success:function(data){//正常返回ServerResponse
		    	var data = eval('(' + data + ')');
		    	if(data.status == Util.SUCCESS) {
		    		$.messager.alert("系统提示", data.msg);
		    		$("#dialog").dialog("close");
		    		$("#datagrid").datagrid("reload");
		    	}
		    }    
		});  
	}
	
	
	
	
	$(function(){
		/*展示数据的datagrid表格*/
		$("#datagrid").datagrid({
			url:'${ctx}/staffAction_pageQuery.action',
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
			     {field:'num',title:'ID',width:100,align:'center'},    
			     {field:'account',title:'员工账号',width:80,align:'center'},    
			     {field:'password',title:'员工密码',width:80,align:'center'},    
			     {field:'role',title:'员工角色',width:80,align:'center',formatter:function(value,row,index){
			    	 if(value == 1){
			    		 return "销售人员";
			    	 } else if(value == 2) {
			    		 return "销售经理";
			    	 }else if(value == 3) {
			    		 return "主管";
			    	 }}},    
			     {field:'name',title:'员工真实姓名',width:80,align:'center'},    
			     {field:'phone',title:'手机号',width:80,align:'center'},    
			     {field:'qq',title:'QQ号',width:80,align:'center'},    
			     {field:'wechat',title:'微信账号',width:80,align:'center'},    
			     {field:'date',title:'注册账号时间',width:80,align:'center'},    
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
	
	
</script>
</head>
<body>
	<table id="datagrid"></table>
	<!-- toolbar 开始 -->
	<div id="toolbar">
		<a class="easyui-linkbutton" href="javascript:openAddDialog()" iconCls="icon-add">添加</a>
		<a class="easyui-linkbutton" href="javascript:openUpdateDialog()" iconCls="icon-edit">修改</a>
		<a class="easyui-linkbutton" href="javascript:doDelete()" iconCls="icon-remove">删除</a>
		<!-- &nbsp;&nbsp;&nbsp;&nbsp;
		<input class="easyui-searchbox" data-options="prompt:'用户名',searcher:doSearch" style="width:150px"></input> -->
		<div >
		        员工真实姓名：<input type="text" id="s_name"></input>
		        员工账号：<input type="text" id="s_num"></input>
		        员工角色：<select id="s_role"  name="devResult" class="easyui-combobox" style="width:90px;">
		        	<option value="">--请选择--</option>
				    <option value="1" >销售人员</option>   
				    <option value="2" >销售经理</option>   
				    <option value="3" >主管</option> 
		        </select>
	 	  <a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	<!-- toolbar 结束 -->
	
<!-- 添加和修改的dialog 开始 -->
	<div id="dialog" class="easyui-dialog" closed="true"
		style="width:700;height:350,padding: 10px 20px" buttons="#dialog-button">
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id"/>
			<table cellspacing="8px">
				<tr>
					<td>ID：</td>
					<td><input type="text" id="num" name="num" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td>员工账号：</td>
					<td><input type="text" id="account" name="account" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>员工密码：</td>
					<td><input type="text" id="password" name="password" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td>员工角色：</td>
						<td>
						<select class="easyui-combobox" id="role" name="role" editable="false" panelHeight='auto'>
							<option value="">请选择</option>
							<option value="1">销售人员</option>
							<option value="2">销售经理</option>
							<option value="3">主管</option>
						</select>
						<font color="red">*</font>
					</td>	
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>员工真实姓名：</td>
					<td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
				</tr>
					<tr>
					<td>手机号：</td>
					<td><input type="text" id="phone" name="phone" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>QQ号：</td>
					<td><input type="text" id="qq" name="qq" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
				</tr>
				</tr>
					<tr>
					<td>微信账号：</td>
					<td><input type="text" id="wechat" name="wechat" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>注册账号时间：</td>
						<td>
					<input  id="date" name="date"  type= "text" class= "easyui-validatebox" />
					<font color="red">*</font></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 添加和修改的dialog 结束 -->

	<!-- dialog-button 开始 -->
	<div id="dialog-button">
		<a href="javascript:doSave()" calss="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDialog()" calss="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	<!-- dialog-button 结束 -->
</body>
</html>


