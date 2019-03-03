package Controller;

import Model.User;
import Model.UserDAO;
import Model.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("userName").trim();
        String password = request.getParameter("passowrd").trim();
        //HttpSession session = request.getSession();
        UserDAO uDAO = new UserDAOImpl();
        User u = new User(name, password);
        System.out.println("LoginCheck");
        if(uDAO.query(u)){
            //登录用户合法，进入系统
            RequestDispatcher dispatcher = request.getRequestDispatcher("/View/UserEmail/EmailPage.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("/View/Login/login.jsp");
        }
    }
}
