<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
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
    <script type="text/javascript">
    function num(){
    var val = $("#phone").val();
    $.get("${pageContext.request.contextPath}/user/code","phone="+val,function (data) {
        alert(data);
        $("#code").blur(function () {
            var val1 = $("#code").val();
            if(val1 == data){
                $("#qqq").click(function () {
                    $("#myform").submit();
                })
            }else{
                alert("验证码输入不正确")
                $("#qqq").click(function () {
                })
            }

        })

    },"json")
    }
    function yz(a){
        var val = $("#inputPassword5").val();
        var val1 = $("#inputPassword4").val();
        if(val1 != val){
            alert("两次密码不一致！")
        }
    }
    function testphone() {
        var phone1 = $("#phone1").val();
        $.post("${pageContext.request.contextPath}/user/testPhone","phone="+phone1,function (data) {
            if(data == "no"){
                alert("该手机号已注册！");
            }else{

            }
        },"text")
    }

    </script>
    <style>

    </style>
    </head>
<body>
<%--页头--%>
<jsp:include page="loginhead.jsp"/>
<%--页体--%>
<div id = "body">
    <jsp:include page="registbody.jsp"/>
</div>
<%--页尾--%>
<jsp:include page="foot.jsp"/>
</body>
</html>
