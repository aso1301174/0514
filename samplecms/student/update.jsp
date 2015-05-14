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
  <body>
    <h1>
      <img src="./img/logo.jpg" width="224" height="77" alt=""/>
      検定取得状況管理システム
    </h1>
    <hr>
    こんにちは、
    <%
    out.println(session.getAttribute("log_name"));
    %>
    さん　>>　学生でログインしています　>>　取得状況更新 >>
    <input type="button" class="button button-3d" value="ログアウト" onClick="logout()" />
    <hr>
    <br>
    <div id="light">
    <form method="POST" action="./student_home">
      <input type="submit" class="button button-3d" value="学生ホーム" />
      </form>
      <form method="POST" action="./update">
      <input type="submit" class="button button-3d" value="取得状況更新" />
      </form>
    </div>
    <h2>
      取得状況更新
    </h2>
    <hr width="500" align="left">
    <br>

    <%
    ArrayList<Map<String,String>> array = (ArrayList<Map<String, String>>)request.getAttribute("test");
    if (array.size() == 0){
      out.println("現在追加できる資格が登録されていません。");
    }else {
      out.println("<form method=\"POST\" action=\"./update_adding\">");
      out.println("<select name=\"t_id\">");
      for (int i = 0; i < array.size(); i++) {
        Map<String, String> map = array.get(i);
        String test_name = map.get("test_name");
        String test_id = map.get("test_id");
        String test_class = map.get("t_class");
        String test_name_test_class = test_name + " ： " + test_class;
        %>
        <option name="t_id" value="<%=test_id%>">
          <%=test_name_test_class%>
        </option>
        <%
      }
      out.println("</select>");
      out.println("<input type=\"submit\" value=\"追加\" style=\"cursor:pointer;width:100px;height:25px\" /></form>");
    }
    array.clear();
    %>
    <br>
    <br>
  </body>
</html>
