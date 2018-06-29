package ua.nure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Copyfmts")

public class CopyDbToSQL extends HttpServlet{
    public CopyDbToSQL(){super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MyMongUserDAO mong = new MyMongUserDAO();
        MySQLUserDAO.getInctance().putToDB(mong.getAllUsers());

    }

}
