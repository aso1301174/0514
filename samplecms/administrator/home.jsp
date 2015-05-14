<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<html>
<head>
<meta charset="utf-8">
<title>検定取得状況管理システム</title>
    <script language="javascript">
      <!--
      function logout(){
	if(window.confirm('ログアウトしますか？')){
		location.href='./index.html'
	}
	else{
		window.alert('キャンセルされました。');
	}
}
      //-->
    </script>
<link href="./css/buttons.css" rel="stylesheet" type="text/css">
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>

<h1><img src="./img/logo.jpg" width="224" height="77" alt=""/> 検定取得状況管理システム</h1>
<hr>
こんにちは、
<% out.println(session.getAttribute("log_name")); %>
さん　>>　管理者でログインしています　>>　管理者ホーム画面　>>　
<input type="button" class="button button-3d" value="ログアウト" onClick="logout()" />
<hr>
<br>
<div id="light">
<form method="POST" action="./home">
  <input type="button" class="button button-3d" value="管理者ホーム" />
  </form>
  <input type="button" class="button button-3d" value="学生→検定検索" onClick="location.href='./student_test_search'" />
  <br>
  <input type="button" class="button button-3d" value="検定→学生検索" onClick="location.href='./test_student_search'" />
  <br>
  <input type="button" class="button button-3d" value="学生管理" onClick="location.href='./student_management'" />
  <br>
  <input type="button" class="button button-3d" value="検定管理" onClick="location.href='./test_management'" />
  <br>
</div>
<h3><em><strong><font color="red">左の項目から作業を選択してください。</font></strong></em></h3>
現在の学生、検定一覧<br><br>
<%
  ArrayList<HashMap<String,String>> userData;
  HashMap<String,String> user;
  userData = (ArrayList<HashMap<String,String>>)request.getAttribute("user");

  out.println("<table border=\"1\" width=\"30%\">");
  out.println("<tr>");
  out.println("<td align=\"center\"><b>ID</b></td><td align=\"center\"><b>名前</b></td>");
  out.println("</tr>");

  for(int i=0;i<userData.size();i++){
    user = userData.get(i);
    out.println("<tr>");
    out.println("<td align=\"center\">" + user.get("log_id"));
    out.println("<td align=\"center\">" + user.get("log_name"));
  }
  out.println("</tr>");
  out.println("</table><br /><br />");
  
    ArrayList<HashMap<String,String>> testData;
  HashMap<String,String> test;
  testData = (ArrayList<HashMap<String,String>>)request.getAttribute("test");

  out.println("<table border=\"1\" width=\"30%\">");
  out.println("<tr>");
  out.println("<td align=\"center\"><b>検定名</b></td><td align=\"center\"><b>級</b></td>");
  out.println("</tr>");

  for(int i=0;i<testData.size();i++){
    test = testData.get(i);
    out.println("<tr>");
    out.println("<td align=\"center\">" + test.get("test_name"));
    out.println("<td align=\"center\">" + test.get("class"));
  }
  out.println("</tr>");
  out.println("</table>");
  %>
</body></html>
