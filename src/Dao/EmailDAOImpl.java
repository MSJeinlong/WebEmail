package Dao;

import Tools.DBConnection;
import bean.Email;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailDAOImpl implements EmailDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public static void main(String[] args) {
        Email email = new Email();
        email.setUsername("Zachary");
        email.setEmail("15917362227@163.com");
        email.setPassword("111314141");
        email.setAlias("网易邮箱");
        email.setNumber(6);

        EmailDAO emailDAO = new EmailDAOImpl();
//        System.out.println(emailDAO.add(email));
//        System.out.println(emailDAO.query(email));
//        email.setPassword("985211");
//        System.out.println(emailDAO.update(email));
      /*  System.out.println(emailDAO.delete(email));
        System.out.println(emailDAO.query(email));*/
    }

    @Override
    public boolean add(Email email) {
        String sql = "insert into user_email(name, email, password, alias, number) values(?, ?, ?, ?, ?)";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            pstmt.setString(3, email.getPassword());
            pstmt.setString(4, email.getAlias());
            pstmt.setInt(5, email.getNumber());
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return false;
    }

    @Override
    public boolean contains(Email email) {
        String sql = "select * from user_email where name = ? and email = ? and password = ? and id != ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            pstmt.setString(3, email.getPassword());
            pstmt.setInt(4, email.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Email> getUserAllEmails(String name) {
        String sql = "select * from user_email where name = ?";
        List<Email> list = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Email email = new Email();
                email.setId(rs.getInt("id"));
                email.setUsername(name);
                email.setEmail(rs.getString("email"));
                email.setPassword(rs.getString("password"));
                email.setAlias(rs.getString("alias"));
                email.setNumber(rs.getInt("number"));
                list.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean update(Email email) {
        String sql = "update user_email set password = ?, alias = ?, number = ? where name = ? and email = ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getPassword());
            pstmt.setString(2, email.getAlias());
            pstmt.setInt(3, email.getNumber());
            pstmt.setString(4, email.getUsername());
            pstmt.setString(5, email.getEmail());
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return false;
    }

    @Override
    public boolean delete(Email email) {
        String sql = "delete from user_email where name = ? and email = ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return false;
    }
}
