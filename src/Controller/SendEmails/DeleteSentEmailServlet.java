package Controller.SendEmails;

import Dao.SendEmailDAO;
import Dao.SendEmailDAOImpl;
import bean.Email;
import bean.SentEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteSentEmailServlet")
public class DeleteSentEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        String[] strs = ids.split(" ");
        int[] idArray = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            idArray[i] = Integer.parseInt(strs[i]);
        }

        SendEmailDAO dao = new SendEmailDAOImpl();
        int successCount = 0;
        int failCount = 0;
        for (int id:idArray) {
            if(dao.delete(id)){
                successCount++;
            } else {
                failCount++;
            }
        }
        HttpSession session = request.getSession();
        Email email = (Email) session.getAttribute("currEmail");
        List<SentEmail> sentEmailList = dao.getAllSentEmail(email);
        // System.out.println("获取sentEmailList-----------------");
        session.setAttribute("sentEmailList", sentEmailList);
        session.setAttribute("sentEmailNumber", sentEmailList.size());
        session.setAttribute("deleteSentEmail", "成功删除 "+successCount+" 个， 失败 "+failCount+" 个");
        response.sendRedirect("/sentEmailList.jsp");
    }
}
