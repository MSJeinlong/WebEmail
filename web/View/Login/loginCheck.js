/**
 * 检查登录用户的输入是否合法
 */
function checkSignIn() {
	var userName = document.getElementById("userName").value;
	var password = document.getElementById("password").value;
	// alert(userName + ":" + password);
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

function checkSignUp() {
	var userName = document.getElementById("Name_SignUp").value;
	var phone = document.getElementById("PhoneNumber").value;
	var password = document.getElementById("Password_SignUp").value;
	var pattern = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
	if(userName == "" || phone == "" || password == ""){
		alert("用户名、密码或手机号码不能为空！");
		return false;
	}else if (pattern.test(userName) || pattern.test(password)) {
		alert("用户名或密码只能由字母和数字组成！")
		return false;
	}
	return true;
}
