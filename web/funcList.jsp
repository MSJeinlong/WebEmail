<%@ page import="java.util.List" %>
<%@ page import="bean.Email" %><%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/17/2019
  Time: 10:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>邮箱功能导航</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <!--引用本地的bootstrap文件和jquery文件-->
    <link rel="stylesheet" href="/Bootstrap/css/bootstrap.min.css"/>
    <script src="/Bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="/Bootstrap/js/bootstrap.min.js"></script>
    <script src="/js/changeRightFrame.js"></script>
    <style>
        .container-fluid {
            margin: 0;
            padding: 0;
        }

        body {
            font-family: "微软雅黑";
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <ul class="nav nav-pills nav-stacked">
        <li role="presentation" class="navli">
            <a href="#"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 发邮件</a>
        </li>
        <li role="presentation" class="navli" onclick="showContacts()">
            <a href="#"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 通讯录<span
                    class="badge pull-right">${contactNumber}</span></a>
        </li>
        <li role="presentation" class="navli" onclick="showEmails()">
            <a href="#"><span class="glyphicon glyphicon-inbox" aria-hidden="true"></span> 收件箱<span
                    class="badge pull-right">14</span></a>
        </li>
        <li role="presentation" class="navli">
            <a href="#"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 草稿箱</a>
        </li>
        <li role="presentation" class="navli">
            <a href="#"><span class="glyphicon glyphicon-star" aria-hidden="true"></span> 收藏箱</a>
        </li>
        <li role="presentation">
            <a href="#"><span class="glyphicon glyphicon-send" aria-hidden="true"></span> 已发送</a>
        </li>
        <li role="presentation" class="navli">
            <a href="#"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 已删除</a>
        </li>
        <li role="presentation" class="navli">
            <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> 邮箱账号 <span class="caret"></span>
            </a>
        </li>
    </ul>
    <div class="collapse" id="collapseExample">
        <div class="well">
            <ul class="nav nav-pills nav-stacked">
                <%
                    List<Email> list = (List<Email>) session.getAttribute("emailsList");
                    for (Email emial : list) {
                %>
                <li role="presentation" class="coll-li"><a href="#"><strong><%=emial.getAlias()%></strong>:<%=emial.getEmail()%>
                </a></li>
                <%
                    }
                %>
                <%--<li role="presentation" class="coll-li">--%>
                <%--<a href="#">新浪邮箱</a>--%>
                <%--</li>--%>
                <%--<li role="presentation" class="coll-li">--%>
                <%--<a href="#">qq邮箱</a>--%>
                <%--</li>--%>
            </ul>
        </div>
    </div>
</div>
<script>
    $(".navli").mousedown(function () {
        $(this).addClass('active');
        $('.navli').not($(this)).removeClass("active");
    });
    $(".coll-li").mousedown(function () {
        $(this).addClass("active");
        $(".coll-li").not($(this)).removeClass("active");
    });
</script>
</body>
</html>
