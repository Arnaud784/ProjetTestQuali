package quali.model;

import java.util.ArrayList;
import java.util.List;

public class UsersList {
	
	private List<User> usersList;
	
	protected UsersList() {
		this.usersList = new ArrayList<>();
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}
}
