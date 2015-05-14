<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="userbeanses" scope="session" class="common.DbinfoBean" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者メインメニュー</title>
</head>
<body>
	<h2>メインメニュー</h2>
	<form action="/cms/logout" method="POST">
	<table border="0"  width="100%" height="100%">
	<tr>
	<td align="right">
	<p><%= session.getAttribute("name") %>さん、ようこそ</p>
	<input type="submit" value="ログアウト">
	</td>
	</tr>
	<tr>
	<td><a href = "/cms/cms_man/refer_stu.jsp">学生→検定検索</a></td>
	</tr>
	<tr>
	<td><a href = "/cms/referapp">検定→学生検索</a></td>
	</tr>
	<tr>
	<td><a href = "/cms/cms_man/stu_master">学生マスタメンテナンス</a></td>
	</tr>
	<tr>
	<td><a href = "/cms/appmaster">検定マスタメンテナンス</a></td>
	</tr>
	<tr>
	<td><a href = "/cms/cms_man/app_entry.jsp">検定実施登録</a></td>
	</tr>
	</table>
	</form>
</body>
</html>