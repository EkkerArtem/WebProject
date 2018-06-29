package ua.nure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() { super();
    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//User userhave = new User();
		User userhave = new User.Builder(request.getParameter("nicknamehave"),request.getParameter("emailhave")).phone("phonehave").build();
		//userhave.setNicke(request.getParameter("nicknamehave"));
		//userhave.setEmail(request.getParameter("emailhave"));
		//userhave.setPhone(request.getParameter("phonehave"));
		
		//User userwant = new User();
		User userwant = new User.Builder(request.getParameter("nicknamewant"),request.getParameter("emailwant")).phone("phonewant").build();
		//userwant.setNicke(request.getParameter("nicknamewant"));
		//userwant.setEmail(request.getParameter("emailwant"));
		//userwant.setPhone(request.getParameter("phonewant"));
		String dbtype = request.getParameter("dbSelect");

		if (dbtype.equals("mysql")) {
			if(!(userhave.getNicke().equals(userwant.getNicke()))){
				CareTaker care = new CareTaker();
				Originator origin = new Originator();
				origin.setNameM(userhave.getNicke());
				care.addMemento(origin.save());
			}
			//TODO сделать кнопку и привязать метод origin.restore(care.getMemento());!!!!
			MySQLUserDAO.getInctance().addListener(new AdmChangeDB());


			//TODO пройдет 2 звпроса в БД или 1???!
			MySQLUserDAO.getInctance().updateUser(userhave, userwant);
			MySQLUserDAO.getInctance().removeListener(new AdmChangeDB());

		} else{
		MyMongUserDAO monguser = new MyMongUserDAO();
		monguser.updateUser(userhave,userwant);
	}
    }

}
