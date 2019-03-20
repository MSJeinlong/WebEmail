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

    public static void main(String[] args) {
        ContactDAO contactDAO = new ContactDAOImpl();
        Contact contact = new Contact();
        contact.setUser_name("Zachary");
        contact.setContact_name("邮箱");
        List<Contact> list = contactDAO.query(contact);
        for (Contact c: list) {
            System.out.println(c);
        }
    }

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
    public boolean contains(Contact contact) {
        String sql = "select * from contacts where user_name = ? and contact_name = ? and contact_email = ? and id != ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, contact.getUser_name());
            pstmt.setString(2, contact.getContact_name());
            pstmt.setString(3, contact.getContact_email());
            pstmt.setInt(4, contact.getId());
            rs = pstmt.executeQuery();
            if(rs.next()){
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
    public List<Contact> query(Contact contact) {
        List<Contact> contactList = new ArrayList<>();
        String sql = "select * from contacts where user_name = ? and contact_name like ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, contact.getUser_name());
            pstmt.setString(2, "%" + contact.getContact_name() +"%");
            rs = pstmt.executeQuery();
           while (rs.next()){
               Contact contact1 = new Contact();
               contact1.setId(rs.getInt("id"));
               contact1.setUser_name(contact.getUser_name());
               contact1.setContact_name(rs.getString("contact_name"));
               contact1.setContact_email(rs.getString("contact_email"));
               contactList.add(contact1);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return contactList;
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
                contact.setId(rs.getInt("id"));
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
        return list;
    }

    @Override
    public boolean update(Contact contact) {
        String sql = "update contacts set contact_name = ?, contact_email = ? where id = ?";
        try {
            conn = DBConnection.getConnection();
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1, contact.getContact_name());
            pstmt.setString(2, contact.getContact_email());
            pstmt.setInt(3, contact.getId());
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
