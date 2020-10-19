package client;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jdbc.conn.*;
@WebServlet("/register_user")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InfoOperate userInfo = new InfoOperate(new ConnectionClass());  
		String userID = request.getParameter("user_name");
	    String password = request.getParameter("user_password");
	    if(userInfo.insertUser(userID, password)) {
	    	request.getRequestDispatcher("/regist.jsp?okay=yes").forward(request, response);
	    }
	    else {
	    	request.getRequestDispatcher("/regist.jsp?okay=no").forward(request, response);
	    }
	}

}
