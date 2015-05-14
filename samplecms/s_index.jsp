<%@ page contentType="text/html; charset=utf-8" %>
<html>
    <head>
    <meta charset="utf-8">
    <title>管理者ホーム</title>
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
    <link href="./css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
<h1><img src="./img/logo.jpg" width="224" height="77" alt=""/> 検定取得状況管理システム</h1>
<hr>
こんにちは、
<% out.println(session.getAttribute("log_name")); %>
さん　>>　学生でログインしています　>>　
<input type="button" value="ログアウト" onClick="logout()" />
<hr>
<br>
<div id="light">
      <input type="button" value="学生ホーム" style="cursor:pointer;width:200px;height:50px" onClick="location.href='k_index.jsp'" />
      <br>
      <input type="button" value="取得状況参照" style="cursor:pointer;width:200px;height:50px" onClick="location.href='./reference'" />
      <br>
      <input type="button" value="取得状況更新" style="cursor:pointer;width:200px;height:50px" onClick="location.href='./update'" />
      <br>
    </div>
</body>
</html>
