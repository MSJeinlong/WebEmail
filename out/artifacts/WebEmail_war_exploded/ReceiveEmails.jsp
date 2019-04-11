<%@ page import="java.util.List" %>
<%@ page import="bean.Contact" %>
<%@ page import="bean.Email" %>
<%@ page import="bean.SentEmail" %>
<%@ page import="bean.ReceivedEmail" %><%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/18/2019
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收件箱</title>
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
            font-size: 12px;
        }

        #panel-table {
            margin-top: 60px;
        }

        #email-search {
            margin-top: 0px;
        }
    </style>
</head>
<body>
<div class="container-fluid">

    <div class="panel panel-info navbar-fixed-top">
        <div class="panel-heading">
            <%--<button type="button" class="btn btn-success" onclick="">删除</button>--%>
            <%--<button type="button" class="btn btn-primary btn-sm" onclick="">回复</button>--%>
            <%--<button type="button" class="btn btn-info btn-sm" onclick="showEmails()">刷新</button>--%>
            <%--<button type="button" class="btn btn-success btn-sm" onclick="">转发</button>--%>
            <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal"
                    onclick="">删除
            </button>
            <form action="/SearchReceivedEmailServlet" class="navbar-form navbar-right" method="post" id="email-search">
                <div class="form-group">
                    <input type="text" name="keyName" class="form-control"  value="${param.keyName}" placeholder="邮件搜索"/>
                </div>
                <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-search"></span>
                </button>
            </form>
        </div>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">删除确认</h4>
                </div>
                <div class="modal-body">
                    <%--<h4 class="modal-title" id="">title</h4>--%>
                    <p class="text-warning">
                        <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> 您确定要删除选中的邮件吗？
                    </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary confirm">确认</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>

    <div class="panel panel-info" id="panel-table">
        <!--自适应的，好看的，响应式的表格-->
        <div class="table-responsive">
            <form action="#" method="post">
                <table class="table table-striped table-hover table-condensed">

                    <tr class="info">
                        <th><input type="checkbox" id="checkAll" onclick="selectAll()"/> 全选</th>
                        <th>发件人</th>
                        <th>主题</th>
                        <th>时间</th>
                        <th>是否有附件</th>
                    </tr>
                    <%
                        List<ReceivedEmail> list = (List<ReceivedEmail>) session.getAttribute("receivedEmails");
                        for (int i = 0; i < list.size(); i++) {
                            ReceivedEmail receivedEmail = list.get(i);
                    %>
                    <tr>
                        <td><input type="checkbox" name="checkOne"/>
                            <a href="/readReceivedEmail.jsp?id=<%=i%>"> <%=i + 1%>
                        </a>
                        </td>
                        <td><a href="/readReceivedEmail.jsp?id=<%=i%>"><%=receivedEmail.getSender()%>
                        </a>
                        </td>
                        <td><a href="/readReceivedEmail.jsp?id=<%=i%>"><%=receivedEmail.getSubject()%>
                        </a>
                        </td>
                        <td><%=receivedEmail.getSentDate()%>
                        </td>
                        <td><%= receivedEmail.isHaveAttachment() == true ? "是" : "否"%>
                        </td>
                    </tr>
                    <%
                        }
                    %>

                </table>
            </form>

        </div>
    </div>

    <script>
        $(".confirm").click(function () {
            //隐藏Modal
            $("#myModal").modal("hide");
            //执行删除操作
            deleteReceivedEmails();
        });
    </script>
</div>
<%
    String refreshMess = (String) session.getAttribute("refreshMess");
    if (refreshMess != null && !refreshMess.equals("")) {
%>
<script>
    alert("<%=refreshMess%>");
    window.parent.document.getElementById("leftFrame").contentWindow.location.reload(true);
</script>

<%
        session.setAttribute("refreshMess", "");
    }
%>
<%
    String trueDeleteMess = (String) session.getAttribute("trueDelete");
    if (trueDeleteMess != null && !trueDeleteMess.equals("")) {
%>
<script>
    alert("<%=trueDeleteMess%>");
    window.parent.document.getElementById("leftFrame").contentWindow.location.reload(true);
</script>
<%
        session.setAttribute("trueDelete", "");
    }
%>
</body>
</html>
