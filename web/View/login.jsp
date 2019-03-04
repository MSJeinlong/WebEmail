<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/3/2019
  Time: 5:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>Lowin</title>
    <link rel="stylesheet" href="/View/auth.css">
    <script type="text/javascript" src="/View/check.js"></script>
</head>

<body>
<div class="lowin lowin-blue">
    <div class="lowin-brand">
        <img src="/View/kodinger.jpg" alt="logo">
    </div>
    <div class="lowin-wrapper">
        <div class="lowin-box lowin-login">
            <div class="lowin-box-inner">
                <form action="/LoginServlet" method="post" onsubmit="return checkLogin()">
                    <p>Sign in to continue</p>
                    <div class="lowin-group">
                        <label>UserName <a href="#" class="login-back-link">Sign in?</a></label>
                        <input type="text" autocomplete="text" name="userName" class="lowin-input" id="userName">
                    </div>
                    <div class="lowin-group password-group">
                        <label>Password <a href="#" class="forgot-link"></a></label>
                        <input type="password" name="password" autocomplete="current-password" class="lowin-input"
                               id="password">
                    </div>
                    <%--<button class="lowin-btn login-btn">--%>
                    <%--Sign In--%>
                    <%--</button>--%>
                    <input type="submit" value="Sign In" class="lowin-btn"/>
                    <div class="text-foot">
                        Don't have an account? <a href="" class="register-link">Register</a>
                    </div>
                </form>
            </div>
        </div>

        <div class="lowin-box lowin-register">
            <div class="lowin-box-inner">
                <form action="/RegisterServlet" method="post" onsubmit="return checkRegister()">
                    <p>Let's create your account</p>
                    <div class="lowin-group">
                        <label>UserName</label>
                        <input type="text" name="userName" class="lowin-input" id="userName_Reg">
                    </div>
                    <div class="lowin-group">
                        <label>PhoneNumber</label>
                        <input type="number" min="0" name="phoneNumber" class="lowin-input" id="PhoneNumber">
                    </div>
                    <div class="lowin-group">
                        <label>Password</label>
                        <input type="password" name="password" class="lowin-input"
                               id="password_Reg">
                    </div>
                    <%--<button class="lowin-btn">--%>
                    <%--Sign Up--%>
                    <%--</button>--%>
                    <input type="submit" value="Sign Up" class="lowin-btn"/>
                    <div class="text-foot">
                        Already have an account? <a href="" class="login-link">Login</a>
                    </div>
                </form>
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

    <footer class="lowin-footer">
        Design By JunLong Chi!
    </footer>
</div>

<script src="/View/auth.js"></script>
<script>
    Auth.init({
        login_url: '#login',
        forgot_url: '#forgot'
    });
</script>
</body>

