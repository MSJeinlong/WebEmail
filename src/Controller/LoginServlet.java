package Controller;

import Dao.*;
import bean.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("userName").trim();
        String password = request.getParameter("password").trim();
        HttpSession session = request.getSession();
        UserDAO uDAO = new UserDAOImpl();
        User u = new User(name, password);
        //System.out.println(u.getUserName()+":"+u.getPassword());
        if (uDAO.query(u)) {
            //session.setAttribute("message", "登录成功");
            //登录用户合法，进入系统
           /* RequestDispatcher dispatcher = request.getRequestDispatcher("/View/Emails.jsp");
            dispatcher.forward(request, response);*/
            //保存用户信息到session
            session.setAttribute("user", u);
            session.setAttribute("username", u.getUserName());
            session.setAttribute("password", u.getPassword());
            //获取用户的联系人信息
            ContactDAO contactDAO = new ContactDAOImpl();
            List<Contact> contactList = new ArrayList<>();
            contactList = contactDAO.getAllContacts(name);
            session.setAttribute("contactList", contactList);
            session.setAttribute("contactNumber", contactList.size());
            //获取用户的邮箱信息
            EmailDAO dao = new EmailDAOImpl();
            List<Email> emailsList = dao.getUserAllEmails(name);
            //System.out.println(emailsList.size());
            session.setAttribute("emailsList", emailsList);
            System.out.println("login emailsList:"+emailsList.size());
            //设置用户的当前邮箱
            if(emailsList == null || emailsList.size() == 0){
                response.sendRedirect("/mainPage.jsp");
                return;
            }
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
            List<ReceivedEmail> list = receivedEmailDAO.getAllReceiveEmails(currEmail);
            session.setAttribute("receivedEmails", list);
            session.setAttribute("receivedEmailsNumber", list.size());

            //获取所有已经删除的邮件
            List<ReceivedEmail> deleteList = receivedEmailDAO.getAllDeleteEmails(currEmail);
            session.setAttribute("deletedEmails", deleteList);
            session.setAttribute("deletedEmailsNumber", deleteList.size());

            //获取所有收藏的邮件
            List<ReceivedEmail> favoritesList = receivedEmailDAO.getFavoritesEmails(currEmail);
            session.setAttribute("favoritesEmails", favoritesList);
            session.setAttribute("favoritesEmailsNumber", favoritesList.size());
            session.setAttribute("message", "登录成功！");

            //获取未发送的再草稿箱的邮件
            List<SentEmail> draftsEmails = sendEmailDAO.getAllDraftsEmails(currEmail);
            session.setAttribute("draftsEmails", draftsEmails);
            session.setAttribute("draftsEmailsNumber", draftsEmails.size());

            //设置功能页面的收件箱为active
            session.setAttribute("active", 3);
            session.setAttribute("activeEmailBox", 0);
            response.sendRedirect("/mainPage.jsp");
        } else {
            session.setAttribute("message", "用户名或密码错误！");
            response.sendRedirect("/login.jsp");
        }
    }
}
