<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/22/2019
  Time: 7:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户管理</title>
</head>
<body>

<form action="/UploadSave" method="post" enctype="multipart/form-data">
    <p>上传文件：<input type="file" name="uploadfile" /></p>
    <input type="text" name="username"/>
    <br />
    <input type="password" name="password"/>
    <p><input type="submit" value="上传" />
</form>

${message}
${message="" }
</body>
</html>
