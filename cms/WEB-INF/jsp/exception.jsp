<%--
date:2014XXXX
name:��V��2A�@�����@����
comm:���ʃG���[�t�@�C���B�{���͏ڍׂȃG���[���b�Z�[�W��\������ׂ��ł͂Ȃ��B
�@�@�@�@�@�@�@�@�@�@�@�@�@���A����̓f�o�b�O�̂��ߕ\�����Ă݂�
--%>

<html>
	<head><title>�G���[���������܂���</title></head>

	<body>
		<h1 style="color:white;background-color:#525D76;font-size:2em;">
			�G���[���������܂���</h1>
		<p>���݂̉�ʂɂ����āA�v���I�ȃG���[���������܂����B<br />
		���΂炭�����Ă��G���[�����P����Ȃ��ꍇ�ɂ́A�{��ʂ̃L���v�`����Y�t�̏�A
		<a href="kihara@asojuku.ac.jp">�Ǘ���</a>�܂ł��A�����������B</p>
		<hr />
		
		<table border="1" bordercolordark="#FFFFFF">
			<tr>
				<th valign="top" nowrap>�X�e�[�^�X</th>
				<td>${requestScope['javax.servlet.error.status_code']}<br /></td>
			</tr><tr>
				<th valign="top" nowrap>��O�����ӏ�</th>
				<td>${requestScope['javax.servlet.error.request_uri']}<br /></td>
			</tr><tr>
				<th valign="top" nowrap>��O�N���X</th>
				<td>${requestScope['javax.servlet.error.exception_type']}<br /></td>
			</tr><tr>
				<th valign="top" nowrap>��O���b�Z�[�W</th>
				<td>${requestScope['javax.servlet.error.message']}</pre></td>
			</tr>
		</table>
	</body>
</html>
