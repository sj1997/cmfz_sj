<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script>
    $(function () {
        $("#tab").jqGrid({
            styleUI:"Bootstrap",
            multiselect :true,
            url : "${pageContext.request.contextPath}/album/findByPage",
            datatype: "json",
            colNames:["编号","专辑名称","专辑封面","章节数量","专辑得分","专辑作者","播音员","专辑简介","出版时间"],
            colModel:[
                {name:"id"},
                {name:"title",editable:true,},
                {name:"cover",editable:true,edittype:"file",
                    formatter:function(cellvalue, options, rowObject) {
                        return "<img class = 'book-cover'height = '50px' src = '${pageContext.request.contextPath}/carouselPic/"+cellvalue+"'/>"
                    }
                },
                {name:"count"},
                {name:"score",editable:true,edittype:"number"},
                {name:"author",editable:true},
                {name:"broadcast",editable:true},
                {name:"brief",editable:true},
                {name:"publishTime",editable:true,edittype:"date"}],
            width : 1000,
            pager: "pager",
            editurl:"${pageContext.request.contextPath}/album/ope",
            rowNum:3,
            pager:"#pager",
            viewrecords:true,
            mtype: "post",
            height : '100%',
            autowidth:true,
            rowList:[3,5,10],
            page: 1,
            caption: "专辑管理",
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
                        url : "${pageContext.request.contextPath}/chapter/findByPage?id="+zjid,
                        datatype : "json",
                        autowidth:true,
                        colNames : [ '编号', '专辑编号', '音频名称', '音频大小',"下载路径",'在线欣赏','上传时间','操作' ],
                        colModel : [
                            {name : "id"},
                            {name : "album.id",width:"100px"},
                            {name : "title",editable:true,width:"100px"},
                            {name : "size",width:"100px"},
                            {name: "downPath",editable:true,edittype:"file",width:"100px"},
                            {name: "downPath",
                                width: "300px" ,
                                formatter:function(cellvalue, options, rowObject) {
                                    return "<audio controls src='${pageContext.request.contextPath}/music/"+cellvalue+"'></audio>" ;
                                }
                            },
                            {name : "uploadTime",editable:true,edittype:"date"},
                            {width:"100px",
                                name: "downPath",
                                formatter:function(cellvalue, options, rowObject) {
                                    return "<a class='btn btn-success' href='${pageContext.request.contextPath}/chapter/download?name="+cellvalue+"' role='button'><span class= 'glyphicon glyphicon-save'></span></a>" ;
                                }
                            },
                        ],
                        rowNum : 3,
                        pager : pager_id,
                        rowList:[3,5,10],
                        height : '100%',
                        styleUI:"Bootstrap",
                        caption: "章节管理",
                        editurl:"${pageContext.request.contextPath}/chapter/ope?fid="+zjid,
                        viewrecords:true,
                        records:true,
                        multiselect :true,
                    });
                jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                    "#" + pager_id, {search:false},{
                        closeAfterEdit:true,
                        afterSubmit:function(response){
                            $.ajaxFileUpload({
                                url:"${pageContext.request.contextPath}/chapter/upload",
                                fileElementId:"downPath",
                                data:{"id":response.responseText},
                                type:"post",
                                success:function(){
                                    $("tab").trigger("reloadGrid");
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
                                url:"${pageContext.request.contextPath}/chapter/upload",
                                fileElementId:"downPath",
                                data:{"id":response.responseText},
                                type:"post",
                                success:function(){
                                    $("tab").trigger("reloadGrid");
                                },
                                error:function () {
                                    alert("文件上传失败！")
                                }

                            })
                            return "[true]";
                        }
                    });
            },
        }).jqGrid("navGrid","#pager",{search:false},{
            closeAfterEdit:true,
            afterSubmit:function(response){
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/album/upload",
                    fileElementId:"cover",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function(){
                        $("tab").trigger("reloadGrid");
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
                    url:"${pageContext.request.contextPath}/album/upload",
                    fileElementId:"cover",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function(){
                        $("tab").trigger("reloadGrid");
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
    <h1>专辑管理<audio src="${pageContext.request.contextPath}/music/Potent.mp3" >dsgr</audio></h1>
</div>
<table id="tab"></table>
<div id="pager"></div>
