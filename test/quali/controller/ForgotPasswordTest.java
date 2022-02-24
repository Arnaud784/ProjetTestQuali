package quali.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import quali.model.Context;

public class ForgotPasswordTest {

	@Before
	public void init() {
		Context.getInstance().load();
	}

	@Test
	public void passwordNotNullTest() {
		String password = Context.getInstance().recovery("user@gmail.com");
		assertNotNull(password);
	}

	@Test
	public void passwordNullTest() {
		String password = Context.getInstance().recovery("unknow@gmail.com");
		assertNull(password);
	}

	@Test
	public void recoveryTest() {
		String password = Context.getInstance().recovery("user@gmail.com");
		assertEquals("mdp", password);
	}

	@Test
	public void recupMdpTest() {
		Context.getInstance().setPasswordIsForgot("password");
		assertEquals("password", Context.getInstance().getPasswordIsForgot());
	}
}
