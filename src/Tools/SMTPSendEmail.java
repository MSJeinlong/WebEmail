package Tools;

import bean.Email;
import bean.SentEmail;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class SMTPSendEmail {
    private static String email_from;
    private static String username;
    private static String password;
    private static String email_to;
    private static String email_cc;
    private static String email_bcc;
    private static String SMTPServer;
    private static String subject;
    private static String content;
    private static String attachmentDir;
    private static String attachmentName;
    private static String attachmentSize;

    //根据Email设置参数
    public static void setProps(Email email) {
        email_from = email.getEmail();
        int index = email_from.indexOf("@");
        username = email_from.substring(0, index);
        password = email.getPassword();
        SMTPServer = email.getSmtpHost();
    }

    public static void send1() {


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
    public static boolean send2(SentEmail sentEmail) {

        //设置邮件参数
        email_to = sentEmail.getEmailTO();
        email_cc = sentEmail.getEmailCC();
        email_bcc = sentEmail.getEmailBC();
        subject = sentEmail.getSubject();
        content = sentEmail.getContent();
        attachmentDir = sentEmail.getAttachmentPath();
        attachmentName = sentEmail.getAttachmentName();
        //attachmentSize = sentEmail.getAttachmentSize();

        /*得到session*/
        Properties props = new Properties();
        props.setProperty("mail.host", SMTPServer);
        props.setProperty("mail.smtp.auth", "true");

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
            if (email_cc != null && !email_cc.equals("")) {
                mimeMessage.setRecipients(Message.RecipientType.CC, email_cc);/*设置抄送*/
            }
            if (email_bcc != null && !email_bcc.equals("")) {
                mimeMessage.setRecipients(Message.RecipientType.BCC, email_bcc);/*设置密送*/
            }

            mimeMessage.setSubject(subject);
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
            part.setContent(content, "text/html;charset=utf-8");
            //把主体部件添加到集合中
            list.addBodyPart(part);

            //再创建MimeBodyPart
            if (sentEmail.isContainsAttachment()) {
                MimeBodyPart part1 = new MimeBodyPart();
                //设置部件内容
                try {
                    part1.attachFile(new File(attachmentDir+"\\"+attachmentName));
                    //设置显示的文件名称，其中MimeUtility.encodeText处理乱码问题
                    part1.setFileName(MimeUtility.encodeText(attachmentName));
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                list.addBodyPart(part1);
            }
            mimeMessage.setContent(list);/*把它设置给邮件作为邮件的内容*/

            /*4.发*/
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Email email = new Email();
        email = new Email();
        email.setEmail("15917362227@163.com");
        email.setPassword("En9857361");
        email.setSmtpHost("smtp.163.com");
        SMTPSendEmail.setProps(email);
        SentEmail sentEmail = new SentEmail();
        sentEmail.setEmailTO("2869897445@qq.com");
        sentEmail.setSubject("This is Email Subject");
        sentEmail.setContent("This is Email Content");
        sentEmail.setContainsAttachment(true);
        sentEmail.setAttachmentPath("F:\\Study\\TestFile\\test2.txt");
        sentEmail.setAttachmentName("test2.txt");
        SMTPSendEmail.send2(sentEmail);
    }

}
