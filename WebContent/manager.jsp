<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员登录界面</title>
<link href="css/manager.css" rel="stylesheet" type="text/css">
<script>
	var error ='<%=request.getParameter("isright")%>';
	if(error=='no'){
		alert("管理通行密钥错误！");
	}
</script>
</head>
<body>
	<div id="header">
	   	<div id="denglu">
	   	<font color="#c0c0c0" size="+1" ><a href="login.jsp">用户登录入口></a></font>
	   	</div>
  	</div>
   	<div id="maincontent">
	   	<div id="content">
	   		<form action="login_manager" method="post">
		  		<font color="#ffffff" size="+2" face="微软雅黑">管理员密钥:</font>
		  		<input type="password" name="m_password"><br/>
		  		<input type="submit" value="登录">
			</form></div>
   	</div>
</body>
</html>