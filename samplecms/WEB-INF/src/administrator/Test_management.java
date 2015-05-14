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

public class Test_management extends HttpServlet {

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
			String selectSql = "select * from test;";
			stmt = conn.prepareStatement(selectSql);
			rs = stmt.executeQuery();
			// ﾃﾞｰﾀがある間、出力

			HashMap<String, String> test;
			ArrayList<HashMap<String, String>> testData = new ArrayList<HashMap<String, String>>();

			while (rs.next()) {
				String test_id = rs.getString("test_id");
				String test_name = rs.getString("test_name");
				String t_class = rs.getString("class");

				test = new HashMap<String, String>();
				test.put("test_id", test_id);
				test.put("test_name", test_name);
				test.put("t_class", t_class);

				testData.add(test);

			}

			request.setAttribute("test", testData);

			RequestDispatcher rd = request
					.getRequestDispatcher("./administrator/test_management.jsp");
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
