<%@ page import="java.util.List" %>
<%@ page import="bean.Contact" %><%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/18/2019
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <!--引用本地的bootstrap文件和jquery文件-->
    <link rel="stylesheet" href="/Bootstrap/css/bootstrap.min.css"/>
    <script src="/Bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="/Bootstrap/js/bootstrap.min.js"></script>
    <script src="/js/changeRightFrame.js"></script>
    <script src="/js/check.js"></script>
    <style>
        body {
            font-family: "微软雅黑";
        }
    </style>
</head>
<body>
<div class="container">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">修改密码</h3>
        </div>
        <div class="panel-body">
            <div class="col-md-5 col-md-offset-3">
                <form action="/NewPasswordServlet" method="post" class="form-horizontal" onsubmit="return checkNewPassword()">
                    <div class="form-group">
                        <label for="oldPassword" class="col-sm-4 control-label">原密码：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="oldPassword" name="oldPassword"
                                   required="required" placeholder="oldPassword">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newPassword" class="col-sm-4 control-label">新密码：</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="newPassword" name="newPassword"
                                   required="required" placeholder="newPassword" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="conPassword" class="col-sm-4 control-label">确认新密码：</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="conPassword" name="conPassword"
                                   required="required" placeholder="confirmPassword" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <button type="submit" class="btn btn-success btn-block">保存</button>
                        </div>
                        <div class="col-sm-4">
                            <button type="button" class="btn btn-warning btn-block" onclick="showUserInfo()">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="panel-footer">
            <strong>提示：</strong>修改密码后请用新密码登录。
        </div>
    </div>
</div>
<%--根据Servlet的处理结果弹出提示框--%>
<%
    String mess = (String) session.getAttribute("changePasswordMess");
    if (mess != null && !mess.equals("")) {
%>
<script>
    alert("<%=mess%>");

</script>
<%
        session.setAttribute("changePasswordMess", "");
    }
%>
</body>
</html>
