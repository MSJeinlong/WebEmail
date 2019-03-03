<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>WebEmail Login</title>

    <link rel="stylesheet" href="auth.css">
    <script type="text/javascript" src="loginCheck.js"></script>
</head>
<body>
<div class="lowin lowin-blue">
    <div class="lowin-brand">
        <img src="kodinger.jpg" alt="logo">
    </div>
    <div class="lowin-wrapper">
        <div class="lowin-box lowin-login">
            <div class="lowin-box-inner">
                <form name="form" action="/index.jsp" method="post" onsubmit="return checkSignIn()">
                    <p>Sign in to continue</p>
                    <div class="lowin-group">
                        <label>UserName <a href="#" class="login-back-link">Sign in?</a></label>
                        <input type="text"  name="userName" class="lowin-input" id="userName">
                    </div>
                    <div class="lowin-group password-group">
                        <label>Password <a href="#" class="forgot-link"></a></label>
                        <input type="password" name="password"  class="lowin-input"
                               id="password">
                    </div>
                    <input type="submit" value="Sign In" class="lowin-btn login-btn"/>
                    <div class="text-foot">
                        Don't have an account? <a href="" class="register-link">Register</a>
                    </div>
                </form>
            </div>
        </div>

        <div class="lowin-box lowin-register">
            <div class="lowin-box-inner">
                <form action="" method="post" onsubmit="return checkSignUp()">
                    <p>Let's create your account</p>
                    <div class="lowin-group">
                        <label>Name</label>
                        <input type="text" name="name"  class="lowin-input" id="Name_SignUp">
                    </div>
                    <div class="lowin-group">
                        <label>Password</label>
                        <input type="password" name="password"  class="lowin-input" id="Password_SignUp">
                    </div>
                    <div class="lowin-group">
                        <label>PhoneNumber</label>
                        <input type="number"  name="phone" class="lowin-input" id="PhoneNumber">
                    </div>
                    <input type="submit" class="lowin-btn" value="Sign Up" />
                    <%--<input type="button" class="lowin-btn" value="submit" onclick="func()"/>--%>
                    <div class="text-foot">
                        Already have an account? <a href="" class="login-link">Login</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <footer class="lowin-footer">
        Design By <a href="http://fb.me/itskodinger">Junlong Chi</a>
    </footer>
</div>

<script src="auth.js"></script>
<script>
    Auth.init({
        login_url: '#login',
        forgot_url: '#forgot'
    });
</script>
</body>
</html>