package quali.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import quali.model.Context;

public class UnicityTest {

	@Before
	public void init() {
		Context.getInstance().load();
	}

	@Test
	public void unicityUserTest() {
		assertNotNull(Context.getInstance().findUser("user@gmail.com"));
	}

	@Test
	public void unicityUserTestNull() {
		assertNull(Context.getInstance().findUser("unknow@gmail.com"));
	}
}
