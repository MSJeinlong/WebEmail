package Controller.Email;

import Dao.*;
import bean.Email;
import bean.ReceivedEmail;
import bean.SentEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddEmailServlet")
public class AddEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String alias = request.getParameter("alias");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        EmailDAO dao = new EmailDAOImpl();
        Email email1 = new Email(userName, email, password, alias);

        //验证邮箱
        //do something.....

        if(dao.add(email1)){
            session.setAttribute("addEmailBoxMess", "邮箱绑定成功");
            //更新Session的EmailsList
            List<Email> list = dao.getUserAllEmails(userName);
            List<Email> emailsList = dao.getUserAllEmails(userName);
            session.setAttribute("emailsList", emailsList);
            Email currEmail = emailsList.get(0);
            session.setAttribute("currEmail", currEmail);
            session.setAttribute("currEmailAddr", currEmail.getEmail());
            //获取邮箱的所有已发送的邮件
            SendEmailDAO sendEmailDAO = new SendEmailDAOImpl();
            List<SentEmail> sentEmailList = sendEmailDAO.getAllSentEmail(currEmail);
            session.setAttribute("sentEmailList", sentEmailList);
            session.setAttribute("sentEmailNumber", sentEmailList.size());

            //获取邮箱的收件箱的邮件
            ReceivedEmailDAO receivedEmailDAO = new ReceivedEmailDAOImpl();
            List<ReceivedEmail> receivedEmailList = receivedEmailDAO.getAllReceiveEmails(currEmail);
            session.setAttribute("receivedEmails", receivedEmailList);
            session.setAttribute("receivedEmailsNumber", receivedEmailList.size());

            response.sendRedirect("/userInfo.jsp");
        } else {
            session.setAttribute("addEmailBoxMess","邮箱绑定失败\n可能原因：1. 邮箱地址或密码错误.\n2.你的邮箱设置授权码，本系统暂未支持");
            response.sendRedirect("/addMailBox.jsp");
        }
    }
}
