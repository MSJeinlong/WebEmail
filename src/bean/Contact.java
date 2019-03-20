package bean;

/**
 * 这是user_contacts类，即用户——联系人关系
 * 一个用户可以有多个联系人
 */
public class Contact {
    private int id;
    private String user_name;
    private String contact_name;
    private String contact_email;

    public Contact() {
    }

    public Contact(int id, String user_name, String contact_name, String contact_email) {
        this.id = id;
        this.user_name = user_name;
        this.contact_name = contact_name;
        this.contact_email = contact_email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "user_name='" + user_name + '\'' +
                ", contact_name='" + contact_name + '\'' +
                ", contact_email='" + contact_email + '\'' +
                '}';
    }
}
