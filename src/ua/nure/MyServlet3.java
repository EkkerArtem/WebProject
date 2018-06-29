package ua.nure;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet3")
public class MyServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// response.setContentType("html/text/charset=utf8");
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
			String url = "jdbc:mysql://localhost/GamesDB?user=root&password=root";
		
			Connection con = null;
			try {
				con = DriverManager.getConnection(url);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			Statement st = null;
			try {
				st = con.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String sql1="Select Name,rating FROM Game ORDER BY rating";
			ResultSet rs = null;
			try {				rs = st.executeQuery(sql1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				while(rs.next())
				{
					response.getWriter().append((rs.getString("Name") +rs.getInt("rating")+ "  " + "</br>"));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/NewFile2.html");
			rd.forward(request,response);
			//response.sendRedirect(request.getContextPath() + 'NewFile2').forward(request, response);
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
