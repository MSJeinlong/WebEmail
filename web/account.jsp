<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/17/2019
  Time: 10:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <!--引用本地的bootstrap文件和jquery文件-->
    <link rel="stylesheet" href="/Bootstrap/css/bootstrap.min.css"/>
    <script src="/Bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="/Bootstrap/js/bootstrap.min.js"></script>
    <script src="/js/changeRightFrame.js"></script>
    <script src="/js/changeMainPage.js"></script>
    <style>
        body {
            font-family: "微软雅黑";
            background-color: #222222;
        }
    </style>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <!--<a href="#" class="navbar-brand">云知梦</a>-->
                <a class="navbar-brand" href="#">
                    <!--<img alt="Brand" src="/image/background.jpg" height="100%">-->
                    <span class="glyphicon glyphicon-th-large" aria-hidden="true"></span> WebEmail
                </a>
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#mynavbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>

            <div id="mynavbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 账号: ${username}</a>
                    </li>
                    <li onclick="showUserInfo()">
                        <a href="#"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 账号信息</a>
                    </li>
                    <li>
                        <a href="#"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> 当前邮箱：
                            ${currEmailAddr}
                        </a>
                    </li>
                    <li onclick="addMailBox()">
                        <a href="#"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 绑定新邮箱</a>
                    </li>
                    <li onclick="refreshReceivedEmails()">
                        <a href="#"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 刷新同步</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li onclick="mainPageTologin()">
                        <a href="#"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> 退出</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<%--根据Servlet的处理结果，弹出js提示框--%>
<%
    String mess = (String) session.getAttribute("message");
    if (mess == null || mess.equals("")) {
        //不做处理
    } else {%>
<script type="text/javascript">
    //alert("<%=mess%>");
    //登录成功，刷新收件箱
    refreshReceivedEmails();
</script>
<%
        session.setAttribute("message", "");
    }%>
</body>
</html>
