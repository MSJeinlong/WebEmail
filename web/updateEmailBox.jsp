<%@ page import="java.util.List" %>
<%@ page import="bean.Contact" %>
<%@ page import="bean.Email" %><%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/18/2019
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑邮箱</title>
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
<%
    String emailId = request.getParameter("id");
    System.out.println(emailId);
    int id = Integer.parseInt(emailId);
    List<Email> list = (List<Email>) session.getAttribute("emailsList");
    Email email = list.get(id);
    session.setAttribute("emailId", email.getId());
    session.setAttribute("emailAdr", email.getEmail());
    session.setAttribute("emailPasswd", email.getPassword());
    session.setAttribute("emialAlias", email.getAlias());
%>
<body>
<div class="container">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">编辑邮箱</h3>
        </div>
        <div class="panel-body">
            <div class="col-md-5 col-md-offset-3">
                <form action="/UpdateEmailServlet" method="post" class="form-horizontal" onsubmit="return checkEmail()">
                    <input type="hidden" value="${emailId}" name="emailId"/>
                    <div class="form-group">
                        <label for="email" class="col-sm-4 control-label">邮箱地址：</label>
                        <div class="col-sm-8">
                            <input type="email" class="form-control" id="email" name="email"
                                   required="required" placeholder="sample@email.com" value="${emailAdr}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-4 control-label">邮箱密码：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="password" name="password"
                                   required="required" placeholder="password" value="${emailPasswd}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="alias" class="col-sm-4 control-label">邮箱备注：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="alias" name="alias"
                                   required="required" placeholder="alias" value="${emialAlias}"/>
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
            <strong>提示：</strong>目前只支持 @163.com，@sina.cn 这几种邮箱。
        </div>
    </div>
</div>
<%--根据addEmailServlet的处理结果弹出提示框--%>
<%
    String mess = (String) session.getAttribute("updateEmailBoxMess");
    if (mess != null && !mess.equals("")) {
%>
<script>
    alert("<%=mess%>");

</script>
<%
        session.setAttribute("updateEmailBoxMess", "");
    }
%>
</body>
</html>
