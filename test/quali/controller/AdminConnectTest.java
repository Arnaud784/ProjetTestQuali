package quali.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import quali.model.Context;
import quali.model.User;

public class AdminConnectTest {

	User user;

	@Before
	public void init() {
		Context.getInstance().load();
		user = Context.getInstance().findUser("admin@gmail.com", "mdp");
		Context.getInstance().setLoggedUser(user);
	}

	@Test
	public void testInstance()
	{
		assertNotNull(user);
	}

	@Test
	public void testType()
	{
		assertEquals(true, user.isAdmin());
	}

	@Test
	public void testLogged()
	{
		assertNotNull(Context.getInstance().getLoggedUser());
	}
}
