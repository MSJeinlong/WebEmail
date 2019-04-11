package Controller.SendEmails;

import Dao.SendEmailDAO;
import Dao.SendEmailDAOImpl;
import bean.Email;
import bean.SentEmail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/GetAllSentEmailServlet")
public class GetAllSentEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Email email = (Email) session.getAttribute("currEmail");

        SendEmailDAO dao = new SendEmailDAOImpl();
        List<SentEmail> sentEmailList = dao.getAllSentEmail(email);
       // System.out.println("获取sentEmailList-----------------");
        session.setAttribute("sentEmailList", sentEmailList);
        session.setAttribute("sentEmailNumber", sentEmailList.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sentEmailList.jsp");
        dispatcher.forward(request, response);
    }
}
