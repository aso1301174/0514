package common;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description = "検定から学生を検索するサーブレット", urlPatterns =  "/referapp2" )
public class ReferApp2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			// TODO Auto-generated method stub 
		response.setContentType("text/html; charset=UTF-8");
		
		//sessionの使用
		HttpSession session = request.getSession(false);
		
		//Beanの使用
		DbinfoBean info = new DbinfoBean();
		info.setCerName(request.getParameter("cername"));
		session.setAttribute("message", info.referapp());
		System.out.println(info.referapp());
		System.out.println("referapp使った");
		
		//適切なページに遷移
		RequestDispatcher rd = request.getRequestDispatcher("/cms_man/refer_app.jsp");
		rd.forward(request, response);
		System.out.println("対象の学生とばした");
	}
}
