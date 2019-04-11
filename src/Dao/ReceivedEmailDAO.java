package Dao;

import bean.Email;
import bean.ReceivedEmail;

import java.util.List;

public interface ReceivedEmailDAO {
    public boolean add(ReceivedEmail email);
    public List<ReceivedEmail> getAllReceiveEmails(Email email);
    public boolean delete(int id);
    public boolean deleteByEmail(Email email);
    public List<ReceivedEmail> getAllDeleteEmails(Email email);
    public List<ReceivedEmail> getFavoritesEmails(Email email);
    public boolean falseDelete(int id);
    public boolean trueDelete(int id);
    public List<ReceivedEmail> queryEmails(Email email, String keyName);
}
