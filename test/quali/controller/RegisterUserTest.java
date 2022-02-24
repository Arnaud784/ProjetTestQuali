package quali.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import quali.model.Context;
import quali.model.User;

public class RegisterUserTest {

	User user;

	@Before
	public void init() {
		Context.getInstance().load();
		user = new User("firstname", "lastname", "first.last@gmail.com", "password", "5 allé truc", "0655896532", "photo.jpg");
		user.setBirthDay("21-05-1997");
		user.setAdmin(false);
		Context.getInstance().getUsersList().add(user);
		Context.getInstance().setLoggedUser(user);
	}

	@Test
	public void registeredUserTest() {
		assertNotNull(Context.getInstance().findUser("first.last@gmail.com"));
	}

	@Test
	public void testLoggedUser()
	{
		assertNotNull(Context.getInstance().getLoggedUser());
	}

	@Test
	public void testFirstName()
	{
		assertEquals("firstname", user.getFirstName());
	}

	@Test
	public void testLastName()
	{
		assertEquals("lastname", user.getLastName());
	}

	@Test
	public void testEmail()
	{
		assertEquals("first.last@gmail.com", user.getEmail());
	}

	@Test
	public void testAddress()
	{
		assertEquals("5 allé truc", user.getAddress());
	}

	@Test
	public void testPhone()
	{
		assertEquals("0655896532", user.getPhone());
	}

	@Test
	public void testPicture()
	{
		assertEquals("photo.jpg", user.getPicture());
	}

	@Test
	public void testDate()
	{
		assertEquals("21-05-1997", user.getBirthDay());
	}

	@Test
	public void testAdmin()
	{
		assertEquals(false, user.isAdmin());
	}
}
