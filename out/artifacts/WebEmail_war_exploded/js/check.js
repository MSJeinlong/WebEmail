function checkEmail() {
    // alert(1);
    var email = document.getElementById("email").value;
    //alert(2);
    //检查用户输入的邮箱是否被支持
    if (email.indexOf("@163.com") > -1 || email.indexOf("@sina.cn") > -1) {
        //alert(3);
        return true;
    } else {
        alert("您输入的邮箱暂时未支持，请重新输入！");
        return false;
    }
}

function checkNewPassword() {
    var newPassword = document.getElementById("newPassword").value;
    var conPassword = document.getElementById("conPassword").value;
    var pattern = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
    if (newPassword != conPassword) {
        alert("两次输入的密码不一致！")
        return false;
    } else if (pattern.test(newPassword)) {
        alert("密码只能有数字和字母组成，不能含有特殊字符");
        return false;
    }
    return true;
}

function checkSendEmail() {
    var receiver = $("#receiver").val();
    var subject = $("#subject").val();
    var content = $("#content").val();
    //alert(receiver+":"+subject+":"+content);
    if (receiver == null || receiver == "") {
        alert("收件人不能为空！");
        return false;
    } else if (subject == null || subject == "") {
        alert("主题不能为空!");
        return false;
    } else if (content == null || content == "") {
        alert("内容不能为空!");
        return false;
    } else {
        return true;
    }
}