package Controller.Contacts;

import Dao.ContactDAO;
import Dao.ContactDAOImpl;
import bean.Contact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/UpdateContactServlet")
public class UpdateContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int contactId = Integer.parseInt(request.getParameter("contactId"));
        String contactName = request.getParameter("contactName");
        String contactEmail = request.getParameter("contactEmail");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        Contact contact = new Contact(contactId, userName, contactName, contactEmail);
        ContactDAO dao = new ContactDAOImpl();
        if(!dao.contains(contact) && dao.update(contact)){
            session.setAttribute("updateContactMess", "联系人信息修改成功");
            List<Contact> list = dao.getAllContacts(userName);
            session.setAttribute("contactList", list);
            response.sendRedirect("/contacts.jsp");
        } else {
            session.setAttribute("updateContactMess", "联系人信息修改失败！\n原因：1.通讯录已存在这条联系人信息");
//            response.sendRedirect("/updateContact.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/updateContact.jsp");
            dispatcher.forward(request, response);
        }
    }
}
