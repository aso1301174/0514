package administrator;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class Test_management_update extends HttpServlet {
	
	@Resource(name="jdbc/MySQL")
	private DataSource jdbcMySql;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();	

		int k_id = (Integer.parseInt(request.getParameter("k_id")));
		String k_name = request.getParameter("k_name");
		String k_class = request.getParameter("k_class");
		String k_dantai = request.getParameter("k_dantai");
		String k_day = request.getParameter("k_day");
		
		//データベース接続情報の準備
		Connection conn = null;
		
		try {
			//接続
			conn = jdbcMySql.getConnection();		
			
			//SQL文格納のためのオブジェクト（PreparedStatementクラス）
			//実行結果を格納するためのオブジェクト（ResultSetクラス）
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			 //UPDATE文の設定・実行
			 String updateSql = "update test set test_name = ? , dantai = ? , class = ? where test_id = ?;";
			 stmt = conn.prepareStatement(updateSql);
			 stmt.setString(1,k_name);
			 stmt.setString(2,k_dantai);
			 stmt.setString(3,k_class);
			 stmt.setInt(4,k_id);
			 stmt.executeUpdate();//実行
			 
			//UPDATE文の設定・実行
			 String insertSql = "update day set day = ? where test_id = ?;";
			 stmt = conn.prepareStatement(insertSql);
			 stmt.setString(1,k_day);
			 stmt.setInt(2,k_id);
			 stmt.executeUpdate();//実行


			//SQL文設定の準備・SQL文
			String selectSql = "select test.test_id , test.test_name , test.dantai , test.class , day.day from test , day where test.test_id = ?;";
			stmt = conn.prepareStatement(selectSql);
			stmt.setInt(1,k_id);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				//String id = rs.getString("test_id");
				String name = rs.getString("test_name");
				String dantai = rs.getString("dantai");
				String t_class = rs.getString("class");

				session.setAttribute("test_name",name);
				session.setAttribute("dantai",dantai);
				session.setAttribute("t_class",t_class);
				session.setAttribute("k_day",k_day);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("./administrator/t_update.jsp");
			rd.forward(request,response);

			//リソースの開放
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			out.println("例外発生：" + e.getMessage());
			
		} finally {
			try {
			//絶対実行される処理
			if (conn != null) {
				conn.close();
				//out.println("データベース切断に成功しました");
				
			} else {
				out.println("コネクションがありません");
			}
		} catch (SQLException e) {
			out.println("SQLException発生：" + e.getMessage());
		}
		}
	}
}
