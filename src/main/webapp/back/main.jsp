<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/extend/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.3.3.7.min.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/ajaxfileupload.js"></script>
    <title>持明法洲后台管理系统</title>
        <script type = "text/javascript">

            function lu(){
                $("#th").load("${pageContext.request.contextPath}/back/editlunbo.jsp");
            }
            function zj() {
                $("#th").load("${pageContext.request.contextPath}/back/editzhangjie.jsp");
            }
           function wz() {
               $("#th").load("${pageContext.request.contextPath}/back/editwenzhang.jsp");
           }
           function xq(aa) {
               <%--$("#th").load("${pageContext.request.contextPath}/article/queryxq?id="+aa.id);--%>
               $("#th").load("${pageContext.request.contextPath}/back/wenzhangxq.jsp?id="+aa.id);
           }
           function user() {
               $("#th").load("${pageContext.request.contextPath}/back/edituser.jsp");
           }
           function sele(a) {
               $.post("${pageContext.request.contextPath}/user/updateStatus","status="+a.value+"&id="+a.id,function x(aa) {
                   $("#tab").trigger("reloadGrid");
               },"json")
           }
           function selelunbo(a) {
               $.post("${pageContext.request.contextPath}/carousel/updateStatus","status="+a.value+"&id="+a.id,function x(aa) {
                   $("#tab").trigger("reloadGrid");
               },"json")
           }
           function seless(a) {
               $.post("${pageContext.request.contextPath}/guru/updateStatus","status="+a.value+"&id="+a.id,function x(aa) {
                   $("#tab").trigger("reloadGrid");
               },"json")
           }
           function selesex(a) {
               $.post("${pageContext.request.contextPath}/guru/updateSex","sex="+a.value+"&id="+a.id,function x(aa) {
                   $("#tab").trigger("reloadGrid");
               },"json")
           }
           function outpoi() {
               var val = $("#out").val();
              if(val != "" || val != null){
                  $.post("${pageContext.request.contextPath}/user/outpoi","path="+val,function x(aa) {
                      if (aa=="200"){
                        $("#mymo3").modal("show");
                        $("#ts").html("<center><font color = \"red\">导出成功</font></center>")
                      }
                      if(aa == "500"){
                          $("#mymo3").modal("show");
                          $("#ts").html("<center><font color = \"red\">导出失败</font></center>")
                      }
                  },"json")
              }else{

              }
           }
           function outpoixz() {
               $.post("${pageContext.request.contextPath}/user/outpoixz",function x(aa) {
               },"json")
           }
           $(function () {
               if(${param.sign  == "1"}){
                   $("#th").load("${pageContext.request.contextPath}/back/edituser.jsp");
               }
           })
            function userfb() {
                $("#th").load("${pageContext.request.contextPath}/back/userfenbu.jsp");
            }
            function userbb() {
                $("#th").load("${pageContext.request.contextPath}/back/userregistmap.jsp");
            }
        </script>
    <style>
        #mymo3{
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translateX(-50%) translateY(-50%);

        }
    </style>
</head>
<body>
    <jsp:include   page="head.jsp"/>
    <%--页面主体--%>
    <div class= "container-fluid">
    <div class= "row">
        <%--左栏--%>
        <div class="col-sm-2">
            <jsp:include page="left.jsp"/>
        </div>
        <%--右栏--%>
        <div class="col-sm-10" id="th">
            <jsp:include   page="right.jsp"/>
        </div>
    </div>
    </div>
    <%--页尾--%>
    <jsp:include page="foot.jsp"/>
</body>
</html>