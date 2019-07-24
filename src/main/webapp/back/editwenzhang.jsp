<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
<script>
    $(function () {
        KindEditor.create('#editorid',{
            width : '100%',
            resizeType:"1",
            afterBlur:function () {
                this.sync();
            },
            fileManagerJson:"${pageContext.request.contextPath}/article/allpictrue",
            uploadJson:"${pageContext.request.contextPath}/article/uploadPictrue",
            allowFileManager:true,
            filePostName:"file"
            }
        );
        })
    $(function () {
        $("#tab").jqGrid({
            styleUI:"Bootstrap",
            multiselect :true,
            url : "${pageContext.request.contextPath}/guru/findByPage",
            datatype: "json",
            colNames:["编号","上师名称","生平简介","状态","性别"],
            colModel:[
                {name:"id"},
                {name:"name",width:"100px",editable:true,},
                {name:"profile",editable:true},
                {name:"status",
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue == "正常"){
                            return   "<select name = 'status' id = '"+rowObject.id+"'onchange='seless(this)'>" +
                                "<option value = '正常' selected = 'selected' >正常</option>" +
                                "<option value = '冻结'>冻结</option>"
                        }else if(cellvalue == "冻结"){
                            return   "<select name = 'status' id = '"+rowObject.id+"'onchange='seless(this)'>" +
                                "<option value = '正常'>正常</option>" +
                                "<option value = '冻结' selected = 'selected'>冻结</option>"
                        }
                    },editable:true,width:"60px",edittype:"select",editoptions:{value:"0:正常;1:冻结"}
                },
                {name:"sex",editable:true,edittype:"select",width:"60px",editoptions:{value:"男:男;女:女"},
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue == "男"){
                            return   "<select name = 'status' id = '"+rowObject.id+"'onchange='selesex(this)'>" +
                                "<option value = '男' selected = 'selected' >男</option>" +
                                "<option value = '女'>女</option>"
                        }else if(cellvalue == "女"){
                            return   "<select name = 'status' id = '"+rowObject.id+"'onchange='selesex(this)'>" +
                                "<option value = '男'>男</option>" +
                                "<option value = '女' selected = 'selected'>女</option>"
                        }
                    }

                },
            ],
            width : 1000,
            pager: "pager",
            editurl:"${pageContext.request.contextPath}/guru/ope",
            rowNum:3,
            pager:"#pager",
            viewrecords:true,
            mtype: "post",
            height : '100%',
            autowidth:true,
            rowList:[3,5,10],
            page: 1,
            caption: "上师管理",
            rownumbers: true,
            subGrid : true,
            subGridRowExpanded : function(subgrid_id, zjid) {
                var subgrid_table_id, pager_id;
                subgrid_table_id = subgrid_id + "_t";
                pager_id = "p_" + subgrid_table_id;
                $("#" + subgrid_id).html(
                    "<table id='" + subgrid_table_id
                    + "' class='scroll'></table><div id='"
                    + pager_id + "' class='scroll'></div>");
                jQuery("#" + subgrid_table_id).jqGrid(
                    {
                        url : "${pageContext.request.contextPath}/article/findByPage?id="+zjid,
                        datatype : "json",
                        autowidth:true,
                        colNames : [ '编号', '所属上师', '标题',"发表时间",'操作' ],
                        colModel : [
                            {name : "id"},
                            {name : "guru.name",},
                            {name : "title",editable:true},
                            {name: "publishTime",editable:true,edittype:"date"},
                            {name:"id",
                                formatter:function(cellvalue, options, rowObject){
                                    return "<button type='button' class='btn btn-info'id = '"+cellvalue+"' value = '"+cellvalue+"'onclick='xq(this)'>详情页</button>"
                                }
                            }
                        ],
                        rowNum : 3,
                        pager : pager_id,
                        rowList:[3,5,10],
                        height : '100%',
                        styleUI:"Bootstrap",
                        caption: "文章管理",
                        editurl:"${pageContext.request.contextPath}/article/ope?fid="+zjid,
                        viewrecords:true,
                        records:true,
                        multiselect :true,
                    });
                jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                    "#" + pager_id, {search:false,add:false},{closeAfterEdit:true},{closeAfterAdd:true});
            },
        }).jqGrid("navGrid","#pager",{search:false},{closeAfterEdit:true},{closeAfterAdd:true})
    });
    function uploadArticle() {
        $.post("${pageContext.request.contextPath}/article/uploadArticle",$("#myForm").serialize(),function (data) {

        },"json")
        $("#myModal").modal("hide");
        $("#myin").val("");
        $("#editorid").val("");
        $("#tab").trigger("reloadGrid");
    }
    $(function () {
        $.get("${pageContext.request.contextPath}/guru/queryAll",function x(data) {
            var $select = $("#select");
                for(var i = 0 ; i<data.length;i++){
                    $select.append("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
                }
        },"json")
    })
</script>
<div class="page-header">
    <h1>文章管理</h1>
</div>
<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">
    上传文章
</button>
<table id="tab"></table>
<div id="pager"></div>
<div class="modal fade bs-example-modal-lg" id="myModal"  role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="myForm" action="javascript:void(0);">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <input type="text" class="form-control" id = "myin" name = "title" placeholder="文章标题">
                    <select id="select" name = "guru.id" class="form-control" >

                    </select>
                <div class="modal-body">
               <textarea id="editorid" name="content">
                </textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="uploadArticle()">上传</button>
            </div>
            </form>
        </div>
    </div>
</div>