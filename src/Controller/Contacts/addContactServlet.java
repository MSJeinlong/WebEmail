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

@WebServlet("/AddContactServlet")
public class addContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取新增的联系人信息
        String contactName = request.getParameter("contactName");
        String contactEmail = request.getParameter("contactEmail");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        Contact contact = new Contact();
        contact.setUser_name(u.getUserName());
        contact.setContact_name(contactName);
        contact.setContact_email(contactEmail);
        //添加新增的联系人到数据库
        ContactDAO dao = new ContactDAOImpl();
        //如果该联系人的信息没有重复，就添加
        if (!dao.contains(contact) && dao.add(contact)) {
            session.setAttribute("mess", "联系人添加成功");
            //System.out.println("联系人添加成功");
            //更新联系人信息
            List<Contact> contactList = new ArrayList<>();
            contactList = dao.getAllContacts(u.getUserName());
            session.setAttribute("contactList", contactList);
            session.setAttribute("contactNumber", contactList.size());
            session.setAttribute("active", 2);
            response.sendRedirect("/contacts.jsp");
        } else {
            session.setAttribute("mess", "联系人添加失败！\n原因：通讯录已存在这条联系人信息");
            response.sendRedirect("/addContact.jsp");
            //System.out.println("联系人添加失败");
        }
    }
}
