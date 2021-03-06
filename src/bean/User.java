package bean;
/**
 * 
 * @author Think
 * The information about User.
 */

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userName;
	private String password;
	private int status;  //是否已经登录, 0-未登录， 1-已经登录
    private String phone;
	private List<Email> emaiList = new ArrayList<>();

	public User() {

	}

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public User(String userName, String password, String phone){
		this.userName = userName;
		this.password = password;
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Email> getEmaiList() {
		return emaiList;
	}

	public void addEmaiList(Email email) {
		this.emaiList.add(email);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
