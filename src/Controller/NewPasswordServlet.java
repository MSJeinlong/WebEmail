package Controller;

import Dao.UserDAO;
import Dao.UserDAOImpl;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/NewPasswordServlet")
public class NewPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        //根据新密码建立User
        User u = new User(userName, newPassword);
        UserDAO dao = new UserDAOImpl();
        //比较用户输入的原密码是否正确
        if(!password.equals(oldPassword)){
            session.setAttribute("changePasswordMess", "原密码错误！");
            response.sendRedirect("/changePassword.jsp");
        } else if(dao.update(u)){
            session.setAttribute("changePasswordMess", "密码修改成功");
            //更改session中的password
            session.setAttribute("password", newPassword);
            response.sendRedirect("/userInfo.jsp");
        } else {
            session.setAttribute("changePasswordMess", "密码修改失败！");
            response.sendRedirect("/changePassword.jsp");
        }
    }
}
