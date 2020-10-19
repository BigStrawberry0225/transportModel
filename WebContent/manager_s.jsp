<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.jdbc.conn.*" %>
<%@ page import="java.util.Date" %>
<% InfoOperate userInfo = new InfoOperate(new ConnectionClass()); 
	String sql1="SELECT*FROM way_train";
	String sql2="SELECT*FROM way_plane";	
	ResultSet rs1=userInfo.getptms(sql1).executeQuery();
	ResultSet rs2=userInfo.getptms(sql2).executeQuery();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理界面</title>
<link href="css/manager_ss.css" rel="stylesheet" type="text/css"/>
<script>

</script>
</head>
<body>	
<div id="content">
	<%boolean isEmpty1 = true;
	 out.print("<p>列车信息表</p>");
	 out.print("<table>");
	 while(rs1.next()){
		isEmpty1 = false;
		out.print("<tr><td>列车号</td><td>发车时间</td><td>到站时间</td><td>起始地</td><td>目的地</td><td>花费</td></tr>");
		String number = rs1.getString("number");
		String begin_time = rs1.getString("begin_time");
		String end_time = rs1.getString("end_time");
		String from = rs1.getString("from");
		String to = rs1.getString("to");
		String cost = rs1.getString("cost");
		out.print("<tr><td>"+number+"</td><td>"+begin_time+"</td><td>"+end_time+"</td><td>"+from+"</td><td>"+to+
				"</td><td>"+cost+"</td><td><form action=\"delete_train?number="+number+"&begin_time="+begin_time+
				"\" method=\"post\"><input type=\"submit\" id=\"delete\" value=\"删除\"></form></td></tr>");
	 }
	 out.print("</table>");
	 if(isEmpty1){
	 	out.print("<center><p>尚无列车信息</p></center>");
	 }%>
	 <center><p><font size="+2" color="deepgreen">添加列车信息</font></p>
	 <form action="fadd_train" method="post">
            <input type="text" name="trainfile">
            <input type="submit" value="文件添加">
    </form></center>
	
	<form action="add_train" method="post">
	 	<font face="微软雅黑">航班号:</font>
  		<input type="text" name="number">
  		<font face="微软雅黑">发车时间:</font>
		<input type="text" name="begin_time">
  		<font face="微软雅黑">到站时间:</font>
		<input type="text" coding="" name="end_time">
  		<font face="微软雅黑">起始地:</font>
		<input type="text" name="from">
  		<font face="微软雅黑">目的地:</font>
		<input type="text" name="to">
  		<font face="微软雅黑">花费:</font>
		<input type="text" name="cost">
		<input type="submit" value="添加">
	 </form>
	<%boolean isEmpty2 = true;
	out.print("<p>航班信息表</p>");
	out.print("<table>");
	while(rs2.next()){
		out.print("<tr><td>航班号</td><td>起飞时间</td><td>到达时间</td><td>起始地</td><td>目的地</td><td>花费</td><td></td></tr>");
		isEmpty2 = false;
		String number = rs2.getString("number");
		String begin_time = rs2.getString("begin_time");
		String end_time = rs2.getString("end_time");
		String from = rs2.getString("from");
		String to = rs2.getString("to");
		String cost = rs2.getString("cost");
		out.print("<tr><td>"+number+"</td><td>"+begin_time+"</td><td>"+end_time+"</td><td>"+from+"</td><td>"+to+
				"</td><td>"+cost+"</td><td><form action=\"delete_plane?number="+number+"&begin_time="+begin_time+
				"\" method=\"post\"><input type=\"submit\" id=\"delete\" value=\"删除\"></form></td></tr>");
	}
	out.print("</table>");
	if(isEmpty2){
		out.print("<center><p>尚无航班信息</p></center>");
	}%>
	<center><p><font size="+2" color="deepgreen">添加航班信息</font>
	 <form action="fadd_plane" method="post">
         <input type="text" name="planefile">
         <input type="submit" value="文件添加">
    </form></p></center>
	<form action="add_plane" method="post">
	 	<font face="微软雅黑">航班号:</font>
  		<input type="text" name="number">
  		<font face="微软雅黑">发车时间:</font>
		<input type="text" name="begin_time">
  		<font face="微软雅黑">到站时间:</font>
		<input type="text" name="end_time">
  		<font face="微软雅黑">起始地:</font>
		<input type="text" name="from">
  		<font face="微软雅黑">目的地:</font>
		<input type="text" name="to">
  		<font face="微软雅黑">花费:</font>
		<input type="text" name="cost">
		<input type="submit" value="添加">
	 </form>
</div>

</body>
</html>