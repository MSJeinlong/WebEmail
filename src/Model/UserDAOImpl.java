package Model;

import Tools.DBConnection;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();
        User u = new User();
        u.setUserName("Bill");
        u.setPassword("123456");
        u.setPhone("121314917957");
        System.out.println(userDAO.query(u));
    }

    @Override
    public boolean add(User u) {
        conn = DBConnection.getConnection();
        String sql = "insert into User(name, password, phone, status) values(?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, u.getUserName());
            pstmt.setString(2, u.getPassword());
            pstmt.setString(3, u.getPhone());
            pstmt.setInt(4, u.getStatus());
            if(pstmt.executeUpdate() > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             DBConnection.free(conn, pstmt, rs);
        }
        return false;
    }

    @Override
    public boolean query(User u) {
        conn = DBConnection.getConnection();
        String sql = "select * from User where name = ? and password = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, u.getUserName());
            pstmt.setString(2, u.getPassword());
            rs = pstmt.executeQuery();
            if(rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return false;
    }

    @Override
    public boolean update(User u) {
        return false;
    }

    @Override
    public boolean delete(User u) {
        return false;
    }
}
