package Dao;

import bean.Contact;

import java.util.List;

public interface ContactDAO {
    public boolean add(Contact contact);
    public boolean contains(Contact contact);
    public List<Contact> query(Contact contact);
    public List<Contact> getAllContacts(String username);
    public boolean update(Contact contact);
    public boolean delete(Contact contact);
}
