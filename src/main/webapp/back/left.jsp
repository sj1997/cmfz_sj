<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
    <div class="panel panel-default">
        <div class="panel-heading" role="tab" id="headingOne">
            <h4 class="panel-title">
                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                    轮播图
                </a>
            </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
            <div class="panel-body">
                <table>
                    <tr><td><font color = "blue"><a href = "javascript:void(0)" onclick = "lu()">轮播图管理</a></font></td></tr>
                </table>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading" role="tab" id="headingTwo">
            <h4 class="panel-title">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                    专辑
                </a>
            </h4>
        </div>
        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
            <div class="panel-body">
                <table>
                    <tr><td><font color = "blue"><a href = "javascript:void(0);" onclick="zj()">专辑和章节管理</a></font></td></tr>
                </table>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading" role="tab" id="headingThree">
            <h4 class="panel-title">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                    文章
                </a>
            </h4>
        </div>
        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
            <div class="panel-body">
                <table>
                    <tr><td><font color = "blue"><a href = "javascript:void(0);" onclick="wz()">文章管理</a></font></td></tr>
                </table>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading" role="tab" id="headingfour">
            <h4 class="panel-title">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapsefour" aria-expanded="false" aria-controls="collapsefour">
                    用户
                </a>
            </h4>
        </div>
        <div id="collapsefour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingfour">
            <div class="panel-body">
                <table>
                    <tr><td><font color = "blue"><a href = "javascript:void(0);" onclick="user()">用户管理</a></font></td></tr>
                    <tr><td><font color = "blue"><a href = "javascript:void(0);" onclick="userfb()">用户分布</a></font></td></tr>
                    <tr><td><font color = "blue"><a href = "javascript:void(0);" onclick="userbb()">注册报表</a></font></td></tr>
                </table>
            </div>
        </div>
    </div>
</div>
