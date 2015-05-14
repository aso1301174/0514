package login_out;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Logout extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException{

        res.setContentType("text/html; charset=utf-8");
        PrintWriter out = res.getWriter();
        //session切る
        HttpSession session=req.getSession(false);
        if(session!=null)
        session.invalidate();

        res.sendRedirect("./index.html");
    }
}