package common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description = "学生から検定を検索するサーブレット", urlPatterns = "/referstu" )
public class ReferStu extends HttpServlet {
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
		
		//押されたsubmitによって処理を分岐させる
		if(request.getParameter("submitid") != null) {
			//Beanの使用
			DbinfoBean info = new DbinfoBean();
			info.setReferId(request.getParameter("referid"));
			session.setAttribute("stumessage", info.referstuid());
			System.out.println(info.referstuid());
			System.out.println("referstuid使った");
		}else if(request.getParameter("submitkana") != null) {
			//Beanの使用
			DbinfoBean info = new DbinfoBean();	
			info.setReferKana(request.getParameter("referkana"));
			session.setAttribute("stumessage", info.referstukana());
			System.out.println(info.referstukana());
			System.out.println("referstukana使った");
		}else{
			//Beanの使用
			DbinfoBean info = new DbinfoBean();
			info.setStuId(request.getParameter("stuid"));
			session.setAttribute("stumessage", info.referid());
			System.out.println(info.referid());
			System.out.println("referid使った");
		}
		
		//適切なページに遷移
		RequestDispatcher rd = request.getRequestDispatcher("/cms_man/refer_stu.jsp");
		rd.forward(request, response);
	}
}
