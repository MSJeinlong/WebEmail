package Controller.ReceivedEmail;

import Dao.ReceivedEmailDAO;
import Dao.ReceivedEmailDAOImpl;
import Tools.POP3ReceiveEmail;
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

@WebServlet("/RefreshReceivedEmailServlet")
public class RefreshReceivedEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Email email = (Email) session.getAttribute("currEmail");
        //先删除所有属于Email的ReceivedEmail
        ReceivedEmailDAO dao = new ReceivedEmailDAOImpl();
        dao.deleteByEmail(email);
        POP3ReceiveEmail.setProps(email);
        try {
            List<ReceivedEmail> list = POP3ReceiveEmail.resceive();
            //获取邮件实体（未解析），用于删除操作
//            Message[] messages = POP3ReceiveEmail.getMessages();
//            session.setAttribute("messages", messages);
            session.setAttribute("receivedEmails", list);
            session.setAttribute("receivedEmailsNumber", list.size());
            //把获取的邮件保存到数据库
            for (ReceivedEmail receivedEmail:list) {
                dao.add(receivedEmail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.setAttribute("refreshMess", "收件箱同步成功");
        session.setAttribute("active", 3);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ReceiveEmails.jsp");
        dispatcher.forward(request, response);
    }
}
