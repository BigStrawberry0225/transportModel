package com.jdbc.conn;
import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class InfoOperate{
	private ConnectionClass connectionclass=null;
	private Connection conn=null;
	private Statement stmt=null;
	private PreparedStatement ptms=null;
	private static int sum;
	private static int wait;
	public InfoOperate(ConnectionClass connclass) {
		conn=connclass.getConn();//取得连接类中的statement对象
		stmt=connclass.getStatement();//取得连接类中的sql语句
		//这样,当实例化一个连接类 其中的connection已连接 statement已连接,即可使用
	}
	public Statement getstmt() {
		return stmt;
	}
	public PreparedStatement getptms(String sql) {
		try {
			ptms= conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ptms;
	}
	//注册
	public boolean insertUser(String userID,String password) {
		try {
			if(!selectID(userID)) {
				String sql="insert into user values ('"+userID+"','"+password+"')";
				stmt.executeUpdate(sql);
			    return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//判断是否存在这个用户id
	public boolean selectID(String ID) {
		try {
			String sql="SELECT userID FROM user";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				String str=rs.getString("userID");
				if(ID.equals(str)) {
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	//登录
	public boolean selectPassword(String userID,String password) {
		try {
			String sql="SELECT userID,password FROM user";
				ResultSet rs=stmt.executeQuery(sql);//返回结果集
				while(rs.next()){
					String pswd=rs.getString("password");
					String id=rs.getString("userID");
					if(userID.equals(id)&password.equals(pswd)) {
						return true;
					}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;	
	}
	//删除
		public void deleteTrain(String number,String begin_time) {
			try {
				String sql="DELETE FROM way_train where number='"+number+"'and begin_time='"+begin_time+"'";
				ptms=this.getptms(sql);
				ptms.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void deletePlane(String number,String begin_time) {
			try {
				String sql="DELETE FROM way_plane where number='"+number+"'and begin_time='"+begin_time+"'";
				ptms=this.getptms(sql);
				ptms.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	//添加
	public void addTrain(String number,String begin_time,String end_time,String from,String to,double cost) {
		try {
			String sql = "insert into way_train values ('"+number+"','"+begin_time+"','"+end_time+"','"+from+"','"+to+"',"+cost+")";
			ptms=this.getptms(sql);
			ptms.executeUpdate();
			if(!isexisted(from)) {
				String sql1 = "insert into city value('"+from+"',0"+")";
				ptms=this.getptms(sql1);
				ptms.executeUpdate();
			}
			if(!isexisted(to)) {
				String sql2 = "insert into city value('"+to+"',0"+")";
				ptms=this.getptms(sql2);
				ptms.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addPlane(String number,String begin_time,String end_time,String from,String to,double cost) {
		try {
			String sql="insert into way_plane values ('"+number+"','"+begin_time+"','"+end_time+"','"+from+"','"+to+"',"+cost+")";
			ptms=this.getptms(sql);
			ptms.executeUpdate();
			if(!isexisted(from)) {
				String sql1 = "insert into city value('"+from+"',0"+")";
				ptms=this.getptms(sql1);
				ptms.executeUpdate();
			}
			if(!isexisted(to)) {
				String sql2 = "insert into city value('"+to+"',0"+")";
				ptms=this.getptms(sql2);
				ptms.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//文件添加
	public void faddTrain(String path) {
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String str;
			String results[];
			String number,begin_time,end_time,from,to;
			double cost;
			while((str=br.readLine())!=null) {
				results = str.split(",");
				number = results[0];
				begin_time = results[1];
				end_time = results[2];
				from = results[3];
				to = results[4];
				cost = Double(results[5]);
				System.out.print(number);
				addTrain(number,begin_time,end_time,from,to,cost);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void faddPlane(String path) {
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String str;
			String results[];
			String number,begin_time,end_time,from,to;
			double cost;
			while((str=br.readLine())!=null) {
				results = str.split(",");
				number = results[0];
				begin_time = results[1];
				end_time = results[2];
				from = results[3];
				to = results[4];
				cost = Double(results[5]);
				addPlane(number,begin_time,end_time,from,to,cost);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private double Double(String string) {
		// TODO Auto-generated method stub
		double d = Double.parseDouble(string);
		return d;
	}

	//添加的时候顺便添加城市
	public boolean isexisted(String cname) {
		try {
			boolean isexisted = false;
			String sql="SELECT * FROM city where cname='"+cname+"'";
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	//获取城市数量
	public int citycount() {
		int count = 0;
		try {
			String sql="SELECT * FROM city";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				count++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
	public static String[] Dijsktra(int[][]weight,int start,int end){
        int length = weight.length;
        int[] shortPath = new int[length];//存放从start到各个点的最短距离
        shortPath[start] = 0;//start到他本身的距离最短为0
        String path[] = new String[length];//存放从start点到各点的最短路径的字符串表示
        for(int i=0;i<length;i++){
            path[i] = start+"-"+i;
        }
        int visited[] = new int[length];//标记当前该顶点的最短路径是否已经求出，1表示已经求出
        visited[start] = 1;//start点的最短距离已经求出
        for(int count = 1;count<length;count++){
            int k=-1;
            int dmin = Integer.MAX_VALUE;
            for(int i=0;i<length;i++){
                if(visited[i]==0 && weight[start][i]<dmin){
                    dmin = weight[start][i];
                    k=i;
                }
            }
            //选出一个距离start最近的未标记的顶点     将新选出的顶点标记为以求出最短路径，且到start的最短路径为dmin。
            shortPath[k] = dmin;
            visited[k] = 1;
            //以k为中间点，修正从start到未访问各点的距离
            for(int i=0;i<length;i++){
                if(visited[i]==0 && weight[start][k]+weight[k][i]>0&&weight[start][k]+weight[k][i]<weight[start][i]){
                    weight[start][i] = weight[start][k]+weight[k][i];
                    path[i] = path[k]+"-"+i;
                }
            }
        }
        String []road=path[end].split("-");
        for(int i=0;i<road.length;i++){
        	System.out.println(road[i]);
        }
        setSum(shortPath[end]);
        System.out.println("从"+start+"出发到"+end+"的最短路径为："+path[end]+"="+shortPath[end]);
        return road;    
	}
	
	public String[] routemartix(String from,String to,String begin_time,String by,String way){
		String road[]=null;
		if(way.equals("money")) {
			int martix[][] = null;
			martix=getMoney(by);
			int fr = Integer.parseInt(from);
			int t = Integer.parseInt(to);
			road= Dijsktra(martix,fr-1,t-1);
		}
		if(way.equals("convenience")) {
			int martix[][] = null;
			martix=getCon(by);
			int fr = Integer.parseInt(from);
			int t = Integer.parseInt(to);
			road= Dijsktra(martix,fr-1,t-1);
		}
		if(way.equals("time")) {
			int martix[][] = null;
			martix=getTime(by);
			int fr = Integer.parseInt(from);
			int t = Integer.parseInt(to);
			road= Dijsktra(martix,fr-1,t-1);
		}
		return road;
	}
	public String findWay(String[] road,String start_time,String by,String way){
		String start=start_time;
		String txt="";
		for(int i=0;i<road.length-1;i++){
			try {
				String from=getcname(Integer.parseInt(road[i])+1);
				String to=getcname(Integer.parseInt(road[i+1])+1);

				System.out.println("nana"+from+","+to);
				String sql="select*from way_"+by+" b where b.from='"+from+"'and b.to='"+to+"'";
				
				ResultSet rs = this.getptms(sql).executeQuery();
				System.out.println(sql);
				while(rs.next()){
					String begin_time=rs.getString("begin_time");
					String end_time=rs.getString("end_time");
				
					if(timeless(start_time,begin_time)>=0){
						System.out.println(begin_time+"大于"+start_time);
						System.out.println("找到了出发时间");
						String number=rs.getString("number");
						start_time=end_time;
						if(i==road.length-2){
							if(road.length>2){
								String end=end_time;
								setWait(getSum()-timeless(start,end));
							}
							else{
								setWait(0);
							}
						}
						txt+=number+"-";
						System.out.println(txt);
						break;
					}
					else
						System.out.println(begin_time+"小于"+start_time);
				}
				System.out.println("第"+i+"趟结束");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println(txt);
		return txt;
	}
	public int[][] getMoney(String by){
		
		int length = citycount();
		//System.out.println("多长"+length);
		int martix[][] = new int[length][length];
		for (int i=0;i<length;i++) {
			for (int j=0;j<length;j++) {
				martix[i][j] = Integer.MAX_VALUE;
			}
		}
		if(by.equals("train")) {	
			try {
				String sql="SELECT * FROM way_train";
				ResultSet rs = this.getptms(sql).executeQuery();
				while(rs.next()){
					String from=rs.getString("from");
					String to=rs.getString("to");
					int cost = (int)rs.getDouble("cost");
					int f = getcid(from)-1;
					int t = getcid(to)-1;
					martix[f][t] = cost; 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}//返回结果集
			return martix;
		}
		else {
			try {
				String sql="SELECT * FROM way_plane";
				ResultSet rs = this.getptms(sql).executeQuery();
				while(rs.next()){
					String from=rs.getString("from");
					String to=rs.getString("to");
					int cost = (int)rs.getDouble("cost");
					int f = getcid(from)-1;
					int t = getcid(to)-1;
					martix[f][t] = cost; 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//返回结果集
			return martix;
		}
	}
public int[][] getCon(String by){
		
		int length = citycount();
		int martix[][] = new int[length][length];
		for (int i=0;i<length;i++) {
			for (int j=0;j<length;j++) {
				martix[i][j] = Integer.MAX_VALUE;
			}
		}
		if(by.equals("train")) {	
			try {
				String sql="SELECT * FROM way_train";
				ResultSet rs = this.getptms(sql).executeQuery();
				while(rs.next()){
					String from=rs.getString("from");
					String to=rs.getString("to");
					int f = getcid(from)-1;
					int t = getcid(to)-1;
					martix[f][t] = 1; 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}//返回结果集
			return martix;
		}
		else {
			try {
				String sql="SELECT * FROM way_plane";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					String from=rs.getString("from");
					String to=rs.getString("to");
					int f = getcid(from)-1;
					int t = getcid(to)-1;
					martix[f][t] = 1; 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//返回结果集
			return martix;
		}
	}
	private int[][] getTime(String by) {
		int length = citycount(); 
		int martix[][] = new int[length][length];
		for (int i=0;i<length;i++) {
			for (int j=0;j<length;j++) {
				martix[i][j] = Integer.MAX_VALUE;
			}
		}
			try {
				String sql="SELECT * FROM way_"+by;
				ResultSet rs = this.getptms(sql).executeQuery();
				while(rs.next()){
					String from=rs.getString("from");
					String to=rs.getString("to");
					String begin_time=rs.getString("begin_time");
					String end_time=rs.getString("end_time");
					int time=timeless(begin_time,end_time);
					int f = getcid(from)-1;
					int t = getcid(to)-1;
					martix[f][t] = time; 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}//返回结果集
		return martix;
	}
	//获取城市编号
	public int getcid(String cname) {
		int cid = 0;
		try {
			String sql = "select * from city where cname = '"+cname+"'";
			ResultSet rs = this.getptms(sql).executeQuery();
			rs.next();
			cid = rs.getInt("cid");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cid;
	}
	//获取城市名称
	public String getcname(int cid) {
		String cname = null;
		try {
			String sql = "select * from city where cid = '"+cid+"'";
			System.out.println(sql);
			ResultSet rs = this.getptms(sql).executeQuery();
			rs.next();
			cname = rs.getString("cname");
			System.out.println(cname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cname;
	}
	public int timeless(String fromtime,String endtime) {
		int time = 0;
		if(fromtime.equals("")){
			System.out.println("from为空");
			return time;
		}
		String dayandtime1[] = fromtime.split("-");
		String dayandtime2[] = endtime.split("-");
		String day1[] = dayandtime1[0].split("/");
		String time1[] = dayandtime1[1].split(":");
		String day2[] = dayandtime2[0].split("/");
		String time2[] = dayandtime2[1].split(":");
		time = (Integer.parseInt(day2[2])-Integer.parseInt(day1[2]))*24*60
				+(Integer.parseInt(time2[0])-Integer.parseInt(time1[0]))*60+
				(Integer.parseInt(time2[1])-Integer.parseInt(time1[1]));
		System.out.print("差值是"+time);
		return time;
	}
	public static int getSum() {
		return sum;
	}
	public static void setSum(int sum) {
		InfoOperate.sum = sum;
	}
	public static int getWait() {
		return wait;
	}
	public static void setWait(int wait) {
		InfoOperate.wait = wait;
	}
}




