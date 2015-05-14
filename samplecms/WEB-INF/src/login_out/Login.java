package login_out;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
public class Login extends HttpServlet {
	
	private String inputId;
	private String inputPassword;
	private String admin;
	private int flg = 0;
	private String okno = "1";
	
	@Resource(name="jdbc/MySQL")
	private DataSource jdbcMySql;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			
		//入力されたユーザー名、パスワードを変数に設定
		inputId = request.getParameter("username");
		inputPassword = request.getParameter("password");
		admin = "1";
		
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
			String selectSql = "select * from login;";
			stmt = conn.prepareStatement(selectSql);
			rs = stmt.executeQuery();
			//ﾃﾞｰﾀがある間、出力
			while (rs.next()){
				String id = rs.getString("log_id");
				String pass = rs.getString("log_pass");	
				String log_name = rs.getString("log_name");	
				String log_id = rs.getString("log_id");
				String log_admin = rs.getString("log_admin");
				if ((inputId.equals(id)) && (inputPassword.equals(pass))) {
					HttpSession session = request.getSession(true);
						session.setAttribute("log_name",log_name);
						session.setAttribute("log_id",log_id);
						session.setAttribute("okno",okno);
						if (admin.equals(log_admin)){
							flg = 1;
						} else {
							flg = 2;
						}
				}
				}
			
			//リソースの開放
			rs.close();
			stmt.close();
			
			if(flg == 1){
				RequestDispatcher rd = request.getRequestDispatcher("./home");
				rd.forward(request,response);
				flg = 0;
			} else if (flg == 2) {
				RequestDispatcher rd = request.getRequestDispatcher("./student_home");
				rd.forward(request,response);
				flg = 0;
			} else {
				response.sendRedirect("./error.html");
			}
			
		} catch (Exception e) {
			System.out.println("例外発生：" + e.getMessage());
			
		}finally {
			try {
				//絶対実行される処理
				if (conn != null) {
					conn.close();
					
				} else {
					System.out.println("コネクションがありません");
				}
			} catch (SQLException e) {
				System.out.println("SQLException発生：" + e.getMessage());
			}
		}
	}
}