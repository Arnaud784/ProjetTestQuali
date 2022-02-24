package quali.model;

import java.util.ArrayList;
import java.util.List;

public class UsersList {

	private List<User> listUsers;

	protected UsersList() {
		this.listUsers = new ArrayList<>();
	}

	public List<User> getUsersList() {
		return listUsers;
	}

	public void setUsersList(List<User> usersList) {
		this.listUsers = usersList;
	}
}
