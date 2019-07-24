<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script>
    $(function () {
        $("#tab").jqGrid({
            styleUI:"Bootstrap",
            url : "${pageContext.request.contextPath}/carousel/findByPage",
            datatype: "json",
            colNames:["编号","轮播图名称","轮播图图片","状态","上传时间"],
            colModel:[{
                name : "id"
            },{
                name : "title",
                editable:true
            },{
                name: "imgPath",
                editable:true,
                edittype : "file",
                formatter:function(cellvalue, options, rowObject){
                    return "<img class = 'book-cover'height = '50px' src = '${pageContext.request.contextPath}/carouselPic/"+cellvalue+"'/>"
                },
            },{
                name : "status",edittype:"select",editoptions:{value:"on:on;off:off"},
                editable:true,
                formatter:function(cellvalue, options, rowObject){
                    if(cellvalue == "on"){
                        return   "<select name = 'status'id = '"+rowObject.id+"'onchange='selelunbo(this)'>" +
                            "<option value = 'on' selected = 'selected' >on</option>" +
                            "<option value = 'off'>off</option>"
                    }else if(cellvalue == "off"){
                        return   "<select name = 'status' id = '"+rowObject.id+"'onchange='selelunbo(this)'>" +
                            "<option value = 'on'>on</option>" +
                            "<option value = 'off' selected = 'selected'>off</option>"
                    }
                }
            },{
                name : "createTime",
                editable:true,
                edittype:"date",
            }],
            width : 1000,
            pager: "pager",
            editurl:"${pageContext.request.contextPath}/carousel/ope",
            rowNum:3,
            pager:"#pager",
            viewrecords:true,
            mtype: "post",
            autowidth:true,
            rowList:[3,5,10],
            page: 1,
            caption: "轮播图管理",
            multiselect :true,
            rownumbers: true,
        }).jqGrid("navGrid","#pager",{search:false},{
            closeAfterEdit:true,
            afterSubmit:function(response){
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/carousel/upload",
                    fileElementId:"imgPath",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function(){
                        $("#tab").trigger("reloadGrid");
                    },
                    error:function () {
                        alert("文件上传失败！")
                    }
                    })
                return "[true]";
            }
        },{
            closeAfterAdd:true,
            afterSubmit:function(response){
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/carousel/upload",
                    fileElementId:"imgPath",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function(){
                        $("#tab").trigger("reloadGrid");
                    },
                    error:function () {
                        alert("文件上传失败！")
                    }

                })
                return "[true]";
            }
        })
    });
</script>
<div class="page-header">
    <h1>轮播图管理</h1>
</div>
<table id="tab"></table>
<div id="pager"></div>