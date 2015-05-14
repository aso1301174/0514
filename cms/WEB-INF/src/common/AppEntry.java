package common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description = "検定の実施日を登録するサーブレット", urlPatterns = "/appentry" )
public class AppEntry extends HttpServlet {
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
		if(request.getParameter("party") != null) {
			//Beanの使用
			DbinfoBean info = new DbinfoBean();
			info.setPartyName(request.getParameter("partyname"));
			session.setAttribute("entrymessage", info.getparty());
			System.out.println(info.getparty());
			System.out.println("getparty使った");
		}else if(request.getParameter("entry") != null) {
			//Beanの使用
			DbinfoBean info = new DbinfoBean();
			info.setPartyId(request.getParameter("partyid"));
			info.setReferId(request.getParameter("partyname"));
			info.setCerName(request.getParameter("cername"));
			info.setCerDay(request.getParameter("cerday"));
			session.setAttribute("entrymessage", info.entryday());
			System.out.println(info.entryday());
			System.out.println("entryday使った");
		}else{
			//Beanの使用
			DbinfoBean info = new DbinfoBean();
			session.setAttribute("entrymessage", info.setdate());
			System.out.println(info.setdate());
			System.out.println("setdate使った");
		}
		
		//適切なページに遷移
		RequestDispatcher rd = request.getRequestDispatcher("/cms_man/app_entry.jsp");
		rd.forward(request, response);
	}
}
