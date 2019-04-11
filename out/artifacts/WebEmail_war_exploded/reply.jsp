<%@ page import="bean.Contact" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Email" %>
<%@ page import="bean.ReceivedEmail" %><%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/20/2019
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>回复</title>
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
        }

        #panel-form {
            margin-top: 30px;
        }
    </style>
</head>
<%
    //获取要展示给用户的email
    ReceivedEmail receivedEmail = (ReceivedEmail) session.getAttribute("showReceivedEmail");
    String receiver = receivedEmail.getSender();
    int l = receiver.indexOf("<");
    int r = receiver.indexOf(">");
    Email email = (Email) session.getAttribute("currEmail");
    session.setAttribute("sender", email.getEmail());
    session.setAttribute("receiver", receiver.substring(l + 1, r));
    System.out.println(receiver.substring(l + 1, r));
%>
<body>
<div class="container-fluid">
    <div class="panel panel-info navbar-fixed-top">
        <div class="panel-heading">
            <button type="button" class="btn btn-success" onclick="sendEmail()">发送</button>
            <%--<button type="button" class="btn btn-primary">定时发送</button>--%>
            <%--<button type="button" class="btn btn-warning" onclick="">存草稿</button>--%>

            <button type="button" class="btn btn-info cc" data-toggle="collapse" data-target="#collapse-CC">添加抄送</button>
            <button type="button" class="btn btn-info bcc" data-toggle="collapse" data-target="#collapse-BCC">添加密送</button>
            <button type="button" class="btn btn-danger" onclick="showContacts()">取消</button>
        </div>
        <%--<div class="panel-footer">发邮件：</div>--%>
    </div>

    <div class="panel-info" id="panel-form">
        <%--<div class="panel-heading">--%>
        <%--<h3 class="panel-title">发送邮件</h3>--%>
        <%--</div>--%>
        <div class="panel-body"></div>
        <form class="form-horizontal" action="/SendEmailServlet" method="post" id="myForm" enctype="multipart/form-data">
            <div class="form-group">
                <label class="col-sm-2 control-label">发件人：</label>
                <div class="col-sm-9">
                    <p class="form-control-static">${sender}</p>
                </div>
            </div>
            <div class="form-group">
                <label for="email_from" class="col-sm-2 control-label">收件人：</label>
                <div class="col-sm-9">
                    <p class="form-control-static" id="email_from">${receiver}</p>
                    <input type="hidden" name="email_to" id="receiver" value="${receiver}"/>
                    <%--<input type="receivedEmail" name="email_to" class="form-control" id="receiver" placeholder="必填" />--%>
                </div>
            </div>

            <div class="form-group collapse" id="collapse-CC">
                <label for="receiverCC" class="col-sm-2 control-label">抄送人：</label>
                <div class="col-sm-9">
                    <input type="email" name="email_cc" class="form-control" id="receiverCC" placeholder="选填" />
                </div>
            </div>

            <div class="form-group collapse" id="collapse-BCC">
                <label for="receiverBCC" class="col-sm-2 control-label">密送人：</label>
                <div class="col-sm-9">
                    <input type="email" name="email_bcc" class="form-control" id="receiverBCC" placeholder="选填" />
                </div>
            </div>

            <div class="form-group">
                <label for="subject" class="col-sm-2 control-label">主题：</label>
                <div class="col-sm-9">
                    <input type="text" name="subject" class="form-control" id="subject" placeholder="必填" />
                </div>
            </div>
            <div class="form-group">
                <label for="attachment" class="col-sm-2 control-label">附件(可选):</label>
                <div class="col-sm-9">
                    <input type="file" id="attachment" name="attachment"/>
                    <p class="help-block">请单击"choose file"来选择附件</p>
                </div>
            </div>
            <div class="form-group">
                <label for="content" class="col-sm-2 control-label">正文:</label>
                <div class="col-sm-9">
                    <textarea name="content" class="form-control" rows="12" id="content" required="required" placeholder="必填"></textarea>
                </div>
                </label>
            </div>
        </form>
    </div>
</div>
<script>
    <%--抄送按钮的响应--%>
    $(".cc").mousedown(function () {
        //alert($.trim($(this).text())  == "添加抄送");
        if ($.trim($(this).text()) == "添加抄送") {
            $(this).text("删除抄送");
            $(".receiverCC").text("");
        } else {
            $(this).text("添加抄送");
        }
    });

    <%--密送按钮的响应--%>
    $(".bcc").mousedown(function () {
        if ($.trim($(this).text()) == "添加密送") {
            $(this).text("删除密送");
            $(".receiverBCC").text("");
        } else {
            $(this).text("添加密送");
        }
    });
</script>
</body>
</html>
