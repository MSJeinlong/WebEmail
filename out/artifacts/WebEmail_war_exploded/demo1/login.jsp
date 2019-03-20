<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/7/2019
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="javax.mail.*, demo1.POP3Help"
         contentType="text/html;charset=UTF-8" %>
<%
    String host = request.getParameter("host");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String from = "";
    String subject = "";
    Folder folder = POP3Help.getFolder(host,username,password);
    session.setAttribute("folder",folder);
    Message [] messages = folder.getMessages();

    for(int i=0;i<messages.length;i++)
    {
        try
        {
            from = messages[i].getFrom()[0].toString();
            subject = messages[i].getSubject();
            out.print(i + 1);
%>
发件人地址：<%=from %>  邮件主题：<%=subject %>
<a href="displayMsg.jsp?msgnum=<%=i+1%>">查看邮件</a><br/>
<%
        }
        catch(Exception e){}
    }
%>
