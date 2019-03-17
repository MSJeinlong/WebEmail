package bean;

/**
 * 这是用户-Email一一对应的class类
 * 一个用户可以绑定多个Email
 */
public class Email {
    private String username;     //用户名
    private String email;       //用户对应的email
    private String password;    //email对应的密码
    private String alias;       //备注
    private int number;         //该email所对应的收件箱的邮件数量

    public Email() {
    }

    public Email(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Email{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", alias='" + alias + '\'' +
                ", number=" + number +
                '}';
    }
}
