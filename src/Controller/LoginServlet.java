package Controller;

import bean.User;
import Dao.UserDAO;
import Dao.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        if(uDAO.query(u)){
            //session.setAttribute("message", "登录成功");
            //登录用户合法，进入系统
           /* RequestDispatcher dispatcher = request.getRequestDispatcher("/View/Emails.jsp");
            dispatcher.forward(request, response);*/
           response.sendRedirect("/View/Emails.jsp");
        } else {
            session.setAttribute("message", "用户名或密码错误！");
            response.sendRedirect("/View/login.jsp");
        }
    }
}
