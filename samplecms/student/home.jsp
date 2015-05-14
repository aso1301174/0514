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
さん　>>　学生でログインしています　>>　学生ホーム画面　>>　
<input type="button" class="button button-3d" value="ログアウト" onClick="logout()" />
<hr>
<br>
    <div id="light">
    <form method="POST" action="./student_home">
      <input type="button" class="button button-3d" value="学生ホーム" />
      </form>
      <form method="POST" action="./update">
      <input type="submit" class="button button-3d" value="取得状況更新" />
      </form>
      <br>
    </div>
    <h2>
      取得状況参照
    </h2>
    <hr width="500" align="left">
    <h3>
      <%
      out.println(session.getAttribute("log_name"));
      %>
      さんが取得している資格は
    </h3>
    <%
    ArrayList<HashMap<String,String>> userData;
    HashMap<String,String> user;
    userData = (ArrayList<HashMap<String,String>>)request.getAttribute("user");
    out.println("<ul>");
    if (userData.size() == 0){
    out.println("現在ありません");
    }else {

    for(int i=0;i<userData.size();i++){
      user = userData.get(i);
      out.println("<tr>");
      out.println("<li>" + user.get("te.test_name") + user.get("te.class") + "</li>");

    }
    out.println("</ul>");
    }
    %>

</body></html>
