package Dao;

import bean.Email;
import bean.ReceivedEmail;

import java.util.List;

public interface ReceivedEmailsDAO {
    public boolean add(ReceivedEmail email);
    public List<ReceivedEmail> getAllReceiveEmails(Email email);
    public boolean delete(int id);
}
