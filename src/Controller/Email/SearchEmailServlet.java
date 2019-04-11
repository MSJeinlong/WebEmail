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

@WebServlet("/SearchEmailServlet")
public class SearchEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyName = request.getParameter("keyName");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        EmailDAO dao = new EmailDAOImpl();
        List<Email> list = dao.query(userName, keyName);
        session.setAttribute("emailsList", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userInfo.jsp");
        dispatcher.forward(request, response);
    }
}
