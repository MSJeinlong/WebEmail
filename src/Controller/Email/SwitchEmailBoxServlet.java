package Controller.Email;

import Dao.ReceivedEmailDAO;
import Dao.ReceivedEmailDAOImpl;
import Dao.SendEmailDAO;
import Dao.SendEmailDAOImpl;
import Tools.POP3ReceiveEmail;
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

@WebServlet("/SwitchEmailBoxServlet")
public class SwitchEmailBoxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //切换邮箱账号
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        ReceivedEmailDAO dao = new ReceivedEmailDAOImpl();
        List<ReceivedEmail> receivedEmailList = null;
        List<Email> list = (List<Email>) session.getAttribute("emailsList");
        //设置用户的当前邮箱
        Email currEmail = list.get(Integer.parseInt(id));
        session.setAttribute("currEmail", currEmail);
        session.setAttribute("currEmailAddr", currEmail.getEmail());
        //获取邮箱的所有已发送的邮件
        SendEmailDAO sendEmailDAO = new SendEmailDAOImpl();
        List<SentEmail> sentEmailList = sendEmailDAO.getAllSentEmail(currEmail);
        session.setAttribute("sentEmailList", sentEmailList);
        session.setAttribute("sentEmailNumber", sentEmailList.size());

        //获取邮箱的收件箱的邮件
        //先尝试从数据库获取邮件
        System.out.println();
        System.out.println();
        System.out.println("switchEmailBox");
        receivedEmailList = dao.getAllReceiveEmails(currEmail);
        System.out.println("refresh之前------邮件数量:"+receivedEmailList.size());
        //判断邮件是为null或者为0
        if (receivedEmailList == null || receivedEmailList.size() == 0) {
            //POP3协议从邮件服务器获取收件箱的邮件
            POP3ReceiveEmail.setProps(currEmail);

            //ReceivedEmailDAO receivedEmailDAO = new ReceivedEmailDAOImpl();

            try {
                receivedEmailList = POP3ReceiveEmail.resceive();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //已经刷新了收件箱（即从邮件服务器下载了最新的邮件）
            //则保存刚刚获取到的邮件到数据库
            for (ReceivedEmail receivedEmail : receivedEmailList) {
                dao.add(receivedEmail);
            }
        }
        System.out.println("refresh之后------邮件数量:"+receivedEmailList.size());
        System.out.println();
        System.out.println();

        session.setAttribute("receivedEmails", receivedEmailList);
        session.setAttribute("receivedEmailsNumber", receivedEmailList.size());
        session.setAttribute("switchMess", "邮箱切换成功");
        session.setAttribute("activeEmailBox", id);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/funcList.jsp");
//        dispatcher.forward(request, response);
        //本次请求是从funcList.jsp的 <a> 链接发过来的，并且请求完成之后还要返回到funcList.jsp,
        // 所以不能用forward转发页面，否则会陷入request死循环
        response.sendRedirect("/funcList.jsp");
    }
}
