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
        String sql = "insert into user_email(name, email, password, alias, number, smtphost, pop3host) values(?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            pstmt.setString(3, email.getPassword());
            pstmt.setString(4, email.getAlias());
            pstmt.setInt(5, email.getNumber());
            pstmt.setString(6, email.getSmtpHost());
            pstmt.setString(7, email.getPop3Host());
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
    public List<Email> query(String userName, String keyName) {
        List<Email> list = new ArrayList<>();
        String sql = "select * from user_email where name = ? and (email like ? or alias like ?)";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userName);
            pstmt.setString(2, "%"+keyName+"%");
            pstmt.setString(3, "%"+keyName+"%");
            rs = pstmt.executeQuery();
            while (rs.next()){
                Email email = new Email();
                email.setId(rs.getInt("id"));
                email.setEmail(rs.getString("email"));
                email.setPassword(rs.getString("password"));
                email.setAlias(rs.getString("alias"));
                list.add(email);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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
                email.setSmtpHost(rs.getString("smtphost"));
                email.setPop3Host(rs.getString("pop3host"));
                list.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean update(Email email) {
        String sql = "update user_email set email = ?, password = ?, alias = ?, number = ? where id = ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getEmail());
            pstmt.setString(2, email.getPassword());
            pstmt.setString(3, email.getAlias());
            pstmt.setInt(4, email.getNumber());
            pstmt.setInt(5, email.getId());
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
        String sql = "delete from user_email where id = ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, email.getId());
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
