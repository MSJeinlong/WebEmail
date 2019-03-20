function showContacts() {
    window.parent.document.getElementById("rightFrame").src = "/GetAllContactsServlet";
}

function addContacts() {
    // window.parent.document.getElementById("rightFrame").src = "addContact.jsp";
    window.location.href = "addContact.jsp";
}

function showEmails() {
    window.parent.document.getElementById("rightFrame").src = "/emailList.jsp";
}

function selectAll() {
    //拿到全选的那个复选框
    var checkAll = document.getElementById("checkAll");
    //获取其他所有的复选框
    var checkOnes = document.getElementsByName("checkOne");
    //根据全选是否被选中执行 全选/全部不选操作

    for (var i = 0; i < checkOnes.length; i++) {
        checkOnes[i].checked = checkAll.checked;
    }
}

function updateContact() {
    //获取所有的复选框
    var checkOnes = document.getElementsByName("checkOne");
    //判断有多少个复选框被选中了
    var array = [];
    var j = 0;
    for (var i = 0; i < checkOnes.length; i++) {
        if (checkOnes[i].checked == true) {
            array[j++] = i;
        }
    }
    //不能同时编辑多个联系人
    if (array.length > 1) {
        alert("不能同时编辑多个联系人");
    } else if (array.length == 0) {
        alert("请选择一条联系人信息");
    } else {
        //跳转到编辑页面
        // alert(array[0]);
        // window.location.href = "/updateContact.jsp?id="+array[0];
        window.location.href = "/updateContact.jsp?id=" + array[0];
    }
}

function deleteContacts() {
    //获取所有的复选框
    var checkOnes = document.getElementsByName("checkOne");
    //判断有多少个复选框被选中了
    // var array = [];
    // var j = 0;
    var ids = "";
    for (var i = 0; i < checkOnes.length; i++) {
        if (checkOnes[i].checked == true) {
            //array[j++] = i;
            ids += i + " ";
        }
    }
    if (ids == "") {
        alert("你未选中任何一条联系人信息！");
    } else {
        window.location.href = "/DeleteContactsServlet?ids=" + ids;
    }
}

function addMailBox() {
    window.parent.document.getElementById("rightFrame").src = "/addMailBox.jsp";
}

function showUserInfo() {
    window.parent.document.getElementById("rightFrame").src = "/userInfo.jsp";
}

function showChangePassword() {
    window.location.href = "/changePassword.jsp";
}

function updateEmailBox() {
    //获取所有的复选框
    var checkOnes = document.getElementsByName("checkOne");
    //判断有多少个复选框被选中了
    var array = [];
    var j = 0;
    for (var i = 0; i < checkOnes.length; i++) {
        if (checkOnes[i].checked == true) {
            array[j++] = i;
        }
    }
    //不能同时编辑多个联系人
    if (array.length > 1) {
        alert("不能同时编辑多个邮箱");
    } else if (array.length == 0) {
        alert("请选择一个邮箱进行编辑");
    } else {
        //跳转到编辑页面
        //alert(array[0]);
        // window.location.href = "/updateContact.jsp?id="+array[0];
        window.location.href = "/updateEmailBox.jsp?id=" + array[0];
    }
}

function deleteEmailBox() {
    //获取所有的复选框
    var checkOnes = document.getElementsByName("checkOne");
    //判断有多少个复选框被选中了
    // var array = [];
    // var j = 0;
    var ids = "";
    for (var i = 0; i < checkOnes.length; i++) {
        if (checkOnes[i].checked == true) {
            //array[j++] = i;
            ids += i + " ";
        }
    }
    if (ids == "") {
        alert("你未选中任何一个邮箱！");
    } else {
        window.location.href = "/DeleteEmailBoxServlet?ids=" + ids;
    }
}