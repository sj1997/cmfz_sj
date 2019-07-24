<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
<script type="text/javascript">
    $(function () {
        $.post("${pageContext.request.contextPath}/article/queryxq","id=${param.id}",function x(aa) {
            $("#title").text(aa.title);
            $("#edid").html(aa.content);
            $("#sm").text("上师:"+aa.guru.name);
        },"json")
    })

</script>
<div class="page-header">
    <h1><center><font color = "#00008b">文章管理</font></center></h1>
</div>
<div class="page-header">
    <h3><center id="title"></center></h3>
    <h5><center><font color="red" id="sm"></font></center></h5>
</div>
<div id="edid"></div>


