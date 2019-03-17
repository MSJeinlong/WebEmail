package Dao;

import Tools.DBConnection;
import bean.Email;
import bean.ReceivedEmail;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceivedEmailDAOImpl implements ReceivedEmailsDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public static void main(String[] args) {
        ReceivedEmailsDAO rmDao = new ReceivedEmailDAOImpl();

    }

    @Override
    public boolean add(ReceivedEmail email) {
        String sql = "insert into emails_receive(user_name, mail_address, subject, sender, receiverTO, receiverCC, sentDate, isSeen, priority," +
                "isReplySign, mailSize, isHaveFile, fileDir, fileSize, fileName, content, isFavorites, isDelete, mailNumber) " +
                "value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = dateFormat.parse(email.getSendDate());
                Timestamp d = new Timestamp(date.getTime());
                pstmt.setTimestamp(7, d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pstmt.setBoolean(8, email.isSeen());
            pstmt.setString(9, email.getPriority());
            pstmt.setBoolean(10, email.isReplySign());
            pstmt.setInt(11, email.getEmialSize());
            pstmt.setBoolean(12, email.isHaveFile());
            pstmt.setString(13, email.getFileDir());
            pstmt.setInt(14, email.getFileSize());
            pstmt.setString(15, email.getFileName());
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
        String sql = "select * from emails where user_name = ? and email_address = ?";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email.getUsername());
            pstmt.setString(2, email.getEmail());
            rs = pstmt.executeQuery();
            while (rs.next()){
                int index = 1;
                ReceivedEmail receivedEmail = new ReceivedEmail();
                receivedEmail.setEmailId(rs.getInt(index++));
                receivedEmail.setUserName(rs.getString(index++));
                receivedEmail.setEmailNumber(rs.getInt(index++));
                receivedEmail.setEmailAddress(rs.getString(index++));
                receivedEmail.setSubject(rs.getString(index++));
                receivedEmail.setSender(rs.getString(index++));
                receivedEmail.setReceiverTO(rs.getString(index++));
                receivedEmail.setReceiverCC(rs.getString(index++));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String sentDate = dateFormat.format(rs.getTimestamp(index++));
                receivedEmail.setSendDate(sentDate);
                receivedEmail.setSeen(rs.getBoolean(index++));
                receivedEmail.setPriority(rs.getString(index++));
                receivedEmail.setReplySign(rs.getBoolean(index++));
                receivedEmail.setEmialSize(rs.getInt(index++));
                receivedEmail.setHaveFile(rs.getBoolean(index++));
                receivedEmail.setFileDir(rs.getString(index++));
                receivedEmail.setFileSize(rs.getInt(index++));
                receivedEmail.setFileName(rs.getString(index++));
                receivedEmail.setContent(rs.getString(index++));
                receivedEmail.setFavorites(rs.getBoolean(index++));
                receivedEmail.setDelete(rs.getBoolean(index++));
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
}
