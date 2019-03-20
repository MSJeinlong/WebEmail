<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/17/2019
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Login to WebEmail</title>
    <!--引用本地的bootstrap文件和jquery文件-->
    <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css"/>
    <script src="Bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="Bootstrap/js/bootstrap.min.js"></script>
    <script src="js/loginCheck.js"></script>
    <style>
        body {
            font-family: "微软雅黑";
            background-image: url("backgroundImage.jpg");
             background-position: center 0;
             background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
             -webkit-background-size: cover;
             -o-background-size: cover;
             -moz-background-size: cover;
             -ms-background-size: cover;
        }

        h1 {
            color: white;
        }

        a {
            color: white;
        }

        h1 {
            text-align: center;
        }

        a {
            background-color: aliceblue;
            color: black;
        }

        .panel {
            margin-top: 60px;
        }

        form {
            margin-top: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-info">
                <div class="panel-heading">Welcome to WebEmail!</div>
                <div class="panel-body">
                    <!-- Nav tabs -->
                    <ul class="nav nav-pills nav-justified" role="tablist">
                        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                                  data-toggle="tab">登录</a></li>
                        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab"
                                                   data-toggle="tab">注册</a></li>
                    </ul>
                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="home">
                            <form action="/LoginServlet" method="post" onsubmit="return checkLogin()">
                                <div class="form-group">
                                    <label class="text-primary" for="exampleInputUserName1">UserName</label>
                                    <input type="text" class="form-control" name="userName" id="exampleInputUserName1"
                                           placeholder="UserName" required="required"/>
                                </div>
                                <div class="form-group">
                                    <label class="text-primary" for="exampleInputPassword1">Password</label>
                                    <input type="password" class="form-control" name="password"
                                           id="exampleInputPassword1" placeholder="Password" required="required"/>
                                </div>
                                <button type="submit" class="btn btn-primary btn-block">Sign In</button>
                            </form>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="profile">
                            <form action="/RegisterServlet" method="post" onsubmit="return checkRegister()">
                                <div class="form-group">
                                    <label class="text-primary" for="exampleInputUserName2">UserName</label>
                                    <input type="text" class="form-control" name="userName" id="exampleInputUserName2"
                                           placeholder="UserName" required="required"/>
                                </div>
                                <div class="form-group">
                                    <label class="text-primary" for="exampleInputPassword2">Set Password</label>
                                    <input type="password" class="form-control" name="password"
                                           id="exampleInputPassword2" placeholder="Password" required="required"/>
                                </div>
                                <div class="form-group">
                                    <label class="text-primary" for="exampleInputPassword3">Confirm Password</label>
                                    <input type="password" class="form-control" id="exampleInputPassword3"
                                           placeholder="Password" required="required"/>
                                </div>
                                <button type="submit" class="btn btn-primary btn-block">Sign Up</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--根据Servlet的处理结果，弹出js提示框--%>
<%
    String mess = (String) session.getAttribute("message");
    if (mess == null || mess.equals("")) {
        //不做处理
    } else {%>
<script type="text/javascript">
    alert("<%=mess%>");
</script>
<%
        session.setAttribute("message", "");
    }%>
</body>
</html>
