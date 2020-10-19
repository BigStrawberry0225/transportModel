package client;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.conn.*;
@WebServlet("/login_user")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InfoOperate userInfo = new InfoOperate(new ConnectionClass());  
		String userID = request.getParameter("user_name");
	    String password = request.getParameter("user_password");
	    if(userInfo.selectPassword(userID, password)) {
	    	HttpSession session = request.getSession(true);
	    	session.setAttribute("userID", userID);
	    	System.out.println("登陆");
	    	request.getRequestDispatcher("/index.jsp").forward(request, response);
	    }
	    else {
	    	request.getRequestDispatcher("/login.jsp?isright=no").forward(request, response);
	    }
	}

}
