<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="userbeanses" scope="session" class="common.DbinfoBean" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生メインメニュー</title>
</head>
<body>
	<h2>メインメニュー</h2>
	<p><%= session.getAttribute("name") %>さん、ようこそ</p>
	<form action="/cms/logout" method="POST">
	<table border="0">
	<tr>
	<td><input type="submit" value="ログアウト"></td>
	</tr>
	<tr>
	<td><a href = "/cms/cms_stu/sapprefer.jsp">取得状況参照</a></td>
	</tr>
	<tr>
	<td><a href = "/cms/cms_stu/sappupdate.jsp">取得状況更新</a></td>
	</tr>
	</table>
	</form>
</body>
</html>