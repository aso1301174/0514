<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="userbeanses" scope="session" class="common.DbinfoBean" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検定実施日登録</title>
</head>
<body>
	<h2>検定実施日登録</h2>
	<form action="/cms/logout" method="POST">
	<table border="0"  width="100%" height="100%">
	<tr>
	<td align="right">
	<p><%= session.getAttribute("name") %>さん、ようこそ</p>
	<input type="submit" value="ログアウト">
	</td>
	</tr>
	<tr>
	<td><p>対象の検定を選択し、実施日を入力してください</p></td>
	</tr>
	</table>
	</form>
	<form action="/cms/appentry" method="POST">
	<table border="0">
	<tr>
	<td>主催団体<input type="text" name="partyname"></td>
	<td><input type="submit" name="party" value="検索"></td>
	</tr>
	<tr>
	<td>
	<% 
	out.println(session.getAttribute("entrymessage"));
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