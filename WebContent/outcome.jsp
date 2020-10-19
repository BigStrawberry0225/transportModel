<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.jdbc.conn.*" %>
<%request.setCharacterEncoding("UTF-8");
	InfoOperate userInfo = new InfoOperate(new ConnectionClass());
	
	String from1 = request.getParameter("from");
	//String sql2="SELECT * FROM test2";
	String to1 = request.getParameter("to");
	String begin_time1 = request.getParameter("begin_time");
	String by = request.getParameter("by");
	//String sql3="SELECT * FROM test3";
	String way = request.getParameter("way");
	String road=null;
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询结果</title>
<link href="css/outcome.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div>
		<a href="index.jsp">返回查询</a>
		<%
		System.out.println("选择的方式是"+by+",选择的原则是"+way);
		String[] str=userInfo.routemartix(from1,to1,begin_time1, by, way);
		road=userInfo.findWay(str,begin_time1,by,way);
		if(!road.equals("")){
			String[] numbers=road.split("-");
			if(by.equals("train")){
			out.print("<table><tr><td>列车号</td><td>发车时间</td><td>到站时间</td><td>起始地</td><td>目的地</td><td>花费</td></tr>");
			for(int i=0;i<numbers.length;i++){
				String sql="select*from way_train where number='"+numbers[i]+"'";
				ResultSet rs1=userInfo.getptms(sql).executeQuery();
				while(rs1.next()){
					String number = rs1.getString("number");
					String begin_time = rs1.getString("begin_time");
					String end_time = rs1.getString("end_time");
					String from = rs1.getString("from");
					String to = rs1.getString("to");
					String cost = rs1.getString("cost");
					out.print("<tr><td>"+number+"</td><td>"+begin_time+"</td><td>"+end_time+"</td><td>"+from+"</td><td>"+to+
							"</td><td>"+cost+"</td></tr>");	
				}
			}
			out.print("</table>");
			}
			if(by.equals("plane")){
				out.print("<table><tr><td>飞机号</td><td>起飞时间</td><td>着陆时间</td><td>起始地</td><td>目的地</td><td>花费</td></tr>");
				for(int i=0;i<numbers.length;i++){
					String sql="select*from way_plane where number='"+numbers[i]+"'";
					ResultSet rs1=userInfo.getptms(sql).executeQuery();
					while(rs1.next()){
						String number = rs1.getString("number");
						String begin_time = rs1.getString("begin_time");
						String end_time = rs1.getString("end_time");
						String from = rs1.getString("from");
						String to = rs1.getString("to");
						String cost = rs1.getString("cost");
						out.print("<tr><td>"+number+"</td><td>"+begin_time+"</td><td>"+end_time+"</td><td>"+from+"</td><td>"+to+
								"</td><td>"+cost+"</td></tr>");
					}
					out.print("</table>");
				}
			}
				out.print("<br>本次的路线是:");
				for(int j=0;j<str.length-1;j++){
					int temp=Integer.parseInt(str[j])+1;
					out.print(userInfo.getcname(temp)+"→");
				}
				int temp=Integer.parseInt(str[str.length-1])+1;
				out.print(userInfo.getcname(temp));
				if(way.equals("time")){
					out.print("<br>乘坐时间为"+userInfo.getSum()+"分钟");
					out.print("<br>等待时间为"+userInfo.getWait()+"分钟");
				}
				if(way.equals("money")){
					out.print("<br>此方案花费最少，为"+userInfo.getSum()+"元");
				}
				if(way.equals("convenience")){
					out.print("<br>此方案周转次数最少，为"+userInfo.getSum()+"次");
				}
		}
		%>	
	</div>
</body>
</html>