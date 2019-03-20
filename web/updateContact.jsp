<%@ page import="bean.Contact" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/18/2019
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改联系人</title>
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
<%
    String id = request.getParameter("id");
    List<Contact> list = (List<Contact>) session.getAttribute("contactList");
    int index = Integer.parseInt(id);
    String contactName = list.get(index).getContact_name();
    String contactEmail = list.get(index).getContact_email();
    int contactId = list.get(index).getId();
    session.setAttribute("contactName", contactName);
    session.setAttribute("contactEmail", contactEmail);
    session.setAttribute("contactId", contactId);
%>
<div class="container">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">修改联系人</h3>
        </div>
        <div class="panel-body">
            <div class="col-md-5 col-md-offset-3">
                <form action="/UpdateContactServlet" method="post" class="form-horizontal">
                    <input type="hidden" name="contactId" value="${contactId}"/>
                    <div class="form-group">
                        <label for="contactName" class="col-sm-4 control-label">联系人姓名：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="contactName" name="contactName"
                                   required="required" placeholder="ContactName" value="${contactName}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="contactEmail" class="col-sm-4 control-label">联系人邮箱：</label>
                        <div class="col-sm-8">
                            <input type="email" class="form-control" id="contactEmail" name="contactEmail"
                                   required="required" placeholder="ContactsEmail" value="${contactEmail}"/>
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
    <%--根据updateContactServlet的处理结果，弹出js提示框--%>
    <%
        String mess = (String) session.getAttribute("updateContactMess");
        if (mess != null && !mess.equals("")) {
    %>
    <script>
        alert("${mess}");
    </script>
    <%
            session.setAttribute("updateContactMess", "");
        }
    %>
</div>
</body>
</html>
