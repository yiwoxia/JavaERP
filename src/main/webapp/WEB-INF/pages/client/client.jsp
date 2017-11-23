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
		url:'${ctx}/clientAction_findAll.action',
		method:'get',
		fit:true,
		singleSelect:false,
		toolbar:'#toolbar',
		rownumbers:true,
		fitColumns:true,
		pagination:true,
		columns:[[    
		     {field:'cb',checkbox:true,align:'center'},    
		     {field:'id',title:'(系统)编号',width:80,align:'center'},    
		     {field:'name',title:'客户姓名',width:100,align:'center'},    
		     {field:'level',title:'客户等级',width:80,align:'center'},    
		     {field:'zfb',title:'支付宝',width:80,align:'center'},    
		     {field:'weixin',title:'微信',width:100,align:'center'},    
		     {field:'salesperso',title:'所属销售员',width:100,align:'center'},    
		     {field:'phone',title:'电话',width:100,align:'center'},    
		     {field:'address',title:'住址',width:100,align:'center'},    
		     {field:'id_card',title:'身份证',width:100,align:'center'},    
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
//如果分配指派人，指派时间为当前时间
$(function(){
	$("#assignMan").combobox({
		onSelect:function(record){
			if(record.trueName!=''){
				$("#assignTime").val(Util.getCurrentDateTime());
			}else{
				$("#assignTime").val("");
			}
		}
	}); 
 });
var url;
/* 打开添加dialog */
function openAddDialog() {
	$("#dialog").dialog("open").dialog("setTitle","添加信息");
	$('#form').form("clear");
	$("#createMan").val('${currentUser.name}');
	$("#createTime").val(Util.getCurrentDateTime());
	url = "${ctx}/clientAction_addBatch.action";
	
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
	url = "${ctx}/clientAction_updateBatch.action";
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
		'name' : $("#nameSearch").val(),
		'level' : $("#levelSearch").val(),
		'salesperso' : $("#salespersoSearch").val(),
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
					"${ctx}/clientAction_deleteBatch.action",
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
function doExportExcel(){
	 		window.location.href="${ctx}/saleChance/exportExcel.action";
	 	}
	 	
	 	
	 	
	 	
function uploadExcel(){     
    //得到上传文件的全路径  
    //var fileName= $('#uploadExcel').filebox('getValue');  
   $('#questionTypesManage').form('submit', {    
	    url:"${ctx}/saleChance/inportExcel.action",    
	    onSubmit: function(){    
	        // do some check  
	        //validate none 做表单字段验证，当所有字段都有效的时候返回true。该方法使用validatebox(验证框)插件。 
	        // return false to prevent submit;  
	    },    
	    success:function(data){//正常返回ServerResponse
	    	var data = eval('(' + data + ')');
	    	if(data.status == Util.SUCCESS) {
	    		$.messager.show({
					title:'系统提示',
					msg:data.msg,
					timeout:3000,
					showType:'fade'
				});
	    		$("#datagrid").datagrid("reload");
	    	}
	    	else {
				$.messager.alert('系统提示',data.msg);
			}
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
		<div>
		      客户名称：<input style="width: 100px;" type="text" id="nameSearch"></input>
		       客户等级：<input type="text" id="levelSearch" class="easyui-combobox"
					 data-options="
					 	url:'${ctx}/dataDic_findCustomerLeveldic.action',
					 	valueField: 'value',
					 	textField: 'value',
					 	panelHeight:'auto'
					 	 "/>
			所属销售员：<input style="width: 100px;" type="text" id="salespersoSearch"></input>
		  <a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	<!-- toolbar 结束 -->
	
	<!-- 日历 -->
	<div id="cc" class="easyui-calendar"></div>
	
	<!-- 添加和修改的dialog 开始 -->
	<div id="dialog" style="width:650;height:280,padding: 10px 20px"  modal="true" closed="true">
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id"/>
			<table cellspacing="8px">
		   		<tr>
		   			<td>客户名称：</td>
		   			<td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
		   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   			<td>客户等级</td>
		   			<td><input type="text" id="level" name="level" class="easyui-combobox"
					 data-options="
					 	url:'${ctx}/dataDic_findCustomerLeveldic.action',
					 	valueField: 'value',
					 	textField: 'value',
					 	panelHeight:'auto'
					 	 "/>
					 </td>
		   		</tr>
		   		<tr>
		   			<td>支付宝：</td>
		   			<td><input type="text" id="zfb" name="zfb" /></td>
		   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   			<td>微信：</td>
		   			<td><input type="text" id="weixin" name="weixin" /></td>
		   		</tr>
		   		<tr>
		   			<td>所属销售员：</td>
		   			<td><input type="text" id="salesperso" name="salesperso" /></td>
		   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   			<td>电话：</td>
		   			<td><input type="text" id="phone" name="phone" /></td>
		   		</tr>
		   		<tr>
		   			<td>地址：</td>
		   			<td><input type="text" id="address" name="address" /></td>
		   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   			<td>身份证：</td>
		   			<td><input type="text" id="id_card" name="id_card" /></td>
		   		</tr>
		   	</table>
		</form>
	</div>
	<!-- 添加和修改的dialog 结束 -->

</body>
</html>


