<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script>
    $(function () {
        $("#tab").jqGrid({
            styleUI:"Bootstrap",
            url : "${pageContext.request.contextPath}/user/findByPage",
            datatype: "json",
            colNames:["编号","姓名","法名","密码","注册手机号","省份","城市","性别","个性签名","状态","注册时间","头像","信仰上师"],
            colModel:[
                {name : "id"},
                {name : "profile",width:"100px"},
                {name : "dharmaName",width:"100px"},
                {name : "password",
                    formatter:function(cellvalue, options, rowObject){
                    return "************";
                    }
                },
                {name : "phone"},
                {name : "province",width:"100px"},
                {name : "city",width:"100px"},
                {name : "gender",width:"80px"},
                {name : "personalSign"},
                {name : "status",width:"100px",
                    formatter:function(cellvalue, options, rowObject){
                    if(cellvalue == "正常"){
                        return   "<select name = 'status'id = '"+rowObject.id+"'onchange='sele(this)'>" +
                        "<option value = '正常' selected = 'selected' >正常</option>" +
                        "<option value = '冻结'>冻结</option>"
                    }else if(cellvalue == "冻结"){
                        return   "<select name = 'status' id = '"+rowObject.id+"'onchange='sele(this)'>" +
                            "<option value = '正常'>正常</option>" +
                            "<option value = '冻结' selected = 'selected'>冻结</option>"
                    }
                    },
                    editable:true},
                {name : "registTime"},
                {name : "cover",
                    formatter:function(cellvalue, options, rowObject){
                        return "<img class = 'book-cover'height = '50px' src = '${pageContext.request.contextPath}/carouselPic/"+cellvalue+"'/>"
                    }
                },
                {name : "guru.name"},
                ],
            width : 1000,
            pager: "pager",
            editurl:"${pageContext.request.contextPath}/user/ope",
            rowNum:3,
            pager:"#pager",
            viewrecords:true,
            mtype: "post",
            autowidth:true,
            rowList:[3,5,10],
            page: 1,
            caption: "用户管理",
            rownumbers: true,
        }).jqGrid("navGrid","#pager",{search:false,add:false,del:false,edit:false},{
            closeAfterEdit:true,
        },{
            closeAfterAdd:true,
        })
    });
</script>
<div class="page-header">
    <h1>用户管理</h1>
</div>
<form class="form-inline">
    <div class="form-group">
        <input type="text" class="form-control" id="out" placeholder="请输入导出地址">
    </div>
    <button type="button" class="btn btn-default" onclick="outpoi()">导出表信息</button>
</form>


<form class="form-inline" action="${pageContext.request.contextPath}/user/inpoi" method="post"  id = "myform" enctype="multipart/form-data">
    <div class="form-group">
        <input type="file" name = "file" class="form-control" id="exampleInputEmail3" placeholder="">
    </div>
    <button type="submit" class="btn btn-default" >导入表信息</button>
</form>





<table id="tab"></table>
<div id="pager"></div><a type="button" class="btn btn-success" href="${pageContext.request.contextPath}/user/outpoixz"><span class = "glyphicon glyphicon-download-alt">下载到本地</span></a>
<div class="modal  loadMoal" id = "mymo3"  id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabe2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabe2"><center>信息提示</center></h4>
            </div>
            <div class="modal-body">
                <h3 id = "ts"></h3>
            </div>
        </div>
    </div>
</div>