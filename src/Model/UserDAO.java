package Model;

public interface UserDAO {
	public boolean add(User u);

	public boolean query(User u);

	public boolean update(User u);

	public boolean delete(User u);
}
