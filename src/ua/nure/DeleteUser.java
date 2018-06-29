package ua.nure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet(name = "DeleteUser", urlPatterns = {"/DeleteUser"})
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteUser() { super(); }

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//User userdel = new User();
		User userdel = new User.Builder(request.getParameter("nicknamehavetodel"),request.getParameter("emailhavetodel")).phone("phonehavetodel").build();
		//userdel.setNicke(request.getParameter("nicknamehavetodel"));
		//userdel.setPhone(request.getParameter("phonehavetodel"));
		//userdel.setEmail(request.getParameter("emailhavetodel"));

		MySQLUserDAO.getInctance().deleteUser(userdel);


	}

}
