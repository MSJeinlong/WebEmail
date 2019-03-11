<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/7/2019
  Time: 6:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<frameset rows="25%,*">
    2     <frame src="/mailDemo/DisplayHead?msgnum=<%=request.getParameter("msgnum")%>" scrolling="no">
    3     <frame src="/mailDemo/DisplayContent?msgnum=<%=request.getParameter("msgnum")%>" scrolling="no">
    4 </frameset>
</body>
</html>
