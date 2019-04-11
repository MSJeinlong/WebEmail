<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/22/2019
  Time: 12:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>转发</title>
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
            font-size: 12px;
        }

        .container-fluid {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<%
    String flag = request.getParameter("flag");
    session.setAttribute("flag", flag);
%>
<body>
<div class="container-fluid">
    <div class="panel panel-info">
        <div class="panel-heading">
            <button class="btn btn-warning btn-sm" onclick="back(${flag})">返回</button>
                转发邮件
        </div>
        <div class="panel-body">
            <div class="col-md-6 col-md-offset-3">
                <form class="form-horizontal" action="/ForwardEmailServlet" method="post" id="myForm">
                    <input type="hidden" name="flag" value="${flag}"/>
                    <div class="form-group">
                        <label for="receiver" class="col-md-2 control-label">收件人：</label>
                        <div class="col-md-9">
                            <input type="email" name="email_to" class="form-control" id="receiver" placeholder="收件人邮箱地址" required="required"/>
                        </div>
                    </div>
                    <%--<input type="submit" class="btn btn-success btn-block form-control" value="发送"/>--%>
                    <button type="submit" class="col-md-9 col-md-offset-2 btn btn-success">发送</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
