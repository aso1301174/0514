package administrator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//SQL関連で使用するクラスのロード
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.*;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class Student_management_delete extends HttpServlet {
	
	@Resource(name="jdbc/MySQL")
	private DataSource jdbcMySql;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String log_id = request.getParameter("log_id");
		
		//データベース接続情報の準備
		Connection conn = null;
		try {
			
			//接続
			conn = jdbcMySql.getConnection();			
			
			//SQL文格納のためのオブジェクト（PreparedStatementクラス）
			//実行結果を格納するためのオブジェクト（ResultSetクラス）
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			//DELETE文の設定・実行
			String deleteSql = "delete from login where log_id = ? and log_student= '1' ;";
			stmt = conn.prepareStatement(deleteSql);
			stmt.setString(1,log_id);
			stmt.executeUpdate();
			
			String deleteSql2 = "delete from situation where log_id = ?;";
			stmt = conn.prepareStatement(deleteSql2);
			stmt.setString(1,log_id);
			stmt.executeUpdate();
			
			//SQL文設定の準備・SQL文の実行
			String selectSql = "select * from login where log_student = '1' order by log_id asc;";
			stmt = conn.prepareStatement(selectSql);
			rs = stmt.executeQuery();
			
			//ﾃﾞｰﾀがある間、出力
			HashMap<String,String> user;
			ArrayList<HashMap<String,String>> userData = new ArrayList<HashMap<String,String>>();
			
			while (rs.next()) {
				String id = rs.getString("log_id");
				String name = rs.getString("log_name");
				
				user = new HashMap<String,String>();
				user.put("log_id", id);
				user.put("log_name", name);
				
				userData.add(user);

			}

			request.setAttribute("user", userData);
			
			RequestDispatcher rd = request.getRequestDispatcher("./administrator/s_delete.jsp");
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
