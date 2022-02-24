package quali.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import quali.model.Context;
import quali.model.User;

public class FailLoginUserTest {

	User user;

	@Before
	public void init() {
		Context.getInstance().load();
		user = Context.getInstance().findUser("unknow@gmail.com", "mdp");
	}

	@Test
	public void testInstance()
	{
		assertNull(user);
	}
}
