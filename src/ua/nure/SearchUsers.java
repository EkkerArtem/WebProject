package ua.nure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/searchall")

public class SearchUsers extends HttpServlet{
    public SearchUsers(){super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        MyMongUserDAO mong = new MyMongUserDAO();
        mong.SeartchUsers();
        long finish = System.currentTimeMillis();
        long resMONG = finish - start;
    }

}
