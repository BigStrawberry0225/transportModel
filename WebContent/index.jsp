<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.jdbc.conn.*" %>
<!DOCTYPE html>
<%String userID = (String)session.getAttribute("userID");%>
<%  InfoOperate userInfo = new InfoOperate(new ConnectionClass()); 
	String sql="SELECT * FROM city";
	ResultSet rs1=userInfo.getptms(sql).executeQuery();
	ResultSet rs2=userInfo.getptms(sql).executeQuery();
%>
<html>
<head>
<meta charset="UTF-8">
<title>交通咨询模拟系统</title>
<link href="css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>
     <div id="header">
     	<div id="daohang">
     		<center><font size="+2" color="#c0c0c0">全国交通咨询模拟系统</font>
     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="yellow">欢迎您，<%=userID%></font></center>
			
		</div>
     </div>
    <div id="query">  
	    <form class="staritem" action="outcome.jsp" method="post">
	    	<font size="+1" color="purple">起始地</font>
			<select class="f" name="from">
			<%String cname1;
			int cid1;
			while(rs1.next()){
				cname1 = rs1.getString("cname");
				cid1 = rs1.getInt("cid");
				out.print("<option value=\""+cid1+"\">"+cname1+"</option>");
			}%>
			</select>
			<font size="+1" color="purple">目的地</font>
			<select class="f" name="to">
			<%String cname2;
				int cid2;
				while(rs2.next()){
				cname2 = rs2.getString("cname");
				cid2 = rs2.getInt("cid");
				out.print("<option value="+cid2+">"+cname2+"</option>");
			}%>
			</select>
			<font size="+1" color="purple">出发时间</font>
			<input type="text" name="begin_time">
			<font size="+1" color="purple">出行方式</font>
			<select class="f" name="by">
				<option value="train">火车</option>
				<option value="plane">飞机</option>
			</select>
			<font size="+1" color="purple">出行方案</font>
			<select class="way" name="way">
				<option value="money">花费最少方案</option>
				<option value="time">时间最短方案</option>
				<option value="convenience">中转次数最少方案</option>
			</select>
			<input type="submit" value="查询" />
		</form>  	
    </div>
		
</body>
</html>
