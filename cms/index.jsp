<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<table>
	<tr>
	<td>
	<font size="5">検定取得状況管理システム</font>
	</td>
	</tr>
	</table>
	<form action="/cms/login" method="POST" name="login">
	<table border="0" width="100%" height="100%">
	<tr>
	<td align="center"><h2>ログイン</h2></td>
	</tr>
	<tr>
	<td align="center">IDとパスワードを入力し、学生か管理者を選んでください</td>
	<td></td>
	</tr>
	<tr>
	<td align="center">ログインID<input type="text" name="id"><FONT color="red">※半角英数</FONT></td>
	</tr>
	<tr>
	<td align="center">パスワード<input type="password" name="pw"><FONT color="red">※半角英数</FONT></td>
	</tr>
	<tr>
	<td align="center">
	<input type="radio" name="use" value="student" checked>学生
	<input type="radio" name="use" value="manager">管理者
	</td>
	</tr>
	<tr>
	<td align="center">
	<input type="image" src="/cms/img/loginbutton.gif" alt="ログイン">
	</td>
	</tr>
	</table>
	</form>
</body>
</html>