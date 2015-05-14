package administrator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.*;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class Student_management_insert extends HttpServlet {
	
	private int flg = 0;
	
	@Resource(name="jdbc/MySQL")
	private DataSource jdbcMySql;
	
	@SuppressWarnings("null")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();	

		String i_id = request.getParameter("i_id");
		String i_name = request.getParameter("i_name");
		String PWD1 = request.getParameter("PWD1");
		
		session.setAttribute("i_id",i_id);
		session.setAttribute("i_name",i_name);
		session.setAttribute("pass",PWD1);
		
		//データベース接続情報の準備
		Connection conn = null;
		
		try {
			//接続
			conn = jdbcMySql.getConnection();		
			
			//SQL文格納のためのオブジェクト（PreparedStatementクラス）
			//実行結果を格納するためのオブジェクト（ResultSetクラス）
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String Select0Sql = "select * from login;";
			 stmt = conn.prepareStatement(Select0Sql);
			 rs = stmt.executeQuery();//実行
			
				while (rs.next()){
					String id = rs.getString("log_id");
					if (i_id.equals(id)) {
						flg = 1;
						break;
					} else {
						flg = 2;
					}
				}
				
			if(flg == 2){

			 //UPDATE文の設定・実行
			 String insertSql = "insert into login values (?,?,?,1,0)";
			 stmt = conn.prepareStatement(insertSql);
			 stmt.setString(1,i_id);
			 stmt.setString(2,PWD1);
			 stmt.setString(3,i_name);
			 stmt.executeUpdate();//実行
			 
			 /*

			//SQL文設定の準備・SQL文
			String selectSql = "select * from login where log_student = 1 order by log_id asc;";
			stmt = conn.prepareStatement(selectSql);
			rs = stmt.executeQuery();
			
			HashMap<String,String> user;
			ArrayList<HashMap<String,String>> userData = new ArrayList<HashMap<String,String>>();
			
			while (rs.next()) {
				String l_id = rs.getString("log_id");
				String name = rs.getString("log_name");
				String pass = rs.getString("log_pass");
				
				user = new HashMap<String,String>();
				user.put("log_id", l_id);
				user.put("log_name", name);
				user.put("log_pass", pass);
				
				userData.add(user);

			}

			request.setAttribute("user", userData);
			*/
			
			RequestDispatcher rd = request.getRequestDispatcher("./administrator/s_insert.jsp");
			rd.forward(request,response);
			flg = 0;
			
			} else if (flg == 1) {
				session.setAttribute("log_id_e",i_id);
				RequestDispatcher rd = request.getRequestDispatcher("./administrator/s_insert_e.jsp");
				rd.forward(request,response);
				flg = 0;
			}
				
			//リソースの開放
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			out.println(e);
			
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
