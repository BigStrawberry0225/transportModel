package server;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.conn.*;
@WebServlet("/add_train")
public class Add_train extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		InfoOperate userInfo = new InfoOperate(new ConnectionClass());
		System.out.println("添加火车");
	    String number = request.getParameter("number");
	    String begin_time = request.getParameter("begin_time");
	    String end_time = request.getParameter("end_time");
	    String from = request.getParameter("from");
	    String to = request.getParameter("to");
	    double cost = Double.parseDouble(request.getParameter("cost"));
	    userInfo.addTrain(number,begin_time,end_time,from,to,cost);
	    request.getRequestDispatcher("/manager_s.jsp").forward(request, response);
	}
}
