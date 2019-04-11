package Controller.ReceivedEmail;

import Dao.ReceivedEmailDAO;
import Dao.ReceivedEmailDAOImpl;
import bean.Email;
import bean.ReceivedEmail;
import bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchReceivedEmailServlet")
public class SearchReceivedEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyName = request.getParameter("keyName");
        HttpSession session = request.getSession();
        Email email = (Email) session.getAttribute("currEmail");

        ReceivedEmailDAO dao = new ReceivedEmailDAOImpl();
        List<ReceivedEmail> list = dao.queryEmails(email, keyName);
        session.setAttribute("receivedEmails", list);
        session.setAttribute("receivedEmailsNumber", list.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ReceiveEmails.jsp");
        dispatcher.forward(request, response);
    }
}
