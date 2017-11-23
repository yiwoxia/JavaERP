<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="background-image: url("${ctx}/resources/image/123.jpg"); ">
<div style="color:infotext; margin-top: 40px; margin-left: 50px;">
	<p style="font-size: 45px;">欢迎进入ERP管理系统！请登录:</p>
</div>
<div style="margin: 100px 500px;">

<div class="easyui-panel" title="Login to system" style="width:400px;padding:30px 70px 20px 70px;">
	<div style="margin-bottom:10px">
		<input class="easyui-textbox" id="name" style="width:100%;height:40px;padding:12px" data-options="prompt:'Username',iconCls:'icon-man',iconWidth:38">
	</div>
	<div style="margin-bottom:20px">
		<input class="easyui-textbox" id="password" type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'Password',iconCls:'icon-lock',iconWidth:38">
	</div>
	<div style="margin-bottom:20px">
		<input type="checkbox" checked="checked">
		<span>Remember me</span>
	</div>
	验证码：<input type="text" name="checkCode" id="checkCode"/>
		<img id="codeImg" src="${pageContext.request.contextPath}/checkImgServlet" onclick="refreshCode()"/>
	<div>
		<a href="javascript:login()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
			<span style="font-size:14px;">Login</span>
		</a>
	</div>
</div>
</div>
<script type="text/javascript">
function login(){
	var name = $("#name").val();
	var password = $("#password").val();
	var checkCode = $("#checkCode").val();
	$.ajax({
		 type : 'post',
        url : "${ctx}/adminAction_login.action", //url
        data :{"name":name,
        	"password":password,
        	"checkCode":checkCode
        	}, //data
        success : function(data) { //callback
       	if(data.status == Util.SUCCESS) {
       		$.messager.show({
				title:'系统提示',
				msg:data.msg,
				timeout:3000,
				showType:'fade'
			});
       		top.location.href="${ctx}/page_index_index.action";
           } else {
        	   $.messager.alert('系统提示',data.msg);
           }
       }
});
}
function refreshCode() {
	$("#codeImg").attr("src", "${pageContext.request.contextPath}/checkImgServlet?" + Math.random());
}
</script>
</body>
</html>