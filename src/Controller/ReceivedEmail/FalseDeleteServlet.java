package Controller.ReceivedEmail;

import Dao.ReceivedEmailDAO;
import Dao.ReceivedEmailDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/FalseDeleteServlet")
public class FalseDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        String[] strs= ids.split(" ");
        ReceivedEmailDAO dao = new ReceivedEmailDAOImpl();
        int successCount = 0;
        int failCount = 0;
        for (String str:strs) {
            int id = Integer.parseInt(str);
            if(dao.falseDelete(id)){
                successCount++;
            } else {
                failCount++;
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("falseDelete", successCount+"个删除成功，"+ failCount +" 失败");
        //List<ReceivedEmail> list = dao.getAllReceiveEmails()
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ReceiveEmails.jsp");
        dispatcher.forward(request, response);
    }
}
