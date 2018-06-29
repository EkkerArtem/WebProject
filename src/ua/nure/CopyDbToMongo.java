package ua.nure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Copyfstm")
public class CopyDbToMongo extends HttpServlet {
    public CopyDbToMongo(){super();}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MyMongUserDAO mong = new MyMongUserDAO();

        mong.putToDB(MySQLUserDAO.getInctance().getAllUsers());

    }


}
