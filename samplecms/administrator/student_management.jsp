
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
        if (document.myForm.PWD1.value == document.myForm.PWD2.value)
        {
          if (window.confirm('登録しますか？')){
            return true;
          }else{
            window.alert('キャンセルされました');
            return false;
          }
        }
        else{
          alert("パスワードが一致していません");
          return false;
        }
      }
      function okno(){
        if(window.confirm('削除しますか？')){
          return true;
        }
        else{
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
    さん　>>　管理者でログインしています　>>　学生管理　>>　
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
      学生新規登録
    </h2>
    <hr width="500" align="left">
    <br>
    <form method="POST" name="myForm" action="./student_management_insert">
      <input type="hidden" name="PWD">
      名前
      <br>
      <input type="text" name="i_name" placeholder="例：麻生　太郎" size="45" required />
      <br>
      <br>
      ID　(7桁でお願いします)
      <br>
      <input type="number" name="i_id" min="0" max="9999999" placeholder="例：0123456" size="45" required />
      <br>
      <br>
      パスワード
      <br>
      <input type="password" name="PWD1" placeholder="例：Password" size="45" required />
      <br>
      <br>
      パスワード（再入力）
      <br>
      <input type="password" name="PWD2" placeholder="例：Password" size="45" required />
      <br>
      <br>
      <input type="submit" onClick="return checkMe()" value="登録" />
      <input type="reset" value="リセット" />
    </form>
    <br>
    <br>
    <h2>
      学生削除
    </h2>
    <hr width="500" align="left">
    <br>
    <br>

    削除したい学生
    <br>

    <%
    ArrayList<Map<String,String>> array = (ArrayList<Map<String, String>>)request.getAttribute("user");
    if (array.size() == 0){
      out.println("<br>現在学生が登録されていません。");
    }else{
      out.println("<form method=\"POST\" name=\"myokno\" action=\"./student_management_delete\">");
      out.println("<select name=\"log_id\">");
      for (int i = 0; i < array.size(); i++) {
        Map<String, String> map = array.get(i);
        String log_id = map.get("log_id");
        String log_name = map.get("log_name");
        String log_id_log_name = log_id + " ： " + log_name;
        %>
        <option name="d_id" value="<%=log_id%>">
          <%=log_id_log_name%>
        </option>
        <%
      }
      array.clear();
      out.println("</select>");
      out.println("<br><br><input type=\"submit\" onClick=\"return okno()\" value=\"削除\" />");
    }
    %>

  </form>
</body>
</html>
