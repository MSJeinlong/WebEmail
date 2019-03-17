package Email;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class SendDemo1 {
    private String email_from = "15917362227@sina.cn";
    private String username = "15917362227";
    private String password = "intel365";
    private String email_to = "15917362227@163.com";
    private String SMTPServer = "smtp.sina.cn" ;

    public void fun1() {
        /*得到session*/
        Properties props = new Properties();
        props.setProperty("mail.host", SMTPServer);
        props.setProperty("mail.smtp.auth", "true");
        // props.setProperty()


        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //设置用户名和密码
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        /*2.创建MimeMessage*/
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(email_from));/*设置发件人邮箱*/
            mimeMessage.setRecipients(Message.RecipientType.TO, email_to);/*设置收件人邮箱*/
            // mimeMessage.setRecipients(Message.RecipientType.CC, "15917362227@163.com");/*设置抄送*/

            mimeMessage.setSubject("Email Testing.");
            mimeMessage.setContent("This an Email to you", "text/html;charset=utf-8");

            /*3.发*/
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /*发带有邮件的邮件*/
    public void fun2() {
        /*得到session*/
        Properties props = new Properties();
        props.setProperty("mail.host", "smtp.163.com");
        props.setProperty("mail.smtp.auth", "true");
        email_from = "15917362227@163.com";
         username = "15917362227";
         password = "En9857361";
        email_to = "2869897445@qq.com";

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        /*2.创建MimeMessage*/
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(email_from));/*设置发件人邮箱*/
            mimeMessage.setRecipients(Message.RecipientType.TO, email_to);/*设置收件人*/
            //mimeMessage.setRecipients(Message.RecipientType.CC, "15917362227@163.com");/*设置抄送*/

            mimeMessage.setSubject("这是封from Zachary的测试邮件（有附件）");
            /*3.当发送包含附件的邮件时，邮件体就为多邮件题
             * (1).创建一个多部件的部件内容！MimeMultipart
             * (2).MimeMultipart就是一个集合，用来装卸多个主题部件
             * 主体部件叫MimeBodyPart
             * (3).把MimeMultipart设置给MineMessage的内容！
             * */
            MimeMultipart list = new MimeMultipart();   //创建多部件内容
            //创建MimeBodyPart
            MimeBodyPart part = new MimeBodyPart();
            //设置主体部件的内容
            part.setContent("Hi, I'm Zachary.Nice to meet you.", "text/html;charset=utf-8");
            //把主体部件添加到集合中
            list.addBodyPart(part);

            //再创建MimeBodyPart
            MimeBodyPart part1 = new MimeBodyPart();
            //设置部件内容
            try {
                part1.attachFile(new File("F:\\Study\\TestFile\\test2.txt"));
                //设置显示的文件名称，其中MimeUtility.encodeText处理乱码问题
                part1.setFileName(MimeUtility.encodeText("test6.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            list.addBodyPart(part1);
            mimeMessage.setContent(list);/*把它设置给邮件作为邮件的内容*/

            /*4.发*/
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SendDemo1 sendDemo1 = new SendDemo1();
        sendDemo1.fun1();
    }
}
