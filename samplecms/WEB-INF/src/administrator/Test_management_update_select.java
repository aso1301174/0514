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

public class Test_management_update_select extends HttpServlet {
	
	@Resource(name="jdbc/MySQL")
	private DataSource jdbcMySql;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String t_id = request.getParameter("test_id");
		
		//データベース接続情報の準備
		Connection conn = null;
		try {
			//接続
			conn = jdbcMySql.getConnection();	
			
			//SQL文格納のためのオブジェクト（PreparedStatementクラス）
			//実行結果を格納するためのオブジェクト（ResultSetクラス）
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			//SQL文設定の準備・SQL文の実行
			String selectSql = "select te.test_id,te.test_name,te.dantai,te.class,da.day from test te,day da where te.test_id = ? and da.test_id = ?;";
			stmt = conn.prepareStatement(selectSql);
			stmt.setString(1,t_id);
			stmt.setString(2,t_id);
			rs = stmt.executeQuery();
			if(rs.next()){
			String id = rs.getString("test_id");
			String test_name = rs.getString("test_name");
			String dantai = rs.getString("dantai");
			String te_class = rs.getString("class");
			String te_day = rs.getString("day");
			
			HttpSession session = request.getSession(true);
			session.setAttribute("ut_id",id);
			session.setAttribute("u_test_name",test_name);
			session.setAttribute("u_dantai",dantai);
			session.setAttribute("u_te_class",te_class);
			if (te_day == null){
				te_day = "";
				session.setAttribute("u_te_day",te_day);
			}else{
				session.setAttribute("u_te_day",te_day);
			}
			
			
			
			
			}//ﾃﾞｰﾀがある間、出力

			//リソースの開放
			rs.close();
			stmt.close();
			
			RequestDispatcher rd = request.getRequestDispatcher("./administrator/t_update_select.jsp");
			rd.forward(request,response);
			
		} catch (Exception e) {
			out.println("例外発生：" + e.getMessage());
			
		} finally {
			try {
			//絶対実行される処理
			if (conn != null) {
				conn.close();
				
			} else {
				out.println("コネクションがありません");
			}
		} catch (SQLException e) {
			out.println("SQLException発生：" + e.getMessage());
		}
		}
	}
}
