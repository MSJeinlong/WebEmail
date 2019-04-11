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
        <li role="presentation" class="nav-li ${active==1?"active":""}" onclick="showSendEmail()">
            <a href="#"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 发邮件</a>
        </li>
        <li role="presentation" class="nav-li ${active==2?"active":""}" onclick="showContacts()">
            <a href="#"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 通讯录
                <span class="badge pull-right">${contactNumber}</span></a>
        </li>
        <li role="presentation" class="nav-li ${active==3?"active":""}" onclick="showEmails()">
            <a href="#"><span class="glyphicon glyphicon-inbox" aria-hidden="true"></span> 收件箱
                <span class="badge pull-right">${receivedEmailsNumber}</span></a>
        </li>
        <%--<li role="presentation" class="navli">--%>
        <%--<a href="#"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 草稿箱--%>
        <%--<span class="badge pull-right">${draftsEmailsNumber}</span></a>--%>
        <%--</li>--%>
        <%--<li role="presentation" class="navli">--%>
        <%--<a href="#"><span class="glyphicon glyphicon-star" aria-hidden="true"></span> 收藏箱--%>
        <%--<span class="badge pull-right">${favoritesEmailsNumber}</span></a>--%>
        <%--</li>--%>
        <li role="presentation" class="nav-li ${active==4?"active":""}" onclick="showAllSentEmail()">
            <a href="#"><span class="glyphicon glyphicon-send" aria-hidden="true"></span> 已发送
                <span class="badge pull-right">${sentEmailNumber}</span></a>
        </li>
        <%--<li role="presentation" class="navli">--%>
        <%--<a href="#"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 已删除--%>
        <%--<span class="badge pull-right">${deletedEmailsNumber}</span></a>--%>
        <%--</li>--%>
        <li role="presentation" class="nav-li">
            <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> 邮箱账号 <span class="caret"></span>
            </a>
        </li>
    </ul>
    <div class="collapse" id="collapseExample">

        <ul class="nav nav-pills nav-stacked">
            <%
                List<Email> list = (List<Email>) session.getAttribute("emailsList");
                for (int i = 0; i < list.size(); i++) {
                    Email email = list.get(i);
                    session.setAttribute("i", i);
            %>
            <li role="presentation" class="coll-li pull-left ${activeEmailBox==i?"active":""}"><a
                    href="/SwitchEmailBoxServlet?id=${i}"><strong><%=email.getAlias()%>:
            </strong><%=email.getEmail()%>
            </a></li>
            <%
                }
            %>
        </ul>
    </div>
</div>
<%
    String mess = (String) session.getAttribute("switchMess");
    if (mess != null && !mess.equals("")) {
%>
<script>
    alert("<%=mess%>");
    window.parent.document.getElementById("rightFrame").src = "/ReceiveEmails.jsp";
    window.parent.document.getElementById("topFrame").contentWindow.location.reload(true);
</script>
<%
        session.setAttribute("switchMess", "");
    }
%>
<script>
    $(".nav-li").mousedown(function () {
        $(this).addClass('active');
        $('.nav-li').not($(this)).removeClass("active");
    });
    $(".coll-li").mousedown(function () {
        $(this).addClass("active");
        $(".coll-li").not($(this)).removeClass("active");
    });
</script>
</body>
</html>
