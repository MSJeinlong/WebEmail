package Controller.SendEmails;

import bean.SentEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ShowSentContentServlet")
public class ShowSentContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        SentEmail email = (SentEmail) session.getAttribute("showSentEmail");
        PrintWriter out=response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>邮件正文:</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>"+email.getContent()+"</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
