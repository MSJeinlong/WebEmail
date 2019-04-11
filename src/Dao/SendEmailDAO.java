package Dao;

import bean.Email;
import bean.SentEmail;

import java.util.List;

public interface SendEmailDAO {
    public boolean add(SentEmail sentEmail);
    public List<SentEmail> getAllSentEmail(Email email);
    public List<SentEmail> getAllDeleteEmail(Email email);
    public List<SentEmail> getAllDraftsEmails(Email email);
    public List<SentEmail> queryEmails(Email email, String keyName);
    public boolean delete(int id);
}
