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

@WebServlet(urlPatterns="/logout")
public class logout extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String html = "";
		String mess = "";
		
		//セッション・オブジェクトの確認
		HttpSession session = request.getSession(false);
		//セッションが存在する場合
		if(session != null){
			session.invalidate();//セッションの破棄
			System.out.println("　はきしたかな");
			mess = "ログアウトしました";
		}else{
			mess = "セッションが存在しません";
		}
//		out.println(html);
//		out.println("<a href=/cms/cms_common/index.jsp>トップページへ戻る</a>");
		RequestDispatcher rd = request.getRequestDispatcher("/cms_common/logout.jsp");
		rd.forward(request, response);
		out.close();
	}

}
