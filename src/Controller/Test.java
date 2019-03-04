package Controller;

import Model.User;
import Model.UserDAO;
import Model.UserDAOImpl;

public class Test {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();
        User u = new User();
        u.setUserName("Bill");
        u.setPassword("123456");
        u.setPhone("121314917957");
        System.out.println(userDAO.add(u));
    }
}
