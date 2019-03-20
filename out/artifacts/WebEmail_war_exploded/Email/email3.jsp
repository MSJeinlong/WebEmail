<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 3/4/2019
  Time: 9:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page import="javax.activation.*" %>
<%
    String result;
    // 收件人的电子邮件
    String to = "2869897445@qq.com";

    // 发件人的电子邮件名和密碼
    String from = "15917362227@163.com";
    String psd = "En96387125";
    //设置用户名
    String user = "15917362227";

    Properties properties = new Properties();

    try {
        // 设置用户的认证方式
        properties.setProperty("mail.smtp.auth", "true");
        //设置传输协议
        properties.setProperty("mail.transport.protocol", "smtp");
        //SMTP邮件服务器
        properties.setProperty("mail.smtp.host", "smtp.163.com");
        //SMTP邮件服务器默认端口
        properties.setProperty("mail.smtp.port", "25");

        // 获取默认的Session对象。
        Session mailSession = Session.getDefaultInstance(properties);

        // 创建一个默认的MimeMessage对象。
        Message message = new MimeMessage(mailSession);
        // 根据session对象获取邮件传输对象Transport
        Transport transport = mailSession.getTransport();

        // 设置 From: 头部的header字段
        message.setFrom(new InternetAddress(from));
        // 设置 To: 头部的header字段
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
       // message.addRecipient(Message.RecipientType.CC, new InternetAddress(from));
        // 设置 Subject: header字段
        message.setSubject("Email Testing!(Design by Zachary)");
        // 现在设置的实际消息

        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setText("Hi, this a message to you. I'm Zachary.");

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();
        //使用绝对路径
        String filename = "F:\\Study\\test.txt";
        //添加文件到message
        DataSource source = new FileDataSource(filename);

        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);

        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        message.setSentDate(new Date());

        // 设置发件人的账户名和密码
        transport.connect(user, psd);
        // 发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        result = "Sent message successfully....";
    } catch (Exception e) {
        e.printStackTrace();
        result = "Error: unable to send message....";
    }

%>
<html>
<head>
    <title>Send Email using JSP</title>
</head>
<body>
<center>
    <h1>Send Email using JSP</h1>
</center>
<p align="center">
    <%
        out.println("Result: " + result + "\n");
    %>
</p>
</body>
</html>