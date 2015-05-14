<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="userbeanses" scope="session" class="common.DbinfoBean" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検定マスタメンテナンス</title>
</head>
<body>
	<h2>検定マスタメンテナンス</h2>
	<form action="/cms/logout" method="POST">
	<table border="0"  width="100%" height="100%">
	<tr>
	<td align="right">
	<p><%= session.getAttribute("name") %>さん、ようこそ</p>
	<input type="submit" value="ログアウト">
	</td>
	</tr>
	<tr>
	<td><p>検定の基本データを入力し、追加または更新を行ってください</p></td>
	</tr>
	</table>
	</form>
	<form action="/cms/appmaster" method="POST">
	<table border="0">
	<tr><td>主催団体<input type="text" name="partyname"></td></tr>
	<tr><td>検定名<input type="text" name="cername"></td></tr>
	<tr><td><input type="submit" name="insert" value="追加"></td></tr>
	<!-- <tr><td><a href = "/cms/cms_man/mmain.jsp">メインメニューへ戻る</a></td></tr> -->
	</table>
	</form>
	<% 
	out.println(session.getAttribute("appmastermessage"));
	%>
</body>
</html>