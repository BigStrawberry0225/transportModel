package server;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.conn.ConnectionClass;
import com.jdbc.conn.InfoOperate;
@WebServlet("/fadd_train") 
public class Fadd_train extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	InfoOperate userInfo = new InfoOperate(new ConnectionClass());
        String path = request.getParameter("trainfile");
        System.out.print(path);
        userInfo.faddTrain(path);
	    request.getRequestDispatcher("/manager_s.jsp").forward(request, response);
    }
 
}