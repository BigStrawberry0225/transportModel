package server;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login_manager")
public class Login_m extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String password = request.getParameter("m_password");
	    if(password.equals("201800301177")) {
	    	request.getRequestDispatcher("/manager_s.jsp").forward(request, response);
	    }
	    else {
	    	request.getRequestDispatcher("/manager.jsp?isright=no").forward(request, response);
	    }
	}
}