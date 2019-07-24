<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<div class = "container-fluid">
    <div class="bg">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <form action="${pageContext.request.contextPath}/user/login"  method="post" id = "myfor">
                <div class="form-inline form-group">
                    <div class="input-group">
                        <input type="text" id = "phone1"  name = "phone" width = "5px" placeholder="请输入有效手机号"/>
                    </div>
                    <button onclick="num()" type="button" class = "btn-primary">获取验证码</button>
                </div>
                <div class="form-inline form-group">
                    <div class="input-group">
                        <input type="text" id = "code" placeholder="验证码..."/>
                    </div>
                    <a  >输入验证码</a>
                </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-8">
                            <div class="checkbox ">
                                <label>
                                    <input type="checkbox" id = "checkb" name = "checkbox" value = "1" >我已同意<a href="javascript:void(0);">《持明法洲隐私协议》</a>
                                </label>
                            </div>
                        </div>
                    </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-6">
                        <%--         <button type="button" class="btn btn-primary col-sm-12" onclick="sub()" data-container=" " data-toggle = '' data-placement="" data-content = '' id = "login1">Login</button>--%>
                        <a tabindex="0" data-placement="top"  class="btn btn-primary col-sm-12" role="button" data-trigger="focus" title="提示" data-content="请勾选上方协议" id = "qq">1登录</a>
                    </div>
                </div>
            </form>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-6">
                        <%--         <button type="button" class="btn btn-primary col-sm-12" onclick="sub()" data-container=" " data-toggle = '' data-placement="" data-content = '' id = "login1">Login</button>--%>
                        <a onclick="regist()" id = "login">没有账号？点我立即注册</a>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-6">
                        <a onclick="loginuser()">使用账号密码登录</a>
                    </div>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
    </div>
</div>
<div class="modal fade loadMoal" id = "mymo" data-backdrop = "static" id="myModa2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel"><center>LOADING......</center></h4>
            </div>
            <div class="modal-body">
                <div class="progress">
                    <div id = "jd" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 10%">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>




<div class="modal  loadMoal" id = "mymo1" tabindex="-1" role="dialog" aria-labelledby="myModalLabe2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabe2"><center>信息提示</center></h4>
            </div>
            <div class="modal-body">
                <h3><center><font color = "red">请勾选协议框</font></center></h3>
            </div>
        </div>
    </div>
</div>



<div class="modal  loadMoal" id = "mymo3" tabindex="-1" role="dialog" aria-labelledby="myModalLabe3">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabe3"><center>信息提示</center></h4>
            </div>
            <div class="modal-body">
                <h3><center><font color = "red">该用户已冻结，联系管理员<a href = "http://www.mps.gov.cn/">TEL:18888888888</a></font></center></h3>
            </div>
        </div>
    </div>
</div>