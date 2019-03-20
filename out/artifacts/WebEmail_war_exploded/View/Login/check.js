/*检查登录用户的输入*/
function checkLogin() {
    var userName = document.getElementById('userName').value;
    var password = document.getElementById('password').value;
    var pattern = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
    if (userName == "" || password == "") {
        alert("UserName or password can't be empty!");
        return false;
    } else if (pattern.test(userName) || pattern.test(password)) {
        alert("UserName or password contains special characters!");
        return false;
    }
    return true;
}

/*检查用户注册时的输入*/
function checkRegister() {
    //alert("1");
    var userName = document.getElementById("userName_Reg").value;
   // alert("2");
    var phoneNumber = document.getElementById("PhoneNumber").value;
   // alert("3");
    var password = document.getElementById("password_Reg").value;
  //  alert("4");
    var pattern = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
   // alert("5");
    if (userName == "" || password == "") {
        alert("UserName, password or phoneNumber can't be empty!");
        return false;
    } else if (pattern.test(userName) || pattern.test(password)) {
        alert("UserName or password contains special characters!");
        return false;
    } else if (phoneNumber.length != 11) {
        alert("PhoneNumber's length must be 11");
        return false;
    }
    return true;
}