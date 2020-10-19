<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script>
	var error ='<%=request.getParameter("isright")%>';
	if(error=='no'){
		alert("用户名或密码错误");
	}
</script>

</head>
<body>
	<div id="header">
	   	<div id="denglu">
	   	<font color="#c0c0c0" size="+1" >没有账号？<a href="regist.jsp">去注册></a></font>
	   	<br/><br/>
	   	<font color="#c0c0c0" size="+1" ><a href="manager.jsp">管理员登录入口></a></font>
	   	</div>
  	</div>
   	<div id="maincontent">
	   	<div id="content">
	   		<form action="login_user" method="post">
		  		<font color="#ffffff" size="+2" face="微软雅黑">用户名:</font>
		  		<input type="text" name="user_name" placeholder="请输入用户名"><br/>
		  		<font color="#ffffff" size="+2" face="微软雅黑">密  &nbsp;&nbsp;码:</font>
				<input type="password" name="user_password" placeholder="请输入密码..."><br/>
				<input type="submit" value="登录">
			</form></div>
   	</div>
</body>
</html>