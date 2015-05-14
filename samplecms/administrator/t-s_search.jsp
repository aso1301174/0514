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
      function logout(){
        if(window.confirm('ログアウトしますか？')){
          location.href='../index.html'
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
  さん　>>　管理者でログインしています　>>　検定→学生検索　>>　
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
    検定→学生検索
  </h2>
  <hr width="500" align="left">
  <br>
  <form method="GET" action="./test_student_search_select">
    検索したい検定を選択してください
    <br>
    <select name="s_id">
      <%
      ArrayList<Map<String,String>> array = (ArrayList<Map<String, String>>)request.getAttribute("test");
      for (int i = 0; i < array.size(); i++) {
        Map<String, String> map = array.get(i);
        String test_id = map.get("test_id");
        String test_name = map.get("test_name");
        String te_class = map.get("class");
        String test_name_test_class = test_name + " ： " + te_class;
        %>
        <option name="t_id" value="<%=test_id%>">
          <%=test_name_test_class%>
        </option>
        <%
      }
      array.clear();
      %>
    </select>
    <br>
    <br>
    <input type="submit" value="検索" style="cursor:pointer;width:100px;height:25px"  />
  </form>
</body>
</html>
