function showContacts() {
    window.parent.document.getElementById("rightFrame").src = "/GetAllContactsServlet";
}

function addContacts() {
    // window.parent.document.getElementById("rightFrame").src = "addContact.jsp";
    window.location.href = "addContact.jsp";
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
    //alert(1);
    window.parent.document.getElementById("rightFrame").src = "/GetAllEmailServlet";
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

function showEmails() {
    window.parent.document.getElementById("rightFrame").src = "/GetAllReceivedEmailServlet";
   // window.location.reload(true);
}

function sendEmailToContact() {
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
        alert("不能同时写信多个联系人");
    } else if (array.length == 0) {
        alert("请选择一个联系人");
    } else {
        //跳转到编辑页面
        //alert(array[0]);
        // window.location.href = "/updateContact.jsp?id="+array[0];
        window.location.href = "/sendEmail2.jsp?id=" + array[0];
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

function showSendEmail() {
    window.parent.document.getElementById("rightFrame").src = "/sendEmail.jsp";
}

function sendEmail() {
    var form = $("#myForm");
    if(checkSendEmail()){
        form.submit();
    }
}

function showAllSentEmail() {
    window.parent.document.getElementById("rightFrame").src = "/GetAllSentEmailServlet";
    //alert(1);
   // window.location.reload(true);
}

function refreshReceivedEmails() {
    //alert("同步收件箱");
    window.parent.document.getElementById("rightFrame").src = "/RefreshReceivedEmailServlet";
    //window.parent.document.getElementById("leftFrame").contentWindow.location.reload(true);
}

function subjectToInbox() {
    //alert(1);
    window.parent.document.location.href = "/ReceiveEmails.jsp";
    //alert(2);
}

function backToSentEmails() {
    window.parent.document.location.href = "/sentEmailList.jsp";
}

function deleteReceivedEmails() {
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
        alert("你未选中任何一条邮件信息！");
    } else {
        window.location.href = "/TrueDeleteServlet?ids=" + ids;
    }
}

function showReply() {
    window.parent.document.location.href = "/reply.jsp";
}

function forward(flag) {
    window.parent.document.location.href = "/forward.jsp?flag="+flag;
}

function back(flag) {
    //alert(flag);
    if(flag == 1){
        window.document.location.href = "/ReceiveEmails.jsp";
    } else if(flag == 2){
        window.document.location.href = "/sentEmailList.jsp";
    }
}

function deleteSentEmail() {
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
        alert("你未选中任何一条邮件信息！");
    } else {
        window.location.href = "/DeleteSentEmailServlet?ids=" + ids;
    }
}
