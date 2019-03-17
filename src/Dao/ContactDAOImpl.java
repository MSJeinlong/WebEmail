package Dao;

import Tools.DBConnection;
import bean.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public boolean add(Contact contact) {
        String sql = "insert into contacts(user_name, contact_name, contact_email) value(?,?,?)";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, contact.getUser_name());
            pstmt.setString(2, contact.getContact_name());
            pstmt.setString(3, contact.getContact_email());
            if (pstmt.executeUpdate() > 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return false;
    }

    @Override
    public boolean query(Contact contact) {
        String sql = "select * from contacts where user_name = ? and contact_name = ? and contact_email = ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, contact.getUser_name());
            pstmt.setString(2, contact.getContact_name());
            pstmt.setString(3, contact.getContact_email());
            rs = pstmt.executeQuery();
            if (rs.next()) {
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
    public List<Contact> getAllContacts(String username) {
        String sql = "select * from contacts where user_name = ?";
        List<Contact> list = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Contact contact = new Contact();
                contact.setUser_name(username);
                contact.setContact_name(rs.getString("contact_name"));
                contact.setContact_email(rs.getString("contact_email"));
                list.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return null;
    }

    @Override
    public boolean update(Contact contact) {
        String sql = "update contacts set contact_name = ?, contact_email = ? where user_name = ?";
        try {
            conn = DBConnection.getConnection();
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1, contact.getContact_name());
            pstmt.setString(2, contact.getContact_email());
            pstmt.setString(3, contact.getUser_name());
            if(pstmt.executeUpdate() > 0){
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
    public boolean delete(Contact contact) {
        String sql = "delete from contacts where user_name = ? and contact_name = ? and contact_email = ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, contact.getUser_name());
            pstmt.setString(2, contact.getContact_name());
            pstmt.setString(3, contact.getContact_email());
            if(pstmt.executeUpdate() > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return false;
    }
}
