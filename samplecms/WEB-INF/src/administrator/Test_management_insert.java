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

public class Test_management_insert extends HttpServlet {
	
	@Resource(name="jdbc/MySQL")
	private DataSource jdbcMySql;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();	

		String t_name = request.getParameter("name");
		String te_class = request.getParameter("class");
		String group = request.getParameter("group");
		
		//データベース接続情報の準備
		Connection conn = null;
		
		try {
			//接続
			conn = jdbcMySql.getConnection();		
			
			//SQL文格納のためのオブジェクト（PreparedStatementクラス）
			//実行結果を格納するためのオブジェクト（ResultSetクラス）
			PreparedStatement stmt = null;
			PreparedStatement stmt2 = null;
			ResultSet rs = null;
			ResultSet rs2 = null;
			
			 //UPDATE文の設定・実行
			 String insertSql = "insert into test values ('0',?,?,?);";
			 stmt = conn.prepareStatement(insertSql);
			 stmt.setString(1,t_name);
			 stmt.setString(2,group);
			 stmt.setString(3,te_class);
			 stmt.executeUpdate();//実行

			//SQL文設定の準備・SQL文
			String selectSql = "select * from test;";
			stmt = conn.prepareStatement(selectSql);
			rs = stmt.executeQuery();
			
			//insertしたIDを取得する
			String select2Sql = "select last_insert_id();";
			stmt2 = conn.prepareStatement(select2Sql);
			rs2 = stmt2.executeQuery();

			int last_id;
			if (rs2.next()) {
				last_id = rs2.getInt(1);
		    } else {
		    	last_id = 9999;
		    }
			session.setAttribute("last_id",last_id);
			
			int d_id = last_id;
			String insertSql2 = "insert into day values (?,null);";
			 stmt = conn.prepareStatement(insertSql2);
			 stmt.setInt(1,d_id);
			 stmt.executeUpdate();//実行
			 
			HashMap<String,String> test;
			ArrayList<HashMap<String,String>> testData = new ArrayList<HashMap<String,String>>();
			
			while (rs.next()) {
				String id = rs.getString("test_id");
				String name = rs.getString("test_name");
				String dantai = rs.getString("dantai");
				String test_class = rs.getString("class");
				
				test = new HashMap<String,String>();
				test.put("test_id", id);
				test.put("test_name", name);
				test.put("dantai", dantai);
				test.put("class", test_class);
				
				testData.add(test);

			}

			request.setAttribute("test", testData);
			
			RequestDispatcher rd = request.getRequestDispatcher("./administrator/t_insert.jsp");
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
