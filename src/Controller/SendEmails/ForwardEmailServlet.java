package Controller.SendEmails;

import Dao.SendEmailDAO;
import Dao.SendEmailDAOImpl;
import Tools.SMTPSendEmail;
import bean.Email;
import bean.ReceivedEmail;
import bean.SentEmail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/ForwardEmailServlet")
public class ForwardEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取邮件
        HttpSession session = request.getSession();
        Email currEmail = (Email) session.getAttribute("currEmail");
        String receiver = request.getParameter("email_to");
        SentEmail sentEmail = new SentEmail();
        String flag = request.getParameter("flag");

        //根据flag判断是转发收件箱的邮件还是已发送的邮件
        if (flag.equals("1")) {
            //flag = 1为转发收件箱的邮件
            ReceivedEmail email = (ReceivedEmail) session.getAttribute("showReceivedEmail");
            //System.out.println("收件人" + receiver);

            sentEmail.setUserName(email.getUserName());
            sentEmail.setEmailNumber(email.getEmailNumber());
            sentEmail.setEmailFrom(currEmail.getEmail());
            sentEmail.setEmailTO(receiver);
            sentEmail.setEmailCC(email.getReceiverCC());
            //sentEmail.setEmailBC(email.getR);
            sentEmail.setSubject(email.getSubject());
            sentEmail.setContent(email.getContent());
            sentEmail.setContainsAttachment(email.isHaveAttachment());
            sentEmail.setAttachmentPath(email.getAttachmentPath());
            sentEmail.setAttachmentName(email.getAttachmentName());
            sentEmail.setAttachmentSize(email.getAttachmentSize());
            //设置发送时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            sentEmail.setSendDate(df.format(new Date()));
            sentEmail.setDelete(email.isDelete());
            sentEmail.setFavorites(email.isFavorites());
        } else if(flag.equals("2")){
            //flag = 2为转发已发送的邮件
            SentEmail sentEmail12 = (SentEmail) session.getAttribute("showSentEmail");
            sentEmail.setUserName(sentEmail12.getUserName());
            sentEmail.setEmailNumber(sentEmail12.getEmailNumber());
            sentEmail.setEmailFrom(currEmail.getEmail());
            sentEmail.setEmailTO(receiver);
            sentEmail.setEmailCC(sentEmail12.getEmailCC());
            //sentEmail.setEmailBC(email.getR);
            sentEmail.setSubject(sentEmail12.getSubject());
            sentEmail.setContent(sentEmail12.getContent());
            sentEmail.setContainsAttachment(sentEmail12.isContainsAttachment());
            sentEmail.setAttachmentPath(sentEmail12.getAttachmentPath());
            sentEmail.setAttachmentName(sentEmail12.getAttachmentName());
            sentEmail.setAttachmentSize(sentEmail12.getAttachmentSize());
            //设置发送时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            sentEmail.setSendDate(df.format(new Date()));
            sentEmail.setDelete(sentEmail12.isDelete());
            sentEmail.setFavorites(sentEmail12.isFavorites());
        }


        SendEmailDAO dao = new SendEmailDAOImpl();


        SMTPSendEmail.setProps(currEmail);
        if (SMTPSendEmail.send2(sentEmail)) {
            //设置邮件为已发送
            sentEmail.setSent(true);
            dao.add(sentEmail);
            session.setAttribute("sendEmailMess", "邮件发送成功");
            //更新session
            List<SentEmail> list = (List<SentEmail>) dao.getAllSentEmail(currEmail);
            session.setAttribute("sentEmailList", list);
            session.setAttribute("sentEmailNumber", list.size());
        } else {
            session.setAttribute("sendEmailMess", "邮件发送失败");

        }
        session.setAttribute("active", 4);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sentEmailList.jsp");
        dispatcher.forward(request, response);
    }
}
