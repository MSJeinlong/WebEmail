function checkLogin() {
    var userName = document.getElementById("exampleInputUserName1").value;
    var password = document.getElementById("exampleInputPassword1").value;
   // alert(password);
    var pattern = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
    if (pattern.test(userName) || pattern.test(password)) {
        alert("用户名或密码含有特殊符，请重新输入");
        return false;
    }
    return true;
}

function checkRegister() {
    var userName = document.getElementById("exampleInputUserName2").value;
    var password1 = document.getElementById("exampleInputPassword2").value;
    var password2 = document.getElementById("exampleInputPassword3").value;
    var pattern = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
    if(password1 != password2){
        alert("两次的密码不一致");
        return false;
    } else if(pattern.test(userName) || pattern.test(password1)){
        alert("用户名或密码含有特殊字符，请重新输入");
        return false;
    } else if(password1.length < 6){
        alert("密码必须大于6位");
        return false;
    }
    // alert("注册成功！");
    return true;
}