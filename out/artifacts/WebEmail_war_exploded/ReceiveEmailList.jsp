<%@ page import="java.util.List" %>
<%@ page import="bean.Contact" %>
<%@ page import="bean.Email" %>
<%@ page import="bean.SentEmail" %><%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/18/2019
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>已发送邮件</title>
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

        .navbar-form {
            margin-top: 2px;
        }

        #panel-table {
            margin-top: 60px;
        }
    </style>
</head>
<body>
<div class="container-fluid">

    <div class="panel panel-info navbar-fixed-top">
        <div class="panel-heading">
            <%--<button type="button" class="btn btn-success" onclick="">删除</button>--%>
            <button type="button" class="btn btn-primary" onclick="">收藏</button>
            <button type="button" class="btn btn-info" onclick="">刷新</button>
            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal"
                    onclick="">删除
            </button>
            <%--<form action="/SearchContactsServlet" class="navbar-form navbar-right" method="post">--%>
            <%--<div class="form-group">--%>
            <%--<input type="text" name="keyName" class="form-control" placeholder="邮件搜索"/>--%>
            <%--</div>--%>
            <%--<button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-search"></span>--%>
            <%--</button>--%>
            <%--</form>--%>
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
        <%--<div class="panel-heading">--%>
        <%--${contactNumber}--%>
        <%--</div>--%>
        <%--<div class="panel-body">--%>
        <!--自适应的，好看的，响应式的表格-->
        <div class="table-responsive">
            <form action="#" method="post">
                <table class="table table-striped table-hover table-condensed">

                    <tr class="info">
                        <th><input type="checkbox" id="checkAll" onclick="selectAll()"/> 全选</th>
                        <th>收件人</th>
                        <th>主题</th>
                        <th>发送时间</th>
                    </tr>
                    <%
                        List<SentEmail> list = (List<SentEmail>) session.getAttribute("sentEmailList");
                        for (int i = 0; i < list.size(); i++) {
                            SentEmail sentEmail = list.get(i);
                    %>
                    <tr>
                        <td><input type="checkbox" name="checkOne"/> <%=i + 1%>
                        </td>
                        <td><%=sentEmail.getEmailTO()%>
                        </td>
                        <td><%=sentEmail.getContent()%>
                        </td>
                        <td><%=sentEmail.getSendDate()%>
                        </td>
                    </tr>
                    <%
                        }
                    %>

                </table>
            </form>

        </div>
        <%--</div>--%>
    </div>

    <%--&lt;%&ndash;根据addContactServlet的处理结果，弹出js提示框&ndash;%&gt;--%>
    <%--<%--%>
    <%--String mess = (String) session.getAttribute("mess");--%>
    <%--if (mess == null || mess.equals("")) {--%>
    <%--//不做处理--%>
    <%--} else {%>--%>
    <%--<script type="text/javascript">--%>
    <%--alert("<%=mess%>");--%>
    <%--window.parent.document.getElementById("leftFrame").contentWindow.location.reload(true);--%>
    <%--</script>--%>
    <%--<%--%>
    <%--session.setAttribute("mess", "");--%>
    <%--}%>--%>
    <%--&lt;%&ndash;根据deleteContactServlet的处理结果，弹出js提示框&ndash;%&gt;--%>
    <%--<%--%>
    <%--String deleteMess = (String) session.getAttribute("deleteMess");--%>
    <%--if (deleteMess != null && !deleteMess.equals("")) {--%>
    <%--%>--%>
    <%--<script>--%>
    <%--alert("${deleteMess}");--%>
    <%--window.parent.document.getElementById("leftFrame").contentWindow.location.reload(true);--%>
    <%--</script>--%>
    <%--<%--%>
    <%--session.setAttribute("deleteMess", "");--%>
    <%--}--%>
    <%--%>--%>

    <%--&lt;%&ndash;根据updateServlet的处理结果弹出js提示框&ndash;%&gt;--%>
    <%--<%--%>
    <%--String updateMess = (String) session.getAttribute("updateContactMess");--%>
    <%--if (updateMess != null && !updateMess.equals("")) {--%>

    <%--%>--%>
    <%--<h1 class="page-header">${up}</h1>--%>
    <%--<script>--%>
    <%--alert("${updateContactMess}");--%>
    <%--</script>--%>
    <%--<%--%>
    <%--session.setAttribute("updateContactMess", "");--%>
    <%--}--%>
    <%--%>--%>
    <script>
        $(".confirm").click(function () {
            //隐藏Modal
            $("#myModal").modal("hide");
            //执行删除操作
            // deleteContacts();
        });
    </script>
</div>
<%
    String mess = (String) session.getAttribute("sendEmailMess");
    if (mess != null && !mess.equals("")) {
%>
<script>
    alert("<%=mess%>");
    window.parent.document.getElementById("leftFrame").contentWindow.location.reload(true);
</script>
<%
        session.setAttribute("sendEmailMess", "");
    }
%>
</body>
</html>
