<%@ page import="java.util.List" %>
<%@ page import="bean.ReceivedEmail" %>
<%@ page import="bean.SentEmail" %><%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/21/2019
  Time: 4:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看已发送的邮件内容</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <!--引用本地的bootstrap文件和jquery文件-->
    <link rel="stylesheet" href="/Bootstrap/css/bootstrap.min.css"/>
    <script src="/Bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="/Bootstrap/js/bootstrap.min.js"></script>
    <script src="/js/changeRightFrame.js"></script>
    <script src="/js/check.js"></script>
    <style>
        body{
            font-family: "微软雅黑";
            font-size: 12px;
        }
        .container-fluid{
            margin: 0;
            padding: 0;
        }
        #topFrame{
            height: 30%;
            width: 100%;
            border: 0;
        }
        #bottomFrame{
            height: 69%;
            width: 100%;
            border: 0;
            border-top: 1px solid dodgerblue;
        }
    </style>
</head>
<%
    //获取要查看的邮件的id
    String id = request.getParameter("id");
    int showEmailId = Integer.parseInt(id);
    List<SentEmail> list = (List<SentEmail>) session.getAttribute("sentEmailList");
    //根据id获取要查看的邮件，并存入session
    session.setAttribute("showSentEmail", list.get(showEmailId));
%>
<body>
<div class="container-fluid">
    <iframe id="topFrame" src="/showSentEmailSubject.jsp"></iframe>
    <iframe id="bottomFrame" src="/ShowSentContentServlet"></iframe>
</div>
</body>
</html>
