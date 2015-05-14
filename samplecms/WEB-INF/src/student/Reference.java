package student;

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

public class Reference extends HttpServlet {

	@Resource(name = "jdbc/MySQL")
	private DataSource jdbcMySql;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		if (session == null){
			
		}		

		String l_id = (String) session.getAttribute("log_id");
		
		// データベース接続情報の準備
		Connection conn = null;
		try {
			// 接続
			conn = jdbcMySql.getConnection();

			// SQL文格納のためのオブジェクト（PreparedStatementクラス）
			// 実行結果を格納するためのオブジェクト（ResultSetクラス）
			PreparedStatement stmt = null;
			ResultSet rs = null;

			// SQL文設定の準備・SQL文の実行
			String selectSql = "select lo.log_id , lo.log_name , te.test_name , te.class from test te, situation si, login lo WHERE si.log_id = lo.log_id AND te.test_id = si.test_id AND lo.log_id = ?;";
			stmt = conn.prepareStatement(selectSql);
			stmt.setString(1,l_id);
			rs = stmt.executeQuery();
			// ﾃﾞｰﾀがある間、出力

			HashMap<String, String> user;
			ArrayList<HashMap<String, String>> userData = new ArrayList<HashMap<String, String>>();

			while (rs.next()) {
				String t_name = rs.getString("te.test_name");
				String t_class = rs.getString("te.class");
				user = new HashMap<String, String>();
				user.put("te.test_name", t_name);
				user.put("te.class", t_class);
				userData.add(user);

			}

			request.setAttribute("user", userData);

			RequestDispatcher rd = request
					.getRequestDispatcher("./student/reference.jsp");
			rd.forward(request, response);

			// リソースの開放
			rs.close();
			stmt.close();

		} catch (Exception e) {
			out.println("例外発生：" + e.getMessage());

		} finally {
			try {
				// 絶対実行される処理
				if (conn != null) {
					conn.close();
					// out.println("データベース切断に成功しました");

				} else {
					out.println("コネクションがありません");
				}
			} catch (SQLException e) {
				out.println("SQLException発生：" + e.getMessage());
			}
		}
	}
}
