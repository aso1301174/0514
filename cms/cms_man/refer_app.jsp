<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者メインメニュー</title>
</head>
<body>
	<h2>検定→学生検索</h2>
	<form action="/cms/logout" method="POST">
	<table border="0"  width="100%" height="100%">
	<tr>
	<td align="right">
	<p><%= session.getAttribute("name") %>さん、ようこそ</p>
	<input type="submit" value="ログアウト">
	</td>
	</tr>
	<tr>
	<td><p>対象の検定を選択してください</p></td>
	</tr>
	</table>
	</form>
	<form action="/cms/referapp2" method="POST">
	<table>
	<tr>
	<td>
	<% 
	out.println(session.getAttribute("getmessage"));
	%>
	</td>
	</tr>
	<tr>
	<td><input type="submit" value="検索"></td>
	</tr>
	<tr>
	<td><a href = "/cms/cms_man/mmain.jsp">メインメニューへ戻る</a></td>
	</tr>
	</table>
	<% 
	out.println(session.getAttribute("message"));
	%>
	</form>
</body>
</html>