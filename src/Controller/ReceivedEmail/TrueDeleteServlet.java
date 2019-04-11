package Controller.ReceivedEmail;

import Dao.ReceivedEmailDAO;
import Dao.ReceivedEmailDAOImpl;
import Tools.POP3ReceiveEmail;
import bean.Email;
import bean.ReceivedEmail;

import javax.mail.Message;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/TrueDeleteServlet")
public class TrueDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ids = request.getParameter("ids");
        Email email = (Email) session.getAttribute("currEmail");
        String[] strs = ids.split(" ");
        ReceivedEmailDAO dao = new ReceivedEmailDAOImpl();
        int successCount = 0;
        int failCount = 0;
        for (String str : strs) {
            int id = Integer.parseInt(str);
            if (dao.trueDelete(id) && POP3ReceiveEmail.deleteEmails(id)) {
                successCount++;
            } else {
                failCount++;
            }

        }
        session.setAttribute("trueDelete", successCount + " 个成功，" + failCount + " 个失败");
        //同时更新session中的receivedEmails
        List<ReceivedEmail> list = dao.getAllReceiveEmails(email);
        session.setAttribute("receivedEmails", list);
        session.setAttribute("receivedEmailsNumber", list.size());
        session.setAttribute("active", 3);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ReceiveEmails.jsp");
        dispatcher.forward(request, response);
    }
}
