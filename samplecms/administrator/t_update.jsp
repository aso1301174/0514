<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<html>
  <head>
    <meta charset="utf-8">
    <title>
      検定取得状況管理システム
    </title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/start/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js">
    </script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js">
    </script>
    <link rel="stylesheet" href="/resources/demos/style.css" />
    <script>
      $(function() {
        $( "#datepicker" ).datepicker({ dateFormat: "yy/mm/dd" });
        });
      </script>
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
      <link href="./css/buttons.css" rel="stylesheet" type="text/css">
      <link href="./css/style.css" rel="stylesheet" type="text/css">
    </head>

    <h1>
      <img src="./img/logo.jpg" width="224" height="77" alt=""/>
      検定取得状況管理システム
    </h1>
    <hr>
    こんにちは、
    <%
    out.println(session.getAttribute("log_name"));
    %>
    さん　>>　管理者でログインしています　>>　検定管理　>>　検定更新　>>　
    <input type="button" class="button button-3d" value="ログアウト" onClick="logout()" />
    <hr>
    <br>
    <div id="light">
      <form method="POST" action="./home">
        <input type="submit" class="button button-3d" value="管理者ホーム" />
      </form>
      <input type="button" class="button button-3d" value="学生→検定検索" onClick="location.href='./student_test_search'" />
      <br>
      <input type="button" class="button button-3d" value="検定→学生検索" onClick="location.href='./test_student_search'" />
      <br>
      <input type="button" class="button button-3d" value="学生管理" onClick="location.href='./student_management'" />
      <br>
      <input type="button" class="button button-3d" value="検定管理" onClick="location.href='./test_management'" />
      <br>
    </div>
    <h2>
      検定を更新しました
    </h2>
    <hr width="500" align="left">
    <br>
    検定名
    <br />
    <input type="txt" name="k_name" size="80" value="<%= session.getAttribute("test_name") %>"  disabled>
    <br />
    <br />
    級
    <br />
    <input type="txt" name="k_class" placeholder="級無し" value="<%= session.getAttribute("t_class") %>" disabled>
    <br />
    <br />
    主催団体
    <br />
    <input type="txt" name="k_dantai" size="50" value="<%= session.getAttribute("dantai") %>" disabled>
    <br />
    <br />
    実施日
    <br />
    <input type="text" id="datepicker" name="k_day" placeholder="クリックして選択" value="<%= session.getAttribute("k_day") %>" disabled>
    <br />
    <br />
  </body>
</html>
