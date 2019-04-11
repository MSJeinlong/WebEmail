package Controller.SendEmails;

import Dao.SendEmailDAO;
import Dao.SendEmailDAOImpl;
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
import java.util.List;

@WebServlet("/SearchSentEmailServlet")
public class SearchSentEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyName = request.getParameter("keyName");
        HttpSession session = request.getSession();
        Email email = (Email) session.getAttribute("currEmail");
        SendEmailDAO dao = new SendEmailDAOImpl();
        List<SentEmail> list = dao.queryEmails(email, keyName);
        session.setAttribute("sentEmailList", list);
        session.setAttribute("sentEmailNumber", list.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sentEmailList.jsp");
        dispatcher.forward(request, response);
    }
}
