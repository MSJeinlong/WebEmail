package Controller;

import bean.ReceivedEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ReadContentServlet")
public class ReadContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取邮件
        HttpSession session = request.getSession();
        ReceivedEmail email = (ReceivedEmail) session.getAttribute("showReceivedEmail");
        PrintWriter out = response.getWriter();
        //将正文作为html页面输出
        response.setContentType("text/html;charset=UTF-8");
        out.println(email.getContent());
    }
}
