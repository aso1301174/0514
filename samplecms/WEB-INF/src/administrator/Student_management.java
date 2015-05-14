package administrator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.*;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class Student_management extends HttpServlet {

	@Resource(name = "jdbc/MySQL")
	private DataSource jdbcMySql;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();


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
			String selectSql = "select * from login where log_student = 1 order by log_id asc;";
			stmt = conn.prepareStatement(selectSql);
			rs = stmt.executeQuery();
			// ﾃﾞｰﾀがある間、出力

			HashMap<String, String> user;
			ArrayList<HashMap<String, String>> userData = new ArrayList<HashMap<String, String>>();

			while (rs.next()) {
				String id = rs.getString("log_id");
				String name = rs.getString("log_name");

				user = new HashMap<String, String>();
				user.put("log_id", id);
				user.put("log_name", name);

				userData.add(user);

			}

			request.setAttribute("user", userData);

			RequestDispatcher rd = request
					.getRequestDispatcher("./administrator/student_management.jsp");
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
