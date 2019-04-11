<%@ page  language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body>

<form action="/UploadUserSave?id=1" method="post" enctype="multipart/form-data">
<p>上传文件：<input type="file" name="uploadfile" /></p>
<p><input type="submit" value="上传" />
</form>

${message}
${message="" }
</body>
</html>
