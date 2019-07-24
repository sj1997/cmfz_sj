<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<table>
  <form action = "${pageContext.request.contextPath}/admin/login" method = "post">
    <tr>
        <td>用户名</td>
        <td><input type = "text" name = "username"/></td>
    </tr>
    <tr>
         <td>密码</td>
        <td><input type = "password" name = "password"/></td>
    </tr>
    <tr>
         <td><img src = ""></td>
         <td><input type = "text" name = "image"/></td>
    </tr>
     <tr>
          <td><input type = "submit" value = "login"></td>
     </tr>
  </form>
  </table>