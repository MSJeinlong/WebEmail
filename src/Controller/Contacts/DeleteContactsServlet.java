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
import java.util.List;

@WebServlet("/DeleteContactsServlet")
public class DeleteContactsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ids
        String ids = request.getParameter("ids");
        String[] idArr = ids.split(" ");
        int[] arr = new int[idArr.length];
        for (int i = 0; i < idArr.length; i++) {
            arr[i] = Integer.parseInt(idArr[i]);
            //System.out.print(arr[i] + " ");
        }
        //System.out.println("开始删除");
        ContactDAO dao = new ContactDAOImpl();
        HttpSession session = request.getSession();
        List<Contact> list = (List<Contact>) session.getAttribute("contactList");
        //System.out.println("list:" + list.size());
        //System.out.println(arr.toString());
        int successCount = 0;
        int failCount = 0;
        for (int i = 0; i < arr.length; i++) {
            //根据联系人编号获取联系人
            //System.out.println(arr[i]);
            Contact contact = list.get(arr[i]);
            //删除该联系人
            if (dao.delete(contact)) {
                successCount++;
                //list.remove(i);
            } else {
                failCount++;
            }
        }
        User u = (User)session.getAttribute("user");
        //同时更新contactList
        list = dao.getAllContacts(u.getUserName());
        //更新Session里的contactList和contactNumber
        session.setAttribute("contactList", list);
        session.setAttribute("contactNumber", list.size());
        //返回删除的结果
        session.setAttribute("deleteMess", successCount + " 个联系人删除成功， " + failCount + " 个失败");
        response.sendRedirect("/contacts.jsp");
    }
}
