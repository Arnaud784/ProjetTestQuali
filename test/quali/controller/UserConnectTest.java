package quali.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import quali.model.Context;
import quali.model.User;

public class UserConnectTest {
	
	@Before
	public void init() {
		Context.getInstance().load();
	}
	
	@Test
	public void loginUserTest() {
		User user = Context.getInstance().findUser("email", "password");
		assertNotNull(user);
	}
}
