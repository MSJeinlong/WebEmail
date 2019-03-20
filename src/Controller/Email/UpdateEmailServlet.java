package Controller.Email;

import Dao.EmailDAO;
import Dao.EmailDAOImpl;
import bean.Email;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/UpdateEmailServlet")
public class UpdateEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("emailId"));
        String emailAdr = request.getParameter("email");
        String password = request.getParameter("password");
        String alias = request.getParameter("alias");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        Email email = new Email();
        email.setId(id);
        email.setUsername(userName);
        email.setEmail(emailAdr);
        email.setPassword(password);
        email.setAlias(alias);
        EmailDAO dao = new EmailDAOImpl();
        //查询该邮箱是否已经存在
        if (dao.contains(email)) {
            session.setAttribute("updateEmailBoxMess", "该邮箱已经存在！");
//            response.sendRedirect("/updateEmailBox.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/updateEmailBox.jsp");
            dispatcher.forward(request, response);
        } else if (dao.update(email)) {
            session.setAttribute("updateEmailBoxMess", "邮箱信息更新成功");
            List<Email> list = dao.getUserAllEmails(userName);
            session.setAttribute("emailsList", list);
            response.sendRedirect("/userInfo.jsp");
        } else {
            session.setAttribute("updateEmailBoxMess", "邮箱信息更新失败！\n原因：数据库处理错误！");
            response.sendRedirect("/userInfo.jsp");
        }
    }
}
