package quali.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import quali.model.Context;
import quali.model.User;

public class AdminDeconnectTest {
	User user;

	@Before
	public void init() {
		Context.getInstance().load();
		user = Context.getInstance().findUser("admin@gmail.com", "mdp");
		Context.getInstance().setLoggedUser(user);
	}

	@Test
	public void deconnectTest() {
		Context.getInstance().setLoggedUser(null);
		User user = Context.getInstance().getLoggedUser();
		assertNull(user);
	}

}
