<%@ page import="java.util.List" %>
<%@ page import="bean.Email" %>
<%@ page import="javax.print.DocFlavor" %><%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/19/2019
  Time: 10:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账号信息:</title>
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
            margin-top: 100px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="panel panel-info navbar-fixed-top">
        <div class="panel-heading">
            <button type="button" class="btn btn-primary" onclick="showChangePassword()">修改账号密码</button>
            <button type="button" class="btn btn-info" onclick="updateEmailBox()">编辑邮箱</button>
            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal"
                    onclick="">删除
            </button>
            <form action="/SearchEmailServlet" class="navbar-form navbar-right" method="post">
                <div class="form-group">
                    <input type="text" name="keyName" class="form-control" value="${param.keyName}" placeholder="邮箱搜索"/>
                </div>
                <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-search"></span>
                </button>
            </form>
        </div>

        <div class="panel-footer">
            <span class="text-info">已绑定邮箱列表如下:</span>
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
                        <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> 您确定要删除选中的邮箱吗？
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
                        <th>邮箱备注</th>
                        <th>邮箱地址</th>
                        <th>邮箱密码</th>
                    </tr>
                    <%
                        List<Email> list = (List<Email>) session.getAttribute("emailsList");
                        for (int i = 0; i < list.size(); i++) {
                            Email email = list.get(i);
                    %>
                    <tr>
                        <td><input type="checkbox" name="checkOne"/> <%=i + 1%>
                        </td>
                        <td><%=email.getAlias()%>
                        </td>
                        <td><%=email.getEmail()%>
                        </td>
                        <td><input type="password" value="<%=email.getPassword()%>" disabled/></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </form>

        </div>
    </div>
</div>
<%--根据addEmailServlet的处理结果弹出提示框--%>
<%
    String mess = (String) session.getAttribute("addEmailBoxMess");
    if (mess != null && !mess.equals("")) {
%>
<script>
    alert("<%=mess%>");
    window.parent.document.getElementById("leftFrame").contentWindow.location.reload(true);
</script>
<%
        session.setAttribute("addEmailBoxMess", "");
    }
%>
<%--根据Servlet的处理结果弹出提示框--%>
<%
    String mess2 = (String) session.getAttribute("changePasswordMess");
    if (mess2 != null && !mess2.equals("")) {
%>
<script>
    alert("<%=mess2%>");

</script>
<%
        session.setAttribute("changePasswordMess", "");
    }
%>
<%--根据UpdateEmailServlet的处理结果弹出提示框--%>
<%
    String mess3 = (String) session.getAttribute("updateEmailBoxMess");
    if (mess3 != null && !mess3.equals("")) {
%>
<script>
    alert("<%=mess3%>");
    window.parent.document.getElementById("leftFrame").contentWindow.location.reload(true);
</script>
<%
        session.setAttribute("updateEmailBoxMess", "");
    }
%>

<%--根据 DeleteServlet 的处理结果弹出提示框--%>
<%
    String mess4 = (String) session.getAttribute("deleteEmailBoxMess");
    if (mess4 != null && !mess4.equals("")) {
%>
<script>
    alert("<%=mess4%>");
    // window.parent.document.getElementById("leftFrame").contentWindow.location.reload(true);
</script>
<%
        session.setAttribute("deleteEmailBoxMess", "");
    }
%>

<script>
    $(".confirm").click(function () {
        //隐藏Modal
        $("#myModal").modal("hide");
        //执行删除操作
        deleteEmailBox();
    });
</script>
</body>
</html>
