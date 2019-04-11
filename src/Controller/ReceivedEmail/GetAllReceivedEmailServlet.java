package Controller.ReceivedEmail;

import Dao.ReceivedEmailDAOImpl;
import Dao.ReceivedEmailDAO;
import bean.Email;
import bean.ReceivedEmail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/GetAllReceivedEmailServlet")
public class GetAllReceivedEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //到数据库中取出所有的Received邮件
        HttpSession session = request.getSession();
        Email email = (Email) session.getAttribute("currEmail");
        ReceivedEmailDAO dao = new ReceivedEmailDAOImpl();
        List<ReceivedEmail> list = dao.getAllReceiveEmails(email);
        session.setAttribute("receivedEmails", list);
        session.setAttribute("receivedEmailsNumber", list.size());
        session.setAttribute("active", "3");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ReceiveEmails.jsp");
        dispatcher.forward(request, response);
    }
}
