package Dao;

import Tools.DBConnection;
import bean.Email;
import bean.SentEmail;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendEmailDAOImpl implements SendEmailDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public boolean add(SentEmail sentEmail) {
        String sql = "insert into emails_send(user_name, email_number, email_from, email_to, email_cc, email_bc,subject," +
                "content, haveAttachment,attachmentPath,attachmentName,attachmentSize,isFavorites,isDelete, sentDate, isSent) " +
                "value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sentEmail.getUserName());
            pstmt.setInt(2, sentEmail.getEmailNumber());
            pstmt.setString(3, sentEmail.getEmailFrom());
            pstmt.setString(4, sentEmail.getEmailTO());
            pstmt.setString(5, sentEmail.getEmailCC());
            pstmt.setString(6, sentEmail.getEmailBC());
            pstmt.setString(7, sentEmail.getSubject());
            pstmt.setString(8, sentEmail.getContent());
            pstmt.setBoolean(9, sentEmail.isContainsAttachment());
            pstmt.setString(10, sentEmail.getAttachmentPath());
            pstmt.setString(11, sentEmail.getAttachmentName());
            pstmt.setInt(12, sentEmail.getAttachmentSize());
            pstmt.setBoolean(13, sentEmail.isFavorites());
            pstmt.setBoolean(14, sentEmail.isDelete());

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = dateFormat.parse(sentEmail.getSendDate());
                Timestamp d = new Timestamp(date.getTime());
                pstmt.setTimestamp(15, d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pstmt.setBoolean(16, sentEmail.isSent());
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
    public List<SentEmail> getAllSentEmail(Email email) {
        List<SentEmail> list = new ArrayList<>();
        String sql = "select * from emails_send where user_name = ? and email_from = ? and isDelete = false and isSent = true";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SentEmail sentEmail = setSentEmail(rs, email);
                list.add(sentEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return list;
    }

    @Override
    public List<SentEmail> getAllDeleteEmail(Email email) {
        List<SentEmail> list = new ArrayList<>();
        String sql = "select * from emails_send where user_name = ? and email_from = ? and isDelete = true";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SentEmail sentEmail = setSentEmail(rs, email);
                list.add(sentEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return list;
    }

    @Override
    public List<SentEmail> getAllDraftsEmails(Email email) {
        List<SentEmail> list = new ArrayList<>();
        String sql = "select * from emails_send where user_name = ? and email_from = ? and isDelete = false and isSent = false";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SentEmail sentEmail = setSentEmail(rs, email);
                list.add(sentEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return list;
    }

    @Override
    public List<SentEmail> queryEmails(Email email, String keyName) {
        String sql = "select * from emails_send where user_name = ? and email_from = ? " +
                "and (email_to like ? or subject like ?)";
        List<SentEmail> list = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            pstmt.setString(3, "%" + keyName + "%");
            pstmt.setString(4, "%" + keyName + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SentEmail sentEmail = setSentEmail(rs, email);
                list.add(sentEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return list;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from emails_send where email_id = ? ";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return false;
    }

    private SentEmail setSentEmail(ResultSet rs, Email email) {
        SentEmail sentEmail = new SentEmail();
        try {
            sentEmail.setEmailId(rs.getInt("email_id"));
            sentEmail.setUserName(email.getUsername());
            sentEmail.setEmailFrom(email.getEmail());
            sentEmail.setEmailTO(rs.getString("email_to"));
            sentEmail.setEmailCC(rs.getString("email_cc"));
            sentEmail.setEmailBC(rs.getString("email_bc"));
            sentEmail.setSubject(rs.getString("subject"));
            sentEmail.setContent(rs.getString("content"));
            sentEmail.setSent(rs.getBoolean("isSent"));

            sentEmail.setSendDate(rs.getString("sentDate"));


            sentEmail.setContainsAttachment(rs.getBoolean("haveAttachment"));
            sentEmail.setAttachmentPath(rs.getString("attachmentPath"));
            sentEmail.setAttachmentName(rs.getString("attachmentName"));
            sentEmail.setAttachmentSize(rs.getInt("attachmentSize"));
            sentEmail.setFavorites(rs.getBoolean("isFavorites"));
            sentEmail.setDelete(rs.getBoolean("isDelete"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sentEmail;
    }
}
