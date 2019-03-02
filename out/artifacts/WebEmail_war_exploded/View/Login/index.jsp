<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body {
	text-align: center;
}
</style>
<script type="text/javascript" >
function check() {
	var userName = document.getElementById("name");
	var password = document.getElementById("password");
	alert(userName + ":" + password);
	var pattern = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
	if (userName == "" || password == "") {
		alert("用户名或密码不能为空！");
		return false;
	} // 检查userName或password是否有特殊密码
	else if (pattern.test(userName) || pattern.test(password)) {
		alert("用户名或密码只能由字母和数字组成！")
		return false;
	}
	return true;
}
</script>
</head>
<body>
<form name="form1"  onsubmit="return check()">
	<input type="text" name="userName" id="name"/><br />
	<input type="password" name="password" id="password"/><br />
	<input type="submit" value="提交">
</form>
</body>
</html>