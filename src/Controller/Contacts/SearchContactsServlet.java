package Controller.Contacts;

import Dao.ContactDAO;
import Dao.ContactDAOImpl;
import bean.Contact;
import bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchContactsServlet")
public class SearchContactsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyName = request.getParameter("keyName");
        ContactDAO contactDAO = new ContactDAOImpl();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        Contact contact = new Contact();
        contact.setUser_name(u.getUserName());
        contact.setContact_name(keyName);
        List<Contact> contactList = contactDAO.query(contact);
        session.setAttribute("contactList", contactList);
        session.setAttribute("contactNumber",contactList.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/contacts.jsp");
        dispatcher.forward(request, response);
    }
}
