import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

// code from Richard, imported in here for simplicity
public class DataStorage {

	private final Map<String, User> data;

	public DataStorage() {
		this.data = new HashMap<String, User>();
	}

	public void store(String name, String number, String password) {
		User user = new User(name, number, password);
		data.put(number, user); // key: phone number is unique
	}

	public SortedSet<User> showData() {
		return new TreeSet<User>(data.values());
	}

	public User showUser(String number) {
		return data.get(number);
	}

	public void changePassword(String number, String password) {
		data.get(number).setPassword(password);
	}

	public boolean findUser(String name, String number, String password) {
		return (data.get(number).getName().equals(name) && data.get(number).getPassword().equals(password));
	}

	// demo code to make Richard's part work with the database
	@Nullable
	public User findUser(String name, String password) {
		return data.values().stream().filter(it -> it.getName().equals(name) && it.getPassword().equals(password))
				.findFirst()
				.orElse(null);
	}
}





