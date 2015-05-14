<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="userbeanses" scope="session" class="common.DbinfoBean" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者メインメニュー</title>
</head>
<body>
	<h2>学生→検定検索</h2>
	<form action="/cms/logout" method="POST">
	<table border="0"  width="100%" height="100%">
	<tr>
	<td align="right">
	<p><%= session.getAttribute("name") %>さん、ようこそ</p>
	<input type="submit" value="ログアウト">
	</td>
	</tr>
	<tr>
	<td><p>任意の学生の、IDもしくはフリガナを入力してください</p></td>
	</tr>
	</table>
	</form>
	<form action="/cms/referstu" method="POST">
	<table border="0">
	<tr>
	<td>ID<input type="text" name="referid"><FONT color="red">※半角英数</FONT></td>
	<td><input type="submit" name="submitid" value="検索"></td>
	</tr>
	<tr>
	<td>フリガナ<input type="text" name="referkana"></td>
	<td><input type="submit" name="submitkana" value="検索"></td>
	</tr>
	<tr>
	<td>
	<% 
	out.println(session.getAttribute("stumessage"));
	%>
	</td>
	</tr>
	<tr>
	<td><a href = "/cms/cms_man/mmain.jsp">メインメニューへ戻る</a></td>
	</tr>
	</table>
	</form>
</body>
</html>