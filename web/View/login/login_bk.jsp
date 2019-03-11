<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/3/2019
  Time: 2:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login to WebEmail</title>
    <link type="text/css" rel="stylesheet" href="login.css">
    <script src="check.js"></script>
</head>
<body>
<h1>Welcome to WebEmail!</h1>
<div align="center">
<form action="/LoginServlet" method="post" onsubmit="">
    <table>
        <caption>Sign in to continue</caption>
        <tr>
            <td>UserName:</td>
            <td colspan="2"><input type="text" name="userName" id="userName"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" id="password"/></td>
        </tr>
        <tr>
            <td>AuthCode：</td>
            <td><input type="text" name="inputCode" id="inputCode" /></td>
            <td><img src="/GetAuthCodeServlet" id="authImg" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Login"/></td>
            <td><input type="reset" value="reset" /></td>
        </tr>
        <tr>
            <td><a href="">Register</a></td>
            <td>(Don't have a account?)</td>
        </tr>
    </table>
    <%--<p>Sign in to continue</p>
    <label>UserName:</label>
    <input type="text" name="userName" id="userName"/>
    <br/><br/>
    <label>Password</label>
    <input type="password" name="password" id="password"/>
    <br/><br/>
    <input type="submit" value="Sign In"/>
    <br/><br/>
    <label>Don't have a account? <a href="">Register</a></label>--%>
</form>
<span class="message">${message}</span>
${message=""}
</div>
</body>
</html>
