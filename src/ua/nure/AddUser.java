package ua.nure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "adduser", urlPatterns = {"/adduser","/chose"})
public class AddUser extends HttpServlet{
	
	public AddUser() {
        super();
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User user = new User();

		User user = new User.Builder(request.getParameter("nickname"),request.getParameter("email")).phone(request.getParameter("phone")).build();
		//user.setNicke(request.getParameter("nickname"));
		//user.setEmail(request.getParameter("email"));
		//user.setPhone(request.getParameter("phone"));

		String dbtype = request.getParameter("dbSelect");

		if (dbtype.equals("mysql")) {
			long start = System.currentTimeMillis();

			MySQLUserDAO.getInctance().addUser(user);

			long finish = System.currentTimeMillis();
			long resSQL = finish - start;
			System.out.println(resSQL);
		} else {
			long start = System.currentTimeMillis();
			MyMongUserDAO monguser = new MyMongUserDAO();


				monguser.addUser(user);

			long finish = System.currentTimeMillis();
			long resMONG = finish - start;
			System.out.println(resMONG);
		}
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
