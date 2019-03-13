function addAccount() {
    window.parent.document.getElementById("rightFrame").src = "addEmailAccount.html";
}

function check() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    if(email == "" || password == ""){
        alert("邮箱地址或密码不能为空");
        return false;
    }
    return true;
}
