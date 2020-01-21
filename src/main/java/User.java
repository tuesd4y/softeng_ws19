
// code from Richard, imported in here for simplicity
public class User implements Comparable<User> {

	private final String name;
	private final String number;
	private String password;

	User(String name, String number, String password) {
		this.name = name;
		this.number = number;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public int compareTo(User other) {
		if(name.equals(other.name))
			return number.compareTo(other.number);
		else
			return this.name.compareTo(other.name);
	}

	@Override
	public String toString() {
		return "\n" + name + ",  phone number=" + number;
		//Test case: To see that feature ChangePassword works correct
		//return "\n" + name + ",  phone number=" + number + ",  password=" + password;
	}

}
