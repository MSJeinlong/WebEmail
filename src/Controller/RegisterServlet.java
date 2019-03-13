package Controller;

import bean.User;
import Dao.UserDAO;
import Dao.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        UserDAO userDAO = new UserDAOImpl();
        User u = new User(userName, password, phoneNumber);
        if(userDAO.add(u)){
            session.setAttribute("message", "注册成功！");
        } else {
            session.setAttribute("message", "注册失败！原因：用户名重复！");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/View/login.jsp");
        dispatcher.forward(request, response);
    }
}
