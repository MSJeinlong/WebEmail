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

@WebServlet("/DeleteEmailBoxServlet")
public class DeleteEmailBoxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ids
        String ids = request.getParameter("ids");
        String[] idArr = ids.split(" ");
        int[] emailIds = new int[idArr.length];
        for (int i = 0; i < idArr.length; i++) {
            emailIds[i] = Integer.parseInt(idArr[i]);
            //System.out.print(arr[i] + " ");
        }
        int successCount = 0;
        int failCount = 0;
        EmailDAO dao = new EmailDAOImpl();
        HttpSession session = request.getSession();
        List<Email> list = (List<Email>) session.getAttribute("emailsList");
        for (int i:emailIds) {
            Email email = list.get(i);
            if(dao.delete(email)){
                successCount++;
            } else {
                failCount++;
            }
        }
        //同时要更新Session中的emailsList
        String userName = (String) session.getAttribute("username");
        list = dao.getUserAllEmails(userName);
        session.setAttribute("emailsList", list);
        session.setAttribute("deleteEmailBoxMess", "成功删除 "+successCount+" 个， 失败 "+failCount+" 个");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userInfo.jsp");
        dispatcher.forward(request, response);
    }
}
