package Controller.Contacts;

import Dao.ContactDAO;
import Dao.ContactDAOImpl;
import bean.Contact;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GetAllContactsServlet")
public class GetAllContactsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到用户的所有联系人
        HttpSession session = request.getSession();
        User u = (User)session.getAttribute("user");
        List<Contact> list= new ArrayList<>();
        ContactDAO dao = new ContactDAOImpl();
        list = dao.getAllContacts(u.getUserName());
        session.setAttribute("contactList", list);
        //System.out.println("得到所有联系人");
        response.sendRedirect("/contacts.jsp");
    }
}
