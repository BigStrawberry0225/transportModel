package server;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.conn.*;
@WebServlet("/delete_train")
public class Delete_train extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InfoOperate userInfo = new InfoOperate(new ConnectionClass());
		System.out.println("删除");
	    String number = request.getParameter("number");
	    String begin_time = request.getParameter("begin_time");
	    System.out.print(begin_time);
	    userInfo.deleteTrain(number,begin_time);
	    request.getRequestDispatcher("/manager_s.jsp").forward(request, response);
	}
}