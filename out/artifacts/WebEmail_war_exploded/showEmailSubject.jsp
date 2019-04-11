<%@ page import="bean.ReceivedEmail" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/21/2019
  Time: 4:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    //获取要展示给用户的email
    ReceivedEmail email = (ReceivedEmail) session.getAttribute("showReceivedEmail");
    session.setAttribute("attachmentName", email.getAttachmentName());
    session.setAttribute("attachmentSize", email.getAttachmentSize());
    session.setAttribute("attachmentPath", email.getAttachmentPath()  + email.getAttachmentName());
%>
<body>
<div class="container-fluid">
    <div class="panel panel-info navbar-fixed-top">
        <div class="panel-heading">
            <button type="button" class="btn btn-warning btn-sm" onclick="subjectToInbox()">返回</button>
            <button type="button" class="btn btn-success btn-sm" onclick="showReply()">回复</button>
            <button type="button" class="btn btn-primary btn-sm" onclick="forward(1)">转发</button>
            <%--<button type="button" class="btn btn-info btn-sm" onclick="">收藏</button>--%>
            <%--<button type="button" class="btn btn-info cc" data-toggle="collapse" data-target="#collapse-CC">添加抄送</button>--%>
            <%--<button type="button" class="btn btn-info bcc" data-toggle="collapse" data-target="#collapse-BCC">添加密送</button>--%>
            <%--<button type="button" class="btn btn-danger btn-sm" onclick="">删除</button>--%>
        </div>
        <%--<div class="panel-body">--%>
        <dl class="dl-horizontal">
            <dt>主题：</dt>
            <dd><%=email.getSubject()%>
            </dd>
            <dt>发件人：</dt>
            <dd><%=email.getSender()%>
            </dd>
            <dt>收件人：</dt>
            <dd><%=email.getReceiverTO()%>
            </dd>
            <%
                String receiverCC = email.getReceiverCC();
                if (receiverCC != null && !receiverCC.equals("")) {
            %>
            <dt>抄送人：</dt>
            <dd><%=receiverCC%>
            </dd>
            <%
                }
            %>
            <dt>时间：</dt>
            <dd><%=email.getSentDate()%>
            </dd>
            <dt>附件：</dt>
            <dd>
                <%
                    if (email.isHaveAttachment()) {
                %>
                <span class="text-info"><%=email.getAttachmentName()%>(${attachmentSize} KB)</span>
                <%--<a href="/DownloadServlet?c=${attachmentPath}" class="btn btn-xs btn-success">下载附件</a>--%>
                <form action="/DownloadServlet" method="post">
                    <input type="hidden" value="${attachmentPath}" name="filePath"/>
                    <input type="submit" class="btn btn-success btn-xs" value="下载附件" />
                </form>
                <%
                } else {
                %>
                无
                <%
                    }
                %>
            </dd>
        </dl>
        <%--<%--%>
            <%--if (email.isHaveAttachment()) {--%>
        <%--%>--%>
        <%--<form action="/DownloadServlet" method="post">--%>
            <%--<input type="hidden" value="${attachmentPath}" name="filePath"/>--%>
            <%--<input type="submit" class="btn btn-success btn-xs" value="下载附件" />--%>
        <%--</form>--%>
        <%--<%--%>
            <%--}--%>
        <%--%>--%>
        <%--</div>--%>
    </div>
</div>
</body>
</html>
