package Dao;

import bean.Email;

import java.util.List;

public interface EmailDAO {
    public boolean add(Email email);
    public boolean contains(Email email);
    public List<Email> getUserAllEmails(String name);
    public boolean update(Email email);
    public boolean delete(Email email);
}
