package common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description = "検定の情報を追加・更新するサーブレット", urlPatterns = "/appmaster" )
public class AppMaster extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			// TODO Auto-generated method stub 
		response.setContentType("text/html; charset=UTF-8");

		DbinfoBean info = new DbinfoBean();
		
		//sessionの使用
		HttpSession session = request.getSession(false);
		
		//押されたsubmitによって処理を分岐させる
		if(request.getParameter("insert") != null) {
			//Beanの使用
			info = new DbinfoBean();
			info.setPartyName(request.getParameter("partyname"));
			info.setCerName(request.getParameter("cername"));
			session.setAttribute("insertappmessage", info.insertapp());
			System.out.println(info.insertapp());
			System.out.println("insertapp使った");
		}else if(request.getParameter("update") != null) {
			//Beanの使用
			info = new DbinfoBean();
			info.getCertificationId();
			info.setReferId(request.getParameter("partyname"));
			info.setCerName(request.getParameter("cername"));
			session.setAttribute("updateappmessage", info.updateapp());
			session.setAttribute("text", info.getText());
			System.out.println(info.updateapp());
			System.out.println("updateapp使った");
		}else if(request.getParameter("select") != null){
			
		}else{
			//Beanの使用
			info = new DbinfoBean();
			session.setAttribute("appmastermessage", info.getappmaster());
			System.out.println(info.getappmaster());
			System.out.println("getappmaster使った");
			}
		
		//適切なページに遷移
		RequestDispatcher rd = request.getRequestDispatcher(info.getPath());
		rd.forward(request, response);
	}
}