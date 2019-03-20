<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/17/2019
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WebEmail</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <!--引用本地的bootstrap文件和jquery文件-->
    <link rel="stylesheet" href="/Bootstrap/css/bootstrap.min.css"/>
    <script src="/Bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="/Bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/mainPage.css">
    <link rel="stylesheet" href="/css/clearMargin.css">
    <style>
        .container-fluid{
            padding: 0;
            margin: 0;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <iframe class="top" src="account.jsp" id="topFrame"></iframe>
    <iframe class="left" src="funcList.jsp" id="leftFrame"></iframe>
    <iframe class="right" src="userInfo.jsp" id="rightFrame"></iframe>
</div>
</body>
</html>
