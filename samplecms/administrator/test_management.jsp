<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<html>
  <head>
    <meta charset="utf-8">
    <title>
      検定取得状況管理システム
    </title>
    <script language="javascript">
      <!--
      function checkMe()
      {
        if (window.confirm('登録しますか？')){
          return true;
        }else{
          window.alert('キャンセルされました');
          return false;
        }
      }

      function logout(){
        if(window.confirm('ログアウトしますか？')){
          location.href='./index.html'
        }
        else{
          window.alert('キャンセルされました');
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
  さん　>>　管理者でログインしています　>>　検定管理　>>　
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
    検定新規登録
  </h2>
  <hr width="500" align="left">
  <br>
  <form method="POST" action="./test_management_insert">
    検定名
    <br>
    <input type="text" name="name" placeholder="例：基本情報技術者試験" size="45" required />
    <br>
    <br>
    級
    <br>
    <input type="number" name="class" min="1" placeholder="無い場合は空白" size="15" />
    級
    <br>
    <br>
    実施団体
    <br>
    <input type="text" name="group" placeholder="例：IPA 独立行政法人" size="45" required />
    <br>
    <br>
    <input type="submit" onClick="return checkMe()" value="登録" />
    <input type="reset" value="リセット" />
  </form>
  <br>
  <br>
  <h2>
    検定情報更新
  </h2>
  <hr width="500" align="left">
  <br>
  <form method="GET" action="./test_management_update_select">
    変更したい検定
    <br>
    <select name="test_id">
      <%
      ArrayList<Map<String,String>> array = (ArrayList<Map<String, String>>)request.getAttribute("test");
      for (int i = 0; i < array.size(); i++) {
        Map<String, String> map = array.get(i);
        String test_id = map.get("test_id");
        String test_name = map.get("test_name");
        String t_class = map.get("t_class");
        String te_cl = test_name + "  " + t_class;
        %>
        <option name="t_id" value="<%=test_id%>">
          <%=te_cl%>
        </option>
        <%
      }
      array.clear();
      %>
    </select>
    <br>
    <br>
    <input type="submit" onClick="return okno()" value="選択" />
  </form>


</body>
</html>
