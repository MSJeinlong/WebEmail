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
    <title>添加联系人</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <!--引用本地的bootstrap文件和jquery文件-->
    <link rel="stylesheet" href="/Bootstrap/css/bootstrap.min.css"/>
    <script src="/Bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="/Bootstrap/js/bootstrap.min.js"></script>
    <script src="/js/changeRightFrame.js"></script>
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
            <h3 class="panel-title">新增联系人</h3>
        </div>
        <div class="panel-body">
            <div class="col-md-5 col-md-offset-3">
                <form action="/AddContactServlet" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label for="contactName" class="col-sm-4 control-label">联系人姓名：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="contactName" name="contactName"
                                   required="required" placeholder="ContactName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="contactEmail" class="col-sm-4 control-label">联系人邮箱：</label>
                        <div class="col-sm-8">
                            <input type="email" class="form-control" id="contactEmail" name="contactEmail"
                                   required="required" placeholder="ContactsEmail">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <button type="submit" class="btn btn-success btn-block">保存</button>
                        </div>
                        <div class="col-sm-4">
                            <button type="button" class="btn btn-warning btn-block" onclick="showContacts()">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%--根据Servlet的处理结果，弹出js提示框--%>
    <%
        String mess = (String) session.getAttribute("mess");
        if (mess == null || mess.equals("")) {
            //不做处理
        } else {%>
    <script type="text/javascript">
        alert("<%=mess%>");
    </script>
    <%
            session.setAttribute("mess", "");
        }%>
</div>
</body>
</html>
