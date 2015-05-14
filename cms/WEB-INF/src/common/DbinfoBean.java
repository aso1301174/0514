package common;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.sql.DataSource;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class DbinfoBean implements Serializable{
	
	private String id;
	private String password;
	private String name;
	private String use;
	private String path;
	private String text;
	private String cername;
	private String referid;
	private String referkana;
	private String deptname;
	private String stuname;
	private String stuid;
	private String partyname;
	private String partyid;
	private String cerday;
	private String certificationid;
	
	//setter　入力された値を受け取る
	public void setId(String id){this.id = id;}
	public void setPw(String pw){this.password = pw;}
	public void setUse(String use){this.use = use;}
	public void setName(String name){this.name = name;}
	public void setCerName(String cername){this.cername = cername;}
	public void setReferId(String referid){this.referid = referid;}
	public void setReferKana(String referkana){this.referkana = referkana;}
	public void setDeptName(String deptname){this.deptname = deptname;}
	public void setStuName(String stuname){this.stuname = stuname;}
	public void setStuId(String stuid){this.stuid = stuid;}
	public void setPartyName(String partyname){this.partyname = partyname;}
	public void setPartyId(String partyid){this.partyid = partyid;}
	public void setCerDay(String cerday){this.cerday = cerday;}
	public void setCertificationId(String certificationid){this.certificationid = certificationid;}
		
	//getter
	public String getPath(){return path;}
	public String getName(){return name;}
	public String getId(){return id;}
	public String getPw(){return password;}
	public String getText(){return text;}
	public String getCerName(){return cername;}
	public String getReferId(){return referid;}
	public String getReferKana(){return referkana;}
	public String getdeptName(){return deptname;}
	public String getStuId(){return stuid;}
	public String getStuName(){return stuname;}
	public String getPartyName(){return partyname;}
	public String getPartyId(){return partyid;}
	public String getCerDay(){return cerday;}
	public String getCertificationId(){return certificationid;}
	
	//"jdbc/MySQL"という名前のリソースを読み込み
	//これ以降、jdbcMySqlという名前でDB設計を使用可能
	@Resource(name="jdbc/MySQL")
	private DataSource jdbcMySql;
	
	//ログイン処理
	public void loginUser(){
		
		String sql = null;//sql文を格納
		Connection con = null;//DB接続情報格納
		PreparedStatement stmt = null;//sql実行用
		ResultSet rs = null;//selectの結果を格納
		
		try{
			//▼▼DB接続
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
			con = ds.getConnection();
			//▲▲
			
			//SQL文設定。登録されている検定を取得
			if(use.equals("student")){
				sql = "SELECT stu_name,stu_pw FROM student WHERE stu_id = ?;";
			}else{
				sql = "SELECT man_name,man_pw FROM manager WHERE man_address = ?;";	
			}
			//入力された"ID"に合ったデータを一件取得
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			rs.next();
		
			//学生か管理者によって、取得する項目を変える
			if(use.equals("student")){
				if(password.equals(rs.getString("stu_pw"))){
					setName(rs.getString("stu_name"));
					System.out.println(rs.getString("stu_name"));
					System.out.println("namae hsitts:" + getName());
					path = "/cms_stu/smain.jsp";
				}else{
					path = "/cms_common/loginerror.jsp";
				}
			}else{
				if(password.equals(rs.getString("man_pw"))){
					setName(rs.getString("man_name"));
					path = "/cms_man/mmain.jsp";
					System.out.println(rs.getString("man_name"));
				}else{
					path = "/cms_common/loginerror.jsp";
				}
			}
		}catch(Exception e){
			path = "/cms_common/loginerror.jsp";
		}finally{
			try{
				if(rs != null){rs.close();}
				if(stmt != null){stmt.close();}
				if(con != null){con.close();}
			}catch(Exception e){
				System.out.println("こことおた3");
			}
		}
	}
	
	//検定→学生検索処理
		public String referapp(){
			System.out.println("検定→学生");
			String message = "";
			
			//データベース接続情報の確認
			Connection con = null;
			
			try{
				//▼▼DB接続
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
				con = ds.getConnection();
				//▲▲
				
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				//SQL文設定。登録された検定を取得する。
				//実施一覧の取得
				String sql = "select a.stu_id,b.stu_name,c.dept_name"
						+ " from student_certificate a,student b,department c"
						+ " where a.cer_id IN"
						+ " (select cer_id from certificate where cer_name = ?)"
						+ " and a.stu_id = b.stu_id"
						+ " and b.dept_id = c.dept_id;";

				//入力された"検定"に合ったデータを一件取得
				stmt = con.prepareStatement(sql);
				stmt.setString(1, cername);
				System.out.println(cername);
				rs = stmt.executeQuery();
				System.out.println("sql実行したっぽい");
				//データがある間、出力
				message = "<table border=\"1\">";
				while(rs.next()){
					System.out.println("whileだよ");
					message += "<tr><td>"+rs.getString("stu_id")+"</td><td>"+rs.getString("stu_name")+"</td><td>"+rs.getString("dept_name")+"</td></tr>";
				}
				message += "</table>";
				System.out.println(message);
			}catch(Exception e){
				System.out.println("エラーだよ");
			}
			finally{
				try{
					if(con != null){con.close();}
				}catch(Exception e){
					System.out.println("こことおた3");
				}
			}
			return message;
		}
		
	//検定一覧の取得
		public String getapp(){
			System.out.println("検定一覧取得");
			String getmessage = "";
			
			//データベース接続情報の確認
			Connection con = null;
			
			try{
				//▼▼DB接続
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
				con = ds.getConnection();
				//▲▲
				
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				//SQL文設定。登録された検定を取得する。
				//実施一覧の取得
				String sql = "SELECT * FROM certification;";

				//入力された"検定"に合ったデータを一件取得
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery();
				//データがある間、出力
				getmessage = "<select name=\"cername\">";
				while(rs.next()){
					getmessage += "<option value=\""+rs.getString("cer_name")+"\">"+rs.getString("cer_name")+"</option>";
				}
				getmessage += "</select>";
				System.out.println(getmessage);
			}catch(Exception e){
				System.out.println("エラーだよ");
			}
			finally{
				try{
					if(con != null){con.close();}
				}catch(Exception e){
					System.out.println("こことおた3");
				}
			}
			return getmessage;
		}
		
	//学科の取得
		public String getdept(){
			System.out.println("学科一覧取得");
			String getdeptmessage = "";
					
			//データベース接続情報の確認
			Connection con = null;
					
			try{
				//▼▼DB接続
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
				con = ds.getConnection();
				//▲▲
						
				PreparedStatement stmt = null;
				ResultSet rs = null;
						
				//SQL文設定。登録された検定を取得する。
				//実施一覧の取得
				String sql = "SELECT * FROM department;";

				//入力された"検定"に合ったデータを一件取得
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery();
				//データがある間、出力
				getdeptmessage = "<select name=\"dept\">";
				while(rs.next()){
					getdeptmessage += "<option value=\""+rs.getString("dept_name")+"\">"+rs.getString("dept_name")+"</option>";
				}
				getdeptmessage += "</select>";
				System.out.println(getdeptmessage);
			}catch(Exception e){
				System.out.println("エラーだよ");
			}
			finally{
				try{
					if(con != null){con.close();}
				}catch(Exception e){
					System.out.println("こことおた3");
				}
			}
			return getdeptmessage;
		}

	//学生→検定検索処理(学籍番号)
		public String referstuid(){
			System.out.println("学生→検定(学籍番号)");
			String stumessage = "";
			
			//データベース接続情報の確認
			Connection con = null;
			
			try{
				//▼▼DB接続
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
				con = ds.getConnection();
				//▲▲
				
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				//入力されたIDをもとに検定を取得する
				String sql = "select p.party_name,c.cer_name"
						+ " from certificate c,party p"
						+ " where c.party_id = p.party_id"
						+ " and c.cer_id in"
						+ " (select s.cer_id"
						+ " from student_certificate s"
						+ " where s.stu_id like ?);";
				
				stmt = con.prepareStatement(sql);
				stmt.setString(1, "%" + referid + "%");
				System.out.println(referid);
				rs = stmt.executeQuery();
				System.out.println("sql実行したっぽい");
				//データがある間、出力
				stumessage = "<table border=\"1\">" + "<tr><td>主催団体</td><td>検定名</td></tr>";
				while(rs.next()){
					System.out.println("whileだよ");
					stumessage += "<tr><td>"+rs.getString("party_name")+"</td><td>"+rs.getString("cer_name")+"</td></tr>";
				}
				stumessage += "</table>";
				System.out.println(stumessage);	
			}catch(Exception e){
				System.out.println("エラーだよ" + e.getMessage());
			}
			finally{
				try{
					if(con != null){con.close();}
				}catch(Exception e){
					System.out.println("こことおた3");
				}
			}
			return stumessage;
		}

	//学生→検定検索処理(フリガナ)
		public String referstukana(){
		System.out.println("学生→検定(フリガナ)");
		String stumessage = "";
		
		//データベース接続情報の確認
		Connection con = null;
		
		try{
			//▼▼DB接続
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
			con = ds.getConnection();
			//▲▲
			
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			//入力されたフリガナをもとに学生の情報を取得する
			String sql = "select s.stu_id,s.stu_name,d.dept_name"
					+ " from student s,department d"
					+ " where s.stu_kana like ?"
					+ " and s.dept_id = d.dept_id;";

			//入力された"学生"に合ったデータを一件取得
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + referkana + "%");
			System.out.println(referkana);
			rs = stmt.executeQuery();
			System.out.println("sql実行したっぽい");
			//データがある間、出力
			stumessage = "<table border=\"1\">" + "<tr><td>学籍番号</td><td>氏名</td><td>学科</td><tr>";
			while(rs.next()){
				System.out.println("whileだよ");
				stumessage += "<tr><td>"+rs.getString("stu_id")+"</td>"
						+ "<td>"+rs.getString("stu_name")+"</td>"
						+ "<td>"+rs.getString("dept_name")+"</td>"
						+ "<td>"+"<input type=\"submit\" name=\"candidata\" value=\"検索\">"
						+ "<input type=\"hidden\" name=\"stuid\" value=\""+rs.getString("stu_id")+"\"></td></tr></form>";
			}
			stumessage += "</table>";
			System.out.println(stumessage);
		}catch(Exception e){
			System.out.println("エラーだよ");
		}
		finally{
			try{
				if(con != null){con.close();}
			}catch(Exception e){
				System.out.println("こことおた3");
			}
		}
		return stumessage;
	}

	//学生→検定検索処理(選択された学生)
		public String referid(){
			System.out.println("学生→検定(選択された学生)");
			String stumessage = "";
			
			//データベース接続情報の確認
			Connection con = null;
			
			try{
				//▼▼DB接続
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
				con = ds.getConnection();
				//▲▲
				
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				//入力されたフリガナをもとに学生の情報を取得する
				String sql = "select c.party_name,c.cer_name"
						+ " from certificate c"
						+ " where c.cer_id in"
						+ " (select s.cer_id"
						+ " from student_certificate s"
						+ " where s.stu_id like ?);";

				//入力された"学生"に合ったデータを一件取得
				stmt = con.prepareStatement(sql);
				stmt.setString(1, "%" + stuid + "%");
				System.out.println(stuid);
				rs = stmt.executeQuery();
				System.out.println("sql実行したっぽい");
				//データがある間、出力
				stumessage = "<table border=\"1\">" + "<tr><td>主催団体</td><td>検定名</td></tr>";
				while(rs.next()){
					System.out.println("whileだよ");
					stumessage += "<tr><td>"+rs.getString("party_name")+"</td>"
							+ "<td>"+rs.getString("cer_name")+"</td></tr>";
				}
				stumessage += "</table>";
				System.out.println(stumessage);
			}catch(Exception e){
				System.out.println("エラーだよ");
			}
			finally{
				try{
					if(con != null){con.close();}
				}catch(Exception e){
					System.out.println("こことおた3");
				}
			}
			return stumessage;
		}
		
	//実施検定の取得
		public String getparty(){
			System.out.println("実施検定一覧取得");
			String getpartymessage = "";
					
			//データベース接続情報の確認
			Connection con = null;
					
			try{
				//▼▼DB接続
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
				con = ds.getConnection();
				//▲▲
						
				PreparedStatement stmt = null;
				ResultSet rs = null;
						
				//SQL文設定。登録された検定を取得する。
				//入力された"検定"に合ったデータを一件取得
				String sql = "select p.party_name,c.cer_name"
						+ " from certification c,party p"
						+ " where p.party_name like ?"
						+ " and c.party_id = p.party_id;";

				//入力された"学生"に合ったデータを一件取得
				stmt = con.prepareStatement(sql);
				stmt.setString(1, "%" + partyname + "%");
				System.out.println(partyname);
				rs = stmt.executeQuery();
				System.out.println("sql実行したっぽい");
				//データがある間、出力
				getpartymessage = "<table border=\"1\"><tr><td>主催団体</td><td>検定名</td><td></td></tr>";
				while(rs.next()){
					System.out.println("whileだよ");
					getpartymessage += "<tr><td>"+rs.getString("party_name")+"</td>"
							+ "<td>"+rs.getString("cer_name")+"</td>"
							+ "<td><input type=\"submit\" name=\"date\" value=\"実施日登録へ\"></td></tr>";
//							+ "<td><input type=\"hidden\" name=\"partyid\" value=\""+rs.getString("party_id")+"\"></td>"
//							+ "<td><input type=\"hidden\" name=\"cername\" value=\""+rs.getString("cer_name")+"\"></td>"
//							+ "<td><input type=\"hidden\" name=\"cerclass\" value=\""+rs.getString("class")+"\"></td>";
				}
				getpartymessage += "</table>";
				setPartyId(rs.getString("party_id"));
				setCerName(rs.getString("cer_name"));
				System.out.println(getpartymessage);
			}catch(Exception e){
				System.out.println("エラーだよ");
			}
			finally{
				try{
					if(con != null){con.close();}
				}catch(Exception e){
					System.out.println("こことおた3");
				}
			}
			return getpartymessage;
		}
		
	//実施日入力処理
		public String setdate(){
			System.out.println("実施日入力");
			String setdatemessage = "";
					
			//データベース接続情報の確認
			Connection con = null;
					
			try{
				//▼▼DB接続
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
				con = ds.getConnection();
				//▲▲
						
				PreparedStatement stmt = null;
				ResultSet rs = null;
						
//				//SQL文設定。登録された検定を取得する。
//				//入力された"検定"に合ったデータを一件取得
//				String sql = "select p.party_name,c.cer_name,c.class"
//						+ " from certification c,party p"
//						+ " where p.party_name like ?"
//						+ " and c.party_id = p.party_id;";
//
//				//入力された"学生"に合ったデータを一件取得
//				stmt = con.prepareStatement(sql);
//				stmt.setString(1, "%" + partyname + "%");
//				System.out.println(partyname);
//				rs = stmt.executeQuery();
//				System.out.println("sql実行したっぽい");
//				//データがある間、出力
				setdatemessage = "<table><tr><td>実施日を入力してください</td>"
						+ "<td>(例)2014年4月20日→20140420</td></tr>"
						+ "<tr><td>実施日<input type=\"text\" name=\"cerday\"></td>"
						+ "<td><input type=\"submit\" name=\"entry\" value=\"登録\"></td><tr>";
//				while(rs.next()){
//					System.out.println("whileだよ");
//					setdatemessage += "<tr><td>"+rs.getString("party_name")+"</td>"
//							+ "<td>"+rs.getString("cer_name")+"</td>"
//							+ "<td>"+rs.getString("class")+"</td>"
//							+ "<td><input type=\"submit\" name=\"date\" value=\"実施日登録へ\"></td>"
//							+ "<td><input type=\"hidden\" name=\"partyid\" value=\""+rs.getString("party_id")+"\"></td>"
//							+ "<td><input type=\"hidden\" name=\"cername\" value=\""+rs.getString("cer_name")+"\"></td>"
//							+ "<td><input type=\"hidden\" name=\"cerclass\" value=\""+rs.getString("class")+"\"></td></tr>";
//				}
//				setdatemessage += "</table>";
				System.out.println(setdatemessage);
			}catch(Exception e){
				System.out.println("エラーだよ");
			}
			finally{
				try{
					if(con != null){con.close();}
				}catch(Exception e){
					System.out.println("こことおた3");
				}
			}
			return setdatemessage;
		}
		
	//実施日登録処理
		public String entryday(){
			System.out.println("実施日登録");
			String entrymessage = "";
					
			//データベース接続情報の確認
			Connection con = null;
					
			try{
				//▼▼DB接続
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
				con = ds.getConnection();
				//▲▲
						
				PreparedStatement stmt = null;
				ResultSet rs = null;
						
				//SQL文設定。登録された検定を取得する。
				//入力された"検定"に合ったデータを一件取得
				String sql = "insert into certificate(party_id,cer_name,cer_day)"
						+ "values(?,?,?);";

				//入力された"学生"に合ったデータを一件取得
				stmt = con.prepareStatement(sql);
				stmt.setString(1, partyid);
				stmt.setString(2, cername);
				stmt.setString(3, cerday);
				System.out.println(partyid);
				System.out.println(cername);
				System.out.println(cerday);
				rs = stmt.executeQuery();
				System.out.println("sql実行したっぽい");
				//データがある間、出力
				entrymessage = "<table border=\"1\">"
						+ "<tr><td>主催団体</td><td>検定名</td><td>級</td><td>実施日</td><tr>";
				while(rs.next()){
					System.out.println("whileだよ");
					entrymessage += "<tr><td>"+rs.getString("party_name")+"</td>"
							+ "<td>"+rs.getString("cer_name")+"</td>"
							+ "<td>"+rs.getString("cer_day")+"</td></tr>";
				}
				entrymessage += "</table>";
				System.out.println(entrymessage);
			}catch(Exception e){
				System.out.println("エラーだよ");
			}
			finally{
				try{
					if(con != null){con.close();}
				}catch(Exception e){
					System.out.println("こことおた3");
				}
			}
			return entrymessage;
		}
		
	//更新のための検定一覧の取得
		public String getappmaster(){
			System.out.println("更新のための検定一覧取得");
			String appmastermessage = "";
					
			//データベース接続情報の確認
			Connection con = null;
					
			try{
				//▼▼DB接続
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
				con = ds.getConnection();
				//▲▲
						
				PreparedStatement stmt = null;
				ResultSet rs = null;
						
				//SQL文設定。登録された検定を取得する。
				//実施一覧の取得
				String sql = "SELECT certification_id,party_name,cer_name"
						+ " FROM certification";

				//入力された"検定"に合ったデータを一件取得
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery();
				System.out.println("sql実行したっぽい");
				//データがある間、出力
				appmastermessage = "<table border=\"1\"><form action=\"/cms/cms_man/app_update.jsp\" method=\"POST\"" + "<tr><td>主催団体</td><td>検定名</td></tr>";
				while(rs.next()){
					System.out.println("whileだよ");
					appmastermessage += "<tr><td>"+rs.getString("party_name")+"</td>"
							+ "<td>"+rs.getString("cer_name")+"</td>"
							+ "<td>"+"<input type=\"submit\" name=\"select\" value=\"更新\">"
							//+ "<input type=\"hidden\" name=\"certificationid\" value=\""+rs.getString("certification_id")+"\"></td>"
							+ "</tr>";
				}
				appmastermessage += "</form></table><a href = \"/cms/cms_man/mmain.jsp\">メインメニューへ戻る</a>";
				path = "/cms_man/app_master.jsp";
				setCertificationId(rs.getString("certification_id"));
				System.out.println(appmastermessage);
				System.out.println(certificationid);
			}catch(Exception e){
				System.out.println("エラーだよ" + e.getMessage());
			}
			finally{
				try{
					if(con != null){con.close();}
				}catch(Exception e){
					System.out.println("こことおた3");
				}
			}
			return appmastermessage;
		}
		
	//検定情報の追加処理
		public String insertapp(){
			System.out.println("検定情報の追加");
			String insertappmessage = "";
					
			//データベース接続情報の確認
			Connection con = null;
					
			try{
				//▼▼DB接続
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
				con = ds.getConnection();
				//▲▲
						
				PreparedStatement stmt = null;
				ResultSet rs = null;
						
				//SQL文設定。登録された検定を取得する。
				//実施一覧の取得
				String sql = "INSERT INTO certification(party_name,cer_name)"
						+ " VALUES(?,?);";

				//入力された"検定"に合ったデータを一件取得
				stmt = con.prepareStatement(sql);
				stmt.setString(1, partyname);
				stmt.setString(2, cername);
				stmt.executeUpdate();
				System.out.println("update実行したっぽい");
				
				String selectsql = "SELECT party_name,cer_name"
						+ " FROM certification";
				stmt = con.prepareStatement(selectsql);
				rs = stmt.executeQuery();
				System.out.println("select実行したっぽい");
				//データがある間、出力
				insertappmessage = "<table border=\"1\">" + "<tr><td>主催団体</td><td>検定名</td></tr>";
				while(rs.next()){
					System.out.println("whileだよ");
					insertappmessage += "<tr><td>"+rs.getString("party_name")+"</td>"
							+ "<td>"+rs.getString("cer_name")+"</td></tr></form>";
				}
				insertappmessage += "</table>";
				path = "/cms_man/app_master2.jsp";
				System.out.println(insertappmessage);
			}catch(Exception e){
				System.out.println("エラーだよ" + e.getMessage());
			}
			finally{
				try{
					if(con != null){con.close();}
				}catch(Exception e){
					System.out.println("こことおた3");
				}
			}
			return insertappmessage;
		}
		
	//検定情報の更新
		public String updateapp(){
			System.out.println("検定情報の更新");
			String updateappmessage = "";
					
			//データベース接続情報の確認
			Connection con = null;
					
			try{
				//▼▼DB接続
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
				con = ds.getConnection();
				//▲▲
						
				PreparedStatement stmt = null;
				ResultSet rs = null;
						
				//SQL文設定。登録された検定を取得する。
				//実施一覧の取得
				String sql = "UPDATE certification SET party_name = ?"
						+ " WHERE certification_id = ?;";

				//入力された"検定"に合ったデータを一件取得
				stmt = con.prepareStatement(sql);
				stmt.setString(1, partyname);
				stmt.setString(2, certificationid);
				stmt.executeUpdate();
				System.out.println("update1実行したっぽい");
				System.out.println(certificationid);
				
				String sql2 = "UPDATE certification SET cer_name = ?"
						+ " WHERE certification_id = ?;";

				//入力された"検定"に合ったデータを一件取得
				stmt = con.prepareStatement(sql2);
				stmt.setString(1, cername);
				stmt.setString(2, certificationid);
				stmt.executeUpdate();
				System.out.println("update2実行したっぽい");
				System.out.println(certificationid);
				
				String selectsql = "SELECT party_name,cer_name"
						+ " FROM certification";
				stmt = con.prepareStatement(selectsql);
				rs = stmt.executeQuery();
				//データがある間、出力
				updateappmessage = "<table border=\"1\">" + "<tr><td>主催団体</td><td>検定名</td></tr>";
				while(rs.next()){
					System.out.println("whileだよ");
					updateappmessage += "<tr><td>"+rs.getString("party_name")+"</td>"
							+ "<td>"+rs.getString("cer_name")+"</td></tr></form>";
				}
				updateappmessage += "</table>";
				path = "/cms_man/app_update2.jsp";
				System.out.println(updateappmessage);
			}catch(Exception e){
				System.out.println("エラーだよ" + e.getMessage());
			}
			finally{
				try{
					if(con != null){con.close();}
				}catch(Exception e){
					System.out.println("こことおた3");
				}
			}
			return updateappmessage;
		}
}