package Dao;

import Tools.DBConnection;
import bean.Email;
import bean.ReceivedEmail;

import javax.print.DocFlavor;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceivedEmailDAOImpl implements ReceivedEmailDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public boolean add(ReceivedEmail email) {
        String sql = "insert into emails_receive(user_name, email_address, subject, sender, receiverTO, receiverCC, sentDate, isSeen, priority," +
                "isReplySign, emailSize, haveAttachment, attachmentPath,  attachmentName, attachmentSize, content, isFavorites, isDelete, emailNumber) " +
                "value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUserName());
            pstmt.setString(2, email.getEmailAddress());
            pstmt.setString(3, email.getSubject());
            pstmt.setString(4, email.getSender());
            pstmt.setString(5, email.getReceiverTO());
            pstmt.setString(6, email.getReceiverCC());
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = dateFormat.parse(email.getSentDate());
                Timestamp d = new Timestamp(date.getTime());
                pstmt.setTimestamp(7, d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pstmt.setBoolean(8, email.isSeen());
            pstmt.setString(9, email.getPriority());
            pstmt.setBoolean(10, email.isReplySign());
            pstmt.setInt(11, email.getEmailSize());
            pstmt.setBoolean(12, email.isHaveAttachment());
            pstmt.setString(13, email.getAttachmentPath());
            pstmt.setString(14, email.getAttachmentName());
            pstmt.setInt(15, email.getAttachmentSize());
           // pstmt.setString(15, email.getFileName());
            pstmt.setString(16, email.getContent());
            pstmt.setBoolean(17, email.isFavorites());
            pstmt.setBoolean(18, email.isDelete());
            pstmt.setInt(19, email.getEmailNumber());
            if (pstmt.executeUpdate() > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return false;
    }

    @Override
    public List<ReceivedEmail> getAllReceiveEmails(Email email) {
        List<ReceivedEmail> list = new ArrayList<>();
        String sql = "select * from emails_receive where user_name = ? and email_address = ? and isDelete = false";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            rs = pstmt.executeQuery();
            while (rs.next()){
                ReceivedEmail receivedEmail = setReceivedEmail(rs);
                list.add(receivedEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from emails_receive where id = ? ";
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

    @Override
    public boolean deleteByEmail(Email email) {
        String sql = "delete from emails_receive where user_name = ? and email_address = ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            if(pstmt.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ReceivedEmail> getAllDeleteEmails(Email email) {
        List<ReceivedEmail> list = new ArrayList<>();
        String sql = "select * from emails_receive where user_name = ? and email_address = ? and isDelete = true";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            rs = pstmt.executeQuery();
            while (rs.next()){
                ReceivedEmail receivedEmail = setReceivedEmail(rs);
                list.add(receivedEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ReceivedEmail> getFavoritesEmails(Email email) {
        List<ReceivedEmail> list = new ArrayList<>();
        String sql = "select * from emails_receive where user_name = ? and email_address = ? and isFavorites = true";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            rs = pstmt.executeQuery();
            while (rs.next()){
                ReceivedEmail receivedEmail = setReceivedEmail(rs);
                list.add(receivedEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean falseDelete(int id) {
        String sql = "update emails_receive set isDelete = true where email_id = ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
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
    public boolean trueDelete(int id) {
        String sql = "delete from emails_receive where emailNumber = ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
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
    public List<ReceivedEmail> queryEmails(Email email, String keyName) {
        String sql = "select * from emails_receive where user_name = ? and email_address = ? " +
                "and (sender like ? or subject like ?)";
        List<ReceivedEmail> list = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            pstmt.setString(3, "%"+keyName+"%");
            pstmt.setString(4, "%"+keyName+"%");
            rs = pstmt.executeQuery();
            while (rs.next()){
                ReceivedEmail receivedEmail = setReceivedEmail(rs);
                list.add(receivedEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.free(conn, pstmt, rs);
        }
        return list;
    }

    private ReceivedEmail setReceivedEmail(ResultSet rs){
        int index = 1;
        ReceivedEmail receivedEmail = null;
        try {
            receivedEmail = new ReceivedEmail();
            receivedEmail.setEmailId(rs.getInt(index++));
            receivedEmail.setUserName(rs.getString(index++));
            receivedEmail.setEmailNumber(rs.getInt(index++));
            receivedEmail.setEmailAddress(rs.getString(index++));
            receivedEmail.setSubject(rs.getString(index++));
            receivedEmail.setSender(rs.getString(index++));
            receivedEmail.setReceiverTO(rs.getString(index++));
            receivedEmail.setReceiverCC(rs.getString(index++));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sentDate = dateFormat.format(rs.getTimestamp(index++));
            receivedEmail.setSentDate(sentDate);
            receivedEmail.setSeen(rs.getBoolean(index++));
            receivedEmail.setPriority(rs.getString(index++));
            receivedEmail.setReplySign(rs.getBoolean(index++));
            receivedEmail.setEmailSize(rs.getInt(index++));
            receivedEmail.setHaveAttachment(rs.getBoolean(index++));
            receivedEmail.setAttachmentName(rs.getString(index++));
            receivedEmail.setAttachmentPath(rs.getString(index++));
            receivedEmail.setAttachmentSize(rs.getInt(index++));
            //    receivedEmail.setFileName(rs.getString(index++));
            receivedEmail.setContent(rs.getString(index++));
            receivedEmail.setFavorites(rs.getBoolean(index++));
            receivedEmail.setDelete(rs.getBoolean(index++));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  receivedEmail;
    }
}
