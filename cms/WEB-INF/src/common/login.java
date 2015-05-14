package common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	
@WebServlet(description = "ログイン判定を行うサーブレット", urlPatterns =  "/login" ) 
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
	// TODO Auto-generated method stub PrintWriter out = response.getWriter();
		PrintWriter out = response.getWriter();
		out.println("Hello Servret!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			// TODO Auto-generated method stub 
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
			//Connection con = null;
		
		//Beanの使用
		DbinfoBean info = new DbinfoBean();			

		info.setId(request.getParameter("id"));
		info.setPw(request.getParameter("pw"));
		info.setUse(request.getParameter("use"));
		
		info.loginUser();
		
		if(info.getName() != null){
		if(info.getName().equals("")){
		}else{
			//session作成	
			HttpSession session = request.getSession(true);
			//初めてのアクセスの場合は、sessionオブジェクトにnameを設定
			if(session != null || session.getAttribute("id") != null){
				System.out.println("　通ってる？");
				session.setAttribute("name",info.getName());
				session.setAttribute("id", request.getParameter("id"));
			}
		}
		}else{
			
		}
		
		//適切なページに遷移（学生用or管理者用）
		RequestDispatcher rd = request.getRequestDispatcher(info.getPath());
		rd.forward(request, response);
	}
}