<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<nav class="navbar navbar-default navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="javascript:void(0);">持明法洲后台管理系统 v1.0</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">欢迎&nbsp;&nbsp;&nbsp;${sessionScope.admin.username}<span class= "glyphicon glyphicon-user"></span></a></li>
        <li ><a href="${pageContext.request.contextPath}/admin/exit">退出登录&nbsp;&nbsp;<span class= "glyphicon glyphicon-log-in"></span></a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>