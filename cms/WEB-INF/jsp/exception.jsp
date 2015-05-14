<%--
date:2014XXXX
name:情シ専2A　○○　○○
comm:共通エラーファイル。本来は詳細なエラーメッセージを表示するべきではない。
　　　　　　　　　　　　　が、今回はデバッグのため表示してみる
--%>

<html>
	<head><title>エラーが発生しました</title></head>

	<body>
		<h1 style="color:white;background-color:#525D76;font-size:2em;">
			エラーが発生しました</h1>
		<p>現在の画面において、致命的なエラーが発生しました。<br />
		しばらくたってもエラーが改善されない場合には、本画面のキャプチャを添付の上、
		<a href="kihara@asojuku.ac.jp">管理者</a>までご連絡ください。</p>
		<hr />
		
		<table border="1" bordercolordark="#FFFFFF">
			<tr>
				<th valign="top" nowrap>ステータス</th>
				<td>${requestScope['javax.servlet.error.status_code']}<br /></td>
			</tr><tr>
				<th valign="top" nowrap>例外発生箇所</th>
				<td>${requestScope['javax.servlet.error.request_uri']}<br /></td>
			</tr><tr>
				<th valign="top" nowrap>例外クラス</th>
				<td>${requestScope['javax.servlet.error.exception_type']}<br /></td>
			</tr><tr>
				<th valign="top" nowrap>例外メッセージ</th>
				<td>${requestScope['javax.servlet.error.message']}</pre></td>
			</tr>
		</table>
	</body>
</html>
