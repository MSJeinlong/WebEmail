package Controller.Email;

import Dao.EmailDAO;
import Dao.EmailDAOImpl;
import bean.Email;

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
            session.setAttribute("emailsList", list);
            response.sendRedirect("/userInfo.jsp");
        } else {
            session.setAttribute("addEmailBoxMess","邮箱绑定失败\n可能原因：1. 邮箱地址或密码错误.\n2.你的邮箱设置授权码，本系统暂未支持");
            response.sendRedirect("/addMailBox.jsp");
        }
    }
}
